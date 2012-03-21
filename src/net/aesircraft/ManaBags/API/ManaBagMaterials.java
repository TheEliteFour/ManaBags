package net.aesircraft.ManaBags.API;

import net.aesircraft.ManaBags.Items.*;

public class ManaBagMaterials {

    private DiamondThread DIAMOND_THREAD = ManaMaterial.diamondThread;
    private DiamondWeave DIAMOND_WEAVE = ManaMaterial.diamondWeave;
    private DiamondWeaveUpgrade DIAMOND_WEAVE_UPGRADE = ManaMaterial.diamondWeaveUpgrade;
    private ManaThread MANA_THREAD = ManaMaterial.manaThread;
    private ManaDust MANA_DUST = ManaMaterial.manaDust;
    private ManaCloth MANA_CLOTH = ManaMaterial.manaCloth;
    private ManaBag MANA_BAG = ManaMaterial.manaBag;
    private ManaWorkbench MANA_WORKBENCH = ManaMaterial.manaWorkbench;

    public DiamondThread getDiamondThread() throws FeatureDisabledException {
	if (DIAMOND_THREAD == null) {
	    throw new FeatureDisabledException();
	}
	return DIAMOND_THREAD;
    }

    public DiamondWeave getDiamondWeave() throws FeatureDisabledException {
	if (DIAMOND_WEAVE == null) {
	    throw new FeatureDisabledException();
	}
	return DIAMOND_WEAVE;
    }

    public DiamondWeaveUpgrade getDiamondWeaveUpgrade() throws FeatureDisabledException {
	if (DIAMOND_WEAVE_UPGRADE == null) {
	    throw new FeatureDisabledException();
	}
	return DIAMOND_WEAVE_UPGRADE;
    }

    public ManaThread getManaThread() throws FeatureDisabledException {
	if (MANA_THREAD == null) {
	    throw new FeatureDisabledException();
	}
	return MANA_THREAD;
    }

    public ManaDust getManaDust() throws FeatureDisabledException {
	if (MANA_DUST == null) {
	    throw new FeatureDisabledException();
	}
	return MANA_DUST;
    }

    public ManaCloth getManaCloth() throws FeatureDisabledException {
	if (MANA_CLOTH == null) {
	    throw new FeatureDisabledException();
	}
	return MANA_CLOTH;
    }

    public ManaBag getManaBag() throws FeatureDisabledException {
	if (MANA_BAG == null) {
	    throw new FeatureDisabledException();
	}
	return MANA_BAG;
    }

    public ManaWorkbench getManaWorkbench() throws FeatureDisabledException {
	if (MANA_WORKBENCH == null) {
	    throw new FeatureDisabledException();
	}
	return MANA_WORKBENCH;
    }
}
