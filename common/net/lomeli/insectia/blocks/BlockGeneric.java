package net.lomeli.insectia.blocks;

import net.lomeli.insectia.lib.ModStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockGeneric extends Block{

	private String blockTexture;
	public BlockGeneric(int par1, Material par2Material, String texture) {
		super(par1, par2Material);
		blockTexture = texture;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" + blockTexture);
	}

}
