package danylo.mod;

import danylo.mod.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Starting class of the mod(In fabric.mod.json, the entrypoint is defined
 * with main key). Mod starts inside the .onInitialize() method.
 */
public class DecorMod implements ModInitializer {
	public static final String MOD_ID = "danylo-decor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("DecorMod Initialized");

		ModBlocks.initialize();
//		ModItems.initialize();

		DecorModCreativeTab.registerCustomTab();
	}
}