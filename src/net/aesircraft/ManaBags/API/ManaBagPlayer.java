package net.aesircraft.ManaBags.API;

import net.aesircraft.ManaBags.Bags.PlayerBag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManaBagPlayer {

    private Player player;

    public ManaBagPlayer(Player player) {
	this.player = player;
    }

    public Player getPlayer() {
	return player;
    }

    public ItemStack[] getContentsofSlot1() throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 1);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public ItemStack[] getContentsofSlot2() throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 2);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public ItemStack[] getContentsofSlot3() throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 3);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public ItemStack[] getContentsofSlot4() throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 4);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	return pb.getInventory();
    }

    public void setContentsofSlot1(ItemStack[] items) throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 1);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public void setContentsofSlot2(ItemStack[] items) throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 2);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public void setContentsofSlot3(ItemStack[] items) throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 3);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public void setContentsofSlot4(ItemStack[] items) throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 4);
	if (pb.getType() == 0) {
	    throw new NoBagException();
	}
	pb.setInventory(items);
    }

    public boolean isSlot1Large() throws NoBagException {
	PlayerBag pb = new PlayerBag(player, 1);
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
	PlayerBag pb = new PlayerBag(player, 2);
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
	PlayerBag pb = new PlayerBag(player, 3);
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
	PlayerBag pb = new PlayerBag(player, 4);
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
	PlayerBag pb = new PlayerBag(player, 1);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isSlot2Equipped() {
	PlayerBag pb = new PlayerBag(player, 2);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isSlot3Equipped() {
	PlayerBag pb = new PlayerBag(player, 3);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public boolean isSlot4Equipped() {
	PlayerBag pb = new PlayerBag(player, 4);
	if (pb.getType() == 0) {
	    return true;
	}
	return false;
    }

    public void disableUse() {
	if (!PlayerBag.disabled.contains(player)) {
	    PlayerBag.disabled.add(player);
	}
    }

    public void enableUse() {
	if (PlayerBag.disabled.contains(player)) {
	    PlayerBag.disabled.remove(player);
	}
    }

    public boolean isDisabled() {
	if (PlayerBag.disabled.contains(player)) {
	    return true;
	} else {
	    return false;
	}
    }
}
