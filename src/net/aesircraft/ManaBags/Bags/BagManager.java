package net.aesircraft.ManaBags.Bags;

import net.aesircraft.ManaBags.Config.Config;
import net.aesircraft.ManaBags.ManaBags;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class BagManager {

    private Player player;
    private SpoutPlayer sp;

    public BagManager(Player player) {
	this.player = player;
	sp = SpoutManager.getPlayer(player);
    }

    public Player getPlayer() {
	return player;
    }

    public int getFirstFreeSlot() {
	PlayerBag b = new PlayerBag(player, 1);
	if (b.getType() == 0) {
	    if (!Config.getEnableSlot1()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.slot.1")) {
		    return 1;
		}
	    } else {
		return 1;
	    }
	}
	b = new PlayerBag(player, 2);
	if (b.getType() == 0) {
	    if (!Config.getEnableSlot2()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.slot.2")) {
		    return 2;
		}
	    } else {
		return 2;
	    }
	}
	b = new PlayerBag(player, 3);
	if (b.getType() == 0) {
	    if (!Config.getEnableSlot3()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.slot.3")) {
		    return 3;
		}
	    } else {
		return 3;
	    }
	}
	b = new PlayerBag(player, 4);
	if (b.getType() == 0) {
	    if (!Config.getEnableSlot4()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.slot.4")) {
		    return 4;
		}
	    } else {
		return 4;
	    }
	}
	return 0;
    }

    public int getFirstFreeUpgradeSlot() {
	PlayerBag b = new PlayerBag(player, 1);
	if (b.getType() == 1) {
	    if (!Config.getEnableUpgradeSlot1()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.upgrade.1")) {
		    return 1;
		}
	    } else {
		return 1;
	    }
	}
	b = new PlayerBag(player, 2);
	if (b.getType() == 1) {
	    if (!Config.getEnableUpgradeSlot2()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.upgrade.2")) {
		    return 2;
		}
	    } else {
		return 2;
	    }
	}
	b = new PlayerBag(player, 3);
	if (b.getType() == 1) {
	    if (!Config.getEnableUpgradeSlot3()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.upgrade.3")) {
		    return 3;
		}
	    } else {
		return 3;
	    }
	}
	b = new PlayerBag(player, 4);
	if (b.getType() == 1) {
	    if (!Config.getEnableUpgradeSlot4()) {
		return 0;
	    }
	    if (Config.getUsePermissions()) {
		if (ManaBags.permission.has(player, "manabags.user.upgrade.4")) {
		    return 4;
		}
	    } else {
		return 4;
	    }
	}
	return 0;
    }

    public void addBag() {
	int id = getFirstFreeSlot();
	if (id == 0) {
	    sp.sendNotification("§4Notice", "§eNo free Bag Slot open!", Material.CHEST);
	    return;
	}
	ItemStack i = sp.getItemInHand();
	i.setAmount(i.getAmount() - 1);
	if (i.getAmount() == 0) {
	    sp.getInventory().setItemInHand(null);
	} else {
	    sp.getInventory().setItemInHand(i);
	}
	PlayerBag pb = new PlayerBag(player, id);
	pb.upgradeType(1);
	sp.getWorld().playEffect(sp.getLocation(), Effect.SMOKE, 40);
	if (Config.getUseSoundEffects()) {
	    SpoutManager.getSoundManager().playCustomSoundEffect(ManaBags.getStatic(), sp, "http://aesircraft.net/items/poof.ogg", true);
	}
	sp.sendNotification("§eSlot " + id, "§5Added Mana Bag!", Material.CHEST);
    }

    public void addPermissionBag() {
	int id;
	if (!Config.userPermissions) {
	    if (player.isOp()) {
		while ((id = getFirstFreeSlot()) != 0) {
		    PlayerBag pb = new PlayerBag(player, id);
		    pb.upgradeType(1);
		    pb.upgradeType(2);

		}
	    }
	    return;
	}

	while ((id = getFirstFreeSlot()) != 0) {
	    if (ManaBags.permission.has(player, "manabags.special.bag." + id)) {
		PlayerBag pb = new PlayerBag(player, id);
		pb.upgradeType(1);
	    }
	}
	while ((id = getFirstFreeUpgradeSlot()) != 0) {
	    if (ManaBags.permission.has(player, "manabags.special.upgrade." + id)) {
		PlayerBag pb = new PlayerBag(player, id);
		pb.upgradeType(2);
	    }
	}
    }

    public boolean addCommandBag() {
	int id;
	id = getFirstFreeSlot();
	if (id == 0) {
	    return false;
	}

	PlayerBag pb = new PlayerBag(player, id);
	pb.upgradeType(1);
	return true;

    }

    public boolean addCommandUpgrade() {
	int id;
	id = getFirstFreeUpgradeSlot();
	if (id == 0) {
	    return false;
	}

	PlayerBag pb = new PlayerBag(player, id);
	pb.upgradeType(2);
	return true;
    }

    public void upgradeBag() {
	int id = getFirstFreeUpgradeSlot();
	if (id == 0) {
	    sp.sendNotification("§4Notice", "§eNo upgradable bags!", Material.CHEST);
	    return;
	}
	ItemStack i = sp.getItemInHand();
	i.setAmount(i.getAmount() - 1);
	if (i.getAmount() == 0) {
	    sp.getInventory().setItemInHand(null);
	} else {
	    sp.getInventory().setItemInHand(i);
	}
	PlayerBag pb = new PlayerBag(player, id);
	pb.upgradeType(2);
	sp.getWorld().playEffect(sp.getLocation(), Effect.SMOKE, 40);
	if (Config.getUseSoundEffects()) {
	    SpoutManager.getSoundManager().playCustomSoundEffect(ManaBags.getStatic(), sp, "http://aesircraft.net/items/poof.ogg", true);
	}
	sp.sendNotification("§eSlot " + id, "§5Now Diamond Weave Bag!", Material.CHEST);
    }
}
