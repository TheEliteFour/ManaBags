package net.aesircraft.ManaBags.Bags.Chests;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class ManaLocation {

    private Location location;
    private String string;

    private void setString() {
	String s = "";
	s = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
	string = s;
    }

    private void setLocation() {
	World world = Bukkit.getWorld(string.split(",")[0]);
	double x = Double.parseDouble(string.split(",")[1]);
	double y = Double.parseDouble(string.split(",")[2]);
	double z = Double.parseDouble(string.split(",")[3]);
	location = new Location(world, x, y, z);
    }

    public ManaLocation(Location loc) {
	location = loc;
	setString();
    }

    public ManaLocation(String loc) {
	string = loc;
	setLocation();
    }

    public String getString() {
	return string;
    }

    public Location getLocation() {
	return location;
    }
}
