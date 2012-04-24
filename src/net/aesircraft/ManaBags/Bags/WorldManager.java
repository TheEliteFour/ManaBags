package net.aesircraft.ManaBags.Bags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class WorldManager {

    private boolean newf = false;

    private File getFile() {
	File dir = ManaBags.getStatic().getDataFolder();
	File file = new File(ManaBags.getStatic().getDataFolder() + File.separator + "worlds.yml");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to write bag file!");
	    }
	    newf = true;
	}
	return file;
    }

    private YamlConfiguration getYaml() {
	YamlConfiguration config = new YamlConfiguration();
	try {
	    config.load(getFile());
	} catch (FileNotFoundException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find world file!");
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load world file!");
	} catch (InvalidConfigurationException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load world file!");
	}
	if (newf) {
	    List<String> li = new ArrayList<String>();
	    li.add("ExampleWorld1");
	    li.add("ExampleWorld2");
	    config.set("seperate-inventory-worlds", li);
	    saveYaml(config);
	    try {
		config.load(getFile());
	    } catch (FileNotFoundException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find world file!");
	    } catch (IOException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load world file!");
	    } catch (InvalidConfigurationException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load world file!");
	    }
	}
	return config;

    }

    private void saveYaml(YamlConfiguration config) {
	try {
	    config.save(getFile());
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to write world file!");
	}
    }

    public List<String> getInventoryWorlds() {
	return getYaml().getStringList("seperate-inventory-worlds");
    }
}
