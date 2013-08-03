package net.lomeli.insectia.api;

import net.minecraftforge.common.EnumHelper;

public enum EnumInsectQuartersType {
	DARK(0),
	SWEET(1),
	GREEN(2),
	SMELLY(3);

	private final int id;
	private EnumInsectQuartersType(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public static class EnumInsectQuartersHelper{
		/**
		 * Use this to create your custom living quarters type
		 * @param name Name for your custom type. Do not use the names <i>DARK, SWEET, GREEN, SMELLY</i>
		 * @param id 
		 * @return 
		 */
		public static EnumInsectQuartersType addEnum(String name, int id){
			return EnumHelper.addEnum(EnumInsectQuartersType.class, name, id);
		}
	}
}
