package net.lomeli.insectia.core;

import net.lomeli.insectia.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMod extends CreativeTabs {

	public CreativeTabMod(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	@Override
	public Item getTabIconItem(){
		return ModItems.bWidowSpider;
    }
	
	@Override
	public String getTranslatedTabLabel(){
		return this.getTabLabel();
    }

}
