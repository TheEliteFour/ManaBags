package net.aesircraft.ManaBags.Items;

import net.aesircraft.ManaBags.Config.Config;
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
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("AAA", "BBB", "AAA");
	r.setIngredient('A', ManaMaterial.diamondThread);
	r.setIngredient('B', MaterialData.whiteWool);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
