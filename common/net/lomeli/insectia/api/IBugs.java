package net.lomeli.insectia.api;

import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * You must implement this for your bugs to
 * work properly in the InsectRegistry
 * @author Anthony
 *
 */
public interface IBugs {
	
	public Item[] getItemsProduced();
	
	public int getDropChance();
	
	public int getProductionTime();
	
	public void getEffectOnNerbyEntities(World world, int x, int y, int z);
	
	public EnumInsectQuartersType getPreferedLivingType();

}
