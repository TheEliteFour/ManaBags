package net.aesircraft.ManaBags.Bags;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerBag {

    private Player player;
    private int id;
    private int type;
    private boolean big = false;
    private VirtualChest small;
    private VirtualLargeChest large;
    private boolean attach = false;
    
    public static List<Player> disabled=new ArrayList<Player>();

    public PlayerBag(Player p, int id) {
	this.player = p;
	this.id = id;
	ChestManager c = new ChestManager(this);
	c.load();
    }
    
    public VirtualChest getStandardVirtualChest(){
	if (big){
	    return large;
	}
	else{
	    return small;
	}
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
    
    public void load(){
	ChestManager c = new ChestManager(this);
	c.loadInv();
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
    
    public ItemStack[] getInventory() {
	ChestManager c = new ChestManager(this);
	net.minecraft.server.ItemStack[] i2 =c.retrieveInv();
	ItemStack[] r=new ItemStack[i2.length];
	int ctr=0;
	for (net.minecraft.server.ItemStack i : i2){
	    if (i==null){
		r[ctr]=null;
		continue;
	    }
	    int s=i.getData();
	    r[ctr]=new ItemStack(i.id,i.count,(short)s);
	    ctr++;
	}
	return r;
    }

    public void setInventory(ItemStack[] i) {
	ChestManager c = new ChestManager(this);
	c.setInv(i);
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
