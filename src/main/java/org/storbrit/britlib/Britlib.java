package org.storbrit.britlib;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Britlib implements ModInitializer {
	public static final String MOD_ID = "britlib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final RuntimeResourcePack BRITLIB_PACK = RuntimeResourcePack.create(new Identifier(MOD_ID, "pack"));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Registering Britlib resource pack");
		RRPCallback.BEFORE_VANILLA.register(listener -> listener.add(BRITLIB_PACK));
	}
}
