package net.lomeli.insectia.items.bugs;

import java.util.Random;

import net.lomeli.insectia.api.interfaces.EnumInsectQuartersType;
import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemUnivoltineWorm extends ItemBugs{

	public ItemUnivoltineWorm(int par1, String texture, ItemStack[] producedItems,
			int dropChance, int time, EnumInsectQuartersType quartersType,
			int lifeSpan, BiomeGenBase[] biomes, int day) {
		super(par1, texture, producedItems, dropChance, time, quartersType, lifeSpan, biomes, day);
	}
	
	private int updateTick;
	private Random rand = new Random();
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		if(world != null && entity instanceof EntityPlayer){
			updateTick++;
			if(updateTick >= 30){
				int roll = rand.nextInt(200);
				if(roll < ModInts.chanceOfBite){
					((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.heal.id, 150, 1));
				}
				updateTick = 0;
			}
		}
	}

}
