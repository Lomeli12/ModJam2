package net.lomeli.insectia.blocks.worldgen;

import java.util.Random;

import net.lomeli.insectia.blocks.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0)
			generateOverworld(world, random, chunkX, random.nextInt(150), chunkZ);
	}
	
	public void generateOverworld(World world, Random random, int x, int y, int z){
		new WorldGenRecluseTrap(ModBlocks.recluseTrap.blockID).generate(world, random, x, y, z);	
	}

}
