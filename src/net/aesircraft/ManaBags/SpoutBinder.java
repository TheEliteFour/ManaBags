package net.aesircraft.ManaBags;

import net.aesircraft.ManaBags.Items.ManaMaterial;
import net.aesircraft.ManaBags.Keys.Bag1Key;
import net.aesircraft.ManaBags.Keys.Bag2Key;
import net.aesircraft.ManaBags.Keys.Bag3Key;
import net.aesircraft.ManaBags.Keys.Bag4Key;
import net.aesircraft.ManaBags.Keys.WorkbenchKey;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.KeyBindingManager;
import org.getspout.spoutapi.keyboard.Keyboard;

public class SpoutBinder {
    
    private static ManaMaterial manaMaterial;
    
    public static ManaMaterial getManaMaterial() {
	return manaMaterial;
    }
    
    public static void bind(ManaBags plug){
	manaMaterial = new ManaMaterial(plug);
	manaMaterial.load(plug);
	KeyBindingManager kbm = SpoutManager.getKeyBindingManager();
	kbm.registerBinding("Bag.1", Keyboard.KEY_NUMPAD1, "Open Bag 1", new Bag1Key(), plug);
	kbm.registerBinding("Bag.2", Keyboard.KEY_NUMPAD2, "Open Bag 2", new Bag2Key(), plug);
	kbm.registerBinding("Bag.3", Keyboard.KEY_NUMPAD3, "Open Bag 3", new Bag3Key(), plug);
	kbm.registerBinding("Bag.4", Keyboard.KEY_NUMPAD4, "Open Bag 4", new Bag4Key(), plug);
	kbm.registerBinding("Workbench", Keyboard.KEY_NUMPAD0, "Workbench", new WorkbenchKey(), plug);
    }
    
}
