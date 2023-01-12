package org.storbrit.britlib.reg;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.storbrit.britlib.Britlib;
import org.storbrit.britlib.item.RuntimeModelItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRegistry {
    private static final Map<Item, List<Item>> TARGETS = new HashMap<>();

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

        if (!TARGETS.containsKey(target)) {
            TARGETS.put(target, new ArrayList<>(List.of(target)));
        }

        List<Item> newList = TARGETS.get(target);
        newList.add(result);
        TARGETS.put(target, newList);

        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(
            TARGETS.get(target).get(TARGETS.get(target).indexOf(result) - 1), result));

        return result;
    }
}
