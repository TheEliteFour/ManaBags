package net.aesircraft.ManaBags.Bags.Chests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import net.aesircraft.ManaBags.Bags.View;
import net.aesircraft.ManaBags.Bags.VirtualChest;
import net.aesircraft.ManaBags.Bags.VirtualLargeChest;
import net.aesircraft.ManaBags.ManaBags;
import net.minecraft.server.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class ManaChest {
    
    public static HashMap<Player,ManaChest> open=new HashMap<Player,ManaChest>();

    private Location location;
    private ManaLocation mloc;
    private VirtualChest chest;
    private Block block;

    public ManaChest(Block b) {
	location = b.getLocation();
	mloc = new ManaLocation(b.getLocation());
	block = b;
	load();
    }
    
    public VirtualChest getVirtualChest(){
	return chest;
    }

    private void load() {
	chest = new VirtualLargeChest("Mana Chest");
	loadInventory();
    }

    private File getFile() {
	File dir = ManaBags.getStatic().getDataFolder();
	File dir2 = new File(dir, "Chests");
	File file = new File(dir2, mloc.getString() + ".mcf");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	if (!dir2.exists()) {
	    dir2.mkdir();
	}
	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException ex) {
		ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to write chest file!");
	    }
	}
	return file;
    }

    private YamlConfiguration getYaml() {
	YamlConfiguration config = new YamlConfiguration();
	try {
	    config.load(getFile());
	} catch (FileNotFoundException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to find chest file!");
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to load chest file!");
	} catch (InvalidConfigurationException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to understand chest file!");
	}
	return config;
    }

    private void save(YamlConfiguration config) {
	try {
	    config.save(getFile());
	} catch (IOException ex) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaBags] Failed to write chest file!");
	}
    }

    private void loadInventory() {
	List<String> l = getYaml().getStringList("items");
	ItemStack[] i;
	i = new ItemStack[54];
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
	chest.addItemStack(i);
    }

    private void delete() {
	getFile().delete();
    }

    public void save() {
	YamlConfiguration config = getYaml();
	List<String> l = new ArrayList<String>();
	org.bukkit.inventory.ItemStack[] i = null;
	i = chest.getContents();
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
	    config.set("items", l);
	}
	save(config);
    }

    public void breakNaturally(BlockBreakEvent event) {
	event.setCancelled(true);
	FakeChest b = new FakeChest(block, chest);
	BlockBreakEvent ev = new BlockBreakEvent(b, event.getPlayer());
	//Bukkit.getPluginManager().callEvent(ev);
	if (ev.isCancelled()) {
	    return;
	}
	block.setTypeId(0);
	for (Iterator<org.bukkit.inventory.ItemStack> it = b.getDrops().iterator(); it.hasNext();) {
	    org.bukkit.inventory.ItemStack i = it.next();
	    if (i==null)
		continue;
	    block.getWorld().dropItem(location, i);
	}
	delete();
    }

    public void open(Player player) {
	InventoryView gui = new View(player, null);
	InventoryOpenEvent io = new InventoryOpenEvent(gui);
	Bukkit.getPluginManager().callEvent(io);
	if (io.isCancelled()) {
	    return;
	}
	open.put(player, this);
	chest.openChest(player);
    }

    public FakeChest getFakeChest() {
	return new FakeChest(block, chest);
    }
}
