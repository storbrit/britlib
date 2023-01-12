package org.storbrit.britlib.reg;

import net.devtech.arrp.json.models.JModel;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.storbrit.britlib.Britlib;
import org.storbrit.britlib.block.RuntimeBlockstateBlock;
import org.storbrit.britlib.block.RuntimeBlockstateModelBlock;

import java.util.Map;

/**
 * Wrapper functions for creating blocks, dynamically generating their block states and models, and creating block
 * items with the {@link ItemRegistry}.
 */
public class BlockRegistry {
    private static void makeBlockItemModel(Identifier id, RuntimeBlockstateModelBlock block) {
        Britlib.BRITLIB_PACK.addModel(JModel.model(block.getModels().firstKey()), id.withPrefix("item/"));
    }

    /**
     * Adds a block to the registry.
     *
     * @param id            the identifier of the block
     * @param block         the block to register
     * @param makeBlockItem whether to generate a block item
     * @param <B>           any block that extends {@link Block}
     * @return the registered block
     */
    public static <B extends Block> B add(Identifier id, B block, boolean makeBlockItem) {
        B result = Registry.register(Registries.BLOCK, id, block);

        if (block instanceof RuntimeBlockstateBlock) {
            Britlib.BRITLIB_PACK.addBlockState(((RuntimeBlockstateBlock) block).getBlockState(), id);

            if (block instanceof RuntimeBlockstateModelBlock) {
                for (Map.Entry<Identifier, JModel> entry :
                    ((RuntimeBlockstateModelBlock) block).getModels().entrySet()) {
                    Britlib.BRITLIB_PACK.addModel(entry.getValue(), entry.getKey());
                }
            }
        }

        if (makeBlockItem) {
            ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()));
            if (block instanceof RuntimeBlockstateModelBlock) {
                makeBlockItemModel(id, (RuntimeBlockstateModelBlock) block);
            }
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
    public static <B extends Block> B add(Identifier id, B block, ItemGroup group) {
        B result = add(id, block, false);
        ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()), group);
        if (block instanceof RuntimeBlockstateModelBlock) {
            makeBlockItemModel(id, (RuntimeBlockstateModelBlock) block);
        }
        return result;
    }

    /**
     * Adds a block to the registry and inserts it into an item group after a target.
     *
     * @param id     the identifier of the block
     * @param block  the block to register
     * @param group  the group to add the block to
     * @param target the target item to insert the registered block after
     * @param <B>    any block that extends {@link Block}
     * @return the registered block
     */
    public static <B extends Block> B add(Identifier id, B block, ItemGroup group, Item target) {
        B result = add(id, block, false);
        ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()), group, target);
        if (block instanceof RuntimeBlockstateModelBlock) {
            makeBlockItemModel(id, (RuntimeBlockstateModelBlock) block);
        }
        return result;
    }
}
