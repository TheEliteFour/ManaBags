package net.aesircraft.ManaBags.Bags.Chests;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import net.aesircraft.ManaBags.Bags.VirtualChest;
import net.aesircraft.ManaBags.Items.ManaMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.SpoutChunk;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;

public class FakeChest implements Block, SpoutBlock{

    private Block block;
    private VirtualChest chest;
    private Collection<ItemStack> drops;

    public FakeChest(Block block, VirtualChest chest) {
	this.block = block;
	this.chest = chest;
	setDrops();
    }

    public Collection<ItemStack> getFakeDrops() {
	return drops;
    }

    private void setDrops() {
	Collection<ItemStack> drops = block.getDrops();
	drops.clear();
	for (ItemStack i : chest.getContents()) {
	    drops.add(i);
	}
	//DROP CHEST TOO!
	drops.add(ManaMaterial.manaChamber.getItemDrop());
	this.drops = drops;
    }

    @Override
    public byte getData() {
	return 2;
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
	FakeState st = new FakeState(this, chest);
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

    @Override
    public void setTypeAsync(Material mtrl) {
	//again no
    }

    @Override
    public void setTypeIdAsync(int i) {
	//not quite
    }

    @Override
    public void setDataAsync(byte b) {
	//what need do you have
    }

    @Override
    public void setTypeIdAndDataAsync(int i, byte b) {
	//um no?
    }

    @Override
    public void setCustomBlock(CustomBlock cb) {
	//dont dothat either
    }

    @Override
    public Serializable setData(String string, Serializable srlzbl) {
	//dont do that
	return null;
    }

    @Override
    public Serializable getData(String string) {
	return ((SpoutBlock) block).getData(string);
    }

    @Override
    public Serializable removeData(String string) {
	return ((SpoutBlock) block).removeData(string);
    }

    @Override
    public String getName() {
	return ((SpoutBlock) block).getName();
    }

    @Override
    public void setBlockPowered(boolean bln) {
	((SpoutBlock) block).setBlockPowered(bln);
    }

    @Override
    public void setBlockPowered(boolean bln, BlockFace bf) {
	((SpoutBlock) block).setBlockPowered(bln, bf);
    }

    @Override
    public void resetBlockPower() {
	((SpoutBlock) block).resetBlockPower();
    }

    @Override
    public org.getspout.spoutapi.material.Block getBlockType() {
	return ((SpoutBlock) block).getBlockType();
    }

    @Override
    public SpoutItemStack toItemStack() {
	return ((SpoutBlock) block).toItemStack();
    }

    @Override
    public SpoutItemStack toItemStack(int i) {
	return ((SpoutBlock) block).toItemStack(i);
    }

    @Override
    public SpoutBlock getRelative(int i, int i1, int i2) {
	return ((SpoutBlock) block).getRelative(i, i1, i2);
    }

    @Override
    public SpoutBlock getRelative(BlockFace bf) {
	return ((SpoutBlock) block).getRelative(bf);
    }

    @Override
    public SpoutChunk getChunk() {
	return ((SpoutBlock) block).getChunk();
    }

    @Override
    public short getCustomBlockId() {
	return ((SpoutBlock) block).getCustomBlockId();
    }

    @Override
    public boolean isCustomBlock() {
	return true;
    }

    @Override
    public void removeCustomBlockData() {
	//dont do that
    }

    @Override
    public CustomBlock getCustomBlock() {
	return ((SpoutBlock) block).getCustomBlock();
    }
}
