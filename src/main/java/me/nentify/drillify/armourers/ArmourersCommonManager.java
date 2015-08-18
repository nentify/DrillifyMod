package me.nentify.drillify.armourers;

import me.nentify.drillify.armourers.skin.DrillSkin;
import riskyken.armourersWorkshop.api.common.IArmourersCommonManager;
import riskyken.armourersWorkshop.api.common.skin.ISkinDataHandler;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.entity.IEntitySkinHandler;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinTypeRegistry;

import java.io.InputStream;

public class ArmourersCommonManager implements IArmourersCommonManager {

    public static ArmourersCommonManager instance;

    private ISkinDataHandler skinDataHandler;
    private ISkinTypeRegistry skinTypeRegistry;
    private IEntitySkinHandler npcSkinDataHandler;

    public ISkinType drillSkin;

    public ISkinPointer drillSkinIron;
    public ISkinPointer drillSkinDiamond;
    public ISkinPointer drillSkinObsidian;

    public ArmourersCommonManager() {
        instance = this;
    }

    @Override
    public void onLoad(ISkinDataHandler skinDataHandler, ISkinTypeRegistry skinTypeRegistry, IEntitySkinHandler npcSkinDataHandler) {
        this.skinDataHandler = skinDataHandler;
        this.skinTypeRegistry = skinTypeRegistry;
        this.npcSkinDataHandler = npcSkinDataHandler;
    }

    public void postInit() {
        drillSkin = new DrillSkin();
        skinTypeRegistry.registerSkin(drillSkin);

        // TODO Move this into a server side world load event.
        drillSkinIron = loadSkin("Drill Iron.armour");
        drillSkinDiamond = loadSkin("Drill Diamond.armour");
        drillSkinObsidian = loadSkin("Drill Obsidian.armour");
    }

    private ISkinPointer loadSkin(String skinName) {
        InputStream inputStream = getClass().getResourceAsStream("/assets/drillify/skins/" + skinName);
        return skinDataHandler.addSkinToCache(inputStream);
    }
}
