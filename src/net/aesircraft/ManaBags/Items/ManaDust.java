package net.aesircraft.ManaBags.Items;

import java.util.logging.Level;
import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class ManaDust extends GenericCustomItem {

    public ManaDust(Plugin plugin) {
	super(plugin, "Mana Dust", Config.getManaDustTexture());
	if (Config.getUseManaDustRecipe()){
	    RecipeCreator r=new RecipeCreator();
	    if (!r.setRecipe(this, Config.getManaDustRecipe())){
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] Inproper Mana Dust recipe, using default!");
	    }
	    else{
		return;
	    }
	}
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABA", "BCB", "ABA");
	r.setIngredient('A', MaterialData.glowstoneDust);
	r.setIngredient('B', MaterialData.lapisLazuli);
	if (Config.getHardMode()) {
	    r.setIngredient('C', MaterialData.diamond);
	} else {
	    r.setIngredient('C', MaterialData.redstone);
	}
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
