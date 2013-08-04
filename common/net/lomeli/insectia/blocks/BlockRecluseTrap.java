package net.lomeli.insectia.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.lomeli.insectia.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRecluseTrap extends BlockGeneric{

	public BlockRecluseTrap(int par1, String texture) {
		super(par1, Material.cactus, texture);
		this.setHardness(3F);
	}
	
	public boolean canBlockStay(World world, int x, int y, int z){
		int grassid = Block.grass.blockID;
		if(world.getBlockId(x - 1, y, z) == grassid ||
			world.getBlockId(x + 1, y, z) == grassid ||
			world.getBlockId(x, y, z - 1) == grassid ||
			world.getBlockId(x, y, z + 1) == grassid)
			return true;
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public int getBlockColor(){
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.getGrassColor(d0, d1);
    }
	
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1){
            for (int l1 = -1; l1 <= 1; ++l1){
                int i2 = par1IBlockAccess.getBiomeGenForCoords(par2 + l1, par4 + k1).getBiomeGrassColor();
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }
	
	public int idDropped(int par1, Random par2Random, int par3){
		return ModItems.bRecluseSpider.itemID;
	}
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity){
		par5Entity.attackEntityFrom(DamageSource.cactus, 1F);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
        float f = 0.7F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)(par3 + 1) - f), (double)((float)(par4 + 1) - f));
    }
	
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
        float f = 1F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)(par3 + 1), (double)((float)(par4 + 1) - f));
    }
}
