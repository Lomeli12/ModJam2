package net.lomeli.insectia.api;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public interface IBugs {
	
	public Item[] getItemsProduced();
	
	public int getDropChance();
	
	public int getProductionTime();
	
	public void getEffectOnNerbyEntities(World world, int x, int y, int z);

}
