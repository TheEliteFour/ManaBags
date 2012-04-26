package net.aesircraft.ManaBags.Bags.Chests;

import net.aesircraft.ManaBags.Items.ManaMaterial;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;

public class ChestListener implements Listener {

    public static boolean locked = false;

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
	if (locked == true) {
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
	locked = true;
	mc.breakNaturally(e);
	locked = false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBurn(BlockBurnEvent e) {
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
    public void onDamage(BlockDamageEvent e) {
	SpoutBlock sb = (SpoutBlock) e.getBlock();
	if (!sb.isCustomBlock()) {
	    return;
	}
	if (!sb.getCustomBlock().getFullName().toLowerCase().equals("manabags.mana chamber")) {
	    return;
	}
	//e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIgnite(BlockIgniteEvent e) {
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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e) {
	if (locked == true) {
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
	BlockPlaceEvent ev = new BlockPlaceEvent(fc, e.getBlockReplacedState(), e.getBlockAgainst(), new ItemStack(Material.CHEST, 1), e.getPlayer(), e.canBuild());
	locked = true;
	Bukkit.getPluginManager().callEvent(ev);
	locked = false;

	e.setCancelled(ev.isCancelled());
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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent e) {
	if (locked == true) {
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
	ManaChest mc = new ManaChest(e.getClickedBlock());
	PlayerInteractEvent ev = new PlayerInteractEvent(e.getPlayer(), e.getAction(), e.getPlayer().getItemInHand(), mc.getFakeChest(), e.getBlockFace());
	locked = true;
	//Bukkit.getPluginManager().callEvent(ev);
	locked = false;
	if (ev.isCancelled()) {
	    return;
	}
	if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
	    return;
	}
	mc.open(e.getPlayer());
    }
}
