package net.lomeli.insectia.blocks;

import java.util.Random;

import net.lomeli.insectia.items.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockRecluseTrap extends BlockGeneric{

	public BlockRecluseTrap(int par1, String texture) {
		super(par1, Material.cactus, texture);
		this.setHardness(3F);
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
