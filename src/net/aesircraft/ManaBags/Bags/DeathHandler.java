package net.aesircraft.ManaBags.Bags;

import com.cypherx.xauth.xAuth;
import com.orange451.UltimateArena.main;
import java.util.ArrayList;
import java.util.List;
import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathHandler {

    public static void drop(Player player, PlayerDeathEvent e) {


	if (Config.getUseUltimateArena()) {
	    main arena = (main) Bukkit.getServer().getPluginManager().getPlugin("UltimateArena");
	    if ((arena.isInArena(player))) {
		return;
	    }
	}
	if (Config.getUsexAuth()) {
	    xAuth x = (xAuth) Bukkit.getServer().getPluginManager().getPlugin("xAuth");
	    if (!x.getPlyrMngr().getPlayer(player).isAuthenticated()) {
		return;
	    }
	}
	if (PlayerBag.disabled.contains(player))
	    return;
	PlayerBag pb;
	List<ItemStack[]> l = new ArrayList<ItemStack[]>();
	if (Config.userPermissions) {
	    if (Config.getUsePermissions()) {
		if (!ManaBags.permission.has(player, "manabags.user.nodrop.slot.1")) {
		    pb = new PlayerBag(player, 1);
		    if (pb.getType() > 0 && Config.getDeathDropSlotSlot1()) {
			l.add(pb.getInventory());
			pb.setInventory(new ItemStack[1]);
		    }
		}

	    }
	} else {
	    pb = new PlayerBag(player, 1);
	    if (pb.getType() > 0 && Config.getDeathDropSlotSlot1()) {
		l.add(pb.getInventory());
		pb.setInventory(new ItemStack[1]);
	    }
	}
	if (Config.userPermissions) {
	    if (Config.getUsePermissions()) {
		if (!ManaBags.permission.has(player, "manabags.user.nodrop.slot.2")) {
		    pb = new PlayerBag(player, 2);
		    if (pb.getType() > 0 && Config.getDeathDropSlotSlot2()) {
			l.add(pb.getInventory());
			pb.setInventory(new ItemStack[1]);
		    }
		}

	    }
	} else {	    
	    pb = new PlayerBag(player, 2);
	    if (pb.getType() > 0 && Config.getDeathDropSlotSlot2()) {
		l.add(pb.getInventory());
		pb.setInventory(new ItemStack[1]);
	    }
	}
	if (Config.userPermissions) {
	    if (Config.getUsePermissions()) {
		if (!ManaBags.permission.has(player, "manabags.user.nodrop.slot.3")) {
		    pb = new PlayerBag(player, 3);
		    if (pb.getType() > 0 && Config.getDeathDropSlotSlot3()) {
			l.add(pb.getInventory());
			pb.setInventory(new ItemStack[1]);
		    }
		}

	    }
	} else {
	    pb = new PlayerBag(player, 3);
	    if (pb.getType() > 0 && Config.getDeathDropSlotSlot3()) {
		l.add(pb.getInventory());
		pb.setInventory(new ItemStack[1]);
	    }
	}
	if (Config.userPermissions) {
	    if (Config.getUsePermissions()) {
		if (!ManaBags.permission.has(player, "manabags.user.nodrop.slot.4")) {
		    pb = new PlayerBag(player, 4);
		    if (pb.getType() > 0 && Config.getDeathDropSlotSlot4()) {
			l.add(pb.getInventory());
			pb.setInventory(new ItemStack[1]);
		    }
		}

	    }
	} else {
	    pb = new PlayerBag(player, 4);
	    if (pb.getType() > 0 && Config.getDeathDropSlotSlot4()) {
		l.add(pb.getInventory());
		pb.setInventory(new ItemStack[1]);
	    }
	}
	if (l.size() > 0) {
	    for (ItemStack[] i : l) {
		for (ItemStack ea : i) {
		    e.getDrops().add(ea);
		}
	    }
	}






    }
}
