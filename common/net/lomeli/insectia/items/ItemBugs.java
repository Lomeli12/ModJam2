package net.lomeli.insectia.items;

import net.minecraft.item.Item;

public class ItemBugs extends ItemGeneric{

	private Item[] itemsProduced;
	public ItemBugs(int par1, String texture, Item[] producedItems) {
		super(par1, texture);
	}
	
	public Item[] getItemsProduced(){
		return itemsProduced;
	}

}
