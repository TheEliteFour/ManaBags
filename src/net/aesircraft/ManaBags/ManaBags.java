package net.aesircraft.ManaBags;

import java.util.logging.Logger;
import net.aesircraft.ManaBags.API.ManaBagAPI;
import net.aesircraft.ManaBags.Bags.BagListener;
import net.aesircraft.ManaBags.Commands.givebag;
import net.aesircraft.ManaBags.Commands.givebagupgrade;
import net.aesircraft.ManaBags.Commands.whatisthis;
import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.Items.ManaMaterial;
import net.aesircraft.ManaBags.Keys.Bag1Key;
import net.aesircraft.ManaBags.Keys.Bag2Key;
import net.aesircraft.ManaBags.Keys.Bag3Key;
import net.aesircraft.ManaBags.Keys.Bag4Key;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.KeyBindingManager;
import org.getspout.spoutapi.keyboard.Keyboard;

public class ManaBags extends JavaPlugin {

    private static ManaBags instance = null;
    public static final Logger logger = Logger.getLogger("Minecraft");
    private static ManaMaterial manaMaterial;
    public static Permission permission = null;
    private static ManaBagAPI api;

    public static ManaBagAPI getManaBagAPI() {
	return api;
    }

    private Boolean setupPermissions() {
	RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	if (permissionProvider != null) {
	    permission = permissionProvider.getProvider();
	}
	return (permission != null);
    }

    public static ManaBags getStatic() {
	return instance;
    }

    private void setStatic() {
	instance = this;
    }

    public static ManaMaterial getManaMaterial() {
	return manaMaterial;
    }

    @Override
    public void onDisable() {
	logger.info("[ManaBag] Unloaded!");
    }

    @Override
    public void onEnable() {
	setStatic();
	PluginManager pm = getServer().getPluginManager();
	Config.writeDefaults();
	Config.upgrade();
	if (Config.getUsePermissions()) {
	    if (pm.isPluginEnabled("Vault")) {
		setupPermissions();
		Config.userPermissions = true;
	    }
	}
	manaMaterial = new ManaMaterial(this);
	KeyBindingManager kbm = SpoutManager.getKeyBindingManager();
	kbm.registerBinding("Bag.1", Keyboard.KEY_NUMPAD1, "Open Bag 1", new Bag1Key(), this);
	kbm.registerBinding("Bag.2", Keyboard.KEY_NUMPAD2, "Open Bag 2", new Bag2Key(), this);
	kbm.registerBinding("Bag.3", Keyboard.KEY_NUMPAD3, "Open Bag 3", new Bag3Key(), this);
	kbm.registerBinding("Bag.4", Keyboard.KEY_NUMPAD4, "Open Bag 4", new Bag4Key(), this);
	new BagListener(this);
	getCommand("whatisthis").setExecutor(new whatisthis(this));
	getCommand("givebag").setExecutor(new givebag(this));
	getCommand("givebagupgrade").setExecutor(new givebagupgrade(this));
	api = new ManaBagAPI();
	logger.info("[ManaBag] Loaded " + this.getDescription().getName() + " build " + this.getDescription().getVersion() + "!");


    }
}
