package org.storbrit.britlib.reg;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apiguardian.api.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper functions for creating blocks, dynamically generating their models, and inserting them into item groups.
 */
public final class ItemRegistry {
	private static final Map<Item, List<Item>> TARGETS = new HashMap<>();

	/**
	 * Adds an item to the registry.
	 *
	 * @param id   the identifier of the item
	 * @param item the item to register
	 * @param <I>  any item that extends {@link Item}
	 * @return the registered item
	 */
	@API(status = API.Status.STABLE)
	public static <I extends Item> I add(Identifier id, I item) {
		return Registry.register(Registries.ITEM, id, item);
	}

	/**
	 * Adds an item to the registry and inserts it into an item group.
	 *
	 * @param id    the identifier of the item
	 * @param item  the item to register
	 * @param group the item group to add the item to
	 * @param <I>   any item that extends {@link Item}
	 * @return the registered item
	 */
	@API(status = API.Status.STABLE)
	public static <I extends Item> I add(Identifier id, I item, ItemGroup group) {
		I result = add(id, item);
		ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addItem(result));
		return result;
	}

	/**
	 * Adds an item to the registry and inserts it into an item group after a target.
	 *
	 * @param id     the identifier of the item
	 * @param item   the item to register
	 * @param group  the item group to add the item to
	 * @param target the target item to insert the registered item after
	 * @param <I>    any item that extends {@link Item}
	 * @return the registered item
	 */
	// Maybe get rid of the group parameter and autodetect it from the target, although I've tried doing that locally,
	// and it doesn't seem to work
	@API(status = API.Status.STABLE)
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
