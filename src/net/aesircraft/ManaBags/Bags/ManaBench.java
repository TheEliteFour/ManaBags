package net.aesircraft.ManaBags.Bags;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ManaBench {

    private Player player;

    public ManaBench(Player player) {
	this.player = player;
    }

    private File getFile() {
	File dir = new File(ManaBags.getStatic().getDataFolder() + File.separator + "bench");
	File file = new File(ManaBags.getStatic().getDataFolder() + File.separator + "bench" + File.separator + player.getName().toLowerCase() + ".lck");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	return file;
    }

    public boolean haveBench() {
	return getFile().exists();
    }

    public boolean giveBench() {
	SpoutPlayer sp = SpoutManager.getPlayer(player);
	if (haveBench()) {
	    sp.sendNotification("§4Notice", "§eAlready Equipped!", Material.WORKBENCH);
	    return false;
	}
	if (Config.userPermissions) {
	    if (!ManaBags.permission.has(player, "manabags.user.diamondbench")) {
		return false;
	    }
	}
	try {
	    getFile().createNewFile();
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.WARNING, "[ManaBags] Failed to lock Diamond Bench file!");
	    return false;
	}
	sp.sendNotification("§eSlot " + 0, "§5Now Diamond Bench!", Material.WORKBENCH);
	if (Config.getUseSoundEffects()) {
	    SpoutManager.getSoundManager().playCustomSoundEffect(ManaBags.getStatic(), sp, "http://aesircraft.net/items/poof.ogg", true);
	}
	return true;

    }

    public void givePermissionBench() {
	if (haveBench()) {
	    return;
	}
	if (Config.userPermissions) {
	    if (!ManaBags.permission.has(player, "manabags.special.diamondbench")) {
		return;
	    }
	}
	else{
	    return;
	}
	try {
	    getFile().createNewFile();
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.WARNING, "[ManaBags] Failed to lock Diamond Bench file!");
	    return;
	}
    }

    public void open() {
	player.openWorkbench(null, true);
    }
}
