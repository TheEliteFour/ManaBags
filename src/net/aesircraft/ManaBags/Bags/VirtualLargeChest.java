/*This file is part of GiftPost .

 GiftPost is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 GiftPost is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with GiftPost.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.aesircraft.ManaBags.Bags;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.ItemStack;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class VirtualLargeChest extends VirtualChest {

    protected TileEntityVirtualChest subChest2;
    protected InventoryLargeChest lc;

    public VirtualLargeChest(String chestName) {
	super(chestName);
	subChest2 = new TileEntityVirtualChest();
	subChest2.setName(chestName);
	lc = new InventoryLargeChest(chestName, chest, subChest2);
    }

    public VirtualLargeChest(VirtualLargeChest v) {
	this(v.getName());
	this.addItemStack(v.getMcContents());
    }

    public VirtualLargeChest(VirtualChest v) {
	super(v);
	this.subChest2 = new TileEntityVirtualChest();
	lc = new InventoryLargeChest(chest.getName(), chest, subChest2);
    }

    /**
     * Open the chest for the owner
     */
    @Override
    public void openChest(Player p) {
	EntityPlayer eh = ((CraftPlayer) p).getHandle();
	eh.openContainer(lc);
    }

    /**
     * adding a ItemStack to the chest
     *
     * @param is
     * @return
     */
    @Override
    public boolean addItemStack(ItemStack is) {
	if (isFull()) {
	    return false;
	}
	if (!super.addItemStack(is)) {
	    return subChest2.addItemStack(is);
	}
	return true;
    }

    /**
     * Craftbukkit ItemStack
     *
     * @param index
     * @return
     */
    @Override
    public org.bukkit.inventory.ItemStack getItem(int index) {
	return new CraftItemStack(lc.getItem(index));
    }

    /**
     * Transform every item to a craftbukkit item
     *
     * @return
     */
    @Override
    public org.bukkit.inventory.ItemStack[] getContents() {
	org.bukkit.inventory.ItemStack[] items = new org.bukkit.inventory.ItemStack[lc.getSize()];
	net.minecraft.server.ItemStack[] mcItems = lc.getContents();

	for (int i = 0; i < mcItems.length; i++) {
	    items[i] = mcItems[i] == null ? null : new CraftItemStack(mcItems[i]);
	}

	return items;
    }

    /**
     * is Chest Full
     *
     * @return
     */
    @Override
    public boolean isFull() {
	return super.isFull() && subChest2.isFull();
    }

    /**
     * return the firstIndex where the case is empty.
     *
     * @return
     */
    @Override
    protected int firstEmpty() {
	int firstFree = super.firstEmpty();
	if (firstFree == -1) {
	    return subChest2.firstFree() + chest.size();
	} else {
	    return firstFree;
	}
    }

    @Override
    protected int getMaxItemStack() {
	return lc.getMaxStackSize();
    }

    /**
     * is Chest Empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
	return super.isEmpty() && subChest2.isEmpty();
    }

    /**
     * Number of used Cases
     *
     * @return
     */
    @Override
    public int usedCases() {
	return (chest.size() + subChest2.size()) - leftCases();
    }

    /**
     * Empty chests
     */
    @Override
    public void emptyChest() {
	super.emptyChest();
	subChest2.emptyChest();
    }

    /**
     * Nb of empty cases left
     *
     * @return
     */
    @Override
    public int leftCases() {
	return chest.emptyCasesLeft() + subChest2.emptyCasesLeft();
    }

    /**
     * get all the itemStacks that compose the chest
     *
     * @return
     */
    @Override
    public ItemStack[] getMcContents() {
	return lc.getContents();
    }

    @Override
    public boolean removeItemStack(ItemStack is) {
	if (!super.removeItemStack(is)) {
	    for (int i = 0; i < subChest2.getContents().length; i++) {
		if (subChest2.getContents()[i].equals(is)) {
		    subChest2.removeItemStack(i);
		    return true;
		}
	    }
	    return false;
	}
	return true;
    }

    @Override
    public void removeItemStack(int i) {
	if (i > chest.getSize()) {
	    subChest2.removeItemStack(i - chest.getSize());
	} else {
	    super.removeItemStack(i);
	}
    }

    /**
     * Return the itemStack
     *
     * @param i
     * @return
     */
    @Override
    public ItemStack getItemStack(int i) {
	return lc.getItem(i);
    }

    @Override
    public void setName(String name) {
	lc = new InventoryLargeChest(name, chest, subChest2);
    }

    /**
     * Set the itemStack
     *
     * @param i
     * @param is
     */
    @Override
    public void setItemStack(int i, ItemStack is) {
	lc.setItem(i, is);
    }

    @Override
    public VirtualLargeChest clone() {
	try {
	    VirtualLargeChest result = (VirtualLargeChest) super.clone();
	    return result;
	} catch (Exception e) {
	    throw new AssertionError();
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aranai.virtualchest.VirtualChest#getType()
     */
    @Override
    public ChestType getType() {
	return ChestType.LARGE;
    }
}
