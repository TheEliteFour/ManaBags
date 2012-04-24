package net.aesircraft.ManaBags.Bags.Chests;

import java.util.Collection;
import java.util.List;
import net.aesircraft.ManaBags.Bags.VirtualChest;
import net.aesircraft.ManaBags.Items.ManaMaterial;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.inventory.SpoutItemStack;

public class FakeChest implements Block {

    private Block block;
    private VirtualChest chest;
    private Collection<ItemStack> drops;

    public FakeChest(Block block, VirtualChest chest) {
	this.block = block;
	this.chest = chest;
	setDrops();
    }

    private void setDrops() {
	Collection<ItemStack> drops = block.getDrops();
	drops.clear();
	for (ItemStack i : chest.getContents()) {
	    drops.add(i);
	}
	//DROP CHEST TOO!
	drops.add(new SpoutItemStack(ManaMaterial.manaChamber.getBlockItem(), 1));
	this.drops = drops;
    }

    @Override
    public byte getData() {
	return 2;
    }

    @Override
    public Block getRelative(int i, int i1, int i2) {
	return block.getRelative(i, i1, i2);
    }

    @Override
    public Block getRelative(BlockFace bf) {
	return block.getRelative(bf);
    }

    @Override
    public Block getRelative(BlockFace bf, int i) {
	return block.getRelative(bf, i);
    }

    @Override
    public Material getType() {
	return Material.CHEST;
    }

    @Override
    public int getTypeId() {
	return Material.CHEST.getId();
    }

    @Override
    public byte getLightLevel() {
	return block.getLightLevel();
    }

    @Override
    public byte getLightFromSky() {
	return block.getLightFromSky();
    }

    @Override
    public byte getLightFromBlocks() {
	return block.getLightFromBlocks();
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
	return block.getY();
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
    public void setData(byte b) {
	//doesnt need this
    }

    @Override
    public void setData(byte b, boolean bln) {
	//doesnt need this
    }

    @Override
    public void setType(Material mtrl) {
	//doesnt need this
    }

    @Override
    public boolean setTypeId(int i) {
	//doesnt need this
	return true;
    }

    @Override
    public boolean setTypeId(int i, boolean bln) {
	//doesnt need this
	return true;
    }

    @Override
    public boolean setTypeIdAndData(int i, byte b, boolean bln) {
	//doesnt need this
	return true;
    }

    @Override
    public BlockFace getFace(Block block) {
	return block.getFace(block);
    }

    @Override
    public BlockState getState() {
	FakeState st=new FakeState(this,chest);
	return st;
    }

    @Override
    public Biome getBiome() {
	return block.getBiome();
    }

    @Override
    public void setBiome(Biome biome) {
	//doesnt need this
    }

    @Override
    public boolean isBlockPowered() {
	return block.isBlockPowered();
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
	return block.isBlockIndirectlyPowered();
    }

    @Override
    public boolean isBlockFacePowered(BlockFace bf) {
	return block.isBlockFacePowered(bf);
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace bf) {
	return block.isBlockFaceIndirectlyPowered(bf);
    }

    @Override
    public int getBlockPower(BlockFace bf) {
	return block.getBlockPower(bf);
    }

    @Override
    public int getBlockPower() {
	return block.getBlockPower();
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

    @Override
    public boolean isLiquid() {
	return false;
    }

    @Override
    public double getTemperature() {
	return block.getTemperature();
    }

    @Override
    public double getHumidity() {
	return block.getHumidity();
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
	return PistonMoveReaction.BLOCK;
    }

    @Override
    public boolean breakNaturally() {
	return false;
    }

    @Override
    public boolean breakNaturally(ItemStack is) {
	return false;
    }

    @Override
    public Collection<ItemStack> getDrops() {
	return drops;
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack is) {
	return drops;
    }

    @Override
    public void setMetadata(String string, MetadataValue mv) {
	//doesnt need this
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
	//doesnt need this
    }
}
