package net.lomeli.insectia.tileentity;

import java.util.Random;

import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.api.EnumInsectQuartersType.EnumInsectQuartersHelper;
import net.lomeli.insectia.api.ILivingQuarters;
import net.lomeli.insectia.api.IInsect;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

@Deprecated
public class TileEntityLivingQuarters extends TileEntity 
	implements IInventory, ILivingQuarters{
	
	protected ItemStack[] inventory = new ItemStack[4];
	private EnumInsectQuartersType quartersType;
	
	private int tick;
	
	private Random rand = new Random();
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		this.loadNBT(par1NBTTagCompound);
    }
	
	public void loadNBT(NBTTagCompound nbtTag){
		NBTTagList list = new NBTTagList();
		for(int index = 0; index < list.tagCount(); ++index){
			NBTTagCompound tag = (NBTTagCompound)list.tagAt(index);
			byte slot = tag.getByte("Slot");
			if(slot >=0 && slot < this.inventory.length)
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
		
		this.setQuartersType(EnumInsectQuartersHelper.getTypeByID(nbtTag.getInteger("QuarterType")));
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
		nbtTag.setTag("Inventory", list);
		if(this.getQuartersType() != null)
			nbtTag.setInteger("QuarterType", this.getQuartersType().getID());
	}
	
	public void producedItem(IInsect item, int num){
		for(int i = 0; i < num; i++){
			ItemStack randItem = item.getRandomItem();
			if(randItem != null && this.isItemValidForSlot(i+1, randItem))
				this.inventory[i+1] = randItem.copy();
		}
		int damage = num;
		if(this.quartersType.equals(item.getPreferedLivingType()))
			damage--;
		
		item.hurtBug(this.inventory[0], damage);
		
		if(this.inventory[0].getItemDamage() >= this.inventory[0].getMaxDamage())
			this.inventory[0] = null;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(this.inventory[0] != null && (this.inventory[0].getItem() instanceof IInsect)){
				tick++;
				if(tick >= ((IInsect)this.inventory[0].getItem()).getProductionTime() * 10){
					int j = rand.nextInt(1 + ((IInsect)this.inventory[0].getItem()).getItemsProduced().length);
					
					if(j == ((IInsect)this.inventory[0].getItem()).getItemsProduced().length){
						if(!((IInsect)this.inventory[0].getItem()).getPreferedLivingType().equals(this.getQuartersType()))
							((IInsect)this.inventory[0].getItem()).hurtBug(this.inventory[0], 1);
					}
					else if(this.getStackInSlot(1) != null && this.getStackInSlot(2) != null 
						&& this.getStackInSlot(3) != null)
						tick = 0;
					else
						producedItem(((IInsect)this.inventory[0].getItem()), j - 1);
					
					
					tick = 0;
				}
			}
		}
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
