package net.lomeli.insectia.items;

import java.util.Random;

import net.lomeli.insectia.api.interfaces.EnumHousingType;
import net.lomeli.insectia.api.interfaces.IInsect;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemBugs extends ItemGeneric implements IInsect{

	private ItemStack[] itemsProduced;
	private int dropChance;
	private int time;
	private int timeOfDay;
	private EnumHousingType  quartersType;
	private Random randomItem = new Random();
	private BiomeGenBase[] biomes;
	
	public ItemBugs(int par1, String texture, ItemStack[] producedItems, 
		int dropChance, int time, EnumHousingType quartersType, 
		int lifeSpan, BiomeGenBase[] biomes, int timeOfDay) {
		super(par1, texture);
		this.dropChance = dropChance;
		this.time = time;
		this.quartersType = quartersType;
		this.itemsProduced = producedItems;
		this.biomes = biomes;
		this.timeOfDay = timeOfDay;
		this.setMaxDamage(lifeSpan);
		this.setMaxStackSize(1);
	}
	
	@Override
	public int getLifeSpan(){
		return this.getMaxDamage();
	}
	
	@Override
	public void hurtBug(ItemStack itemStack, int damage){
		itemStack.setItemDamage(this.getDamage(itemStack) + damage);
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
	public EnumHousingType getPreferedLivingType() {
		return this.quartersType;
	}

	@Override
	public ItemStack getRandomItem() {
		int i = randomItem.nextInt(this.itemsProduced.length);
		return this.itemsProduced[i];
	}

	@Override
	public BiomeGenBase[] getPreferedBiome() {
		return this.biomes;
	}

	@Override
	public int getLarvaeItemID() {
		return ModItems.insectLarvae.itemID;
	}

	@Override
	public int larvaeGrowthTime() {
		return 6000;
	}

	@Override
	public int getPreferedTimeOfDay() {
		return this.timeOfDay;
	}

}
