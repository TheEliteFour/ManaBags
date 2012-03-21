package net.aesircraft.ManaBags.Items;

import java.util.List;
import java.util.logging.Level;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.CustomItem;

public class RecipeCreator {

    private SpoutItemStack[] m;

    public RecipeCreator() {
    }

    private boolean parse(List<String> l) {
	if (l == null) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up your recipe config!");
	    return false;
	}
	if (l.size() < 3) {
	    ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up your recipe config!");
	    return false;
	}
	String[] i;
	int ctr = 0;
	SpoutItemStack[] it = new SpoutItemStack[9];
	int ctr2 = 0;
	for (String s : l) {
	    if (s == null) {
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up your recipe config!");
		return false;
	    }
	    if (s.equals("")) {
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, your missing a line!");
		return false;
	    }
	    if (!s.contains(" ")) {
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, " + s + ", is not a proper line!");
		return false;
	    }
	    if (!s.contains("-")) {
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, " + s + ", is not a proper line!");
		return false;
	    }
	    i = s.split(" ");
	    if (i.length != 3) {
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, " + s + ", is not a proper line!");
		return false;
	    }
	    for (String s2 : i) {
		if (s2.split("-").length != 2) {
		    ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, " + s2 + ", is not a proper item!");
		    return false;
		}
		try {
		    if (Material.getMaterial(Integer.parseInt(s2.split("-")[0])) == null) {
			ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, " + s2 + ", is not an item!");
			return false;
		    }
		} catch (NumberFormatException ex) {
		    ManaBags.logger.log(Level.SEVERE, "[ManaCraft] You messed up, " + s2 + ", is not an item!");
		    return false;
		}
		it[ctr2] = new SpoutItemStack(Integer.parseInt(s2.split("-")[0]), Short.parseShort(s2.split("-")[1]));
		ctr2++;
	    }
	    ctr++;
	}
	m = it;
	return true;

    }

    public boolean setRecipe(CustomItem item, List<String> recipe) {
	if (!parse(recipe)) {
	    return false;
	}
	ItemStack i = new SpoutItemStack(item, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABC", "DEF", "GHI");
	r.setIngredient('A', m[0].getMaterial());
	r.setIngredient('B', m[1].getMaterial());
	r.setIngredient('C', m[2].getMaterial());
	r.setIngredient('D', m[3].getMaterial());
	r.setIngredient('E', m[4].getMaterial());
	r.setIngredient('F', m[5].getMaterial());
	r.setIngredient('G', m[6].getMaterial());
	r.setIngredient('H', m[7].getMaterial());
	r.setIngredient('I', m[8].getMaterial());
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
	return true;
    }
}
