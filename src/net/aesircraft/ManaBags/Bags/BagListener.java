package net.aesircraft.ManaBags.Bags;

import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.Items.ManaMaterial;
import net.aesircraft.ManaBags.ManaBags;
import net.aesircraft.ManaBags.SpoutBinder;
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
	if (!Config.getEnableAutoPickup()) {
	    return;
	}
	if (e.isCancelled()) {
	    return;
	}
	Player player = (Player) e.getPlayer();
	int ctr = 36;
	for (ItemStack i : player.getInventory().getContents()) {
	    if (i != null) {
		ctr--;
	    }
	}
	if (ctr < 2) {
	    PlayerBag pb = new PlayerBag(player, 1);
	    pb.load();
	    if (pb.getType() > 0) {
		if (!pb.getStandardVirtualChest().isFull()) {
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct = 0; ct < contents.length; ct++) {
			if (contents[ct] != null) {
			    if (contents[ct].getTypeId() == e.getItem().getItemStack().getTypeId() && contents[ct].getDurability() == e.getItem().getItemStack().getDurability() && (contents[ct].getAmount() + e.getItem().getItemStack().getAmount()) <= contents[ct].getMaxStackSize()) {
				contents[ct].setAmount(contents[ct].getAmount() + e.getItem().getItemStack().getAmount());
				break;
			    }
			}
			if (contents[ct] == null) {
			    contents[ct] = e.getItem().getItemStack();
			    break;
			}
		    }
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }

	    pb = new PlayerBag(player, 2);
	    pb.load();
	    if (pb.getType() > 0) {
		if (!pb.getStandardVirtualChest().isFull()) {
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct = 0; ct < contents.length; ct++) {
			if (contents[ct] != null) {
			    if (contents[ct].getTypeId() == e.getItem().getItemStack().getTypeId() && contents[ct].getDurability() == e.getItem().getItemStack().getDurability() && (contents[ct].getAmount() + e.getItem().getItemStack().getAmount()) <= contents[ct].getMaxStackSize()) {
				contents[ct].setAmount(contents[ct].getAmount() + e.getItem().getItemStack().getAmount());
				break;
			    }
			}
			if (contents[ct] == null) {
			    contents[ct] = e.getItem().getItemStack();
			    break;
			}
		    }
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }

	    pb = new PlayerBag(player, 3);
	    pb.load();
	    if (pb.getType() > 0) {
		if (!pb.getStandardVirtualChest().isFull()) {
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct = 0; ct < contents.length; ct++) {
			if (contents[ct] != null) {
			    if (contents[ct].getTypeId() == e.getItem().getItemStack().getTypeId() && contents[ct].getDurability() == e.getItem().getItemStack().getDurability() && (contents[ct].getAmount() + e.getItem().getItemStack().getAmount()) <= contents[ct].getMaxStackSize()) {
				contents[ct].setAmount(contents[ct].getAmount() + e.getItem().getItemStack().getAmount());
				break;
			    }
			}
			if (contents[ct] == null) {
			    contents[ct] = e.getItem().getItemStack();
			    break;
			}
		    }
		    new ChestManager(pb).setInv(contents);
		    return;
		}
	    }

	    pb = new PlayerBag(player, 4);
	    pb.load();
	    if (pb.getType() > 0) {
		if (!pb.getStandardVirtualChest().isFull()) {
		    e.setCancelled(true);
		    e.getItem().remove();
		    ItemStack[] contents = pb.getStandardVirtualChest().getContents();
		    for (int ct = 0; ct < contents.length; ct++) {
			if (contents[ct] != null) {
			    if (contents[ct].getTypeId() == e.getItem().getItemStack().getTypeId() && contents[ct].getDurability() == e.getItem().getItemStack().getDurability() && (contents[ct].getAmount() + e.getItem().getItemStack().getAmount()) <= contents[ct].getMaxStackSize()) {
				contents[ct].setAmount(contents[ct].getAmount() + e.getItem().getItemStack().getAmount());
				break;
			    }
			}
			if (contents[ct] == null) {
			    contents[ct] = e.getItem().getItemStack();
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
	BagManager bm = new BagManager(player);
	bm.addPermissionBag();
	ManaBench mb = new ManaBench(player);
	mb.givePermissionBench();


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
	    i = new SpoutItemStack(SpoutBinder.getManaMaterial().manaBag, 1);
	} else {
	    a1 = false;
	}
	if (!(!Config.getEnableUpgradeSlot1() && !Config.getEnableUpgradeSlot2() && !Config.getEnableUpgradeSlot3() && !Config.getEnableUpgradeSlot4())) {
	    i2 = new SpoutItemStack(SpoutBinder.getManaMaterial().diamondWeaveUpgrade, 1);
	} else {
	    a2 = false;
	}
	if (Config.getEnableManaWorkbench()) {
	    i3 = new SpoutItemStack(SpoutBinder.getManaMaterial().manaWorkbench, 1);
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
	if (Config.getUseDiamondWorkbench()) {
	    ItemStack dw = new SpoutItemStack(ManaMaterial.diamondWorkBench, 1);
	    if (e.getPlayer().getItemInHand().getDurability() == dw.getDurability()) {
		ManaBench mb = new ManaBench(e.getPlayer());
		ItemStack r = e.getPlayer().getItemInHand();
		i.setAmount(r.getAmount() - 1);
		if (r.getAmount() == 0) {
		    e.getPlayer().getInventory().setItemInHand(null);
		} else {
		    e.getPlayer().getInventory().setItemInHand(r);
		}
		mb.giveBench();
		return;
	    }
	}
	if (a1) {
	    if (e.getPlayer().getItemInHand().getDurability() == i.getDurability()) {
		BagManager bm = new BagManager(e.getPlayer());
		bm.addBag();
		return;
	    }
	}
	if (a2) {
	    if (e.getPlayer().getItemInHand().getDurability() == i2.getDurability()) {
		BagManager bm = new BagManager(e.getPlayer());
		bm.upgradeBag();
		return;
	    }
	}
    }
}
