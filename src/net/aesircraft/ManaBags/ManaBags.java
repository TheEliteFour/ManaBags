package net.aesircraft.ManaBags;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.aesircraft.ManaBags.API.ManaBagAPI;
import net.aesircraft.ManaBags.Bags.BagListener;
import net.aesircraft.ManaBags.Bags.Chests.ChestListener;
import net.aesircraft.ManaBags.Bags.WorldManager;
import net.aesircraft.ManaBags.Commands.givebag;
import net.aesircraft.ManaBags.Commands.givebagupgrade;
import net.aesircraft.ManaBags.Commands.givebench;
import net.aesircraft.ManaBags.Commands.whatisthis;
import net.aesircraft.ManaBags.Config.Config;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ManaBags extends JavaPlugin {

    private static ManaBags instance = null;
    public static final Logger logger = Logger.getLogger("Minecraft");    
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

    @Override
    public void onDisable() {
	logger.info("[ManaBag] Unloaded!");
    }

    @Override
    public void onEnable() {
	setStatic();
	PluginManager pm = getServer().getPluginManager();
	if (!pm.isPluginEnabled("Spout")){
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
	    logger.log(Level.SEVERE,"    MANABAGS - SEVERE USER ERROR!");
	    logger.log(Level.SEVERE,"=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"ManaBags is now disabling it's self.");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"This is happening because you did not");
	    logger.log(Level.SEVERE,"follow instructions and install SpoutPlugin.");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"To prevent errors like this you should");
	    logger.log(Level.SEVERE,"thoroughly read about what you are trying");
	    logger.log(Level.SEVERE,"to install, or just not install it.");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"If you do have SpoutPlugin then read");
	    logger.log(Level.SEVERE,"the rest of your log, completely,");
	    logger.log(Level.SEVERE,"because SpoutPlugin obviously disabled");
	    logger.log(Level.SEVERE,"it's self because you are not using");
	    logger.log(Level.SEVERE,"proper versions.");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"Do not post this error because I will");
	    logger.log(Level.SEVERE,"laugh at you if you do.");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"Have a nice day.");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    logger.log(Level.SEVERE,"");
	    return;
	}
	
	Config.writeDefaults();
	Config.upgrade();
	if (Config.getUsePermissions()) {
	    if (pm.isPluginEnabled("Vault")) {
		setupPermissions();
		Config.userPermissions = true;
	    }
	}
	
	SpoutBinder.bind(this);
	
	new BagListener(this);
	new ChestListener(this);
	getCommand("whatisthis").setExecutor(new whatisthis(this));
	getCommand("givebag").setExecutor(new givebag(this));
	getCommand("givebagupgrade").setExecutor(new givebagupgrade(this));
	getCommand("givebench").setExecutor(new givebench(this));
	WorldManager wm=new WorldManager();
	wm.getInventoryWorlds();
	api = new ManaBagAPI();
	logger.info("[ManaBag] Loaded " + this.getDescription().getName() + " build " + this.getDescription().getVersion() + "!");


    }
}
