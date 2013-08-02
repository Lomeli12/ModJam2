package net.lomeli.insectia.items.bugs;

import java.util.Random;

import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFireAnts extends ItemBugs{

	private Random rand;
	public ItemFireAnts(int par1, String texture, Item[] producedItems, int chance, int time) {
		super(par1, texture, producedItems, chance, time);
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		rand = new Random();
		if(world != null && entity instanceof EntityPlayer){
			int roll = rand.nextInt(100);
			if(roll < ModInts.chanceOfBite){
				((EntityPlayer)entity).setFire(10000);
			}
		}
	}
}
