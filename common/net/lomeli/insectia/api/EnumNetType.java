package net.lomeli.insectia.api;

import net.lomeli.insectia.api.InsectiaItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;

public enum EnumNetType {
	DIRT(0, 2, new Item[]{InsectiaItems.armyAnts.getItem(), InsectiaItems.fireAnts.getItem(), 
		InsectiaItems.dungBeetles.getItem(), InsectiaItems.polyvoltineWorm.getItem()}, new Block[]{Block.dirt, Block.grass}),
	LEAVES(1, 1, new Item[]{InsectiaItems.bivoltineWorm.getItem(), InsectiaItems.bWidowSpider.getItem()}, new Block[]{Block.leaves}),
	SAND(2, 3, new Item[]{InsectiaItems.armyAnts.getItem(), InsectiaItems.fireAnts.getItem(), InsectiaItems.univoltineWorm.getItem(),
		InsectiaItems.polyvoltineWorm.getItem()}, new Block[]{Block.sand}),
	WEB(3, 4, new Item[]{InsectiaItems.longLegSpider.getItem(), InsectiaItems.bRecluseSpider.getItem(), 
		InsectiaItems.bWidowSpider.getItem()}, new Block[]{ 
		Block.web, Block.sapling, Block.cactus, Block.vine, Block.tallGrass, Block.deadBush});
	
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
	
	public static class EnumNetTypeHelper{
		public static EnumNetType addNetType(String name, int id, int chance, Item[] drops, Block[] blockRange){
			return EnumHelper.addEnum(EnumNetType.class, name, id, chance, drops, blockRange);
		}
	}
}
