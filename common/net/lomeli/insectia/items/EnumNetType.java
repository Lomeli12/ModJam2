package net.lomeli.insectia.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum EnumNetType {
	DIRT(0, 2, new Item[]{ModItems.armyAnts, ModItems.fireAnts}, new Block[]{Block.dirt, Block.grass}),
	LEAVES(1, 1, new Item[]{ModItems.greenWorm}, new Block[]{Block.leaves}),
	SAND(2, 3, new Item[]{ModItems.armyAnts, ModItems.fireAnts}, new Block[]{Block.sand}),
	WEB(3, 4, new Item[]{ModItems.longLegSpider, ModItems.bRecluseSpider, ModItems.bWidowSpider}, new Block[]{Block.web});
	
	private final int typeID, dropChance;
	private final Item[] bugRange;
	private final Block[] blockRange;
	private EnumNetType(int id, int chance, Item[] drops, Block[] blockRange){
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
	
	public Item[] getDrops(){
		return this.bugRange;
	}
	
	public Block[] getBlocks(){
		return this.blockRange;
	}
}
