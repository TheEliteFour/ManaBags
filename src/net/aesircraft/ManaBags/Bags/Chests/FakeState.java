package net.aesircraft.ManaBags.Bags.Chests;

import java.util.List;
import net.aesircraft.ManaBags.Bags.VirtualChest;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class FakeState implements BlockState, Chest{
    
    private FakeChest block;
    private VirtualChest chest;
    
    public FakeState(FakeChest block,VirtualChest chest){
	this.chest=chest;
	this.block=block;
    }

    @Override
    public Block getBlock() {
	return block;
    }

    @Override
    public MaterialData getData() {
	MaterialData mat=new MaterialData(block.getType());
	return mat;
    }

    @Override
    public Material getType() {
	return block.getType();
    }

    @Override
    public int getTypeId() {
	return block.getTypeId();
    }

    @Override
    public byte getLightLevel() {
	return block.getLightLevel();
    }

    @Override
    public World getWorld() {
	return block.getWorld();
    }

    @Override
    public int getX() {
	return block.getX();
    }

    @Override
    public int getY() {
	return block.getY();
    }

    @Override
    public int getZ() {
	return block.getZ();
    }

    @Override
    public Location getLocation() {
	return block.getLocation();
    }

    @Override
    public Chunk getChunk() {
	return block.getChunk();
    }

    @Override
    public void setData(MaterialData md) {
	//um, dont do that
    }

    @Override
    public void setType(Material mtrl) {
	//um, dont do that
    }

    @Override
    public boolean setTypeId(int i) {
	//um, dont do that
	return true;
    }

    @Override
    public boolean update() {
	return true;
    }

    @Override
    public boolean update(boolean bln) {
	return true;
    }

    @Override
    public byte getRawData() {
	//dont do this
	return 0;
    }

    @Override
    public void setRawData(byte b) {
	//dont do this
    }

    @Override
    public void setMetadata(String string, MetadataValue mv) {
	//dont do this
    }

    @Override
    public List<MetadataValue> getMetadata(String string) {
	return block.getMetadata(string);
    }

    @Override
    public boolean hasMetadata(String string) {
	return block.hasMetadata(string);
    }

    @Override
    public void removeMetadata(String string, Plugin plugin) {
	//dont do this
    }

    @Override
    public Inventory getBlockInventory() {
	return (Inventory) chest;
    }

    @Override
    public Inventory getInventory() {
	return (Inventory) chest;
    }
    
}
