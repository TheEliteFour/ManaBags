package net.aesircraft.ManaBags.Bags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import net.aesircraft.ManaBags.ManaBags;
import net.minecraft.server.ItemStack;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class ChestManager {

    private PlayerBag bag;
    private boolean newf = false;
    public static HashMap<Player, PlayerBag> bags = new HashMap<Player, PlayerBag>();

    public ChestManager(PlayerBag pb) {
	bag = pb;
    }

    private File getFile() {
	File dir = new File(ManaBags.getStatic().getDataFolder() + File.separator + "bags");
	File dir2 = new File(ManaBags.getStatic().getDataFolder() + File.separator + "bags" + File.separator + bag.getPlayer().getName().toLowerCase());
	File file = new File(dir2, bag.getId() + ".yml");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	if (dir2.exists()) {
	    dir2.mkdir();
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
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find bag file!");
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load bag file!");
	} catch (InvalidConfigurationException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load bag file!");
	}
	if (newf) {
	    config.set("type", 0);
	    saveYaml(config);
	    try {
		config.load(getFile());
	    } catch (FileNotFoundException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find bag file!");
	    } catch (IOException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load bag file!");
	    } catch (InvalidConfigurationException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load bag file!");
	    }
	}
	return config;

    }

    private void saveYaml(YamlConfiguration config) {
	try {
	    config.save(getFile());
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to write bag file!");
	}
    }

    private ItemStack[] loadInventory() {
	WorldManager wm = new WorldManager();
	List<String> world = wm.getInventoryWorlds();
	boolean sep = false;
	for (String s : world) {
	    if (s.toLowerCase().equals(bag.getPlayer().getWorld().getName().toLowerCase())) {
		sep = true;
		break;
	    }
	}
	List<String> l = null;
	if (sep) {
	    l = getYaml().getStringList(bag.getPlayer().getWorld().getName().toLowerCase() + "-items");
	} else {
	    l = getYaml().getStringList("items");

	}
	ItemStack[] i;
	if (!bag.getLarge()) {
	    i = new ItemStack[27];
	} else {
	    i = new ItemStack[54];
	}
	ItemStack t;
	String[] st;
	int ctr = 0;
	for (String s : l) {
	    if (s.equals("null")) {
		i[ctr] = null;
		continue;
	    }
	    st = s.split(":");
	    t = new ItemStack(Integer.parseInt(st[0]), Integer.parseInt(st[1]), Short.parseShort(st[2]));
	    String[] st2;
	    if (st.length > 3) {
		for (int c = 3; c < st.length; c++) {
		    st2 = st[c].split(",");
		    t.addEnchantment(net.minecraft.server.Enchantment.byId[Enchantment.getByName(st2[0]).getId()], Integer.parseInt(st2[1]));
		}
	    }
	    i[ctr] = t;
	    ctr++;
	}
	return i;
    }

    public void save() {
	YamlConfiguration config = getYaml();
	List<String> l = new ArrayList<String>();
	org.bukkit.inventory.ItemStack[] i = null;
	if (!bag.getAttach()) {
	    if (bag.getLarge()) {
		i = bag.getVirtualLargeChest().getContents();
	    } else {
		i = bag.getVirtualChest().getContents();
	    }
	}
	String s;
	if (i != null) {
	    for (org.bukkit.inventory.ItemStack t : i) {
		if (t == null) {
		    l.add("null");
		    continue;
		}
		if (t.getTypeId() == 0) {
		    continue;
		}
		String e = "";
		for (Enchantment en : t.getEnchantments().keySet()) {
		    e = e + ":" + en.getName() + "," + t.getEnchantmentLevel(en);
		}
		s = "" + t.getTypeId() + ":" + t.getAmount() + ":" + t.getDurability() + e;
		l.add(s);
	    }
	    WorldManager wm = new WorldManager();
	    List<String> world = wm.getInventoryWorlds();
	    boolean sep = false;
	    for (String st : world) {
		if (st.toLowerCase().equals(bag.getPlayer().getWorld().getName().toLowerCase())) {
		    sep = true;
		    break;
		}
	    }
	    String loc = "";
	    if (sep) {
		loc = bag.getPlayer().getWorld().getName().toLowerCase() + "-items";
	    } else {
		loc = "items";
	    }
	    config.set(loc, l);
	}
	config.set("type", bag.getType());
	saveYaml(config);
    }

    public PlayerBag load() {
	YamlConfiguration config = getYaml();
	bag.setType(config.getInt("type", 0));
	return bag;
    }

    public PlayerBag loadInv() {
	load();
	if (bag.getLarge()) {
	    bag.setVirtualLargeChest(new VirtualLargeChest("Slot " + bag.getId() + ": Magic Bag LV: 2"));
	    ItemStack[] t = (net.minecraft.server.ItemStack[]) loadInventory();
	    bag.getVirtualLargeChest().addItemStack(t);
	}
	if (!bag.getLarge()) {
	    bag.setVirtualChest(new VirtualChest("Slot " + bag.getId() + ": Magic Bag LV: 1"));
	    ItemStack[] t = (net.minecraft.server.ItemStack[]) loadInventory();
	    bag.getVirtualChest().addItemStack(t);
	}
	return bag;
    }

    public ItemStack[] retrieveInv() {
	load();
	ItemStack[] t = null;
	if (bag.getLarge()) {
	    bag.setVirtualLargeChest(new VirtualLargeChest("Slot " + bag.getId() + ": Magic Bag LV: 2"));
	    t = (net.minecraft.server.ItemStack[]) loadInventory();
	    bag.getVirtualLargeChest().addItemStack(t);
	}
	if (!bag.getLarge()) {
	    bag.setVirtualChest(new VirtualChest("Slot " + bag.getId() + ": Magic Bag LV: 1"));
	    t = (net.minecraft.server.ItemStack[]) loadInventory();
	    bag.getVirtualChest().addItemStack(t);
	}
	return t;
    }

    public void setInv(org.bukkit.inventory.ItemStack[] i) {
	YamlConfiguration config = getYaml();
	List<String> l = new ArrayList<String>();
	String s;
	if (i != null) {
	    for (org.bukkit.inventory.ItemStack t : i) {
		if (t == null) {
		    l.add("null");
		    continue;
		}
		if (t.getTypeId() == 0) {
		    continue;
		}
		String e = "";
		for (Enchantment en : t.getEnchantments().keySet()) {
		    e = e + ":" + en.getName() + "," + t.getEnchantmentLevel(en);
		}
		s = "" + t.getTypeId() + ":" + t.getAmount() + ":" + t.getDurability() + e;
		l.add(s);
	    }
	    WorldManager wm = new WorldManager();
	    List<String> world = wm.getInventoryWorlds();
	    boolean sep = false;
	    for (String st : world) {
		if (st.toLowerCase().equals(bag.getPlayer().getWorld().getName().toLowerCase())) {
		    sep = true;
		    break;
		}
	    }
	    String loc = "";
	    if (sep) {
		loc = bag.getPlayer().getWorld().getName().toLowerCase() + "-items";
	    } else {
		loc = "items";
	    }
	    config.set(loc, l);
	}
	config.set("type", bag.getType());
	saveYaml(config);
    }
}
