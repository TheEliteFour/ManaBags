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

import java.util.HashMap;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemStack;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

public class VirtualChest implements Cloneable {

    protected TileEntityVirtualChest chest;

    /**
     * Constructor
     *
     * @param chestName
     */
    public VirtualChest(String chestName) {
	chest = new TileEntityVirtualChest();
	chest.setName(chestName);
    }

    public VirtualChest(VirtualChest v) {
	this.chest = v.chest;
    }

    /**
     * Open the chest for the owner
     */
    public void openChest(Player p) {

	EntityPlayer eh = ((CraftPlayer) p).getHandle();
	eh.openContainer(chest);
    }

    /**
     * Add some ItemStack to the chest
     *
     * @param iss
     */
    public void addItemStack(ItemStack[] iss) {
	addItemStack(iss, false);
    }

    /**
     * If we want to create new itemStacks and add it.
     *
     * @param iss
     * @param create
     */
    public void addItemStack(ItemStack[] iss, boolean create) {
	if (!create) {
	    for (ItemStack is : iss) {
		if (is != null) {
		    addItemStack(is);
		}
	    }
	} else {
	    for (ItemStack is : iss) {
		if (is != null) {
		    addItemStack(is);
		}
	    }
	}
    }

    /**
     * adding a ItemStack to the chest
     *
     * @param is
     * @return
     */
    public boolean addItemStack(ItemStack is) {
	if (isFull()) {
	    return false;
	}
	return chest.addItemStack(is);
    }

    /**
     * Empty chest
     */
    public void emptyChest() {
	chest.emptyChest();
    }

    /**
     * is Chest Full
     *
     * @return
     */
    public boolean isFull() {
	return chest.isFull();
    }

    /**
     * is Chest Empty
     *
     * @return
     */
    public boolean isEmpty() {
	return chest.isEmpty();
    }

    /**
     * Nb of empty cases left
     *
     * @return
     */
    public int leftCases() {
	return chest.emptyCasesLeft();
    }

    /**
     * Nb of used Cases
     *
     * @return
     */
    public int usedCases() {
	return chest.size() - chest.emptyCasesLeft();
    }

    /**
     * get all the itemStacks that compose the chest
     *
     * @return
     */
    public ItemStack[] getMcContents() {
	return chest.getContents();
    }

    // CraftBukkit Code
    protected int firstPartial(int materialId) {
	org.bukkit.inventory.ItemStack[] inventory = getContents();
	for (int i = 0; i < inventory.length; i++) {
	    org.bukkit.inventory.ItemStack item = inventory[i];
	    if (item != null && item.getTypeId() == materialId
		    && item.getAmount() < item.getMaxStackSize()) {
		return i;
	    }
	}
	return -1;
    }

    protected int firstPartial(Material material) {
	return firstPartial(material.getId());
    }

