package net.lomeli.insectia.blocks;

import net.lomeli.insectia.api.ILivingQuarters;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStatusBlock extends BlockGeneric{

	public BlockStatusBlock(int par1, String texture) {
		super(par1, Material.cloth, texture);
	}
	
	private int tick;
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, 
		EntityPlayer entityPlayer, int i, float j, float k, float f){
		if(entityPlayer != null){
			TileEntity tile = world.getBlockTileEntity(x, y - 1, z);
			if(tile != null &&(tile instanceof ILivingQuarters)){
				tick++;
				if(tick > 1){
					if(tile instanceof IInventory){
						if(((IInventory)tile).getStackInSlot(0) != null){
							int life = ((IInventory)tile).getStackInSlot(0).getMaxDamage() - ((IInventory)tile).getStackInSlot(0).getItemDamage();
							entityPlayer.addChatMessage("Bug: " + ((IInventory)tile).getStackInSlot(0).getDisplayName());
							entityPlayer.addChatMessage("Health: " + life + "/" +((IInventory)tile).getStackInSlot(0).getMaxDamage());
					
						}else{
							entityPlayer.addChatMessage("Empty...");
							if(((ILivingQuarters)tile).getQuartersType() != null)
								entityPlayer.addChatMessage("Type: " + ((ILivingQuarters)tile).getQuartersType().name());
						}
					
						for(int i1 = 1; i1 < ((IInventory)tile).getSizeInventory(); i1++){
							if(((IInventory)tile).getStackInSlot(i1) != null)
							entityPlayer.addChatMessage(((IInventory)tile).getStackInSlot(i1).getDisplayName());
						}
					}else if(tile instanceof ISidedInventory){
						if(((ISidedInventory)tile).getStackInSlot(0) != null){
							int life = ((ISidedInventory)tile).getStackInSlot(0).getMaxDamage() - ((ISidedInventory)tile).getStackInSlot(0).getItemDamage();
							entityPlayer.addChatMessage("Bug: " + ((ISidedInventory)tile).getStackInSlot(0).getDisplayName());
							entityPlayer.addChatMessage("Health: " + life + "/" +((ISidedInventory)tile).getStackInSlot(0).getMaxDamage());
					
						}else{
							entityPlayer.addChatMessage("Empty...");
							if(((ILivingQuarters)tile).getQuartersType() != null)
								entityPlayer.addChatMessage("Type: " + ((ILivingQuarters)tile).getQuartersType().name());
						}
					
						for(int i1 = 1; i1 < ((ISidedInventory)tile).getSizeInventory(); i1++){
							if(((ISidedInventory)tile).getStackInSlot(i1) != null)
							entityPlayer.addChatMessage(((ISidedInventory)tile).getStackInSlot(i1).getDisplayName());
						}
					}
					tick = 0;
				}
			}
		}
		return true;
	}

}
