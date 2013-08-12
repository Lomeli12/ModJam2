package net.lomeli.insectia.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemInsectClock extends ItemGeneric{

	public ItemInsectClock(int par1) {
		super(par1, "insectClock");
	}
	
	private int tick;
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer){
		if(world != null && !world.isRemote){
			tick++;
			if(tick >= 1){
				entityPlayer.addChatMessage("Time: " + (world.getWorldTime() % 24000L));
				tick = 0;
			}
		}
		return itemStack;
	}

}
