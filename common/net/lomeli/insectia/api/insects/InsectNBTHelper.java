package net.lomeli.insectia.api.insects;

import net.lomeli.insectia.api.insects.EnumInsects.EnumDayCycle;
import net.lomeli.insectia.api.insects.EnumInsects.EnumGender;
import net.lomeli.insectia.api.insects.EnumInsects.EnumWeatherTolerence;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InsectNBTHelper{
	
	private static void setupNBT(ItemStack stack){
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
	}
	
	public static void setInsectGender(ItemStack stack, EnumGender gender){
		setupNBT(stack);
		int genderID = gender.getID();
		stack.stackTagCompound.setInteger("Gender", genderID);
	}
	
	public static EnumGender getInsectGender(ItemStack stack){
		int genderID;
		if(!stack.hasTagCompound())
			return null;
		genderID = stack.getTagCompound().getInteger("Gender");
		EnumGender gender = EnumInsects.getGenderByID(genderID);
		return gender != null ? gender : EnumGender.NULL;
	}
	
	public static void setPreferedTimeOfDay(ItemStack stack, EnumDayCycle time){
		setupNBT(stack);
		int timeID = time.getID();
		stack.stackTagCompound.setInteger("Time", timeID);
	}
	
	public static EnumDayCycle getPreferedTimeOfDay(ItemStack stack){
		int timeID;
		if(!stack.hasTagCompound())
			return null;
		timeID = stack.getTagCompound().getInteger("Time");
		EnumDayCycle time = EnumInsects.getPreferedTime(timeID);
		return time != null ? time : EnumDayCycle.NULL;
	}
	
	public static void setWeatherTolerence(ItemStack stack, EnumWeatherTolerence weather){
		setupNBT(stack);
		int id = weather.getID();
		stack.stackTagCompound.setInteger("Weather", id);
	}
	
	public static EnumWeatherTolerence getWeatherTolerence(ItemStack stack){
		int id;
		if(!stack.hasTagCompound())
			return null;
		id = stack.getTagCompound().getInteger("Weather");
		EnumWeatherTolerence tolerence = EnumInsects.getTolerenceByID(id);
		return tolerence != null ? tolerence : EnumWeatherTolerence.SUNNY;
	}
}
