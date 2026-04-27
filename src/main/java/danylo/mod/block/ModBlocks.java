package danylo.mod.block;

import danylo.mod.DecorMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

/**
 * All new blocks are registered in this class.
 */
public class ModBlocks {

    public static final Block TILE = register(
            "tile",
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );


    public static final Block BRICK = register(
            "brick",
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.STONE),
            true
    );

    // SlabBlock to change collision, .noOcclusion() forces to render the texture's bottom (without it the bottom is transparent)
    public static final Block BOOKS = register(
            "books",
            (props) -> new BooksPile(BooksPile.DEFAULT_BOX, props),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );


    public static void initialize() {}

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DecorMod.MOD_ID, name));
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(DecorMod.MOD_ID, name));
    }
}
