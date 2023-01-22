package org.storbrit.britlib.reg;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apiguardian.api.API;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

/**
 * Wrapper functions for creating blocks and block items with the {@link ItemRegistry}.
 */
public final class BlockRegistry {
    /**
     * Adds a block to the registry.
     *
     * @param id            the identifier of the block
     * @param block         the block to register
     * @param makeBlockItem whether to generate a block item
     * @param <B>           any block that extends {@link Block}
     * @return the registered block
     */
    @API(status = API.Status.STABLE)
    public static <B extends Block> B add(Identifier id, B block, boolean makeBlockItem) {
        B result = Registry.register(Registries.BLOCK, id, block);
        if (makeBlockItem) {
            ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()));
        }

        return result;
    }

    /**
     * Adds a block to the registry and inserts it into an item group.
     *
     * @param id    the identifier of the block
     * @param block the block to register
     * @param group the group to add the block to
     * @param <B>   any block that extends {@link Block}
     * @return the registered block
     */
    @API(status = API.Status.STABLE)
    public static <B extends Block> B add(Identifier id, B block, ItemGroup group) {
        B result = add(id, block, false);
        ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()), group);
        return result;
    }

    /**
     * Adds a block to the registry and inserts it into an item group after a target.
     *
     * @param id     the identifier of the block
     * @param block  the block to register
     * @param group  the group to add the block to (must match the target item)
     * @param target the target item to insert the registered block after
     * @param <B>    any block that extends {@link Block}
     * @return the registered block
     */
    @API(status = API.Status.STABLE)
    public static <B extends Block> B add(Identifier id, B block, ItemGroup group, Item target) {
        B result = add(id, block, false);
        ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()), group, target);
        return result;
    }
}
