package net.lomeli.insectia.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LarvaeUtil {

	public static void writeInsect(ItemStack itemStack, int itemID, int itemMeta){
		if(!itemStack.hasTagCompound())
			itemStack.setTagCompound(new NBTTagCompound());
		
		int[] item = {itemID, itemMeta};
		itemStack.stackTagCompound.setIntArray("Insect", item);
	}
	
	public static ItemStack getInsect(ItemStack stack){
		int[] item;
		if(!stack.hasTagCompound())
			return null;
		item = stack.getTagCompound().getIntArray("Insect");
		
		return new ItemStack(item[0], 1, item[1]);
	}
	
}
