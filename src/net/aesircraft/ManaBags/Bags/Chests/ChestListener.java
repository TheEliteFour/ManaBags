package net.aesircraft.ManaBags.Bags.Chests;

import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.getspout.spoutapi.block.SpoutBlock;

public class ChestListener implements Listener {

 
    public ChestListener(ManaBags plugin) {
	Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClose(InventoryCloseEvent e) {
	if (ManaChest.open.containsKey(e.getPlayer())) {
	    ManaChest.open.get(e.getPlayer()).save();
	    ManaChest.open.remove(e.getPlayer());
	}

    }    

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {
	SpoutBlock sb = (SpoutBlock) e.getBlock();
	if (!sb.isCustomBlock()) {
	    return;
	}
	if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
	    return;
	}
	ManaChest mc = new ManaChest(e.getBlock());
	mc.breakNaturally(e);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPiston(BlockPistonRetractEvent e) {
	SpoutBlock sb = (SpoutBlock) e.getRetractLocation().getBlock();
	if (!sb.isCustomBlock()) {
	    return;
	}
	if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
	    return;
	}
	e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPiston(BlockPistonExtendEvent e) {
	for (Block b : e.getBlocks()) {
	    SpoutBlock sb = (SpoutBlock) b;
	    if (!sb.isCustomBlock()) {
		continue;
	    }
	    if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
		continue;
	    }
	    e.setCancelled(true);
	    return;
	}
    }

    @EventHandler(priority = EventPriority.HIGHEST)    
    public void onPlace(BlockPlaceEvent e) {
	if (e.isCancelled()){
	    return;
	}
	SpoutBlock sb = (SpoutBlock) e.getBlock();
	if (!sb.isCustomBlock()) {
	    return;
	}
	if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
	    return;
	}
	ManaChest mc = new ManaChest(e.getBlock());
	FakeChest fc = mc.getFakeChest();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChange(EntityChangeBlockEvent e) {
	SpoutBlock sb = (SpoutBlock) e.getBlock();
	if (!sb.isCustomBlock()) {
	    return;
	}
	if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
	    return;
	}
	e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplode(EntityExplodeEvent e) {
	for (Block b : e.blockList()) {
	    SpoutBlock sb = (SpoutBlock) b;
	    if (!sb.isCustomBlock()) {
		continue;
	    }
	    if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
		continue;
	    }
	    e.blockList().remove(b);
	}
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent e) {
	if (e.isCancelled()) {
	    return;
	}	
	if (e.getClickedBlock() == null) {
	    return;
	}
	SpoutBlock sb = (SpoutBlock) e.getClickedBlock();
	if (!sb.isCustomBlock()) {
	    return;
	}
	if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
	    return;
	}
	if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
	    return;
	}
	e.setCancelled(true);
	ManaChest mc = new ManaChest(e.getClickedBlock());	
	mc.open(e.getPlayer());
    }
}
