package net.aesircraft.ManaBags.Items;

import net.aesircraft.ManaBags.Config.Config;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class ManaThread extends GenericCustomItem {

    public ManaThread(Plugin plugin) {
	super(plugin, "Mana Thread", Config.getManaThreadTexture());
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("AAA", "BCB", "AAA");
	r.setIngredient('A', MaterialData.string);
	r.setIngredient('B', ManaMaterial.manaDust);
	r.setIngredient('C', MaterialData.diamond);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
