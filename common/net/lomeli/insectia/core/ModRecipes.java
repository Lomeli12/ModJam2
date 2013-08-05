package net.lomeli.insectia.core;

import cpw.mods.fml.common.registry.GameRegistry;

import net.lomeli.insectia.blocks.ModBlocks;
import net.lomeli.insectia.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	public static void loadRecipes(){
		blockRecipes();
		itemRecipes();
	}
	
	public static void itemRecipes(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.treatedStick), new Object[]{" S ", "SMS", " S ", 'S',Item.stick, 'M',Item.bucketMilk});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.cookie), new Object[]{ ModItems.cookieCrumb, ModItems.cookieCrumb, 
			ModItems.cookieCrumb, ModItems.cookieCrumb });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.appleRed), new Object[]{ ModItems.appleBit, ModItems.appleBit,
			ModItems.appleBit, ModItems.appleBit});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.beefCooked), new Object[]{ ModItems.steakPiece, ModItems.steakPiece, 
			ModItems.steakPiece, ModItems.steakPiece, ModItems.steakPiece, ModItems.steakPiece, ModItems.steakPiece,
			ModItems.steakPiece, ModItems.steakPiece});
		GameRegistry.addShapelessRecipe(new ItemStack(Block.cloth), new Object[]{ ModItems.woolFluff, ModItems.woolFluff, ModItems.woolFluff,
			ModItems.woolFluff, ModItems.woolFluff, ModItems.woolFluff, ModItems.woolFluff, ModItems.woolFluff , ModItems.woolFluff });
		GameRegistry.addShapelessRecipe(new ItemStack(Block.web), new Object[]{ ModItems.stickyString, ModItems.stickyString,
			ModItems.stickyString, ModItems.stickyString, ModItems.stickyString, ModItems.stickyString, ModItems.stickyString, 
			ModItems.stickyString, ModItems.stickyString });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.bread), new Object[]{ModItems.breadCrumb, ModItems.breadCrumb, ModItems.breadCrumb, 
			ModItems.breadCrumb});
		GameRegistry.addShapelessRecipe(new ItemStack(Block.leaves), new Object[]{ ModItems.leaf, ModItems.leaf, ModItems.leaf, ModItems.leaf, 
			ModItems.leaf, ModItems.leaf, ModItems.leaf, ModItems.leaf, ModItems.leaf });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.silk), new Object[]{ ModItems.silk, ModItems.silk, ModItems.silk, ModItems.silk });
		GameRegistry.addRecipe(new ItemStack(ModItems.fertilizer), new Object[]{ " D ", "DBD", " D ", 'D', ModItems.dung, 
			'B',new ItemStack(Item.dyePowder, 1, 15)});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.webNet), new Object[]{ "STT", "STT", "S  ", 'S',Item.stick, 'T',Item.silk});
		GameRegistry.addRecipe(new ItemStack(ModItems.webNet), new Object[]{ "TTS", "TTS", "  S", 'S',Item.stick, 'T',Item.silk});
		GameRegistry.addRecipe(new ItemStack(ModItems.sandNet), new Object[]{ "TTT", "TWT","TTT", 'T',ModItems.treatedStick, 'W',Block.cloth});
		GameRegistry.addRecipe(new ItemStack(ModItems.dirtNet), new Object[]{ " II", " SI", 'S',ModItems.treatedStick,'I',Item.ingotIron});
		if(!OreDictionary.getOres("ingotTin").isEmpty())
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dirtNet), true, " II", "SI", 'I',"ingotTin", 'S',Item.stick));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.leafNet), new Object[]{" SW", " TS", "T  ", 'S',Item.silk, 'T',Item.stick, 'W',Block.cloth});
		
		
	}
	
	public static void blockRecipes(){
		GameRegistry.addRecipe(new ItemStack(ModBlocks.frameBlock), new Object[]{ "TWT","WIW","TWT", 
			'T',ModItems.treatedStick, 'W',Block.planks, 'I',Item.ingotIron} );
		if(!OreDictionary.getOres("plankWood").isEmpty())
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.frameBlock), true, "TWT","WIW","TWT", 
					'T',ModItems.treatedStick, 'W',"plankWood", 'I',Item.ingotIron));
		
		if(!OreDictionary.getOres("ingotTin").isEmpty()){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.frameBlock), true, "TWT","WIW","TWT", 
				'T',ModItems.treatedStick, 'W',Block.planks, 'I',"ingotTin"));
			if(!OreDictionary.getOres("plankWood").isEmpty())
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.frameBlock), true, "TWT","WIW","TWT", 
						'T',ModItems.treatedStick, 'W',"plankWood", 'I',"ingotTin"));
		}
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.livingQuartersDark), new Object[]{ "IWI", "WFW","IWI", 
			'I',Item.dyePowder, 'W',Block.thinGlass, 'F',ModBlocks.frameBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.livingQuartersSweet), new Object[]{ "IWI", "WFW","IWI", 
			'I',Item.sugar, 'W',Block.thinGlass, 'F',ModBlocks.frameBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.livingQuartersGreen), new Object[]{ "IWI", "WFW","IWI", 
			'I',Block.sapling, 'W',Block.thinGlass, 'F',ModBlocks.frameBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.livingQuartersSmelly), new Object[]{ "IWI", "WFW","IWI", 
			'I',Item.rottenFlesh, 'W',Block.thinGlass, 'F',ModBlocks.frameBlock });
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.statusBlock), new Object[]{ "SIS","IFI","SIS", 'I',Item.ingotIron, 'S',Block.stone, 
			'F',ModBlocks.frameBlock});
		if(!OreDictionary.getOres("ingotTin").isEmpty()){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.statusBlock), true, "SIS","IFI","SIS", 'I',"ingotTin", 
				'S',Block.stone, 'F',ModBlocks.frameBlock));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.incubatorTank), true,
					"IGI", "GFG", "IGI", 'I',"ingotTin", 'G',Block.thinGlass, 'F',ModBlocks.frameBlock));
		}
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.incubatorTank), new Object[]{"IGI", "GFG", "IGI", 
			'I',Item.ingotIron, 'G',Block.thinGlass, 'F',ModBlocks.frameBlock});
	}
}
