package net.lomeli.insectia.tileentity;

import java.util.Random;

import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.api.ILivingQuarters;
import net.lomeli.insectia.api.IBugs;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLivingQuarters extends TileEntity 
	implements IInventory, ILivingQuarters{
	
	private ItemStack[] inventory;
	private EnumInsectQuartersType quartersType;
	
	private int tick;
	
	private Random rand = new Random();
	
	public TileEntityLivingQuarters(){
		this.inventory = new ItemStack[4];
	}
	
	public void producedItem(IBugs item, int num){
		for(int i = 0; i < num; i++){
			this.inventory[i+1] = item.getRandomItem();
		}
		item.hurtBug(this.inventory[0], num);
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(this.inventory[0] != null && (this.inventory[0].getItem() instanceof IBugs)){
				tick++;
				if(tick >= ((IBugs)this.inventory[0].getItem()).getProductionTime()){
					int j = rand.nextInt(1 + ((IBugs)this.inventory[0].getItem()).getItemsProduced().length);
					
					if(j == ((IBugs)this.inventory[0].getItem()).getItemsProduced().length){
						if(!((IBugs)this.inventory[0].getItem()).getPreferedLivingType().equals(this.getQuartersType()))
							((IBugs)this.inventory[0].getItem()).hurtBug(this.inventory[0], 1);
					}
					else
						producedItem(((IBugs)this.inventory[0].getItem()), j);
					
					if(rand.nextInt(100) < ModInts.chanceOfBite)
						((IBugs)this.inventory[0].getItem()).getEffectOnNerbyEntities(worldObj, xCoord, yCoord, zCoord);
					
					tick = 0;
				}
			}
		}
	}
	
	@Override
	public void setQuartersType(EnumInsectQuartersType type) {
		this.quartersType = type;
	}

	@Override
	public EnumInsectQuartersType getQuartersType() {
		return this.quartersType;
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(j < this.inventory[i].stackSize)
			this.inventory[i].stackSize -= j;
		else
			this.inventory[i] = null;
		return this.inventory[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return this.inventory[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.inventory[i] = itemstack;
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(itemstack != null){
			if(this.inventory[i] == null)
				return true;
			if(itemstack.getItem().equals(this.inventory[i].getItem())){
				return true;
			}
		}
		return false;
	}
}
