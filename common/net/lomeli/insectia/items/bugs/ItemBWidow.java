package net.lomeli.insectia.items.bugs;

import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemBWidow extends ItemBugs{

	private Random rand;
	public ItemBWidow(int par1, String texture, Item[] producedItems, int chance, int time) {
		super(par1, texture, producedItems, chance, time);
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
	
	@Override
	public void getEffectOnNerbyEntities(World world, int x, int y, int z){
		if(world != null){
			List<Entity> nearbyEntities = world.getEntitiesWithinAABB(EntityLiving.class, 
				AxisAlignedBB.getAABBPool().getAABB(x-5, y, z-5, x+5, y, z+5));
			for(Object livingEntity : nearbyEntities.toArray()){
				if(livingEntity instanceof EntityLiving){
					((EntityLiving) livingEntity).addPotionEffect(new PotionEffect(Potion.poison.id, 10));
				}
			}
		}
	}
}
