package org.storbrit.britlib.reg;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.storbrit.britlib.Britlib;
import org.storbrit.britlib.item.RuntimeModelItem;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static final Map<Item, Integer> OFFSETS = new HashMap<>();

    public static <I extends Item> I add(Identifier id, I item) {
        I result = Registry.register(Registries.ITEM, id, item);

        if (item instanceof RuntimeModelItem) {
            Britlib.BRITLIB_PACK.addModel(((RuntimeModelItem) item).getModel(), id.withPrefix("item/"));
        }

        return result;
    }

    public static <I extends Item> I add(Identifier id, I item, ItemGroup group) {
        I result = add(id, item);
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addItem(item));
        return result;
    }

    @SuppressWarnings("UnstableApiUsage")
    public static <I extends Item> I add(Identifier id, I item, ItemGroup group, Item target) {
        I result = add(id, item);

        if (!OFFSETS.containsKey(target)) {
            OFFSETS.put(target, 0);
        }
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(content.getDisplayStacks().get(
            content.getDisplayStacks().indexOf(target) + OFFSETS.get(target)), item));

        OFFSETS.put(target, OFFSETS.get(target) + 1);

        return result;
    }
}
