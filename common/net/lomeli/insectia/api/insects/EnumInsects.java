package net.lomeli.insectia.api.insects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumHelper;

public class EnumInsects{
	/* =================================================== INSECT GENDERS =====================================*/
	private static List<EnumGender> genders = new ArrayList<EnumGender>();
	
	/**
	 * Allows you to get the insects gender by
	 * using the gender id they give you.
	 * @param id
	 * @return
	 */
	public static EnumGender getGenderByID(int id){
		EnumGender enumG = genders.get(id);
		return enumG != null ? enumG : EnumGender.NULL;
	}
	
	/**
	 * Allows you to add your own insect "genders".
	 * Has checks to make sure you don't overwrite
	 * pre-existing genders.
	 * @param name
	 * @param id
	 * @return
	 */
	public static EnumGender addGender(String name, int id){
		if(genders.get(id) == null)
			return EnumHelper.addEnum(EnumGender.class, name, id);
		else
			return EnumHelper.addEnum(EnumGender.class, name, getNextIDGender());
	}
	/**
	 * Gets the next available id for genders.
	 * @return
	 */
	public static int getNextIDGender(){
		return genders.size();
	}
	
	/**
	 * Enum for Insect genders. Should be saved via NBT as a int for ID.
	 * NULLB means the insect is genderless, but can still breed.
	 * @author Anthony
	 */
	public static enum EnumGender{
		MALE(0), FEMALE(1), NULL(2), NULLB(3);
		
		private final int typeID;
		EnumGender(int id){
			this.typeID = id;
			EnumInsects.genders.add(id, this);
		}
		
		public int getID(){
			return this.typeID;
		}
	}
	
	/* ================================================= WORK CONDITIONS ===================================*/
	/* Day/Night Cycle */
	private static List<EnumDayCycle> dayCycle = new ArrayList<EnumDayCycle>();
	
	public static EnumDayCycle getPreferedTime(int id){
		EnumDayCycle cycle = dayCycle.get(id);
		return cycle != null ? cycle : EnumDayCycle.NULL;
	}
	
	public static boolean canWorkAtCurrentTime(World world, ItemStack stack){
		boolean canWork = true;
		long currentTime = (world.getWorldTime() % 24000L);
		if(world != null && !world.isRemote && stack != null && stack.getItem() instanceof IInsect){
			EnumDayCycle time = InsectNBTHelper.getPreferedTimeOfDay(stack);
			if(time != null){
				if(currentTime > time.getStartTime() || currentTime < time.getEndTime())
					canWork = true;
				else
					canWork = false;
			}
		}
		return canWork;
	}
	
	public static int getNextTimeID(){
		return dayCycle.size();
	}
	
	public static EnumDayCycle addTimeFrame(String name, int id, int startTime, int endTime){
		if(genders.get(id) == null)
			return EnumHelper.addEnum(EnumDayCycle.class, name, id, startTime, endTime);
		else
			return EnumHelper.addEnum(EnumDayCycle.class, name, getNextTimeID(), startTime, endTime);
	}
	
	/** Should be self explanatory. NULL means the insect can work at any time **/
	public static enum EnumDayCycle{
		
		MORNING(0, 0, 250), DAY(1, 0, 11600), EVENING(2, 11600, 14000), 
		NIGHT(3, 12500, 24000), NULL(4, 0, 24000); 
		
		private final int typeID, minTime, maxTime;
		EnumDayCycle(int id, int min, int max){
			this.minTime = min;
			this.maxTime = max;
			this.typeID = id;
			EnumInsects.dayCycle.add(id, this);
		}
		
		public int getStartTime(){
			return this.minTime;
		}
		
		public int getEndTime(){
			return this.maxTime;
		}
		
		public int getID(){
			return this.typeID;
		}
		
	}
	
	/* ====================================== WEATHER EFFECTS ==========================================*/
	private static List<EnumWeatherTolerence> weather = new ArrayList<EnumWeatherTolerence>();
	
	public static EnumWeatherTolerence getTolerenceByID(int id){
		EnumWeatherTolerence tolerence = weather.get(id);
		return tolerence != null ? tolerence : EnumWeatherTolerence.SUNNY;
	}
	
	public static boolean canWorkInWeather(World world, ItemStack stack){
		boolean canWork = true;
		if((world != null && stack != null) && !world.isRemote && stack.getItem() instanceof IInsect){
			EnumWeatherTolerence tolerence = InsectNBTHelper.getWeatherTolerence(stack);
			if(tolerence != null){
				switch(tolerence){
				case SUNNY:
					if(world.isRaining() || world.isThundering())
						canWork = false;
					break;
				case RAIN:
					if(world.isThundering())
						canWork = false;
					break;
				case THUNDER:
					canWork = true;
					break;
				default:
					break;
				}
			}
		}
		return canWork;
	}
	
	public static enum EnumWeatherTolerence{
		SUNNY(0), RAIN(1), THUNDER(2);
		
		private final int typeID;
		EnumWeatherTolerence(int id){
			this.typeID = id;
			EnumInsects.weather.add(this);
		}
		
		public int getID(){
			return this.typeID;
		}
	}
}
