package net.lomeli.insectia.api.interfaces;

import java.util.List;

import net.lomeli.insectia.api.InsectiaAPI;
import net.lomeli.insectia.api.InsectiaItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;

public enum EnumNetType {
	DIRT(0, 2, InsectiaAPI.dirtTool, InsectiaAPI.dirtBlock),
	LEAVES(1, 1, InsectiaAPI.leafTool, InsectiaAPI.leafBlock),
	SAND(2, 3, InsectiaAPI.sandTool, InsectiaAPI.sandBlock),
	WEB(3, 4, InsectiaAPI.webTool, InsectiaAPI.webBlock);
	
	private final int typeID, dropChance;
	private final List<ItemStack> bugRange;
	private final List<Block> blockRange;
	private EnumNetType(int id, int chance, List<ItemStack> drops, List<Block> blockRange){
		this.typeID = id;
		this.dropChance = chance;
		this.bugRange = drops;
		this.blockRange = blockRange;
	}
	
	public int getID(){
		return this.typeID;
	}
	
	public int getDropChance(){
		return this.dropChance;
	}
	
	public List<ItemStack> getDrops(){
		return this.bugRange;
	}
	
	public List<Block> getBlocks(){
		return this.blockRange;
	}
	
	public static class EnumNetTypeHelper{
		public static EnumNetType addNetType(String name, int id, int chance, ItemStack[] drops, Block[] blockRange){
			return EnumHelper.addEnum(EnumNetType.class, name, id, chance, drops, blockRange);
		}
	}
}
