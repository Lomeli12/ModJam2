package net.lomeli.insectia.items;

import java.util.List;

import net.lomeli.insectia.api.InsectiaAPI;
import net.lomeli.insectia.api.insects.ILarvae;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemLarvae extends ItemGeneric implements ILarvae{

	public ItemLarvae(int par1, String texture) {
		super(par1, texture);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
