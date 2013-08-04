package net.lomeli.insectia.core;

import cpw.mods.fml.common.registry.GameRegistry;

import net.lomeli.insectia.api.InsectiaItems;
import net.lomeli.insectia.api.InsectiaBlocks;
import net.lomeli.insectia.blocks.ModBlocks;
import net.lomeli.insectia.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;

import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	public static void loadRecipes(){
		blockRecipes();
		itemRecipes();
	}
	
	public static void itemRecipes(){
		GameRegistry.addShapedRecipe(InsectiaItems.treatedStick, new Object[]{" S ", "SMS", " S ", 'S',Item.stick, 'M',Item.bucketMilk});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.cookie), new Object[]{ ModItems.cookieCrumb, ModItems.cookieCrumb, 
			ModItems.cookieCrumb, ModItems.cookieCrumb });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.appleRed), new Object[]{ ModItems.appleBit, ModItems.appleBit,
			ModItems.appleBit, ModItems.appleBit});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.beefCooked), new Object[]{ ModItems.steakPiece, ModItems.steakPiece, 
			ModItems.steakPiece, ModItems.steakPiece });
		GameRegistry.addShapelessRecipe(new ItemStack(Block.cloth), new Object[]{ ModItems.woolFluff, ModItems.woolFluff,
			ModItems.woolFluff, ModItems.woolFluff });
		GameRegistry.addShapelessRecipe(new ItemStack(Block.web), new Object[]{ ModItems.stickyString, ModItems.stickyString,
			ModItems.stickyString, ModItems.stickyString, ModItems.stickyString, ModItems.stickyString, ModItems.stickyString, 
			ModItems.stickyString, ModItems.stickyString });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.bread), new Object[]{ModItems.breadCrumb, ModItems.breadCrumb, ModItems.breadCrumb, 
			ModItems.breadCrumb});
		GameRegistry.addShapelessRecipe(new ItemStack(Block.leaves), new Object[]{ ModItems.leaf, ModItems.leaf, ModItems.leaf, ModItems.leaf, 
			ModItems.leaf, ModItems.leaf, ModItems.leaf, ModItems.leaf, ModItems.leaf });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.silk), new Object[]{ ModItems.silk, ModItems.silk });
		GameRegistry.addRecipe(InsectiaItems.fertilizer, new Object[]{ " D ", "DBD", " D ", 'D', ModItems.dung, 
			'B',new ItemStack(Item.dyePowder, 1, 15)});
		
		GameRegistry.addRecipe(InsectiaItems.webNet, new Object[]{ "STT", "STT", "S  ", 'S', Item.stick, 'T',Item.silk});
		GameRegistry.addRecipe(InsectiaItems.webNet, new Object[]{ "TTS", "TTS", "  S", 'S', Item.stick, 'T',Item.silk});
		GameRegistry.addRecipe(InsectiaItems.sandNet, new Object[]{ "TTT", "TWT","TTT", 'T',ModItems.treatedStick, 'W',Block.cloth});
		GameRegistry.addRecipe(InsectiaItems.dirtNet, new Object[]{ " II", " SI", 'S',ModItems.treatedStick,'I',Item.ingotIron});
		GameRegistry.addRecipe(new ShapedOreRecipe(InsectiaItems.dirtNet, true, " II", "SI", 'I',"ingotTin", 'S',Item.stick));
	}
	
	public static void blockRecipes(){
		
	}
}
