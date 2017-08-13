package lykrast.defiledlands.common.util;

import java.util.List;

import com.google.common.collect.Lists;

import lykrast.defiledlands.core.DefiledLands;
import net.minecraft.util.text.translation.I18n;

//Most of the stuff is from Mantle
//https://github.com/SlimeKnights/Mantle/blob/1.12/src/main/java/slimeknights/mantle/util/LocUtils.java
public class LocUtils {

	public static String translateRecursive(String key, Object... params) {
		return I18n.translateToLocal(I18n.translateToLocalFormatted(key, params));
	}

	public static List<String> getTooltips(String text) {
		List<String> list = Lists.newLinkedList();
		if(text == null)
			return list;
		int j = 0;
		int k;
		while((k = text.indexOf("\\n", j)) >= 0)
		{
			list.add(text.substring(j, k));
			j = k+2;
		}

		list.add(text.substring(j, text.length()));

		return list;
	}


	public static String convertNewlines(String line) {
		if(line == null)
			return null;
		int j;
		while((j = line.indexOf("\\n")) >= 0)
		{
			line = line.substring(0, j) + '\n' + line.substring(j+2);
		}

		return line;
	}
	
	public static String prefix(String s)
	{
		return DefiledLands.MODID + "." + s;
	}

}
