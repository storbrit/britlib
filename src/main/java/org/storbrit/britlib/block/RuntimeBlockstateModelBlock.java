package org.storbrit.britlib.block;

import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.util.Identifier;

import java.util.NavigableMap;

/**
 * A block which has a block state and models in the runtime resource pack ({@link JState}).
 */
public interface RuntimeBlockstateModelBlock extends RuntimeBlockstateBlock {
    /**
     * Gets a map of the block's models. The first model in the map be the one used in the block item.
     *
     * @return a map of the block's models
     */
    NavigableMap<Identifier, JModel> getModels();
}
