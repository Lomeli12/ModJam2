package net.lomeli.insectia.blocks.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRecluseTrap extends WorldGenerator{

	private int blockID;
	private int blockMeta;
	
	public WorldGenRecluseTrap(int blockID){
		this.blockID = blockID;
		this.blockMeta = 0;
	}
	
	public WorldGenRecluseTrap(int blockID, int blockMeta){
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		for (int l = 0; l < 64; ++l){
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 127) && 
            		Block.blocksList[this.blockID].canBlockStay(world, i1, j1 - 1, k1) &&
            		random.nextInt(100) < 2){
            	world.setBlock(i1, j1-1, k1, this.blockID, this.blockMeta, 2);
            }
        }
        return true;
	}

}
