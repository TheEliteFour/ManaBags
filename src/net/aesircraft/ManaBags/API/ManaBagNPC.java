package net.aesircraft.ManaBags.API;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.aesircraft.ManaBags.Bags.NPCBag;
import net.aesircraft.ManaBags.Bags.NPCBagManager;
import org.bukkit.inventory.ItemStack;

public class ManaBagNPC {

    private String player;

    public ManaBagNPC(String name) {
	this.player = name;
    }

    public String getName() {
	return player;
    }

    public ItemStack[] getContentsofSlot1() throws NoBagException {
	NPCBag pb = new NPCBag(player, 1);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public ItemStack[] getContentsofSlot2() throws NoBagException {
	NPCBag pb = new NPCBag(player, 2);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public ItemStack[] getContentsofSlot3() throws NoBagException {
	NPCBag pb = new NPCBag(player, 3);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public ItemStack[] getContentsofSlot4() throws NoBagException {
	NPCBag pb = new NPCBag(player, 4);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public void setContentsofSlot1(ItemStack[] items) throws NoBagException {
	NPCBag pb = new NPCBag(player, 1);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public void setContentsofSlot2(ItemStack[] items) throws NoBagException {
	NPCBag pb = new NPCBag(player, 2);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public void setContentsofSlot3(ItemStack[] items) throws NoBagException {
	NPCBag pb = new NPCBag(player, 3);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public void setContentsofSlot4(ItemStack[] items) throws NoBagException {
	NPCBag pb = new NPCBag(player, 4);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public boolean isSlot1Large() throws NoBagException {
	NPCBag pb = new NPCBag(player, 1);
	int type = pb.getType();
	if (type == 0) {
	    throw new NoBagException();
	}
	if (type == 2) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean isSlot2Large() throws NoBagException {
	NPCBag pb = new NPCBag(player, 2);
	int type = pb.getType();
	if (type == 0) {
	    throw new NoBagException();
	}
	if (type == 2) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean isSlot3Large() throws NoBagException {
	NPCBag pb = new NPCBag(player, 3);
	int type = pb.getType();
	if (type == 0) {
	    throw new NoBagException();
	}
	if (type == 2) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean isSlot4Large() throws NoBagException {
	NPCBag pb = new NPCBag(player, 4);
	int type = pb.getType();
	if (type == 0) {
	    throw new NoBagException();
	}
	if (type == 2) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean isSlot1Equipped() {
	NPCBag pb = new NPCBag(player, 1);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isSlot2Equipped() {
	NPCBag pb = new NPCBag(player, 2);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isSlot3Equipped() {
	NPCBag pb = new NPCBag(player, 3);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isSlot4Equipped() {
	NPCBag pb = new NPCBag(player, 4);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isThereFreeBagSlot() {
	if (!this.isSlot4Equipped()) {
	    return true;
	}
	return false;
    }

    public boolean isThereUpgradableBagSlot() {
	try {
	    if (!this.isSlot4Large()) {
		return true;
	    }
	} catch (NoBagException ex) {
	    return false;
	}
	return false;
    }

    public void addBag() throws NoFreeBagException {
	NPCBagManager pb = new NPCBagManager(player);
	pb.addBag();
    }

    public void upgradeBag() throws NoUpgradableBagException {
	NPCBagManager pb = new NPCBagManager(player);
	pb.upgradeBag();
    }
}
