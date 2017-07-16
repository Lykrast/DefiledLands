package lykrast.defiledlands.common.entity;

public interface IEntityDefiled {
	
	default boolean affectedByVilespine()
	{
		return false;
	}
	
	default boolean affectedByCreepingMoss()
	{
		return false;
	}
	
	default boolean affectedByBlastem()
	{
		return false;
	}

}
