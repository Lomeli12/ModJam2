package net.lomeli.insectia.items;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.lib.ModStrings;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGeneric extends Item {

	protected String itemTexture;
	public ItemGeneric(int par1, String texture) {
		super(par1);
		itemTexture = texture;
		this.setCreativeTab(Insectia.modTab);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" + itemTexture);
	}

}
