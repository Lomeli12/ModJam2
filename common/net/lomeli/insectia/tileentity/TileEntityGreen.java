package net.lomeli.insectia.tileentity;

import java.util.Random;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.api.interfaces.EnumHousingType;
import net.lomeli.insectia.api.interfaces.IInsect;
import net.lomeli.insectia.api.interfaces.IHousing;
import net.lomeli.insectia.api.interfaces.EnumHousingType.EnumHousingHelper;
import net.lomeli.insectia.api.InsectiaAPI;
import net.lomeli.insectia.blocks.ModBlocks;
import net.lomeli.insectia.items.ItemLarvae;
import net.lomeli.insectia.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGreen extends TileEntity
	implements IInventory, IHousing{
	
	private ItemStack[] inventory;
	private EnumHousingType type;
	
	public TileEntityGreen(){
		this.inventory = new ItemStack[4];
		this.type = EnumHousingType.GREEN;
	}
	
	private int tick;
	
	private Random rand = new Random();
	
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
		
		this.setQuartersType(EnumHousingHelper.getTypeByID(nbtTag.getInteger("QuarterType")));
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
			for(int j = 1; j < this.inventory.length; j++){
				if(this.isItemValidForSlot(j, randItem)){
					this.setInventorySlotContents(j, randItem);
					break;
				}
			}	
		}
	}
	
	public void randomlyProduceChild(Random rand){
		if(rand.nextInt(100) < 50){
			ItemStack newBug = this.inventory[0].copy();
			newBug.setItemDamage(0);
			ItemStack larvae = new ItemStack(((IInsect)this.inventory[0].getItem()).getLarvaeItemID(), 
				1, newBug.getItemDamage());
			InsectiaAPI.writeInsect(larvae, newBug.itemID, newBug.getItemDamage());
			for(int j = 0; j < this.inventory.length; j++){
				if(this.isItemValidForSlot(j, larvae)){
					this.setInventorySlotContents(j, larvae);
					break;
				}
			}
		}
	}
	
	@Override
	public boolean canWork(IInsect insect){
		boolean canWork = true;
		if(Insectia.limitWorkAtNight){
			switch(insect.getPreferedTimeOfDay()){
				case 0:
					canWork = this.worldObj.isDaytime()
						|| (this.worldObj.getLightBrightness(xCoord, yCoord, zCoord) > 0.4F);
					break;
				case 1:
					canWork = !this.worldObj.isDaytime();
					break;
				default:
					break;
			}
		}	
		return canWork;
	}
	
	public boolean emptySlot(){
		boolean slot = false;
		for(int i = 1; i < this.inventory.length; i++){
			if(this.inventory[i] == null){
				slot = true;
				break;
			}
		}
		
		return slot;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(this.worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == ModBlocks.statusBlock.blockID){
				int l = 0;
				if(this.inventory[0] != null)
					l = this.getInsectLifePercentage() + 1;
				this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord + 1, zCoord, l , 2);
				this.worldObj.markBlockForUpdate(xCoord, yCoord + 1, zCoord);
			}
			if(this.inventory[0] != null && (this.inventory[0].getItem() instanceof IInsect)){
				if(canWork((IInsect)this.inventory[0].getItem()) && emptySlot())
					tick++;
				if(tick >= ((IInsect)this.inventory[0].getItem()).getProductionTime()){
					int j = rand.nextInt(1 + ((IInsect)this.inventory[0].getItem()).getItemsProduced().length);
					producedItem(((IInsect)this.inventory[0].getItem()), j - 1);
					
					if(this.getQuartersType().equals(((IInsect)this.inventory[0].getItem()).getPreferedLivingType()) && rand.nextInt(100) < 50){}
					else
						((IInsect)this.inventory[0].getItem()).hurtBug(this.inventory[0], 1);
					
					tick = 0;
				}
				if(this.inventory[0].getItemDamage() >= this.inventory[0].getMaxDamage()){
					this.randomlyProduceChild(rand);
					this.setInventorySlotContents(0, null);
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
	public EnumHousingType getQuartersType() {
		return this.type;
	}

	@Override
	public void setQuartersType(EnumHousingType type) {
		this.type = type;
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
	
	@Override
	public int getInsectLifePercentage() {
		double percentage = this.inventory[0].getItemDamage() / (this.inventory[0].getMaxDamage() * 0.3);
		return (int)percentage > 2 ? 2 : (int)percentage;
	}
}
