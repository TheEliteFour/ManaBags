package net.aesircraft.ManaBags.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	    List<String> s = new ArrayList<String>();
	    s.add("35-5 35-4 35-3");
	    s.add("35-5 35-4 35-3");
	    s.add("35-5 35-4 35-3");
	    List<String> s2 = new ArrayList<String>();
	    s2.add("35-5 35-4 35-3");
	    s2.add("35-5 35-4 35-3");
	    s2.add("35-5 35-4 35-3");
	    List<String> s3 = new ArrayList<String>();
	    s3.add("35-5 35-4 35-3");
	    s3.add("35-5 35-4 35-3");
	    s3.add("35-5 35-4 35-3");
	    List<String> s4 = new ArrayList<String>();
	    s4.add("35-5 35-4 35-3");
	    s4.add("35-5 35-4 35-3");
	    s4.add("35-5 35-4 35-3");
	    List<String> s5 = new ArrayList<String>();
	    s5.add("35-5 35-4 35-3");
	    s5.add("35-5 35-4 35-3");
	    s5.add("35-5 35-4 35-3");
	    List<String> s6 = new ArrayList<String>();
	    s6.add("35-5 35-4 35-3");
	    s6.add("35-5 35-4 35-3");
	    s6.add("35-5 35-4 35-3");
	    List<String> s7 = new ArrayList<String>();
	    s7.add("35-5 35-4 35-3");
	    s7.add("35-5 35-4 35-3");
	    s7.add("35-5 35-4 35-3");
	    List<String> s8 = new ArrayList<String>();
	    s8.add("35-5 35-4 35-3");
	    s8.add("35-5 35-4 35-3");
	    s8.add("35-5 35-4 35-3");
	    config.set("Use-Mana-Dust-Recipe", false);
	    config.set("Use-Mana-Thread-Recipe", false);
	    config.set("Use-Mana-Cloth-Recipe", false);
	    config.set("Use-Mana-Bag-Recipe", false);
	    config.set("Use-Mana-Workbench-Recipe", false);
	    config.set("Use-Diamond-Thread-Recipe", false);
	    config.set("Use-Diamond-Weave-Recipe", false);
	    config.set("Use-Diamond-Weave-Upgrade-Recipe", false);
	    config.set("Mana-Dust-Recipe", s);
	    config.set("Mana-Workbench-Recipe", s2);
	    config.set("Mana-Thread-Recipe", s3);
	    config.set("Mana-Cloth-Recipe", s4);
	    config.set("Mana-Bag-Recipe", s5);
	    config.set("Diamond-Thread-Recipe", s6);
	    config.set("Diamond-Weave-Recipe", s7);
	    config.set("Diamond-Weave-Upgrade-Recipe", s8);
	    config.set("Use-Hard-Mode", false);
	    config.set("Death-Drop-Slot-1", false);
	    config.set("Death-Drop-Slot-2", false);
	    config.set("Death-Drop-Slot-3", false);
	    config.set("Death-Drop-Slot-4", false);
	    config.set("Enable-Mana-Chamber", true);	    
	    config.set("Mana-Chamber-Texture", "http://aesircraft.net/items/manachest.png");
	    config.set("Mana-Chamber-Texture-Size", 128);
	    config.set("Use-Mana-Chamber-Recipe", false);
	    List<String> s9 = new ArrayList<String>();
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    config.set("Mana-Chamber-Recipe", s9);
	    config.set("Enable-Auto-Pickup", true);
	    config.set("Config-Version", 3);
	    save(config);
	}
	if (config.getInt("Config-Version", 0) == 1) {
	    List<String> s = new ArrayList<String>();
	    s.add("35-5 35-4 35-3");
	    s.add("35-5 35-4 35-3");
	    s.add("35-5 35-4 35-3");
	    List<String> s2 = new ArrayList<String>();
	    s2.add("35-5 35-4 35-3");
	    s2.add("35-5 35-4 35-3");
	    s2.add("35-5 35-4 35-3");
	    List<String> s3 = new ArrayList<String>();
	    s3.add("35-5 35-4 35-3");
	    s3.add("35-5 35-4 35-3");
	    s3.add("35-5 35-4 35-3");
	    List<String> s4 = new ArrayList<String>();
	    s4.add("35-5 35-4 35-3");
	    s4.add("35-5 35-4 35-3");
	    s4.add("35-5 35-4 35-3");
	    List<String> s5 = new ArrayList<String>();
	    s5.add("35-5 35-4 35-3");
	    s5.add("35-5 35-4 35-3");
	    s5.add("35-5 35-4 35-3");
	    List<String> s6 = new ArrayList<String>();
	    s6.add("35-5 35-4 35-3");
	    s6.add("35-5 35-4 35-3");
	    s6.add("35-5 35-4 35-3");
	    List<String> s7 = new ArrayList<String>();
	    s7.add("35-5 35-4 35-3");
	    s7.add("35-5 35-4 35-3");
	    s7.add("35-5 35-4 35-3");
	    List<String> s8 = new ArrayList<String>();
	    s8.add("35-5 35-4 35-3");
	    s8.add("35-5 35-4 35-3");
	    s8.add("35-5 35-4 35-3");
	    config.set("Use-Mana-Dust-Recipe", false);
	    config.set("Use-Mana-Thread-Recipe", false);
	    config.set("Use-Mana-Cloth-Recipe", false);
	    config.set("Use-Mana-Bag-Recipe", false);
	    config.set("Use-Mana-Workbench-Recipe", false);
	    config.set("Use-Diamond-Thread-Recipe", false);
	    config.set("Use-Diamond-Weave-Recipe", false);
	    config.set("Use-Diamond-Weave-Upgrade-Recipe", false);
	    config.set("Mana-Dust-Recipe", s);
	    config.set("Mana-Workbench-Recipe", s2);
	    config.set("Mana-Thread-Recipe", s3);
	    config.set("Mana-Cloth-Recipe", s4);
	    config.set("Mana-Bag-Recipe", s5);
	    config.set("Diamond-Thread-Recipe", s6);
	    config.set("Diamond-Weave-Recipe", s7);
	    config.set("Diamond-Weave-Upgrade-Recipe", s8);
	    config.set("Death-Drop-Slot-1", false);
	    config.set("Death-Drop-Slot-2", false);
	    config.set("Death-Drop-Slot-3", false);
	    config.set("Death-Drop-Slot-4", false);
	    config.set("Enable-Mana-Chamber", true);	    
	    config.set("Mana-Chamber-Texture", "http://aesircraft.net/items/manachest.png");
	    config.set("Mana-Chamber-Texture-Size", 128);
	    config.set("Use-Mana-chamber-Recipe", false);
	    List<String> s9 = new ArrayList<String>();
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    config.set("Mana-Chamber-Recipe", s9);
	    config.set("Enable-Auto-Pickup", true);
	    config.set("Config-Version", 3);
	    save(config);
	}
	if (config.getInt("Config-Version", 0) == 2) {	    
	    config.set("Enable-Mana-Chamber", true);	    
	    config.set("Mana-Chamber-Texture", "http://aesircraft.net/items/manachest.png");
	    config.set("Mana-Chamber-Texture-Size", 128);
	    config.set("Use-Mana-Chamber-Recipe", false);
	    List<String> s9 = new ArrayList<String>();
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    config.set("Mana-Chamber-Recipe", s9);
	    config.set("Enable-Auto-Pickup", true);
	    config.set("Config-Version", 3);
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
	    config.set("Enable-Mana-Chamber", true);	   
	    config.set("Death-Drop-Slot-1", false);
	    config.set("Death-Drop-Slot-2", false);
	    config.set("Death-Drop-Slot-3", false);
	    config.set("Death-Drop-Slot-4", false);
	    config.set("Protect-Creative", true);
	    config.set("Use-xAuth", false);
	    config.set("Use-UltimateArena", false);
	    config.set("Use-Sound-Effects", true);
	    config.set("Mana-Chamber-Texture-Size", 128);
	    config.set("Mana-Chamber-Texture", "http://aesircraft.net/items/manachest.png");
	    config.set("Sound-Effects-File", "http://aesircraft.net/items/poof.ogg");
	    config.set("Mana-Dust-Texture", "http://aesircraft.net/items/manadust.png");
	    config.set("Mana-Thread-Texture", "http://aesircraft.net/items/manathread.png");
	    config.set("Mana-Cloth-Texture", "http://aesircraft.net/items/manacloth.png");
	    config.set("Mana-Bag-Texture", "http://aesircraft.net/items/manabag.png");
	    config.set("Mana-Workbench-Texture", "http://aesircraft.net/items/magicbench.png");
	    config.set("Diamond-Thread-Texture", "http://aesircraft.net/items/diamondthread.png");
	    config.set("Diamond-Weave-Texture", "http://aesircraft.net/items/diamondweave.png");
	    config.set("Diamond-Weave-Upgrade-Texture", "http://aesircraft.net/items/diamondupgrade.png");
	    List<String> s = new ArrayList<String>();
	    s.add("35-5 35-4 35-3");
	    s.add("35-5 35-4 35-3");
	    s.add("35-5 35-4 35-3");
	    List<String> s2 = new ArrayList<String>();
	    s2.add("35-5 35-4 35-3");
	    s2.add("35-5 35-4 35-3");
	    s2.add("35-5 35-4 35-3");
	    List<String> s3 = new ArrayList<String>();
	    s3.add("35-5 35-4 35-3");
	    s3.add("35-5 35-4 35-3");
	    s3.add("35-5 35-4 35-3");
	    List<String> s4 = new ArrayList<String>();
	    s4.add("35-5 35-4 35-3");
	    s4.add("35-5 35-4 35-3");
	    s4.add("35-5 35-4 35-3");
	    List<String> s5 = new ArrayList<String>();
	    s5.add("35-5 35-4 35-3");
	    s5.add("35-5 35-4 35-3");
	    s5.add("35-5 35-4 35-3");
	    List<String> s6 = new ArrayList<String>();
	    s6.add("35-5 35-4 35-3");
	    s6.add("35-5 35-4 35-3");
	    s6.add("35-5 35-4 35-3");
	    List<String> s7 = new ArrayList<String>();
	    s7.add("35-5 35-4 35-3");
	    s7.add("35-5 35-4 35-3");
	    s7.add("35-5 35-4 35-3");
	    List<String> s8 = new ArrayList<String>();
	    s8.add("35-5 35-4 35-3");
	    s8.add("35-5 35-4 35-3");
	    s8.add("35-5 35-4 35-3");
	    List<String> s9 = new ArrayList<String>();
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    s9.add("35-5 35-4 35-3");
	    config.set("Use-Mana-Chamber-Recipe", false);
	    config.set("Use-Mana-Dust-Recipe", false);
	    config.set("Use-Mana-Thread-Recipe", false);
	    config.set("Use-Mana-Cloth-Recipe", false);
	    config.set("Use-Mana-Bag-Recipe", false);
	    config.set("Use-Mana-Workbench-Recipe", false);
	    config.set("Use-Diamond-Thread-Recipe", false);
	    config.set("Use-Diamond-Weave-Recipe", false);
	    config.set("Use-Diamond-Weave-Upgrade-Recipe", false);
	    config.set("Mana-Dust-Recipe", s);
	    config.set("Mana-Workbench-Recipe", s2);
	    config.set("Mana-Thread-Recipe", s3);
	    config.set("Mana-Cloth-Recipe", s4);
	    config.set("Mana-Bag-Recipe", s5);
	    config.set("Diamond-Thread-Recipe", s6);
	    config.set("Diamond-Weave-Recipe", s7);
	    config.set("Diamond-Weave-Upgrade-Recipe", s8);
	    config.set("Mana-Chamber-Recipe", s9);
	    config.set("Enable-Auto-Pickup", true);
	    config.set("Config-Version", 3);
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
    
    public static boolean getDeathDropSlotSlot1() {
	return getYaml().getBoolean("Death-Drop-Slot-1", false);
    }
    
    public static boolean getDeathDropSlotSlot2() {
	return getYaml().getBoolean("Death-Drop-Slot-2", false);
    }
    
    public static boolean getEnableAutoPickup() {
	return getYaml().getBoolean("Enable-Auto-Pickup", true);
    }
    
    public static boolean getDeathDropSlotSlot3() {
	return getYaml().getBoolean("Death-Drop-Slot-3", false);
    }
    
    public static boolean getDeathDropSlotSlot4() {
	return getYaml().getBoolean("Death-Drop-Slot-4", false);
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
    public static boolean getUseManaChamber() {
	return getYaml().getBoolean("Enable-Mana-Chamber", true);
    }

    public static boolean getUseUltimateArena() {
	return getYaml().getBoolean("Use-UltimateArena", false);
    }

    public static String getManaDustTexture() {
	return getYaml().getString("Mana-Dust-Texture", "http://aesircraft.net/items/manadust.png");
    }
    
    public static String getManaChamberTexture() {
	return getYaml().getString("Mana-Chamber-Texture", "http://aesircraft.net/items/manachest.png");
    }
    
    public static int getManaChamberTextureSize() {
	return getYaml().getInt("Mana-Chamber-Texture-Size", 128);
    }

    public static boolean getUseManaDustRecipe() {
	return getYaml().getBoolean("Use-Mana-Dust-Recipe", false);
    }
    
    public static boolean getUseManaChamberRecipe() {
	return getYaml().getBoolean("Use-Mana-Chamber-Recipe", false);
    }

    public static boolean getUseManaThreadRecipe() {
	return getYaml().getBoolean("Use-Mana-Thread-Recipe", false);
    }

    public static boolean getUseManaClothRecipe() {
	return getYaml().getBoolean("Use-Mana-Cloth-Recipe", false);
    }

    public static boolean getUseManaBagRecipe() {
	return getYaml().getBoolean("Use-Mana-Bag-Recipe", false);
    }

    public static boolean getUseManaWorkbenchRecipe() {
	return getYaml().getBoolean("Use-Mana-Workbench-Recipe", false);
    }

    public static boolean getUseDiamondThreadRecipe() {
	return getYaml().getBoolean("Use-Diamond-Thread-Recipe", false);
    }

    public static boolean getUseDiamondWeaveRecipe() {
	return getYaml().getBoolean("Use-Diamond-Weave-Recipe", false);
    }

    public static boolean getUseDiamondWeaveUpgradeRecipe() {
	return getYaml().getBoolean("Use-Diamond-Weave-Upgrade-Recipe", false);
    }

    public static List<String> getManaDustRecipe() {
	return getYaml().getStringList("Mana-Dust-Recipe");
    }

    public static List<String> getManaThreadRecipe() {
	return getYaml().getStringList("Mana-Thread-Recipe");
    }
    
    public static List<String> getManaChamberRecipe() {
	return getYaml().getStringList("Mana-Chamber-Recipe");
    }

    public static List<String> getManaClothRecipe() {
	return getYaml().getStringList("Mana-Cloth-Recipe");
    }

    public static List<String> getManaBagRecipe() {
	return getYaml().getStringList("Mana-Bag-Recipe");
    }

    public static List<String> getManaWorkbenchRecipe() {
	return getYaml().getStringList("Mana-Workbench-Recipe");
    }

    public static List<String> getDiamondThreadRecipe() {
	return getYaml().getStringList("Diamond-Thread-Recipe");
    }

    public static List<String> getDiamondWeaveRecipe() {
	return getYaml().getStringList("Diamond-Weave-Recipe");
    }

    public static List<String> getDiamondWeaveUpgradeRecipe() {
	return getYaml().getStringList("Diamond-Weave-Upgrade-Recipe");
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
