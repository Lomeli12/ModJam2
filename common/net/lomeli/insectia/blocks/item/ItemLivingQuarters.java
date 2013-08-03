package net.lomeli.insectia.blocks.item;

import net.lomeli.insectia.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemLivingQuarters extends ItemBlock {

	public ItemLivingQuarters(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public Icon getIconFromDamage(int par1){
		return ModBlocks.livingQuartersBlock.getIcon(2, par1);
    }
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack){
		return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
	}

}
