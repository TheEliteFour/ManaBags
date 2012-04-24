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

public class DiamondWorkbench extends GenericCustomItem {

    public DiamondWorkbench(Plugin plugin) {
	super(plugin, "Diamond Workbench", Config.getDiamondWorkbenchTexture());
	if (Config.getUseDiamondWorkbenchRecipe()){
	    RecipeCreator r=new RecipeCreator();
	    if (!r.setRecipe(this, Config.getDiamondWorkbenchRecipe())){
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] Improper Diamond Workbench recipe, using default!");
	    }
	    else{
		return;
	    }
	}
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("AAA", "ABA", "AAA");
	r.setIngredient('A', MaterialData.diamond);
	r.setIngredient('B', ManaMaterial.manaWorkbench);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
