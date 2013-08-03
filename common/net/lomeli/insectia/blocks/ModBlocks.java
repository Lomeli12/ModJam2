package net.lomeli.insectia.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.lomeli.insectia.blocks.item.ItemLivingQuarters;
import net.lomeli.insectia.lib.BlockIDs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class ModBlocks {
	
	public static Block livingQuartersBlock, frameBlock, statusBlock;
	public static ItemStack darkQuarters, sweetQuarters, smellyQuarters, greenQuarters;
	public static String[] quarterNames = {"Dark", "Sweet", "Green", "Selly"};
	
	public static void loadBlocks(){
		livingQuartersBlock = new BlockLivingQuarters(BlockIDs.livingQuartersID)
			.setUnlocalizedName("insectlivingquarters");
		frameBlock = new BlockFrameBlock(BlockIDs.frameBlockID, Material.wood, "frameblock")
			.setUnlocalizedName("frameblock");
		statusBlock = new BlockStatusBlock(BlockIDs.statusBlockID, "statusblock")
			.setUnlocalizedName("statusBlock");
		
		registerBlocks();
	}
	
	public static void registerBlocks(){
		GameRegistry.registerBlock(livingQuartersBlock, ItemLivingQuarters.class, "InsectLivingQuarters");
		for(int i = 0; i < quarterNames.length; i++){
			LanguageRegistry.addName(new ItemStack(livingQuartersBlock, 1, i), 
				"Insect Living Quarters: " + quarterNames[i]);
		}
		darkQuarters = new ItemStack(livingQuartersBlock, 1, 0);
		sweetQuarters = new ItemStack(livingQuartersBlock, 1, 1);
		greenQuarters = new ItemStack(livingQuartersBlock, 1, 2);
		smellyQuarters = new ItemStack(livingQuartersBlock, 1, 3);
		
		GameRegistry.registerBlock(frameBlock, "Frame Block");
		GameRegistry.registerBlock(statusBlock, "Status Block");
		
		LanguageRegistry.addName(frameBlock, "Frame Block");
		LanguageRegistry.addName(statusBlock, "Status Block");
	}

}
