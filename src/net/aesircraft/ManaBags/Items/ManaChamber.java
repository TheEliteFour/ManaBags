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
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class ManaChamber extends GenericCubeCustomBlock {

    public ManaChamber(Plugin plugin) {
	super(plugin, "Mana Chamber",Config.getManaChamberTexture(),Config.getManaChamberTextureSize());
	if (Config.getUseManaChamberRecipe()){
	    RecipeCreator r=new RecipeCreator();
	    if (!r.setRecipe(this.getBlockItem(), Config.getManaChamberRecipe())){
		ManaBags.logger.log(Level.SEVERE, "[ManaCraft] Improper Mana Chamber recipe, using default!");
	    }
	    else{
		return;
	    }
	}
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("AAA", "ABA", "AAA");
	r.setIngredient('A', ManaMaterial.manaDust);
	r.setIngredient('B', MaterialData.chest);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
