package org.storbrit.testmod;

import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.storbrit.britlib.item.Item;
import org.storbrit.britlib.reg.ItemRegistry;

public class Testmod implements ModInitializer {
    public static final String MOD_ID = "britlib_testmod";

    public static Item CAKE_SIDE;
    public static Item STONKS;

    @Override
    public void onInitialize(ModContainer mod) {
        CAKE_SIDE = ItemRegistry.add(new Identifier(MOD_ID, "cake_side"), new Item(new QuiltItemSettings()), ItemGroups.FOOD_AND_DRINK, Items.APPLE);
        STONKS = ItemRegistry.add(new Identifier(MOD_ID, "stonks"), new Item(new QuiltItemSettings()), ItemGroups.FOOD_AND_DRINK, Items.APPLE);
    }
}
