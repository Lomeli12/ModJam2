package net.lomeli.insectia.api.interfaces;


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
	
	public boolean canWork(IInsect insect);

}
