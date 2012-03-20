package net.aesircraft.ManaBags.Items;

import net.aesircraft.ManaBags.Config.Config;
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
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABA", "BCB", "ABA");
	r.setIngredient('A', MaterialData.glowstoneDust);
	r.setIngredient('B', MaterialData.lapisLazuli);
	r.setIngredient('C', MaterialData.redstone);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
