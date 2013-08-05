package net.lomeli.insectia.api.interfaces;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.EnumHelper;

public enum EnumHousingType {
	DARK(0),
	SWEET(1),
	GREEN(2),
	SMELLY(3);

	private final int id;
	private EnumHousingType(int id){
		this.id = id;
		EnumHousingHelper.quarterTypeList.add(id, this);
	}
	
	public int getID(){
		return this.id;
	}
	
	public static class EnumHousingHelper{
		public static List<EnumHousingType> quarterTypeList = new ArrayList<EnumHousingType>();
		
		public static int getNextID(){
			return quarterTypeList.size();
		}
		
		public static EnumHousingType getTypeByID(int id){
			return quarterTypeList.get(id);
		}
		/**
		 * Use this to create your custom housing type
		 * @param name Name for your custom type. Do not use the names <i>DARK, SWEET, GREEN, SMELLY</i>
		 * @param id 
		 * @return 
		 */
		public static EnumHousingType addEnum(String name, int id){
			return EnumHelper.addEnum(EnumHousingType.class, name, id);
		}
	}
}
