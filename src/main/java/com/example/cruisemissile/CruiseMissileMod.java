package com.example.cruisemissile;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(CruiseMissileMod.MODID)
public class CruiseMissileMod {
    public static final String MODID = "cruisemissile";

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> CRUISE_MISSILE = ITEMS.register("cruise_missile",
            () -> new CruiseMissileItem(new Item.Properties().stacksTo(16)));

    public CruiseMissileMod(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addToCreativeTab);
    }

    private void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(CRUISE_MISSILE);
        }
    }
}
