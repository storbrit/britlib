package org.storbrit.britlib.block;

import net.devtech.arrp.json.models.JModel;
import net.minecraft.util.Identifier;

import java.util.NavigableMap;

public interface RuntimeBlockstateModelBlock extends RuntimeBlockstateBlock {
    NavigableMap<Identifier, JModel> getModels();
}
