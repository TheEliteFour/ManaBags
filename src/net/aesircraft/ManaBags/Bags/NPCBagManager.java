package net.aesircraft.ManaBags.Bags;

import net.aesircraft.ManaBags.API.NoFreeBagException;
import net.aesircraft.ManaBags.API.NoUpgradableBagException;

public class NPCBagManager {

    private String player;

    public NPCBagManager(String player) {
	this.player = player;
    }

    public String getName() {
	return player;
    }

    public int getFirstFreeSlot() {
	NPCBag b = new NPCBag(player, 1);
	if (b.getType() == 0) {
	    return 1;
	}
	b = new NPCBag(player, 2);
	if (b.getType() == 0) {

	    return 2;

	}
	b = new NPCBag(player, 3);
	if (b.getType() == 0) {

	    return 3;

	}
	b = new NPCBag(player, 4);
	if (b.getType() == 0) {

	    return 4;

	}
	return 0;
    }

    public int getFirstFreeUpgradeSlot() {
	NPCBag b = new NPCBag(player, 1);
	if (b.getType() == 1) {

	    return 1;

	}
	b = new NPCBag(player, 2);
	if (b.getType() == 1) {

	    return 2;

	}
	b = new NPCBag(player, 3);
	if (b.getType() == 1) {

	    return 3;

	}
	b = new NPCBag(player, 4);
	if (b.getType() == 1) {

	    return 4;

	}
	return 0;
    }

    public void addBag() throws NoFreeBagException {
	int id = getFirstFreeSlot();
	if (id == 0) {
	    throw new NoFreeBagException();
	}
	NPCBag pb = new NPCBag(player, id);
	pb.upgradeType(1);
    }

    public void upgradeBag() throws NoUpgradableBagException {
	int id = getFirstFreeUpgradeSlot();
	if (id == 0) {
	    throw new NoUpgradableBagException();
	}
	NPCBag pb = new NPCBag(player, id);
	pb.upgradeType(2);
    }
}
