package net.lomeli.insectia.items.bugs;

import java.util.List;
import java.util.Random;

import net.lomeli.insectia.api.housing.EnumHousingType;
import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemForkkEater extends ItemBugs{
	private Random rand = new Random();
	private int updateTick;
	public ItemForkkEater(int par1, String texture, ItemStack[] producedItems,
			int dropChance, int time, EnumHousingType quartersType,
			int lifeSpan, BiomeGenBase[] biomes) {
		super(par1, texture, producedItems, dropChance, time, quartersType, lifeSpan,
				biomes);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, 
			List par3List, boolean par4) {
		par3List.add("It hates Forkk...");
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		if(world != null && entity instanceof EntityPlayer){
			if(entity instanceof EntityPlayer){
				updateTick++;
				if(updateTick >= 30){
					int roll = rand.nextInt(100);
					if(roll < ModInts.chanceOfBite && !((EntityPlayer)entity).capabilities.isCreativeMode){
						world.playSoundAtEntity(entity, "mob.spider.say", 0.15F, 1F);
						if(((EntityPlayer)entity).username == "Forkk13"){
							((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 900, 3));
						}else
							((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 300, 3));
					}
					updateTick = 0;
				}
			}
		}
	}

}
