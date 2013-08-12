package net.lomeli.insectia.blocks;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.api.InsectiaAPI;
import net.lomeli.insectia.api.insects.IInsect;
import net.lomeli.insectia.api.insects.ILarvae;
import net.lomeli.insectia.lib.ModStrings;
import net.lomeli.insectia.tileentity.TileEntityIncubator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockIncubator extends BlockContainer{

	private Icon[] iconArray;
	public BlockIncubator(int par1) {
		super(par1, Material.glass);
		this.setHardness(0.5F);
		this.setCreativeTab(Insectia.modTab);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		iconArray = new Icon[2];
		String textureName = "incubator_";
		this.iconArray[0] = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" 
				+ textureName + "0");
		this.iconArray[1] = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" 
				+ textureName + "1");
	}
	
	@Override
	public Icon getIcon(int par1, int par2){
		return par1 < 2 ? this.iconArray[1] : this.iconArray[0];
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, 
		EntityPlayer entityPlayer, int i, float j, float k, float f){
		if(!world.isRemote){
			if(entityPlayer != null){
				TileEntityIncubator tile = (TileEntityIncubator)world.getBlockTileEntity(x, y, z);
				if(tile != null){
					if(entityPlayer.isSneaking()){
						if(entityPlayer.inventory.getCurrentItem() == null){
							if((tile.getStackInSlot(0) != null && 
								tile.getStackInSlot(0).getItem() instanceof ILarvae)){
								entityPlayer.addChatMessage("Larvae Type: " + 
								InsectiaAPI.getInsect(tile.getStackInSlot(0)).getDisplayName());
								entityPlayer.addChatMessage("Ticks Left: " + tile.getTimeLeft());
							}else if((tile.getStackInSlot(0) != null && 
								tile.getStackInSlot(0).getItem() instanceof IInsect)){
								entityPlayer.addChatMessage("Your " + tile.getStackInSlot(0).getDisplayName() +
									" has matured!");
							}
						}
					}else{
						if((tile.getStackInSlot(0) == null || 
							tile.getStackInSlot(0).getItem() instanceof ILarvae) && 
							(entityPlayer.inventory.getCurrentItem() != null && 
							entityPlayer.inventory.getCurrentItem().getItem() instanceof ILarvae)){
							tile.setInventorySlotContents(0, entityPlayer.inventory.getCurrentItem());
							entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, 
								null);
						}else if(tile.getStackInSlot(0) != null && 
							tile.getStackInSlot(0).getItem() instanceof IInsect && 
							entityPlayer.inventory.getCurrentItem() == null){
							entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem,
								tile.getStackInSlot(0));
							tile.setInventorySlotContents(0, null);
						}
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean hasTileEntity(int metadata){
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityIncubator();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		super.breakBlock(world, x, y, z, par5, par6);
		dropItems(world, x, y, z);
	}
	
	public void dropItems(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile != null){
			if(tile instanceof IInventory){
				IInventory blockInventory = (IInventory)tile;
				for(int i = 0; i < blockInventory.getSizeInventory(); i++){
					ItemStack stack = blockInventory.getStackInSlot(i);
					if(stack != null && stack.stackSize > 0){
						EntityItem entity = new EntityItem(world, x, y, z, 
							new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage()));
						if(stack.hasTagCompound())
							entity.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
						
						world.spawnEntityInWorld(entity);
						stack.stackSize = 0;
					}
				}
			}
		}
	}
	
	@Override
	public int getRenderBlockPass(){
        return 1;
    }
	
	@Override
	public boolean isOpaqueCube(){
        return false;
    }

}