    protected int firstPartial(org.bukkit.inventory.ItemStack item) {
	org.bukkit.inventory.ItemStack[] inventory = getContents();
	if (item == null) {
	    return -1;
	}
	for (int i = 0; i < inventory.length; i++) {
	    org.bukkit.inventory.ItemStack cItem = inventory[i];
	    if (cItem != null && cItem.getTypeId() == item.getTypeId()
		    && cItem.getAmount() < cItem.getMaxStackSize()
		    && cItem.getDurability() == item.getDurability()) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * return the firstIndex where the case is empty.
     *
     * @return
     */
    protected int firstEmpty() {
	return chest.firstFree();
    }

    protected int getMaxItemStack() {
	return chest.getMaxStackSize();
    }

    /**
     * Set the index to the chosen Bukkit ItemStack
     *
     * @param index
     * @param item
     */
    public void setItem(int index, org.bukkit.inventory.ItemStack item) {
	setItemStack(index, (item == null ? null : CraftItemStack.createNMSItemStack(item)));
    }

    /**
     * Add an Bukkit ItemStack to the virtual chest
     *
     * @param items
     * @return
     */
    public HashMap<Integer, org.bukkit.inventory.ItemStack> addItem(
	    org.bukkit.inventory.ItemStack... items) {
	HashMap<Integer, org.bukkit.inventory.ItemStack> leftover = new HashMap<Integer, org.bukkit.inventory.ItemStack>();

	/*
	 * TODO: some optimization - Create a 'firstPartial' with a 'fromIndex'
	 * - Record the lastPartial per Material
	 */

	for (int i = 0; i < items.length; i++) {
	    org.bukkit.inventory.ItemStack item = items[i];
	    while (true) {
		// Do we already have a stack of it?
		int firstPartial = firstPartial(item);

		// Drat! no partial stack
		if (firstPartial == -1) {
		    // Find a free spot!
		    int firstFree = firstEmpty();

		    if (firstFree == -1) {
			// No space at all!
			leftover.put(i, item);
			break;
		    } else {
			// More than a single stack!
			if (item.getAmount() > getMaxItemStack()) {
			    setItem(firstFree, new CraftItemStack(item));
			    item.setAmount(item.getAmount() - getMaxItemStack());
			} else {
			    // Just store it
			    setItem(firstFree, item);
			    break;
			}
		    }
		} else {
		    // So, apparently it might only partially fit, well lets do
		    // just that
		    org.bukkit.inventory.ItemStack partialItem = getItem(firstPartial);

		    int amount = item.getAmount();
		    int partialAmount = partialItem.getAmount();
		    int maxAmount = partialItem.getMaxStackSize();

		    // Check if it fully fits
		    if (amount + partialAmount <= maxAmount) {
			partialItem.setAmount(amount + partialAmount);
			break;
		    }

		    // It fits partially
		    partialItem.setAmount(maxAmount);
		    item.setAmount(amount + partialAmount - maxAmount);
		}
	    }
	}
	return leftover;
    }

    public void remove(int materialId) {
	org.bukkit.inventory.ItemStack[] items = getContents();
	for (int i = 0; i < items.length; i++) {
	    if (items[i] != null && items[i].getTypeId() == materialId) {
		removeItemStack(i);
	    }
	}
    }

    public void remove(Material material) {
	remove(material.getId());
    }

    /**
     * Remove an Bukkit ItemStack from the VirtualChest
     *
     * @param item
     */
    public void remove(ItemStack item) {
	org.bukkit.inventory.ItemStack[] items = getContents();
	for (int i = 0; i < items.length; i++) {
	    if (items[i] != null && items[i].equals(item)) {
		removeItemStack(i);
	    }
	}
    }

    /**
     * Craftbukkit ItemStack
     *
     * @param index
     * @return
     */
    public org.bukkit.inventory.ItemStack getItem(int index) {
	return new CraftItemStack(chest.getItem(index));
    }

    /**
     * Transform every item to a craftbukkit item
     *
     * @return
     */
    public org.bukkit.inventory.ItemStack[] getContents() {
	org.bukkit.inventory.ItemStack[] items = new org.bukkit.inventory.ItemStack[chest.getSize()];
	net.minecraft.server.ItemStack[] mcItems = chest.getContents();

	for (int i = 0; i < mcItems.length; i++) {
	    items[i] = mcItems[i] == null ? null : new CraftItemStack(mcItems[i]);
	}

	return items;
    }

    // End of CraftBukkit Code
    /**
     * Search for a given itemStack and remove it.
     *
     * @param is
     */
    public boolean removeItemStack(ItemStack is) {
	for (int i = 0; i < this.getMcContents().length; i++) {
	    if (this.getMcContents()[i].equals(is)) {
		chest.removeItemStack(i);
		return true;
	    }
	}
	return false;
    }

    public void removeItemStack(int i) {
	chest.removeItemStack(i);
    }

    /**
     * Return the itemStack
     *
     * @param i
     * @return
     */
    public ItemStack getItemStack(int i) {
	return chest.getItem(i);
    }

    /**
     * Set a given itemStack
     *
     * @param i
     * @param is
     */
    public void setItemStack(int i, ItemStack is) {
	chest.setItem(i, is);
    }

    /**
     * Swap 2 items stacks
     *
     * @param from
     * @param to
     */
    public void swapItemStack(int from, int to) {
	ItemStack first = getItemStack(from);
	ItemStack second = getItemStack(to);
	setItemStack(from, second);
	setItemStack(to, first);
    }

    public String getName() {
	return this.chest.getName();
    }

    public void setName(String name) {
	this.chest.setName(name);
    }

    @Override
    public VirtualChest clone() {
	try {
	    VirtualChest result = (VirtualChest) super.clone();
	    return result;
	} catch (CloneNotSupportedException e) {
	    throw new AssertionError();
	}
    }

    public ChestType getType() {
	return ChestType.NORMAL;
    }
}