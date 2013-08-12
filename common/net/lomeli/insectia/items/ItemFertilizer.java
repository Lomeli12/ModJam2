package net.lomeli.insectia.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFertilizer extends ItemGeneric{

	public ItemFertilizer(int par1, String texture) {
		super(par1, texture);
	}
	
	@Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, 
    		int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		ItemDye.applyBonemeal(itemstack, world, x, y, z, player);
		return true;
	}

}
