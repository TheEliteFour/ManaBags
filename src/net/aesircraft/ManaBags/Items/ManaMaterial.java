package net.aesircraft.ManaBags.Items;

import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.plugin.Plugin;

public class ManaMaterial {

    public static ManaDust manaDust;
    public static ManaThread manaThread;
    public static ManaCloth manaCloth;
    public static ManaBag manaBag;
    public static ManaWorkbench manaWorkbench;
    public static DiamondThread diamondThread;
    public static DiamondWeave diamondWeave;
    public static DiamondWeaveUpgrade diamondWeaveUpgrade;
    public static ManaChamber manaChamber;
    public static DiamondWorkbench diamondWorkBench;

    public ManaMaterial(ManaBags plugin) {	
    }
    
    public void load(ManaBags plugin){
	if (!(!Config.getEnableSlot1() && !Config.getEnableSlot2() && !Config.getEnableSlot3() && !Config.getEnableSlot4() && !Config.getEnableManaWorkbench())) {
	    manaDust = new ManaDust(plugin);
	    manaThread = new ManaThread(plugin);
	    manaCloth = new ManaCloth(plugin);
	    if (!(!Config.getEnableSlot1() && !Config.getEnableSlot2() && !Config.getEnableSlot3() && !Config.getEnableSlot4())) {
		manaBag = new ManaBag(plugin);
	    }
	    if (Config.getEnableManaWorkbench()) {
		manaWorkbench = new ManaWorkbench(plugin);
	    }
	}
	if (!(!Config.getEnableUpgradeSlot1() && !Config.getEnableUpgradeSlot2() && !Config.getEnableUpgradeSlot3() && !Config.getEnableUpgradeSlot4())) {
	    diamondThread = new DiamondThread(plugin);
	    diamondWeave = new DiamondWeave(plugin);
	    diamondWeaveUpgrade = new DiamondWeaveUpgrade(plugin);
	}
	if (Config.getUseManaChamber()){
	    manaChamber=new ManaChamber(plugin);
	}
	
	if (Config.getUseDiamondWorkbench()){
	    diamondWorkBench=new DiamondWorkbench(plugin);
	}
    }

}
