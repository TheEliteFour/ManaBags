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

public class DiamondWeave extends GenericCustomItem {

    public DiamondWeave(Plugin plugin) {
	super(plugin, "Diamond Weave", Config.getDiamondWeaveTexture());
	if (Config.getUseDiamondWeaveRecipe()){
	    RecipeCreator r=new RecipeCreator();
	    if (!r.setRecipe(this, Config.getDiamondWeaveRecipe())){
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] Inproper Diamond Weave recipe, using default!");
	    }
	    else{
		return;
	    }
	}
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("AAA", "BBB", "AAA");
	r.setIngredient('A', ManaMaterial.diamondThread);
	r.setIngredient('B', MaterialData.whiteWool);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
