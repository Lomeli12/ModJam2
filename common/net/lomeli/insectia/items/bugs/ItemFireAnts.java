package net.lomeli.insectia.items.bugs;

import java.util.Random;

import net.lomeli.insectia.api.interfaces.EnumInsectQuartersType;
import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemFireAnts extends ItemBugs{

	private Random rand = new Random();
	private int tick;
	public ItemFireAnts(int par1, String texture, ItemStack[] producedItems, 
		int chance, int time, EnumInsectQuartersType type, int lifeSpan,
		BiomeGenBase[] biomes, int day) {
		super(par1, texture, producedItems, chance, time, type, lifeSpan, biomes, day);
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		if(world != null && entity instanceof EntityPlayer){
			tick++;
			if(tick >= 30){
				int roll = rand.nextInt(100);
				if(roll < ModInts.chanceOfBite){
					((EntityPlayer)entity).setFire(10000);
					((EntityPlayer)entity).setFire(10000);
				}
				tick = 0;
			}
		}
	}
}
