package net.lomeli.insectia.api.housing;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


/**
 * Implement this in the tile entity of your
 * custom living quarters
 * @author Anthony
 *
 */
public interface IHousing {
	
	public EnumHousingType getQuartersType();

	public void setQuartersType(EnumHousingType type);
	
	public int getInsectLifePercentage();
	
	public boolean canWorkTime(World world, ItemStack itemStack);
	
	public boolean canWorkWeather(World world, ItemStack itemStack);

	
}
