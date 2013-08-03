package net.lomeli.insectia.core;

import net.lomeli.insectia.tileentity.TileEntityLivingQuarters;

import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityLivingQuarters.class, "insectLivingQuarters");
	}
}
