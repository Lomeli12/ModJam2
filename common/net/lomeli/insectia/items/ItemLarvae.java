package net.lomeli.insectia.items;

import java.util.List;

import net.lomeli.insectia.api.InsectiaAPI;
import net.lomeli.insectia.api.interfaces.ILarvae;
import net.lomeli.insectia.lib.ModStrings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemLarvae extends ItemGeneric implements ILarvae{

	private Icon icon;
	public ItemLarvae(int par1, String texture) {
		super(par1, texture);
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		if(world != null){
			ItemStack insect = InsectiaAPI.getInsect(itemStack);
			if(insect != null)
				this.icon = insect.getItem().getIconFromDamage(insect.getItemDamage());
		}
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, 
			List par3List, boolean par4) {
		ItemStack stack = InsectiaAPI.getInsect(par1ItemStack);
		if(stack != null)
			par3List.add(stack.getDisplayName());
	}

	@Override
	public ItemStack getLarveType(ItemStack stack) {
		return InsectiaAPI.getInsect(stack);
	}
}
