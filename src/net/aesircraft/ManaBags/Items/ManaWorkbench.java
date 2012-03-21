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

public class ManaWorkbench extends GenericCustomItem {

    public ManaWorkbench(Plugin plugin) {
	super(plugin, "Mana Workbench", Config.getManaWorkBenchTexture());
	if (Config.getUseManaWorkbenchRecipe()){
	    RecipeCreator r=new RecipeCreator();
	    if (!r.setRecipe(this, Config.getManaWorkbenchRecipe())){
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] Inproper Mana Workbench recipe, using default!");
	    }
	    else{
		return;
	    }
	}
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABA", "BCB", "ABA");
	r.setIngredient('A', ManaMaterial.manaThread);
	r.setIngredient('B', ManaMaterial.manaCloth);
	r.setIngredient('C', MaterialData.craftingTable);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
