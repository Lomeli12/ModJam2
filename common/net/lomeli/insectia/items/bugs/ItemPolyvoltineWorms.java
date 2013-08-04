package net.lomeli.insectia.items.bugs;

import java.util.List;
import java.util.Random;

import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemPolyvoltineWorms extends ItemBugs{
	public ItemPolyvoltineWorms(int par1, String texture, ItemStack[] producedItems, 
			int dropChance, int time, EnumInsectQuartersType quartersType, int lifeSpan, BiomeGenBase[] biomes) {
		super(par1, texture, producedItems, dropChance, time, quartersType, lifeSpan, biomes);
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
					((EntityPlayer)entity).addPotionEffect(new PotionEffect(9, 300, 1));
				}
				updateTick = 0;
			}
		}
	}
	
	@Override
	public void getEffectOnNerbyEntities(World world, int x, int y, int z) {
		if(world != null){
			List<Entity> entityList = world.getEntitiesWithinAABB(EntityLiving.class, 
					AxisAlignedBB.getAABBPool().getAABB(x-5, y, z-5, x+5, y, z+5));
			for(Object entity : entityList.toArray()){
				if(entity != null && entity instanceof EntityLiving){
					((EntityLiving)entity).addPotionEffect(new PotionEffect(9, 10000));
				}
			}
		}
	}

}
