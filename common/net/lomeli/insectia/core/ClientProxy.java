package net.lomeli.insectia.core;

import net.lomeli.insectia.tileentity.TileEntityDark;
import net.lomeli.insectia.tileentity.TileEntityGreen;
import net.lomeli.insectia.tileentity.TileEntityIncubator;
import net.lomeli.insectia.tileentity.TileEntitySmelly;
import net.lomeli.insectia.tileentity.TileEntitySweet;

import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityDark.class, "insectLivingQuartersDark");
		GameRegistry.registerTileEntity(TileEntityGreen.class, "insectLivingQuartersGreen");
		GameRegistry.registerTileEntity(TileEntitySmelly.class, "insectLivingQuartersSmelly");
		GameRegistry.registerTileEntity(TileEntitySweet.class, "insectLivingQuartersSweet");
		GameRegistry.registerTileEntity(TileEntityIncubator.class, "insectgrowthchamber");
	}
}
