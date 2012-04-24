package net.aesircraft.ManaBags.Keys;

import com.cypherx.xauth.xAuth;
import com.orange451.UltimateArena.main;
import net.aesircraft.ManaBags.Bags.*;
import net.aesircraft.ManaBags.Config.Config;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryView;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

public class WorkbenchKey implements BindingExecutionDelegate {

    @Override
    public void keyPressed(KeyBindingEvent kbe) {
    }

    @Override
    public void keyReleased(KeyBindingEvent kbe) {
	final Player p = kbe.getPlayer();
	SpoutPlayer sp = SpoutManager.getPlayer(p);
	if (kbe.getScreenType() != ScreenType.GAME_SCREEN) {
	    return;
	}
	if (sp.getGameMode() == GameMode.CREATIVE && Config.getProtectCreative()) {
	    return;
	}
	if (Config.getUseUltimateArena()) {
	    main arena = (main) Bukkit.getServer().getPluginManager().getPlugin("UltimateArena");
	    if ((arena.isInArena(p))) {
		return;
	    }
	}
	if (Config.getUsexAuth()) {
	    xAuth x = (xAuth) Bukkit.getServer().getPluginManager().getPlugin("xAuth");
	    if (!x.getPlyrMngr().getPlayer(p).isAuthenticated()) {
		return;
	    }
	}
	if (PlayerBag.disabled.contains(p))
	    return;
	ManaBench mb=new ManaBench(p);
	if (!mb.haveBench()){
	    return;
	}
	if (ChestManager.bags.containsKey(p)){
	    PlayerBag get = ChestManager.bags.get(p);
	    ChestManager.bags.remove(p);
	    get.close();
	}
	mb.open();
	
    }
}
