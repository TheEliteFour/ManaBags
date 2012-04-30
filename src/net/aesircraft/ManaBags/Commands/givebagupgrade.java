package net.aesircraft.ManaBags.Commands;

import net.aesircraft.ManaBags.Bags.BagManager;
import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class givebagupgrade implements CommandExecutor {

    public static ManaBags plugin;

    public givebagupgrade(ManaBags instance) {
	plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String cmd,
	    String[] comA) {

	if (player instanceof Player) {
	    if (Config.userPermissions) {
		if (!ManaBags.permission.has(player, "manabags.admin") && !player.isOp()) {
		    player.sendMessage("§4You do not have permission to do that!");
		    return true;
		}
	    }
	    else{
		if (!player.isOp()){
		    player.sendMessage("§4You do not have permission to do that!");
		    return true;
		}
	    }
	}

	if (comA.length < 1) {
	    return false;
	}
	Player p = Bukkit.getPlayerExact(comA[0]);
	if (p == null) {
	    player.sendMessage("§4The player needs to be online!");
	    return true;
	}

	BagManager bm = new BagManager(p);
	if (bm.addCommandUpgrade()) {
	    player.sendMessage("§2Successfully gave §b" + p.getName() + "§2 an upgrade!");
	} else {
	    player.sendMessage("§4Could not give §b" + p.getName() + "§4 an upgrade!");
	}
	return true;
    }
}
