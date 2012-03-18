package net.aesircraft.ManaBags.Bags;

import org.bukkit.entity.Player;

public class PlayerBag {

    private Player player;
    private int id;
    private int type;
    private boolean big = false;
    private VirtualChest small;
    private VirtualLargeChest large;
    private boolean attach = false;

    public PlayerBag(Player p, int id) {
	this.player = p;
	this.id = id;
	ChestManager c = new ChestManager(this);
	c.load();
    }

    public void setVirtualLargeChest(VirtualLargeChest chest) {
	large = chest;
    }

    public void setVirtualChest(VirtualChest chest) {
	small = chest;
    }

    public VirtualChest getVirtualChest() {
	return small;
    }

    public VirtualLargeChest getVirtualLargeChest() {
	return large;
    }

    public int getId() {
	return id;
    }

    public int getType() {
	return type;
    }

    public boolean getAttach() {
	return attach;
    }

    public void setType(int type) {
	ChestManager c = new ChestManager(this);
	if (type == 1) {
	    big = false;
	} else if (type == 2) {
	    big = true;
	}
	this.type = type;
    }

    public void upgradeType(int type) {
	ChestManager c = new ChestManager(this);
	if (type == 1) {
	    attach = true;
	} else {
	    c.loadInv();
	}
	this.type = type;
	c.save();
    }

    public void open() {
	ChestManager c = new ChestManager(this);
	c.loadInv();
	if (big) {
	    large.openChest(player);
	} else {
	    small.openChest(player);
	}
	ChestManager.bags.put(player, this);
    }

    public void close() {
	ChestManager c = new ChestManager(this);
	ChestManager.bags.remove(player);
	c.save();
    }

    public Player getPlayer() {
	return player;
    }

    public boolean getLarge() {
	return big;
    }
}
