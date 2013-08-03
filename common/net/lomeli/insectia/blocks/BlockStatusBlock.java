package net.lomeli.insectia.blocks;

import net.lomeli.insectia.tileentity.TileEntityLivingQuarters;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
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
			TileEntityLivingQuarters tile = (TileEntityLivingQuarters)world.getBlockTileEntity(x, y - 1, z);
			if(tile != null){
				tick++;
				if(tick > 1){
					if(tile.getStackInSlot(0) != null){
					int life = tile.getStackInSlot(0).getMaxDamage() - tile.getStackInSlot(0).getItemDamage();
					entityPlayer.addChatMessage("Bug: " + tile.getStackInSlot(0).getDisplayName());
					entityPlayer.addChatMessage("Health: " + life + "/" +tile.getStackInSlot(0).getMaxDamage());
					
					}else
						entityPlayer.addChatMessage("Empty...");
					
					for(int i1 = 1; i1 < tile.getSizeInventory(); i1++){
						if(tile.getStackInSlot(i1) != null)
							entityPlayer.addChatMessage(tile.getStackInSlot(i1).getDisplayName());
					}
					tick = 0;
				}
			}
		}
		return true;
	}

}
