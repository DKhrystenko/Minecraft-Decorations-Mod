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
import net.minecraft.world.phys.shapes.VoxelShape;

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


    // .noOcclusion() forces to render the texture's bottom (without it the bottom is transparent)
    public static final Block BOOKS = register(
            "books",
            (props) -> new BooksPile(CustomDirectionalBlock.builder(props, BooksPile.DEFAULT_BOX)),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );


    public static final Block WALL_LAMP = register(
            "wall_lamp",
            (props) -> new WallLamp(CustomDirectionalBlock.builder(props, WallLamp.DEFAULT_BOX).faceTowardsPlayer()),
            BlockBehaviour.Properties.of().sound(SoundType.GLASS).noOcclusion().lightLevel(WallLamp::getLuminance),
            true
    );


    public static final VoxelShape EASEL_BOX = Block.box(0, 0, 0, 16, 32, 16);
    public static final Block EASEL_EMPTY = register(
            "easel_empty",
            (props) -> CustomDirectionalBlock.builder(props, EASEL_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final Block EASEL_WITH_CANVAS = register(
            "easel_with_canvas",
            (props) -> CustomDirectionalBlock.builder(props, EASEL_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape MOUNTED_MIRROR_BOX = Block.box(2, 0, 0, 14, 15, 1);
    public static final Block MOUNTED_MIRROR = register(
            "mounted_mirror",
            (props) -> CustomDirectionalBlock.builder(props, MOUNTED_MIRROR_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );


    public static final Block PIGMENT_BUCKET = register(
            "pigment_bucket",
            (props) -> new PigmentBucket(CustomDirectionalBlock.builder(props, PigmentBucket.DEFAULT_BOX).faceTowardsPlayer()),
            BlockBehaviour.Properties.of().sound(SoundType.STONE).noOcclusion(),
            true
    );


    public static final Block ALBUM = register(
            "album",
            (props) -> new Album(CustomDirectionalBlock.builder(props, Album.DEFAULT_BOX).faceTowardsPlayer()),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );


    public static final VoxelShape POTTED_FLOWER_BASKET_BOX = Block.box(2, 0, 0, 14, 8, 12);
    @CutoutBlock public static final Block POTTED_FLOWER_BASKET = register(
            "potted_flower_basket",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_FLOWER_BASKET_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_SUNFLOWER_BOX = Block.box(4, 0, 1, 12, 13, 9);
    @CutoutBlock public static final Block POTTED_SUNFLOWER = register(
            "potted_sunflower",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_SUNFLOWER_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_JUNGLE_SPROUT_BOX = Block.box(4, 0, 4, 12, 8, 12);
    @CutoutBlock public static final Block POTTED_JUNGLE_SPROUT = register(
            "potted_jungle_sprout",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_JUNGLE_SPROUT_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_AZURE_BLUET_BOX = Block.box(4, 0, 1, 12, 8, 9);
    @CutoutBlock public static final Block POTTED_AZURE_BLUET = register(
            "potted_azure_bluet",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_AZURE_BLUET_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_SAKURA_SPROUT_BOX = Block.box(4, 0, 4, 12, 6, 12);
    @CutoutBlock public static final Block POTTED_SAKURA_SPROUT = register(
            "potted_sakura_sprout",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_SAKURA_SPROUT_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_MAPLE_SPROUT_BOX = Block.box(4, 0, 4, 12, 6, 12);
    @CutoutBlock public static final Block POTTED_MAPLE_SPROUT = register(
            "potted_maple_sprout",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_MAPLE_SPROUT_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_SPROUT_BOX = Block.box(4, 0, 0, 12, 6, 8);
    @CutoutBlock public static final Block POTTED_SPROUT = register(
            "potted_sprout",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_SPROUT_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_ALLIUM_BOX = Block.box(4, 0, 0, 12, 8, 8);
    @CutoutBlock public static final Block POTTED_ALLIUM = register(
            "potted_allium",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_ALLIUM_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape POTTED_FERN_BOX = Block.box(4, 0, 1, 12, 8, 9);
    @CutoutBlock public static final Block POTTED_FERN = register(
            "potted_fern",
            (props) -> CustomDirectionalBlock.builder(props, POTTED_FERN_BOX).faceTowardsPlayer().build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion(),
            true
    );

    public static final VoxelShape AZURE_BLUET_BOX = Block.box(0, 0, 0, 16, 4, 16);
    @CutoutBlock public static final Block AZURE_BLUET = register(
            "azure_bluet",
            (props) -> CustomDirectionalBlock.builder(props, AZURE_BLUET_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion().noCollision(),
            true
    );

    public static final VoxelShape WHITE_TULIP_BOX = Block.box(0, 0, 0, 16, 4, 16);
    @CutoutBlock public static final Block  WHITE_TULIP = register(
            "white_tulip",
            (props) -> CustomDirectionalBlock.builder(props, WHITE_TULIP_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion().noCollision(),
            true
    );

    public static final VoxelShape GOLD_TULIP_BOX = Block.box(0, 0, 0, 16, 4, 16);
    @CutoutBlock public static final Block GOLD_TULIP = register(
            "gold_tulip",
            (props) -> CustomDirectionalBlock.builder(props, GOLD_TULIP_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion().noCollision(),
            true
    );

    public static final VoxelShape PINK_TULIP_BOX = Block.box(0, 0, 0, 16, 4, 16);
    @CutoutBlock public static final Block PINK_TULIP = register(
            "pink_tulip",
            (props) -> CustomDirectionalBlock.builder(props, PINK_TULIP_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion().noCollision(),
            true
    );

    public static final VoxelShape RED_TULIP_BOX = Block.box(0, 0, 0, 16, 4, 16);
    @CutoutBlock public static final Block RED_TULIP = register(
            "red_tulip",
            (props) -> CustomDirectionalBlock.builder(props, RED_TULIP_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion().noCollision(),
            true
    );









    public static void initialize() {}

    @SuppressWarnings("SameParameterValue")
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
