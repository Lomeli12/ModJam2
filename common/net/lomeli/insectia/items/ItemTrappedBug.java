package net.lomeli.insectia.items;

import java.util.Random;

import net.lomeli.insectia.api.InsectRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTrappedBug extends ItemGeneric {

	private Random rand= new Random();
	public ItemTrappedBug(int par1, String texture) {
		super(par1, texture);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer){
		if(world != null && !world.isRemote){
			int j = rand.nextInt(InsectRegistry.getInstance().getTrappedRegistry().size());
			//System.out.println(rand.nextInt(InsectRegistry.getInstance().getTrappedRegistry().size()));
			ItemStack stack = InsectRegistry.getInstance().getRandomInsect(j);
			return stack;
		}
		return itemStack;
    }

}
