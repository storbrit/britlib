package org.storbrit.britlib.block;

import net.devtech.arrp.json.blockstate.JState;

/**
 * A block which has a block state in the runtime resource pack ({@link JState}).
 */
public interface RuntimeBlockstateBlock {
    /**
     * Gets the block's block state.
     *
     * @return the block's block state
     */
    JState getBlockState();
}
