package net.aesircraft.ManaBags.Bags;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NPCBag {

    private String player;
    private int id;
    private int type;
    private boolean big = false;
    private VirtualChest small;
    private VirtualLargeChest large;
    private boolean attach = false;

    public NPCBag(String p, int id) {
	this.player = p;
	this.id = id;
	NPCChestManager c = new NPCChestManager(this);
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
	NPCChestManager c = new NPCChestManager(this);
	if (type == 1) {
	    big = false;
	} else if (type == 2) {
	    big = true;
	}
	this.type = type;
    }

    public void upgradeType(int type) {
	NPCChestManager c = new NPCChestManager(this);
	if (type == 1) {
	    attach = true;
	} else {
	    c.loadInv();
	}
	this.type = type;
	c.save();
    }

    public void open(Player player) {
	NPCChestManager c = new NPCChestManager(this);
	c.loadInv();
	if (big) {
	    large.openChest(player);
	} else {
	    small.openChest(player);
	}
	NPCChestManager.bags.put(player, this);
    }

    public ItemStack[] getInventory() {
	NPCChestManager c = new NPCChestManager(this);
	net.minecraft.server.ItemStack[] i2 = c.retrieveInv();
	ItemStack[] r = new ItemStack[i2.length];
	int ctr = 0;
	for (net.minecraft.server.ItemStack i : i2) {
	    if (i == null) {
		r[ctr] = null;
		continue;
	    }
	    int s = i.getData();
	    r[ctr] = new ItemStack(i.id, i.count, (short) s);
	    ctr++;
	}
	return r;
    }

    public void setInventory(ItemStack[] i) {
	NPCChestManager c = new NPCChestManager(this);
	c.setInv(i);
    }

    public void close() {
	NPCChestManager c = new NPCChestManager(this);
	NPCChestManager.bags.remove(player);
	c.save();
    }

    public String getName() {
	return player;
    }

    public boolean getLarge() {
	return big;
    }
}
