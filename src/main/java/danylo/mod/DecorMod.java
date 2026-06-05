package danylo.mod;

import danylo.mod.block.CutoutBlock;
import danylo.mod.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;


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

		// To correctly apply transparency for yuushya blocks(without it the transparent parts are displayed as black textures).
		// Code needed because "render_type": "cutout" in model.json doesn't work.


		// Using @CutoutBlock annotation to correctly display textures, more info in CutoutBlock class
		for (Field field : ModBlocks.class.getDeclaredFields()) {
			if (!field.isAnnotationPresent(CutoutBlock.class)) continue;

            Object value;
            try {
                value = field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value instanceof Block block) {
				BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT);
			}
		}
	}
}