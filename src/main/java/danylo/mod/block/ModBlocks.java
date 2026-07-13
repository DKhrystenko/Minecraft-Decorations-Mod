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

    public static final VoxelShape WALL_LAMP_BOX = Block.box(4, 7, 0, 13, 15, 7);
    public static final Block WALL_LAMP = register(
            "wall_lamp",
            (props) -> new ToggledLightBlock(CustomDirectionalBlock.builder(props, WALL_LAMP_BOX).faceTowardsPlayer()),
            BlockBehaviour.Properties.of().sound(SoundType.GLASS).noOcclusion().lightLevel(ToggledLightBlock.luminance(12)),
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



    // Farmhouse Decorations
    public static final VoxelShape ACOUSTIC_GUITAR_BOX = Block.box(4, 0.21, 6.904, 12, 18.962, 15.989);
    public static final Block ACOUSTIC_GUITAR = register(
            "acoustic_guitar",
            (props) -> CustomDirectionalBlock.builder(props, ACOUSTIC_GUITAR_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape ANCIENT_TV_BOX = Block.box(1, 0, 5.75, 15, 11, 15);
    public static final Block ANCIENT_TV = register(
            "ancient_tv",
            (props) -> CustomDirectionalBlock.builder(props, ANCIENT_TV_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape ANCIENT_TV_WITH_LEGS_BOX = Block.box(1, 0, 5.75, 15, 16, 15);
    public static final Block ANCIENT_TV_WITH_LEGS = register(
            "ancient_tv_with_legs",
            (props) -> CustomDirectionalBlock.builder(props, ANCIENT_TV_WITH_LEGS_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape ANTIQUE_OVEN_BOX = Block.box(0, 0, 5.5, 16, 27.25, 16);
    public static final Block ANTIQUE_OVEN = register(
            "antique_oven",
            (props) -> CustomDirectionalBlock.builder(props, ANTIQUE_OVEN_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape BATHROOM_CABINET_BOX = Block.box(-0.75, 0, 0, 16.75, 31.75, 16);
    public static final Block BATHROOM_CABINET = register(
            "bathroom_cabinet",
            (props) -> CustomDirectionalBlock.builder(props, BATHROOM_CABINET_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape BATHROOM_COUNTER_BOX = Block.box(0, 0, 0, 16, 16, 16);
    public static final Block BATHROOM_COUNTER = register(
            "bathroom_counter",
            (props) -> CustomDirectionalBlock.builder(props, BATHROOM_COUNTER_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape BATHROOM_SINK_BOX = Block.box(0, 0, 0, 16, 7, 16);
    public static final Block BATHROOM_SINK = register(
            "bathroom_sink",
            (props) -> CustomDirectionalBlock.builder(props, BATHROOM_SINK_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape BATHTUB_BOX = Block.box(-12, 0, 1, 28, 11, 15.5);
    public static final Block BATHTUB = register(
            "bathtub",
            (props) -> CustomDirectionalBlock.builder(props, BATHTUB_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape BIG_BATHROOM_MIRROR_BOX = Block.box(-9, 4.5, 14.5, 25, 29, 16);
    public static final Block BIG_BATHROOM_MIRROR = register(
            "big_bathroom_mirror",
            (props) -> CustomDirectionalBlock.builder(props, BIG_BATHROOM_MIRROR_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape CHAINED_KEROSENE_LANTERN_BOX = Block.box(5.925, 5.25, 6, 10.075, 16, 10);
    public static final Block CHAINED_KEROSENE_LANTERN = register(
            "chained_kerosene_lantern",
            (props) -> new ToggledLightBlock(CustomDirectionalBlock.builder(props, CHAINED_KEROSENE_LANTERN_BOX)),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().lightLevel(ToggledLightBlock.luminance(12)),
            true
    );

    public static final VoxelShape CIGAR_BOX_BOX = Block.box(4, 0, 5, 12, 8.75, 11.75);
    public static final Block CIGAR_BOX = register(
            "cigar_box",
            (props) -> CustomDirectionalBlock.builder(props, CIGAR_BOX_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape COAT_HANGER_BOX = Block.box(3, -0.765, 3, 13, 31.25, 13);
    public static final Block COAT_HANGER = register(
            "coat_hanger",
            (props) -> CustomDirectionalBlock.builder(props, COAT_HANGER_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape COFFEE_TABLE_BOX = Block.box(-1, 0, -1, 17, 8, 17);
    public static final Block COFFEE_TABLE = register(
            "coffee_table",
            (props) -> CustomDirectionalBlock.builder(props, COFFEE_TABLE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape CUCKOO_CLOCK_BOX = Block.box(3.25, 2.75, 13, 12.75, 15.5, 16);
    public static final Block CUCKOO_CLOCK = register(
            "cuckoo_clock",
            (props) -> CustomDirectionalBlock.builder(props, CUCKOO_CLOCK_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DESK_BOX = Block.box(-15.25, 0, -0.5, 31.75, 16.25, 16.5);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block DESK = register(
            "desk",
            (props) -> CustomDirectionalBlock.builder(props, DESK_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DINING_CHAIR_BOX = Block.box(2, 0, 0, 14, 25.003, 15.569);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block DINING_CHAIR = register(
            "dining_chair",
            (props) -> CustomDirectionalBlock.builder(props, DINING_CHAIR_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DINING_TABLE_MIDDLE_BOX = Block.box(0, 12.75, 0.25, 16, 16, 15.75);
    public static final Block DINING_TABLE_MIDDLE = register(
            "dining_table_middle",
            (props) -> CustomDirectionalBlock.builder(props, DINING_TABLE_MIDDLE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DINING_TABLE_SIDE_BOX = Block.box(0, -0.25, 0.25, 16, 16, 15.75);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block DINING_TABLE_SIDE = register(
            "dining_table_side",
            (props) -> CustomDirectionalBlock.builder(props, DINING_TABLE_SIDE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DOOR_MAT_BOX = Block.box(1, 0, 6, 15, 0.5, 15);
    public static final Block DOOR_MAT = register(
            "door_mat",
            (props) -> CustomDirectionalBlock.builder(props, DOOR_MAT_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DOUBLE_BED_BOX = Block.box(-15.75, 0, 0.25, 15.75, 19.25, 31.75);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block DOUBLE_BED = register(
            "double_bed",
            (props) -> CustomDirectionalBlock.builder(props, DOUBLE_BED_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape DRESSER_BOX = Block.box(0, 0, 4, 16, 16.25, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block DRESSER = register(
            "dresser",
            (props) -> CustomDirectionalBlock.builder(props, DRESSER_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape FRIDGE_BOX = Block.box(0, 0, -0.2, 16, 32, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block FRIDGE = register(
            "fridge",
            (props) -> CustomDirectionalBlock.builder(props, FRIDGE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape GRAMOPHONE_BOX = Block.box(0.75, 0, 3, 13, 15.573, 13);
    public static final Block GRAMOPHONE = register(
            "gramophone",
            (props) -> CustomDirectionalBlock.builder(props, GRAMOPHONE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KEROSENE_LANTERN_BOX = Block.box(5.925, 0, 6, 10.075, 7.75, 10);
    public static final Block KEROSENE_LANTERN = register(
            "kerosene_lantern",
            (props) -> new ToggledLightBlock(CustomDirectionalBlock.builder(props, KEROSENE_LANTERN_BOX)),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().lightLevel(ToggledLightBlock.luminance(12)),
            true
    );

    public static final VoxelShape KITCHEN_ANTIQUE_OVEN_BOX = Block.box(0, 0, 0.625, 16, 27.25, 15.875);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block KITCHEN_ANTIQUE_OVEN = register(
            "kitchen_antique_oven",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_ANTIQUE_OVEN_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_ANTIQUE_OVEN_DECORATED_BOX = Block.box(-0.29, 0, 0.625, 16, 27.25, 15.875);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block KITCHEN_ANTIQUE_OVEN_DECORATED = register(
            "kitchen_antique_oven_decorated",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_ANTIQUE_OVEN_DECORATED_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_CORNER_CABINET_BOX = Block.box(0, 0, 0, 16.05, 16, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block KITCHEN_CORNER_CABINET = register(
            "kitchen_corner_cabinet",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_CORNER_CABINET_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_CORNER_WALL_CABINET_BOX = Block.box(0, 0, 0.012, 15.988, 16, 15.988);
    public static final Block KITCHEN_CORNER_WALL_CABINET = register(
            "kitchen_corner_wall_cabinet",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_CORNER_WALL_CABINET_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_COUNTER_BOX = Block.box(0, 0, 1.25, 16, 16, 16);
    public static final Block KITCHEN_COUNTER = register(
            "kitchen_counter",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_COUNTER_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_DRAWERS_BOX = Block.box(0, 0, 1.25, 16, 16, 16);
    public static final Block KITCHEN_DRAWERS = register(
            "kitchen_drawers",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_DRAWERS_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_HALFWALL_SHELF_BOX = Block.box(0, 7, 6, 16, 16, 16);
    public static final Block KITCHEN_HALFWALL_SHELF = register(
            "kitchen_halfwall_shelf",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_HALFWALL_SHELF_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_SINK_BOX = Block.box(0, 0, 1.25, 16, 20.25, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block KITCHEN_SINK = register(
            "kitchen_sink",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_SINK_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_WALL_CABINET_BOX = Block.box(0, 0, 6, 16, 16, 16);
    public static final Block KITCHEN_WALL_CABINET = register(
            "kitchen_wall_cabinet",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_WALL_CABINET_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape KITCHEN_WALL_SHELF_BOX = Block.box(0, 0, 6, 16, 16, 16);
    public static final Block KITCHEN_WALL_SHELF = register(
            "kitchen_wall_shelf",
            (props) -> CustomDirectionalBlock.builder(props, KITCHEN_WALL_SHELF_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape LAMP_BOX = Block.box(2.5, 0, 2.5, 13.5, 15.5, 13.5);
    public static final Block LAMP = register(
            "lamp",
            (props) -> new ToggledLightBlock(CustomDirectionalBlock.builder(props, LAMP_BOX)),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().lightLevel(ToggledLightBlock.luminance(10)),
            true
    );

    public static final VoxelShape LIQUOR_CABINET_BOX = Block.box(-16, 0, 4.75, 16, 16, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block LIQUOR_CABINET = register(
            "liquor_cabinet",
            (props) -> CustomDirectionalBlock.builder(props, LIQUOR_CABINET_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape NIGHT_STAND_BOX = Block.box(3, 0, 8, 13, 10, 16);
    public static final Block NIGHT_STAND = register(
            "night_stand",
            (props) -> CustomDirectionalBlock.builder(props, NIGHT_STAND_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape PHONE_BOX = Block.box(3.336, 0, 5, 12.664, 5.25, 10.75);
    public static final Block PHONE = register(
            "phone",
            (props) -> CustomDirectionalBlock.builder(props, PHONE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape PIANO_BOX = Block.box(-16, 0, 5, 16.5, 19.5, 16.25);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block PIANO = register(
            "piano",
            (props) -> CustomDirectionalBlock.builder(props, PIANO_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape PIANO_WITH_STOOL_BOX = Block.box(-16, -0.25, -5, 16.5, 19.5, 16.25);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block PIANO_WITH_STOOL = register(
            "piano_with_stool",
            (props) -> CustomDirectionalBlock.builder(props, PIANO_WITH_STOOL_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape PILE_OF_BOOKS_BOX = Block.box(4.522, 0.025, 6.448, 10.288, 3.75, 11.707);
    public static final Block PILE_OF_BOOKS = register(
            "pile_of_books",
            (props) -> CustomDirectionalBlock.builder(props, PILE_OF_BOOKS_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape RADIATOR_BOX = Block.box(-6.25, 0, 10, 20, 13.25, 15);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block RADIATOR = register(
            "radiator",
            (props) -> CustomDirectionalBlock.builder(props, RADIATOR_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape RADIO_BOX = Block.box(2.75, 0, 5.75, 13.25, 11.584, 10);
    public static final Block RADIO = register(
            "radio",
            (props) -> CustomDirectionalBlock.builder(props, RADIO_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape RED_WINE_BOTTLE_BOX = Block.box(6.25, 0, 6.75, 8.75, 5.25, 9.25);
    public static final Block RED_WINE_BOTTLE = register(
            "red_wine_bottle",
            (props) -> CustomDirectionalBlock.builder(props, RED_WINE_BOTTLE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape ROCKING_CHAIR_BOX = Block.box(0, 0, 0, 16, 24.349, 18.934);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block ROCKING_CHAIR = register(
            "rocking_chair",
            (props) -> CustomDirectionalBlock.builder(props, ROCKING_CHAIR_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape ROUND_TABLE_BOX = Block.box(-1, 0, -1, 17, 16, 17);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block ROUND_TABLE = register(
            "round_table",
            (props) -> CustomDirectionalBlock.builder(props, ROUND_TABLE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape RUG_1X_3_BOX = Block.box(-14, 0, 0, 30, 0.5, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block RUG_1X_3 = register(
            "rug_1x_3",
            (props) -> CustomDirectionalBlock.builder(props, RUG_1X_3_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape RUG_3X_3_BOX = Block.box(-10, 0, -10, 26, 0.5, 26);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block RUG_3X_3 = register(
            "rug_3x_3",
            (props) -> CustomDirectionalBlock.builder(props, RUG_3X_3_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SCREEN_CLOTH_1_BOX = Block.box(-0.327, 0, 1.191, 16.004, 29, 8.665);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block SCREEN_CLOTH_1 = register(
            "screen_cloth_1",
            (props) -> CustomDirectionalBlock.builder(props, SCREEN_CLOTH_1_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SCREEN_CLOTH_2_BOX = Block.box(-0.375, 0, 1.276, 16.052, 29, 8.705);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block SCREEN_CLOTH_2 = register(
            "screen_cloth_2",
            (props) -> CustomDirectionalBlock.builder(props, SCREEN_CLOTH_2_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SCREEN_WOOD_1_BOX = Block.box(-0.352, 0, 1.253, 16.113, 30.75, 8.683);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block SCREEN_WOOD_1 = register(
            "screen_wood_1",
            (props) -> CustomDirectionalBlock.builder(props, SCREEN_WOOD_1_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SCREEN_WOOD_2_BOX = Block.box(-0.352, 0, 1.264, 16.092, 30.75, 8.694);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block SCREEN_WOOD_2 = register(
            "screen_wood_2",
            (props) -> CustomDirectionalBlock.builder(props, SCREEN_WOOD_2_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SEASONING_RACK_BOX = Block.box(2, 0, 11, 14, 12.5, 16);
    public static final Block SEASONING_RACK = register(
            "seasoning_rack",
            (props) -> CustomDirectionalBlock.builder(props, SEASONING_RACK_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SINGLE_BED_BOX = Block.box(-1.75, 0, 0.25, 17.75, 18.25, 31.75);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block SINGLE_BED = register(
            "single_bed",
            (props) -> CustomDirectionalBlock.builder(props, SINGLE_BED_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SMALL_BATHROOM_MIRROR_BOX = Block.box(0, 4.5, 14, 16, 27, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block SMALL_BATHROOM_MIRROR = register(
            "small_bathroom_mirror",
            (props) -> CustomDirectionalBlock.builder(props, SMALL_BATHROOM_MIRROR_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SOFA_LEFT_BOX = Block.box(0, 0, 3, 14, 14, 16);
    public static final Block SOFA_LEFT = register(
            "sofa_left",
            (props) -> CustomDirectionalBlock.builder(props, SOFA_LEFT_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SOFA_MIDDLE_BOX = Block.box(0, 1, 3.5, 16, 12.5, 15.75);
    public static final Block SOFA_MIDDLE = register(
            "sofa_middle",
            (props) -> CustomDirectionalBlock.builder(props, SOFA_MIDDLE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape SOFA_RIGHT_BOX = Block.box(2, 0, 3, 16, 14, 16);
    public static final Block SOFA_RIGHT = register(
            "sofa_right",
            (props) -> CustomDirectionalBlock.builder(props, SOFA_RIGHT_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape STANDING_LAMP_BOX = Block.box(3, -0.765, 3, 13, 32, 13);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block STANDING_LAMP = register(
            "standing_lamp",
            (props) -> new ToggledLightBlock(CustomDirectionalBlock.builder(props, STANDING_LAMP_BOX)),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().lightLevel(ToggledLightBlock.luminance(13)),
            true
    );

    public static final VoxelShape TALL_BOOKSHELF_BOX = Block.box(0, 0, 3, 16, 31.5, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block TALL_BOOKSHELF = register(
            "tall_bookshelf",
            (props) -> CustomDirectionalBlock.builder(props, TALL_BOOKSHELF_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape TOOL_BOARD_BOX = Block.box(-13.5, 3.5, 14.4, 13.5, 13.5, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block TOOL_BOARD = register(
            "tool_board",
            (props) -> CustomDirectionalBlock.builder(props, TOOL_BOARD_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape TOWEL_HANGER_BOX = Block.box(1, -7.75, 11.5, 15, 4, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block TOWEL_HANGER = register(
            "towel_hanger",
            (props) -> CustomDirectionalBlock.builder(props, TOWEL_HANGER_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape TYPEWRITER_BOX = Block.box(2, 0, 0.5, 14, 8.051, 15.379);
    public static final Block TYPEWRITER = register(
            "typewriter",
            (props) -> CustomDirectionalBlock.builder(props, TYPEWRITER_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape WALL_SHELF_BOX = Block.box(0, 0, 8.95, 16, 19.5, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block WALL_SHELF = register(
            "wall_shelf",
            (props) -> CustomDirectionalBlock.builder(props, WALL_SHELF_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape WARDROBE_BOX = Block.box(-13.5, 0, 2.5, 13.5, 31.75, 16);  // TODO: geometry extends outside 0-16, clamp/simplify by hand
    public static final Block WARDROBE = register(
            "wardrobe",
            (props) -> CustomDirectionalBlock.builder(props, WARDROBE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape WINE_BOTTLE_BOX = Block.box(6.25, 0, 6.75, 8.75, 5.25, 9.25);
    public static final Block WINE_BOTTLE = register(
            "wine_bottle",
            (props) -> CustomDirectionalBlock.builder(props, WINE_BOTTLE_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape WINE_GLASS_BOX = Block.box(7, 0, 7, 9, 4, 9);
    public static final Block WINE_GLASS = register(
            "wine_glass",
            (props) -> CustomDirectionalBlock.builder(props, WINE_GLASS_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
            true
    );

    public static final VoxelShape WORKBENCH_BOX = Block.box(-14, 0, 1.25, 14, 20, 16);
    public static final Block WORKBENCH = register(
            "workbench",
            (props) -> CustomDirectionalBlock.builder(props, WORKBENCH_BOX).build(),
            BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion(),
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
