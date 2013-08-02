package net.lomeli.insectia.items;

import net.lomeli.insectia.api.IBugs;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemBugs extends ItemGeneric implements IBugs{

	private Item[] itemsProduced;
	private int dropChance;
	private int time;
	public ItemBugs(int par1, String texture, Item[] producedItems, int dropChance, int time) {
		super(par1, texture);
		this.dropChance = dropChance;
		this.time = time;
	}
	
	@Override
	public Item[] getItemsProduced(){
		return itemsProduced;
	}
	
	@Override
	public int getDropChance(){
		return dropChance;
	}

	@Override
	public int getProductionTime() {
		return time;
	}

	@Override
	public void getEffectOnNerbyEntities(World world, int x, int y, int z) {}

}
