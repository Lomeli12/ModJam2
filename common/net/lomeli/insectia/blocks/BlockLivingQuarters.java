package net.lomeli.insectia.blocks;

import java.util.List;
import java.util.Random;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.api.IBugs;
import net.lomeli.insectia.lib.ModStrings;
import net.lomeli.insectia.tileentity.TileEntityLivingQuarters;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockLivingQuarters extends BlockContainer{

	private Icon[] iconArray;
	public BlockLivingQuarters(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(Insectia.modTab);
	}
	
	public EnumInsectQuartersType getTypeBasedOnMetadata(int meta){
		EnumInsectQuartersType type;
		switch(meta){
			case 0:
				type = EnumInsectQuartersType.DARK;
				break;
			case 1:
				type = EnumInsectQuartersType.SWEET;
				break;
			case 2:
				type = EnumInsectQuartersType.GREEN;
				break;
			case 3:
				type = EnumInsectQuartersType.SMELLY;
				break;
			default:
				type = EnumInsectQuartersType.DARK;
				break;
		}
		
		return type;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, 
		EntityPlayer entityPlayer, int i, float j, float k, float f){
		if(entityPlayer != null){
			TileEntityLivingQuarters tileEntity = (TileEntityLivingQuarters)world.getBlockTileEntity(x, y, z);
			if(tileEntity != null){
				int t = world.getBlockMetadata(x, y, z);
				if(tileEntity.getQuartersType() == null)
					tileEntity.setQuartersType(getTypeBasedOnMetadata(t));
				if(!entityPlayer.isSneaking()){
					if(entityPlayer.inventory.getCurrentItem() != null &&
						(entityPlayer.inventory.getCurrentItem().getItem() instanceof IBugs) &&
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
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		super.breakBlock(world, x, y, z, par5, par6);
		dropItems(world, x, y, z);
	}
	
	public void dropItems2(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile != null){
			if(tile instanceof IInventory){
				IInventory blockInventory = (IInventory)tile;
				for(int i = 1; i < blockInventory.getSizeInventory(); i++){
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
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLivingQuarters();
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.iconArray = new Icon[4];
		for(int i = 0; i < this.iconArray.length; i++){
			this.iconArray[i] = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" +
					"livingQuarters_" + i);
		}
	}
	
	public Icon getBlockIcon(int par1){
		return this.iconArray[par1];
	}
	
	@Override
	public Icon getIcon(int par1, int par2)
    {
		return this.iconArray[par2 % this.iconArray.length];
    }
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < this.iconArray.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
        
    }
}
