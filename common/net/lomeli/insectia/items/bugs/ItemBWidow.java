package net.lomeli.insectia.items.bugs;

import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBWidow extends ItemBugs{

	private Random rand;
	public ItemBWidow(int par1, String texture, Item[] producedItems) {
		super(par1, texture, producedItems);
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		rand = new Random();
		if(world != null && entity instanceof EntityPlayer){
			int roll = rand.nextInt(100);
			if(roll < ModInts.chanceOfBite){
				((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 30));
			}
		}
	}
}
