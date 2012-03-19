package net.aesircraft.ManaBags.Bags;

import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.inventory.InventoryCloseEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;

public class BagListener implements Listener {

    public BagListener(ManaBags plugin) {
	Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryEvent(InventoryCloseEvent e) {
	Player player = e.getPlayer();
	if (e.getInventory().getTitle().contains("Magic Bag")) {
	    ChestManager.bags.get(player).close();
	}

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAdd(PlayerInteractEvent e) {
	if (e.isCancelled()) {
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
		e.getPlayer().openWorkbench(null, true);
		return;
	    }
	}

	if (e.getPlayer().getGameMode() == GameMode.CREATIVE && Config.getProtectCreative()) {
	    return;
	}

	if (a2) {
	    if (e.getPlayer().getItemInHand().getDurability() == i.getDurability()) {
		BagManager bm = new BagManager(e.getPlayer());
		bm.addBag();
		return;
	    }
	}
	if (a3) {
	    if (e.getPlayer().getItemInHand().getDurability() == i2.getDurability()) {
		BagManager bm = new BagManager(e.getPlayer());
		bm.upgradeBag();
		return;
	    }
	}
    }
}
