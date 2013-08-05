package net.lomeli.insectia.items.bugs;

import net.lomeli.insectia.api.interfaces.EnumInsectQuartersType;
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
import net.minecraft.world.biome.BiomeGenBase;

import java.util.List;
import java.util.Random;

public class ItemBWidow extends ItemBugs{

	private Random rand = new Random();
	private int updateTick;
	public ItemBWidow(int par1, String texture, ItemStack[] producedItems, 
			int chance, int time, EnumInsectQuartersType type, int lifeSpan, 
			BiomeGenBase[] biomes, int day) {
		super(par1, texture, producedItems, chance, time, type, lifeSpan, biomes, day);
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		if(world != null && entity instanceof EntityPlayer){
			updateTick++;
			if(updateTick >= 30){
				int roll = rand.nextInt(100);
				if(roll < ModInts.chanceOfBite){
					((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 300, 1));
					world.playSoundAtEntity(entity, "mob.spider.say", 0.15F, 1F);
				}
				updateTick = 0;
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
					((EntityLiving) livingEntity).addPotionEffect(new PotionEffect(Potion.poison.id, 10000));
				}
			}
		}
	}
}
