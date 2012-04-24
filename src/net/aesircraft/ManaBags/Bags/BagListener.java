package net.aesircraft.ManaBags.Bags;

import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.inventory.SpoutItemStack;

public class BagListener implements Listener {

    public BagListener(ManaBags plugin) {
	Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryPickup(PlayerPickupItemEvent e) {
	if (!Config.getEnableAutoPickup()){
	    return;
	}
	Player player = (Player) e.getPlayer();
	int ctr=36;
	for (ItemStack i : player.getInventory().getContents()){
	    if (i!=null){
		ctr--;
	    }
	}
	System.out.println("remaining space: "+ctr);
	if (ctr<2){
	    PlayerBag pb=new PlayerBag(player,1);
	    pb.load();
	    System.out.println("test1");
	    if (pb.getType()>0){
		System.out.println("test2");
		if (!pb.getStandardVirtualChest().isFull()){
		    System.out.println("test3");
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct=contents.length-1;ct>0;ct--){
			if (contents[ct]==null){
			    contents[ct]=e.getItem().getItemStack();
			    break;
			}
		    }		   
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }
	    
	    pb=new PlayerBag(player,2);
	    pb.load();
	    if (pb.getType()>0){
		if (!pb.getStandardVirtualChest().isFull()){
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct=contents.length-1;ct>0;ct--){
			if (contents[ct]==null){
			    contents[ct]=e.getItem().getItemStack();
			    break;
			}
		    }		   
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }
	    
	    pb=new PlayerBag(player,3);
	    pb.load();
	    if (pb.getType()>0){
		if (!pb.getStandardVirtualChest().isFull()){
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct=contents.length-1;ct>0;ct--){
			if (contents[ct]==null){
			    contents[ct]=e.getItem().getItemStack();
			    break;
			}
		    }		   
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }
	    
	    pb=new PlayerBag(player,4);
	    pb.load();
	    if (pb.getType()>0){
		if (!pb.getStandardVirtualChest().isFull()){
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct=contents.length-1;ct>0;ct--){
			if (contents[ct]==null){
			    contents[ct]=e.getItem().getItemStack();
			    break;
			}
		    }		   
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }
	   
	    
	    
	}

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryEvent(InventoryCloseEvent e) {
	Player player = (Player) e.getPlayer();
	if (e.getInventory().getTitle().contains("Magic Bag") && ChestManager.bags.containsKey(player)) {
	    ChestManager.bags.get(player).close();
	}

    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent e) {
	Player player = (Player) e.getPlayer();
	BagManager bm=new BagManager(player);
	bm.addPermissionBag();
	

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDeath(PlayerDeathEvent e) {
	DeathHandler.drop(e.getEntity(), e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onAdd(PlayerInteractEvent e) {
	if (PlayerBag.disabled.contains(e.getPlayer())) {
	    return;
	}
	if (e.getPlayer().getItemInHand() == null) {
	    return;
	}
	boolean a1 = true;
	boolean a2 = true;
	boolean a3 = true;
	ItemStack i = null;
	ItemStack i2 = null;
	ItemStack i3 = null;
	if (!(!Config.getEnableSlot1() && !Config.getEnableSlot2() && !Config.getEnableSlot3() && !Config.getEnableSlot4() && !Config.getEnableManaWorkbench())) {
	    i = new SpoutItemStack(ManaBags.getManaMaterial().manaBag, 1);
	} else {
	    a1 = false;
	}
	if (!(!Config.getEnableUpgradeSlot1() && !Config.getEnableUpgradeSlot2() && !Config.getEnableUpgradeSlot3() && !Config.getEnableUpgradeSlot4())) {
	    i2 = new SpoutItemStack(ManaBags.getManaMaterial().diamondWeaveUpgrade, 1);
	} else {
	    a2 = false;
	}
	if (Config.getEnableManaWorkbench()) {
	    i3 = new SpoutItemStack(ManaBags.getManaMaterial().manaWorkbench, 1);
	} else {
	    a3 = false;
	}

	if (a1) {
	    if (e.getPlayer().getItemInHand().getTypeId() != i.getTypeId()) {
		return;
	    }
	}
	if (a2) {
	    if (e.getPlayer().getItemInHand().getTypeId() != i2.getTypeId()) {
		return;
	    }
	}
	if (a3) {
	    if (e.getPlayer().getItemInHand().getTypeId() != i3.getTypeId()) {
		return;
	    }
	}
	if (a3) {

	    if (e.getPlayer().getItemInHand().getDurability() == i3.getDurability() && Config.getEnableManaWorkbench()) {
		if (Config.userPermissions) {
		    if (Config.getUsePermissions()) {
			if (!ManaBags.permission.has(e.getPlayer(), "manabags.user.manabench")) {
			    return;
			}
		    }
		}
		e.getPlayer().openWorkbench(null, true);
		return;
	    }
	}

	if (e.getPlayer().getGameMode() == GameMode.CREATIVE && Config.getProtectCreative()) {
	    return;
	}
        System.out.println("TEST3");
	if (a2) {
	    if (e.getPlayer().getItemInHand().getDurability() == i.getDurability()) {
		BagManager bm = new BagManager(e.getPlayer());
		bm.addBag();
		return;
	    }
	    System.out.println("TEST");
	    if (e.getPlayer().getItemInHand().getDurability() == i2.getDurability()) {
		System.out.println("TEST2");
		BagManager bm = new BagManager(e.getPlayer());
		bm.upgradeBag();
		return;
	    }
	}
    }
}
