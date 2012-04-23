package net.aesircraft.ManaBags.Bags;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class View extends InventoryView{

    private Player player;
    private VirtualChest chest;
    
    public View(Player player, VirtualChest chest){
	this.player=player;
	this.chest=chest;
    }
    
    @Override
    public Inventory getTopInventory() {
	return (Inventory) chest;
    }

    @Override
    public Inventory getBottomInventory() {
	return player.getInventory();
    }

    @Override
    public HumanEntity getPlayer() {
	return player;
    }

    @Override
    public InventoryType getType() {
	return InventoryType.CHEST;
    }
    
}
