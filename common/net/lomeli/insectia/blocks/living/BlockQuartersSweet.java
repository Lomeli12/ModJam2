package net.lomeli.insectia.blocks.living;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.api.IInsect;
import net.lomeli.insectia.lib.ModStrings;
import net.lomeli.insectia.tileentity.TileEntitySweet;

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

public class BlockQuartersSweet extends BlockContainer{

	private Icon[] iconArray;
	public BlockQuartersSweet(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(Insectia.modTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySweet();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, 
		EntityPlayer entityPlayer, int i, float j, float k, float f){
		if(entityPlayer != null){
			TileEntitySweet tileEntity = (TileEntitySweet)world.getBlockTileEntity(x, y, z);
			if(tileEntity != null){
				if(!entityPlayer.isSneaking()){
					if(entityPlayer.inventory.getCurrentItem() != null &&
						(entityPlayer.inventory.getCurrentItem().getItem() instanceof IInsect) &&
						tileEntity.isItemValidForSlot(0, entityPlayer.inventory.getCurrentItem()) &&
						tileEntity.getStackInSlot(0) == null){
						System.out.println("Helllo");
						tileEntity.setInventorySlotContents(0, entityPlayer.inventory.getCurrentItem());
						entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, null);
					}
					else if(tileEntity.getStackInSlot(0) != null){
						entityPlayer.inventory.addItemStackToInventory(tileEntity.getStackInSlot(0));
						tileEntity.setInventorySlotContents(0, null);
						System.out.println("Hil");
					}
				}
				else{
					dropItems2(world, x, y, z);
					System.out.println("Blah");
				}
			}
		}
		return true;
	}
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.iconArray = new Icon[2];
		
		this.iconArray[0] = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":livingQuarters_1");
		this.iconArray[1] = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":base");	
	}
	
	@Override
	public Icon getIcon(int par1, int par2){
		return par1 < 2 ? this.iconArray[1] : this.iconArray[0];
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		super.breakBlock(world, x, y, z, par5, par6);
		dropItems(world, x, y, z);
	}
	
	public void dropItems2(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile != null){
			if(tile instanceof IInventory){
				for(int i = 1; i < ((IInventory)tile).getSizeInventory(); i++){
					ItemStack stack = ((IInventory)tile).getStackInSlot(i);
					if(stack != null && stack.stackSize > 0){
						EntityItem entity = new EntityItem(world, x, y, z, 
							new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage()));
						if(stack.hasTagCompound())
							entity.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
						
						world.spawnEntityInWorld(entity);
						((IInventory)tile).setInventorySlotContents(i, null);
					}
				}
			}
		}
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
        return 0;
    }
	
	@Override
	public boolean isOpaqueCube(){
        return false;
    }
}
