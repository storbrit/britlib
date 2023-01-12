package org.storbrit.britlib.block;

import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.*;

/**
 * Extension of {@link net.minecraft.block.Block} that implements {@link RuntimeBlockstateModelBlock}.
 */
public class Block extends net.minecraft.block.Block implements RuntimeBlockstateModelBlock {
    public Block(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public JState getBlockState() {
        return JState.state(
            JState.variant(
                JState.model(Registries.BLOCK.getId(this)
                    .withPrefix("block/"))));
    }

    @Override
    public NavigableMap<Identifier, JModel> getModels() {
        return new TreeMap<>() {{
            put(Registries.BLOCK.getId(Block.this).withPrefix("block/"),
                JModel.model("minecraft:block/cube_all")
                    .textures(
                        JModel.textures()
                            .var("all", Registries.BLOCK.getId(Block.this)
                                .withPrefix("block/").toString())
                    )
            );
        }};
    }
}
