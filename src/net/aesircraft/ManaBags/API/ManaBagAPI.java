package net.aesircraft.ManaBags.API;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ManaBagAPI {

    private ManaBagMaterials materials = new ManaBagMaterials();

    public ManaBagPlayer getManaBagPlayer(Player player) {
	return new ManaBagPlayer(player);
    }

    public ManaBagNPC getManaBagNPC(String name) {
	return new ManaBagNPC(name);
    }

    public ManaBagMaterials getManaBagMaterials() {
	return materials;
    }
    
    public ManaChamber getManaChamber(Block block){
	return new ManaChamber(block.getLocation());
    }
    
    public ManaChamber getManaChamber(Location location){
	return new ManaChamber(location);
    }
}
