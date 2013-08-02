package net.lomeli.insectia.items;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.lib.ModStrings;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemBugNet extends ItemTool{ 
	private String itemTexture;
	private EnumNetType netType;
	
	public ItemBugNet(int par1, String texture, EnumNetType type) {
		super(par1, 0F, EnumToolMaterial.WOOD, type.getBlocks());
		itemTexture = texture;
		netType = type;
		this.setCreativeTab(Insectia.modTab);
	}

	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" + itemTexture);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, 
			int blockID, int x, int y, int z, EntityLivingBase par7EntityLivingBase){
		
		
		return true;
    }
}
