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
	ItemStack i = new SpoutItemStack(ManaBags.getManaMaterial().manaBag, 1);
	ItemStack i2 = new SpoutItemStack(ManaBags.getManaMaterial().diamondWeaveUpgrade, 1);
	ItemStack i3 = new SpoutItemStack(ManaBags.getManaMaterial().manaWorkbench, 1);
	if (e.getPlayer().getItemInHand().getTypeId() != i.getTypeId()) {
	    return;
	}
	if (e.getPlayer().getItemInHand().getDurability() == i3.getDurability() && Config.getEnableManaWorkbench()) {
	    e.getPlayer().openWorkbench(null, true);
	    return;
	}
	if (e.getPlayer().getGameMode() == GameMode.CREATIVE && Config.getProtectCreative()) {
	    return;
	}
	if (e.getPlayer().getItemInHand().getDurability() == i.getDurability()) {
	    BagManager bm = new BagManager(e.getPlayer());
	    bm.addBag();
	    return;
	}
	if (e.getPlayer().getItemInHand().getDurability() == i2.getDurability()) {
	    BagManager bm = new BagManager(e.getPlayer());
	    bm.upgradeBag();
	    return;
	}
    }
}
