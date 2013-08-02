package net.lomeli.insectia.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InsectRegistry {
	private static final InsectRegistry instance = new InsectRegistry();
	
	private Map insectList = new HashMap<IBugs, Boolean>();
	private HashMap<List<Integer>, Boolean> insectListMeta = new HashMap<List<Integer>, Boolean>();
	
	private InsectRegistry(){}
	
	public static final InsectRegistry getInstance(){
		return instance;
	}
	
	public IBugs addInsect(Item insect, boolean bool){
		if(insect instanceof IBugs)
			return addInsect((IBugs)insect, bool);
		
		return null;
	}
	
	public IBugs addInsect(Item insect, int meta, boolean bool){
		return addInsect(insect.itemID, meta, bool);
	}
	
	public IBugs addInsect(int itemID, int meta, boolean bool){
		if(Item.itemsList[itemID] instanceof IBugs){
			this.insectListMeta.put(Arrays.asList(itemID, meta), bool);
			return (IBugs)Item.itemsList[itemID];
		}
		return null;
	}
	
	public IBugs addInsect(IBugs insect, boolean bool){
		this.insectList.put(insect, bool);
		return insect;
	}
	
	public boolean isObtainableViaSpiders(ItemStack stack){
		if(stack == null)
			return false;
		if(this.insectListMeta.containsKey(Arrays.asList(stack.itemID, stack.getItemDamage())))
			return this.insectListMeta.get(Arrays.asList(stack.itemID, stack.getItemDamage()));
		
		return (boolean) this.insectList.get((IBugs)stack.getItem());
	}
	
	public Map getInsectRegistry(){
		return this.insectList;
	}
	
	public Map<List<Integer>, Boolean> getMetaInsectRegistry(){
		return this.insectListMeta;
	}
	
	public boolean doesRegistryContainBug(Item insect) {
		return this.insectList.containsKey(insect);
	}
	
	public boolean doesRegistryContainBug(IBugs insect) {
		return this.insectList.containsKey(insect);
	}
}
