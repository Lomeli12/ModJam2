package net.lomeli.insectia.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InsectiaAPI {

	public static List<ItemStack> dirtTool = new ArrayList<ItemStack>();
	public static List<ItemStack> webTool = new ArrayList<ItemStack>();
	public static List<ItemStack> leafTool = new ArrayList<ItemStack>();
	public static List<ItemStack> sandTool = new ArrayList<ItemStack>();
	
	public static List<Block> dirtBlock = new ArrayList<Block>();
	public static List<Block> webBlock = new ArrayList<Block>();
	public static List<Block> leafBlock = new ArrayList<Block>();
	public static List<Block> sandBlock = new ArrayList<Block>();
	
	/**
	 * Register's your Insect to the Insect Registry.
	 * @param insect Your Insect. Must implement the IInsect interface
	 * @param bool Set true if you want spiders to catch your insect
	 */
	public static void registerInsect(ItemStack stack, boolean bool){
		InsectRegistry.getInstance().registerInsect(stack, bool);
	}
	
	/**
	 * Register's your Insect to the Insect Registry.
	 * @param insect Your Insect. Must implement the IInsect interface
	 * @param bool Set true if you want spiders to catch your insect
	 */
	public static void registerInsect(Item item, boolean bool){
		InsectRegistry.getInstance().registerInsect(item, bool);
	}
	
	/**
	 * Used by the housing tile entities to write the
	 * 
	 * @param itemStack
	 * @param itemID
	 * @param itemMeta
	 */
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
