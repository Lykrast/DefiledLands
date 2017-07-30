package lykrast.defiledlands.common.entity.passive;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lykrast.defiledlands.common.entity.IEntityDefiled;
import lykrast.defiledlands.common.init.ModBlocks;
import lykrast.defiledlands.common.init.ModItems;
import lykrast.defiledlands.common.util.Config;
import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBookWyrm extends EntityAnimal implements IEntityDefiled {
    public static final ResourceLocation LOOT = new ResourceLocation(DefiledLands.MODID, "entities/book_wyrm");
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.ENCHANTED_BOOK, ModItems.foulCandy);
    private static final DataParameter<Boolean> GOLDEN = EntityDataManager.<Boolean>createKey(EntityBookWyrm.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DIGEST_TIME = EntityDataManager.<Integer>createKey(EntityBookWyrm.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> MAX_LEVEL = EntityDataManager.<Integer>createKey(EntityBookWyrm.class, DataSerializers.VARINT);
    public int digested, digesting, digestTimer;
	
	public EntityBookWyrm(World worldIn)
	{
		super(worldIn);
        this.setSize(0.9F, 0.9F);
        this.spawnableBlock = ModBlocks.grassDefiled;
	}

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(GOLDEN, Boolean.valueOf(false));
        this.dataManager.register(DIGEST_TIME, Integer.valueOf(200));
        this.dataManager.register(MAX_LEVEL, Integer.valueOf(1));
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.2D, false));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26D);
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (digesting > 0 && digestTimer > 0)
        {
        	digestTimer--;
        	
        	if (digestTimer <= 0)
        	{
        		digested++;
        		digesting--;
        		
        		if (digesting > 0) digestTimer = getDigestTime();
        	}
        }
    	
    	if (digested >= getMaxLevel())
    	{
    		digested -= getMaxLevel();
    		
    		if (!this.world.isRemote)
    		{
    	        List<EnchantmentData> list = EnchantmentHelper.buildEnchantmentList(rand, new ItemStack(Items.BOOK), getMaxLevel(), isGolden());
    			
    	        if (Config.multiBook)
    	        {
                	for (EnchantmentData e : list)
                	{
                		ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                		ItemEnchantedBook.addEnchantment(book, e);
            			entityDropItem(book, 0.5F);
                	}
    	        }
    	        else
    	        {
    	        	ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
            		ItemEnchantedBook.addEnchantment(book, list.get(rand.nextInt(list.size())));
        			entityDropItem(book, 0.5F);
    	        }
    		}
    	}
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (!super.processInteract(player, hand))
        {
            ItemStack itemstack = player.getHeldItem(hand);
            
            if (itemstack.getItem() == Items.ENCHANTED_BOOK && !isChild())
            {
            	Map<Enchantment, Integer> list = EnchantmentHelper.getEnchantments(itemstack);
            	
            	if (list.isEmpty()) return false;
            	
            	int i = 0;
            	
            	for (Entry<Enchantment, Integer> e : list.entrySet())
            	{
            		i += e.getKey().getMinEnchantability(e.getValue());
            	}
            	
            	i = (int)(i * Config.conversionRate);
            	
            	if (i > 0)
            	{
            		digesting += i;
            		if (digestTimer == 0) digestTimer = getDigestTime();
                	
                    if (!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }
                    
                	return true;
            	}
            	
            	return false;
            }
            else
            {
            	return false;
            }
        }
        else
        {
            return true;
        }
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        
        setGolden(world.rand.nextInt(100) == 0);
        setDigestTime(MathHelper.getInt(world.rand, 160, 240));
        setMaxLevel(MathHelper.getInt(world.rand, 3, 6));

        if (this.rand.nextInt(5) == 0)
        {
            this.setGrowingAge(-24000);
        }
        
        return livingdata;
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		EntityBookWyrm child = new EntityBookWyrm(world);
		
		if (ageable instanceof EntityBookWyrm) setOffspringAttributes((EntityBookWyrm)ageable, child);
		
		return child;
	}
	
	protected void setOffspringAttributes(EntityBookWyrm parent, EntityBookWyrm child)
	{
		//Golden
		boolean flag1 = isGolden();
		boolean flag2 = parent.isGolden();
		
		if (flag1 || flag2)
		{
			int i = 25;
			if (flag1 && flag2) i = 10;
			
			child.setGolden(world.rand.nextInt(i) == 0);
		}
		else child.setGolden(world.rand.nextInt(100) == 0);
		
		//Digest time
		int j1 = getDigestTime();
		int j2 = parent.getDigestTime();
		int k = j1 + j2 - world.rand.nextInt((int)(Math.max(j1, j2) + 1 * 0.75));
		child.setDigestTime(k / 2);
		
		//Maximum level
		j1 = getMaxLevel();
		j2 = parent.getMaxLevel();
		k = j1 + j2 + world.rand.nextInt(Math.max(j1, j2) + 1);
		child.setMaxLevel(k / 2);
	}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Golden", this.isGolden());
        compound.setInteger("Digest", this.getDigestTime());
        compound.setInteger("MaxLvl", this.getMaxLevel());
        compound.setInteger("Digested", this.digested);
        compound.setInteger("Digesting", this.digesting);
        compound.setInteger("DigestTimer", this.digestTimer);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setGolden(compound.getBoolean("Golden"));
        setDigestTime(compound.getInteger("Digest"));
        setMaxLevel(compound.getInteger("MaxLvl"));
        digested = compound.getInteger("Digested");
        digesting = compound.getInteger("Digesting");
        digestTimer = compound.getInteger("DigestTimer");
    }

    public boolean isGolden() {
		return ((Boolean)this.dataManager.get(GOLDEN)).booleanValue();
	}

	public void setGolden(boolean golden) {
		this.dataManager.set(GOLDEN, Boolean.valueOf(golden));
	}

	public int getDigestTime() {
		return ((Integer)this.dataManager.get(DIGEST_TIME)).intValue();
	}

	public void setDigestTime(int digest) {
        this.dataManager.set(DIGEST_TIME, Integer.valueOf(Math.max(digest, 1)));
	}

	public int getMaxLevel() {
		return ((Integer)this.dataManager.get(MAX_LEVEL)).intValue();
	}

	public void setMaxLevel(int maxLevel) {
        this.dataManager.set(MAX_LEVEL, Integer.valueOf(MathHelper.clamp(maxLevel, 1, 30)));
	}

	@Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == ModItems.foulCandy;
    }

}
