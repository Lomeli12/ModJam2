package net.lomeli.insectia.tileentity;

import net.lomeli.insectia.api.interfaces.IInsect;
import net.lomeli.insectia.api.interfaces.ILarvae;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIncubator extends TileEntity
	implements IInventory{

	private ItemStack[] inventory;
	private int tick;
	
	public TileEntityIncubator(){
		this.inventory = new ItemStack[1];
	}
	
	public int getCurrentTick(){
		return this.tick;
	}
	private ItemStack item;
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(this.inventory[0] != null && 
				this.inventory[0].getItem() instanceof ILarvae){
				if(this.worldObj.isDaytime() || 
					this.worldObj.getLightBrightness(xCoord, yCoord, zCoord) > 0.4F)
					tick++;
				
				item = ((ILarvae)this.inventory[0].getItem()).getLarveType(this.inventory[0]);
				if(item != null){
					if(tick > ((IInsect)item.getItem()).larvaeGrowthTime()){
						this.setInventorySlotContents(0, 
							((ILarvae)this.inventory[0].getItem()).getLarveType(this.inventory[0]));
						tick = 0;
					}
				}else{
					this.setInventorySlotContents(0, null);
					tick = 0;
				}
			}
		}
	}
	
	public int getTimeLeft(){
		return (((IInsect)item.getItem()).larvaeGrowthTime() - tick);
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		this.loadNBT(par1NBTTagCompound);
    }
	
	public void loadNBT(NBTTagCompound nbtTag){
		NBTTagList list = nbtTag.getTagList("Inventory");
		for(int index = 0; index < list.tagCount(); ++index){
			NBTTagCompound tag = (NBTTagCompound)list.tagAt(index);
			byte slot = tag.getByte("Slot");
			if(slot >=0 && slot < this.inventory.length)
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
		this.tick = nbtTag.getInteger("CurrentTick");
	}
	
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		this.addToNBT(par1NBTTagCompound);
	}
	
	public void addToNBT(NBTTagCompound nbtTag){
		NBTTagList list = new NBTTagList();
		for(int index = 0; index < this.inventory.length; ++index){
			if(this.inventory[index] != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) index);
				this.inventory[index].writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbtTag.setInteger("CurrentTick", this.tick);
		nbtTag.setTag("Inventory", list);
	}
	
	@Override
	public Packet getDescriptionPacket(){
		Packet132TileEntityData packet = (Packet132TileEntityData)super.getDescriptionPacket();
		NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();
		this.addToNBT(tag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}
	
	@Override
	public void onDataPacket(INetworkManager networkManager, Packet132TileEntityData packet){
		super.onDataPacket(networkManager, packet);
		NBTTagCompound nbtTag = packet.customParam1;
		this.loadNBT(nbtTag);
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
		return 64;
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
