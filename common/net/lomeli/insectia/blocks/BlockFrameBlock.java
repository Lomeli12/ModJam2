package net.lomeli.insectia.blocks;

import net.minecraft.block.material.Material;

public class BlockFrameBlock extends BlockGeneric{

	public BlockFrameBlock(int par1, Material par2Material, String texture) {
		super(par1, par2Material, texture);
		this.setHardness(1F);
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
