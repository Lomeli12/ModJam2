package net.lomeli.insectia.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.EnumHelper;

public enum EnumInsectQuartersType {
	DARK(0),
	SWEET(1),
	GREEN(2),
	SMELLY(3);

	private final int id;
	private EnumInsectQuartersType(int id){
		this.id = id;
		EnumInsectQuartersHelper.quarterTypeList.add(id, this);
	}
	
	public int getID(){
		return this.id;
	}
	
	public static class EnumInsectQuartersHelper{
		public static List<EnumInsectQuartersType> quarterTypeList = new ArrayList<EnumInsectQuartersType>();
		
		public static int getNextID(){
			return quarterTypeList.size();
		}
		
		public static EnumInsectQuartersType getTypeByID(int id){
			return quarterTypeList.get(id);
		}
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
