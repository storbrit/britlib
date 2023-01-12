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

public class BlockRegistry {
    private static void makeBlockItemModel(Identifier id, RuntimeBlockstateModelBlock block) {
        Britlib.BRITLIB_PACK.addModel(JModel.model(block.getModels().firstKey()), id.withPrefix("item/"));
    }

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

    public static <B extends Block> B add(Identifier id, B block, ItemGroup group) {
        B result = add(id, block, false);
        ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()), group);
        if (block instanceof RuntimeBlockstateModelBlock) {
            makeBlockItemModel(id, (RuntimeBlockstateModelBlock) block);
        }
        return result;
    }

    public static <B extends Block> B add(Identifier id, B block, ItemGroup group, Item target) {
        B result = add(id, block, false);
        ItemRegistry.add(id, new BlockItem(result, new QuiltItemSettings()), group, target);
        if (block instanceof RuntimeBlockstateModelBlock) {
            makeBlockItemModel(id, (RuntimeBlockstateModelBlock) block);
        }
        return result;
    }
}
