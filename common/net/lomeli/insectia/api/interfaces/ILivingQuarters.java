package net.lomeli.insectia.api.interfaces;


/**
 * Implement this in the tile entity of your
 * custom living quarters
 * @author Anthony
 *
 */
public interface ILivingQuarters {
	
	public EnumInsectQuartersType getQuartersType();

	public void setQuartersType(EnumInsectQuartersType type);
	
	public int getInsectLifePercentage();
	
	public boolean canWork(IInsect insect);

}
