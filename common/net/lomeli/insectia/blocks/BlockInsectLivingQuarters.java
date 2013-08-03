package net.lomeli.insectia.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInsectLivingQuarters extends BlockContainer{

	public BlockInsectLivingQuarters(int par1) {
		super(par1, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
