package net.lomeli.insectia.blocks;

import net.lomeli.insectia.api.housing.IHousing;
import net.lomeli.insectia.lib.ModStrings;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockStatusBlock extends BlockGeneric{

	private Icon[] iconArray;
	public BlockStatusBlock(int par1) {
		super(par1, Material.tnt, "");
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.0625F, 1F);
		this.setHardness(2F);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.iconArray = new Icon[4];
		for(int i = 0; i < this.iconArray.length; i++){
			this.iconArray[i] = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":statusblock_" + i);
		}
	}
	
	@Override
	public Icon getIcon(int par1, int par2){
		return par1 != 1 ? this.iconArray[0] : this.iconArray[par2];
	}
	
	private int tick;
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, 
		EntityPlayer entityPlayer, int i, float j, float k, float f){
		if(entityPlayer != null){
			TileEntity tile = world.getBlockTileEntity(x, y - 1, z);
			if(tile != null &&(tile instanceof IHousing)){
				tick++;
				if(tick > 1){
					if(tile instanceof IInventory){
						if(((IInventory)tile).getStackInSlot(0) != null){
							int life = ((IInventory)tile).getStackInSlot(0).getMaxDamage() - ((IInventory)tile).getStackInSlot(0).getItemDamage();
							entityPlayer.addChatMessage("Insect: " + ((IInventory)tile).getStackInSlot(0).getDisplayName());
							entityPlayer.addChatMessage("Health: " + life + "/" +((IInventory)tile).getStackInSlot(0).getMaxDamage());
							if(!((IHousing)tile).canWorkTime(world, ((IInventory)tile).getStackInSlot(0)))
								entityPlayer.addChatMessage("Sleeping...");
							else if(!((IHousing)tile).canWorkWeather(world, ((IInventory)tile).getStackInSlot(0)))
								entityPlayer.addChatMessage("Can't work in this weather!");
						}else{
							entityPlayer.addChatMessage("Empty...");
							if(((IHousing)tile).getQuartersType() != null)
								entityPlayer.addChatMessage("Type: " + ((IHousing)tile).getQuartersType().name());
						}
					
						for(int i1 = 1; i1 < ((IInventory)tile).getSizeInventory(); i1++){
							if(((IInventory)tile).getStackInSlot(i1) != null)
							entityPlayer.addChatMessage(((IInventory)tile).getStackInSlot(i1).getDisplayName());
						}
					}else if(tile instanceof ISidedInventory){
						if(((ISidedInventory)tile).getStackInSlot(0) != null){
							int life = ((ISidedInventory)tile).getStackInSlot(0).getMaxDamage() - ((ISidedInventory)tile).getStackInSlot(0).getItemDamage();
							entityPlayer.addChatMessage("Insect: " + ((ISidedInventory)tile).getStackInSlot(0).getDisplayName());
							entityPlayer.addChatMessage("Health: " + life + "/" +((ISidedInventory)tile).getStackInSlot(0).getMaxDamage());
							if(!((IHousing)tile).canWorkTime(world, ((ISidedInventory)tile).getStackInSlot(0)))
								entityPlayer.addChatMessage("Sleeping...");
							else if(!((IHousing)tile).canWorkWeather(world, ((ISidedInventory)tile).getStackInSlot(0)))
								entityPlayer.addChatMessage("Can't work in this weather!");
						}else{
							entityPlayer.addChatMessage("Empty...");
							if(((IHousing)tile).getQuartersType() != null)
								entityPlayer.addChatMessage("Type: " + ((IHousing)tile).getQuartersType().name());
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

	@Override
	public boolean isOpaqueCube(){
        return false;
    }
}
