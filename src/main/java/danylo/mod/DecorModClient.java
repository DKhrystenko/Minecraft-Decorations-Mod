package danylo.mod;

import danylo.mod.block.CutoutBlock;
import danylo.mod.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.Field;

public class DecorModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

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