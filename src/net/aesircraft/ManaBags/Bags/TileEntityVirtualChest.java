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

import java.util.ArrayDeque;
import java.util.Queue;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityChest;

public class TileEntityVirtualChest extends TileEntityChest {

	protected String name = "Chest";
	protected Queue<Integer> emptyCases;

	TileEntityVirtualChest() {
		super();
		initEmptyCases();
	}

	private void initEmptyCases() {
		emptyCases = new ArrayDeque<Integer>(getSize());
		for (int i = 0; i < getSize(); i++)
			emptyCases.add(i);
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return if the chest is full
	 * 
	 * @return
	 */
	public boolean isFull() {
		return emptyCases.isEmpty();
	}

	/**
	 * Return if the chest is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return emptyCases.size() == getSize();
	}

	/**
	 * return the number of emptyCases
	 * 
	 * @return
	 */
	public int emptyCasesLeft() {
		return emptyCases.size();
	}

	/**
	 * Alias to q_()
	 * 
	 * @return
	 */
	public int size() {
		return getSize();
	}

	/**
	 * Look for the first empty case in the chest to add the stack.
	 * 
	 * @param itemstack
	 * @return
	 */
	public boolean addItemStack(ItemStack itemstack) {
		Integer i = emptyCases.poll();
		if (i == null)
			return false;
		else {
			super.setItem(i, itemstack);
			return true;
		}
	}

	public int firstFree() {
		Integer firstFree = emptyCases.poll();
		return firstFree == null ? -1 : firstFree;
	}

	@Override
	public void setItem(int i, ItemStack itemstack) {
		if (i >= 0 && i < getSize()) {
			if (itemstack != null)
				emptyCases.remove(i);
			else
				emptyCases.add(i);
			super.setItem(i, itemstack);
		}
	}

	public void emptyChest() {
		for (int i = 0; i < this.getContents().length; i++)
			this.getContents()[i] = null;
		initEmptyCases();
	}

	@Override
	public ItemStack splitStack(int i, int j) {
		ItemStack toReturn = super.splitStack(i, j);
		if (toReturn != null) {
			ItemStack afterSuper[] = this.getContents();
			if (afterSuper[i] == null)
				emptyCases.add(i);
		}

		return toReturn;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public ItemStack a(int i, int j) {
		if (this.getContents()[i] != null) {
			ItemStack itemstack;

			if (this.getContents()[i].count <= j) {
				itemstack = this.getContents()[i];
				this.getContents()[i] = null;
				emptyCases.add(i);
				this.update();
				return itemstack;
			} else {
				itemstack = this.getContents()[i].a(j);
				if (this.getContents()[i].count == 0) {
					this.getContents()[i] = null;
					emptyCases.add(i);
				}
				this.update();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public void removeItemStack(int i) {
		this.setItem(i, null);
	}

	@Override
	public String getName() {
		return name;
	}

	public boolean a_(EntityHuman entityhuman) {
		/*
		 * For this proof of concept, we ALWAYS validate the chest. This
		 * behavior has not been thoroughly tested, and may cause unexpected
		 * results depending on the state of the player.
		 * 
		 * Depending on your purposes, you might want to change this. It would
		 * likely be preferable to enforce your business logic outside of this
		 * file instead, however.
		 */
		return true;
	}

	@Override
	public boolean a(EntityHuman entityhuman) {
		/*
		 * For this proof of concept, we ALWAYS validate the chest. This
		 * behavior has not been thoroughly tested, and may cause unexpected
		 * results depending on the state of the player.
		 * 
		 * Depending on your purposes, you might want to change this. It would
		 * likely be preferable to enforce your business logic outside of this
		 * file instead, however.
		 */
		return true;
	}

	public void e() {
		++this.h;
		// this.world.playNote(this.x, this.y, this.z, 1, this.h);
	}

	public void t_() {
		--this.h;
		// this.world.playNote(this.x, this.y, this.z, 1, this.h);
	}
}