package net.lomeli.insectia.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * REGISTER YOUR BUGS HERE OR IT WILL NOT WORK!
 * @author Anthony
 */
public class InsectRegistry {
	private static final InsectRegistry instance = new InsectRegistry();
	
	private List<ItemStack> defaultRegistry = new ArrayList<ItemStack>();
	private List<ItemStack> trappedRegistry = new ArrayList<ItemStack>();
	
	private InsectRegistry(){}
	
	public static final InsectRegistry getInstance(){
		return instance;
	}
	
	public void registerInsect(ItemStack insect, boolean bool){
		if(insect.getItem() instanceof IInsect){
			if(bool)
				trappedRegistry.add(insect);
			
			defaultRegistry.add(insect);
		}
	}
	
	public void registerInsect(Item insect, boolean bool){
		registerInsect(new ItemStack(insect), bool);
	}
	
	public ItemStack getRandomInsect(int rand){
		return getTrappedRegistry().get(rand);
	}
	
	public boolean isObtainableViaSpiders(ItemStack stack){
		return this.trappedRegistry.contains(stack);
	}
	
	public List<ItemStack> getPrimaryRegistry(){
		return this.defaultRegistry;
	}
	
	public List<ItemStack> getTrappedRegistry(){
		return this.trappedRegistry;
	}
	
}
