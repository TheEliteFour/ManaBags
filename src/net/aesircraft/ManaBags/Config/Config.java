package net.aesircraft.ManaBags.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

    private static boolean newFile;
    public static boolean userPermissions = false;

    private static File getFile() {
	File dir = ManaBags.getStatic().getDataFolder();
	File file = new File(dir, "config.yml");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	if (!file.exists()) {
	    try {
		file.createNewFile();
		newFile = true;
	    } catch (IOException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to write config file!");
	    }
	}
	return file;
    }

    public static void upgrade() {
	YamlConfiguration config = getYaml();
	if (config.getInt("Config-Version", 0) == 0) {
	    config.set("Config-Version", 1);
	    config.set("Use-Hard-Mode", false);
	    save(config);
	}
    }

    private static YamlConfiguration getYaml() {
	YamlConfiguration config = new YamlConfiguration();
	try {
	    config.load(getFile());
	} catch (FileNotFoundException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find config file!");
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load config file!");
	} catch (InvalidConfigurationException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] You fudged up the config file!");
	}
	if (newFile) {
	    config.set("Use-Permissions", true);
	    config.set("Use-Hard-Mode", false);
	    config.set("Enable-Slot-1", true);
	    config.set("Enable-Slot-2", true);
	    config.set("Enable-Slot-3", true);
	    config.set("Enable-Slot-4", true);
	    config.set("Enable-Upgrade-Slot-1", true);
	    config.set("Enable-Upgrade-Slot-2", true);
	    config.set("Enable-Upgrade-Slot-3", true);
	    config.set("Enable-Upgrade-Slot-4", true);
	    config.set("Enable-Mana-Workbench", true);
	    config.set("Protect-Creative", true);
	    config.set("Use-xAuth", false);
	    config.set("Use-UltimateArena", false);
	    config.set("Use-Sound-Effects", true);
	    config.set("Sound-Effects-File", "http://aesircraft.net/items/poof.ogg");
	    config.set("Mana-Dust-Texture", "http://aesircraft.net/items/manadust.png");
	    config.set("Mana-Thread-Texture", "http://aesircraft.net/items/manathread.png");
	    config.set("Mana-Cloth-Texture", "http://aesircraft.net/items/manacloth.png");
	    config.set("Mana-Bag-Texture", "http://aesircraft.net/items/manabag.png");
	    config.set("Mana-Workbench-Texture", "http://aesircraft.net/items/magicbench.png");
	    config.set("Diamond-Thread-Texture", "http://aesircraft.net/items/diamondthread.png");
	    config.set("Diamond-Weave-Texture", "http://aesircraft.net/items/diamondweave.png");
	    config.set("Diamond-Weave-Upgrade-Texture", "http://aesircraft.net/items/diamondupgrade.png");
	    config.set("Config-Version", 1);
	    save(config);
	    try {
		config.load(getFile());
	    } catch (FileNotFoundException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find config file!");
	    } catch (IOException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load config file!");
	    } catch (InvalidConfigurationException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] You fudged up the config file!");
	    }
	}
	return config;
    }

    private static void save(YamlConfiguration config) {
	try {
	    config.save(getFile());
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to save config file!");
	}
    }

    public static void writeDefaults() {
	getYaml();
    }

    public static boolean getUsePermissions() {
	if (!userPermissions) {
	    return userPermissions;
	}
	return getYaml().getBoolean("Use-Permissions", true);
    }

    public static boolean getHardMode() {
	return getYaml().getBoolean("Use-Hard-Mode", false);
    }

    public static boolean getEnableSlot1() {
	return getYaml().getBoolean("Enable-Slot-1", true);
    }

    public static boolean getEnableSlot2() {
	return getYaml().getBoolean("Enable-Slot-2", true);
    }

    public static boolean getEnableSlot3() {
	return getYaml().getBoolean("Enable-Slot-3", true);
    }

    public static boolean getEnableSlot4() {
	return getYaml().getBoolean("Enable-Slot-4", true);
    }

    public static boolean getEnableUpgradeSlot1() {
	return getYaml().getBoolean("Enable-Upgrade-Slot-1", true);
    }

    public static boolean getEnableUpgradeSlot2() {
	return getYaml().getBoolean("Enable-Upgrade-Slot-2", true);
    }

    public static boolean getEnableUpgradeSlot3() {
	return getYaml().getBoolean("Enable-Upgrade-Slot-3", true);
    }

    public static boolean getEnableUpgradeSlot4() {
	return getYaml().getBoolean("Enable-Upgrade-Slot-4", true);
    }

    public static boolean getEnableManaWorkbench() {
	return getYaml().getBoolean("Enable-Mana-Workbench", true);
    }

    public static boolean getProtectCreative() {
	return getYaml().getBoolean("Protect-Creative", true);
    }

    public static boolean getUseSoundEffects() {
	return getYaml().getBoolean("Use-Sound-Effects", true);
    }

    public static boolean getUsexAuth() {
	return getYaml().getBoolean("Use-xAuth", false);
    }

    public static boolean getUseUltimateArena() {
	return getYaml().getBoolean("Use-UltimateArena", false);
    }

    public static String getManaDustTexture() {
	return getYaml().getString("Mana-Dust-Texture", "http://aesircraft.net/items/manadust.png");
    }

    public static String getManaThreadTexture() {
	return getYaml().getString("Mana-Thread-Texture", "http://aesircraft.net/items/manathread.png");
    }

    public static String getManaClothTexture() {
	return getYaml().getString("Mana-Cloth-Texture", "http://aesircraft.net/items/manacloth.png");
    }

    public static String getManaBagTexture() {
	return getYaml().getString("Mana-Bag-Texture", "http://aesircraft.net/items/manabag.png");
    }

    public static String getManaWorkBenchTexture() {
	return getYaml().getString("Mana-Workbench-Texture", "http://aesircraft.net/items/magicbench.png");
    }

    public static String getDiamondThreadTexture() {
	return getYaml().getString("Diamond-Thread-Texture", "http://aesircraft.net/items/diamondthread.png");
    }

    public static String getDiamondWeaveTexture() {
	return getYaml().getString("Diamond-Weave-Texture", "http://aesircraft.net/items/diamondweave.png");
    }

    public static String getDiamondWeaveUpgradeTexture() {
	return getYaml().getString("Diamond-Weave-Upgrade-Texture", "http://aesircraft.net/items/diamondupgrade.png");
    }

    public static String getSoundEffectsFile() {
	return getYaml().getString("Sound-Effects-File", "http://aesircraft.net/items/poof.ogg");
    }
}
