package net.lomeli.insectia.blocks;

import net.minecraft.block.material.Material;

public class BlockFrameBlock extends BlockGeneric{

	public BlockFrameBlock(int par1, Material par2Material, String texture) {
		super(par1, par2Material, texture);
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
