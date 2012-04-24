package net.aesircraft.ManaBags.API;

import net.aesircraft.ManaBags.Bags.Chests.ManaChest;
import net.aesircraft.ManaBags.Bags.VirtualChest;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ManaChamber {
    
    private ManaChest chest;
    
    public ManaChamber(Location loc){
	chest=new ManaChest(loc.getBlock());
    }
	
    public Location getLocation(){
	return chest.getFakeChest().getLocation();
    }
    
    public void breakChest(Player player){
	BlockBreakEvent bbe=new BlockBreakEvent(chest.getFakeChest().getLocation().getBlock(),player);
	chest.breakNaturally(bbe);
    }
    
    public VirtualChest getVirtualChest(){
	return chest.getVirtualChest();
    }   
    
}
