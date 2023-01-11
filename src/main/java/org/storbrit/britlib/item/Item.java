package org.storbrit.britlib.item;

import net.devtech.arrp.json.models.JModel;
import net.minecraft.registry.Registries;

public class Item extends net.minecraft.item.Item implements RuntimeModelItem {
    public Item(Settings settings) {
        super(settings);
    }

    @Override
    public JModel getModel() {
        return JModel.model("minecraft:item/generated").textures(
            JModel.textures()
                .layer0(Registries.ITEM.getId(this).withPrefix("item/").toString()));
    }
}
