package net.lomeli.insectia.api.interfaces;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * You must implement this for your bugs to
 * work properly in the InsectRegistry
 * @author Anthony
 *
 */
public interface IInsect {
	
	public ItemStack[] getItemsProduced();
	
	public ItemStack getRandomItem();
	
	public int getDropChance();
	
	public int getProductionTime();
	
	public void getEffectOnNerbyEntities(World world, int x, int y, int z);
	
	public EnumHousingType getPreferedLivingType();
	
	public int getLifeSpan();
	
	public void hurtBug(ItemStack itemStack, int damage);
	
	public BiomeGenBase[] getPreferedBiome();
	
	public int getLarvaeItemID();
	
	public int larvaeGrowthTime();
	
	/**
	 * 0 for Day, 1 for Night, anything else to ignore time.
	 * @author Anthony
	 */
	public int getPreferedTimeOfDay();
}
