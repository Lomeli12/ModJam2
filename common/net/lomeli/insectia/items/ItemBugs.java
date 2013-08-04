package net.lomeli.insectia.items;

import java.util.Random;

import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.api.IInsect;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBugs extends ItemGeneric implements IInsect{

	private ItemStack[] itemsProduced;
	private int dropChance;
	private int time;
	private EnumInsectQuartersType  quartersType;
	private Random randomItem = new Random();
	
	public ItemBugs(int par1, String texture, ItemStack[] producedItems, 
		int dropChance, int time, EnumInsectQuartersType quartersType, int lifeSpan) {
		super(par1, texture);
		this.dropChance = dropChance;
		this.time = time;
		this.quartersType = quartersType;
		this.itemsProduced = producedItems;
		this.setMaxDamage(lifeSpan);
		this.setMaxStackSize(1);
	}
	
	@Override
	public int getLifeSpan(){
		return this.getMaxDamage();
	}
	
	@Override
	public void hurtBug(ItemStack itemStack, int damage){
		itemStack.setItemDamage(getLifeSpan() - damage);
	}
	
	@Override
	public ItemStack[] getItemsProduced(){
		return itemsProduced;
	}
	
	@Override
	public int getDropChance(){
		return dropChance;
	}

	@Override
	public int getProductionTime() {
		return time;
	}

	@Override
	public void getEffectOnNerbyEntities(World world, int x, int y, int z) {}

	@Override
	public EnumInsectQuartersType getPreferedLivingType() {
		return this.quartersType;
	}

	@Override
	public ItemStack getRandomItem() {
		int i = randomItem.nextInt(this.itemsProduced.length);
		return this.itemsProduced[i];
	}

}
