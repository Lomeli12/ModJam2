package net.lomeli.insectia.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.lomeli.insectia.api.InsectiaAPI;
import net.lomeli.insectia.api.InsectiaBlocks;
import net.lomeli.insectia.blocks.living.BlockQuartersDark;
import net.lomeli.insectia.blocks.living.BlockQuartersGreen;
import net.lomeli.insectia.blocks.living.BlockQuartersSmelly;
import net.lomeli.insectia.blocks.living.BlockQuartersSweet;
import net.lomeli.insectia.lib.BlockIDs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;

public class ModBlocks {
	
	public static Block livingQuartersDark, livingQuartersSweet, livingQuartersGreen,
		livingQuartersSmelly, frameBlock, statusBlock, recluseTrap, incubatorTank, advancedFrame,
		advancedHousing;
	
	public static void loadBlocks(){
		livingQuartersDark = new BlockQuartersDark(BlockIDs.livingDarkID).setUnlocalizedName("livingQuartersDark");
		livingQuartersGreen = new BlockQuartersGreen(BlockIDs.livingGreenID).setUnlocalizedName("livingQuartersGreen");
		livingQuartersSmelly = new BlockQuartersSmelly(BlockIDs.livingSmellyID).setUnlocalizedName("livingQuartersSmelly");
		livingQuartersSweet = new BlockQuartersSweet(BlockIDs.livingSweetID).setUnlocalizedName("livingQuartersSweet");
		frameBlock = new BlockFrameBlock(BlockIDs.frameBlockID, Material.wood, "frameblock")
			.setUnlocalizedName("frameblock");
		statusBlock = new BlockStatusBlock(BlockIDs.statusBlockID).setUnlocalizedName("statusBlock");
		recluseTrap = new BlockRecluseTrap(BlockIDs.recluseTrapID).setUnlocalizedName("recluseTrap");
		incubatorTank = new BlockIncubator(BlockIDs.incubationTankID).setUnlocalizedName("incubator");
		
		registerBlocks();
	}
	
	public static void registerBlocks(){
		String livingName = "Insect Housing: ";
		
		GameRegistry.registerBlock(livingQuartersDark, livingName + "Dark");
		GameRegistry.registerBlock(livingQuartersGreen, livingName + "Green");
		GameRegistry.registerBlock(livingQuartersSmelly, livingName + "Smelly");
		GameRegistry.registerBlock(livingQuartersSweet, livingName + "Sweet");
		
		LanguageRegistry.addName(livingQuartersDark, livingName + "Dark"); 
		LanguageRegistry.addName(livingQuartersGreen, livingName + "Green"); 
		LanguageRegistry.addName(livingQuartersSmelly, livingName + "Smelly"); 
		LanguageRegistry.addName(livingQuartersSweet, livingName + "Sweet"); 
		
		GameRegistry.registerBlock(frameBlock, "Frame Block");
		GameRegistry.registerBlock(statusBlock, "Status Block");
		GameRegistry.registerBlock(recluseTrap, "Recluse Spider Trap");
		GameRegistry.registerBlock(incubatorTank, "Growth Chamber");
		
		LanguageRegistry.addName(frameBlock, "Frame Block");
		LanguageRegistry.addName(statusBlock, "Status Block");
		LanguageRegistry.addName(recluseTrap, "Recluse Spider Trap");
		LanguageRegistry.addName(incubatorTank, "Growth Chamber");
		
		MinecraftForge.setBlockHarvestLevel(frameBlock, "axe", 1);
		MinecraftForge.setBlockHarvestLevel(livingQuartersDark, "axe", 1);
		MinecraftForge.setBlockHarvestLevel(livingQuartersGreen, "axe", 1);
		MinecraftForge.setBlockHarvestLevel(livingQuartersSmelly, "axe", 1);
		MinecraftForge.setBlockHarvestLevel(livingQuartersSweet, "axe", 1);
		MinecraftForge.setBlockHarvestLevel(incubatorTank, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(statusBlock, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(recluseTrap, "axe", 1);
		
		InsectiaBlocks.frameBlock = new ItemStack(frameBlock);
		InsectiaBlocks.livingQuartersDark = new ItemStack(livingQuartersDark);
		InsectiaBlocks.livingQuartersGreen = new ItemStack(livingQuartersGreen);
		InsectiaBlocks.livingQuartersSmelly = new ItemStack(livingQuartersSmelly);
		InsectiaBlocks.livingQuartersSweet = new ItemStack(livingQuartersSweet);
		InsectiaBlocks.statusBlock = new ItemStack(statusBlock);
		InsectiaBlocks.insectIncubator = new ItemStack(incubatorTank);
		
		InsectiaAPI.dirtBlock.add(Block.dirt);
		InsectiaAPI.dirtBlock.add(Block.grass);
		
		InsectiaAPI.leafBlock.add(Block.leaves);
		
		InsectiaAPI.sandBlock.add(Block.sand);
		InsectiaAPI.sandBlock.add(Block.deadBush);
		
		InsectiaAPI.webBlock.add(Block.web);
		InsectiaAPI.webBlock.add(Block.sapling);
		InsectiaAPI.webBlock.add(Block.cactus);
		InsectiaAPI.webBlock.add(Block.tallGrass);
		InsectiaAPI.webBlock.add(Block.deadBush);
		InsectiaAPI.webBlock.add(Block.vine);
	}

}
