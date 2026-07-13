package danylo.mod;

import danylo.mod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class DecorModCreativeTab {

    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(DecorMod.MOD_ID, "creative_tab")
    );

    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricItemGroup.builder()
//            .icon(() -> new ItemStack(ModItems.SUSPICIOUS_SUBSTANCE))
            .title(Component.translatable("danylo-decor.DanyloTabName"))
            .displayItems((params, output) -> {
                output.accept(ModBlocks.TILE);
                output.accept(ModBlocks.BRICK);
                output.accept(ModBlocks.BOOKS);
                output.accept(ModBlocks.WALL_LAMP);
                // Yuushya mod
                output.accept(ModBlocks.EASEL_WITH_CANVAS);
                output.accept(ModBlocks.EASEL_EMPTY);
                output.accept(ModBlocks.MOUNTED_MIRROR);
                output.accept(ModBlocks.PIGMENT_BUCKET);
                output.accept(ModBlocks.ALBUM);
                output.accept(ModBlocks.POTTED_FLOWER_BASKET);
                output.accept(ModBlocks.POTTED_SUNFLOWER);
                output.accept(ModBlocks.POTTED_JUNGLE_SPROUT);
                output.accept(ModBlocks.POTTED_AZURE_BLUET);
                output.accept(ModBlocks.POTTED_SAKURA_SPROUT);
                output.accept(ModBlocks.POTTED_MAPLE_SPROUT);
                output.accept(ModBlocks.POTTED_SPROUT);
                output.accept(ModBlocks.POTTED_ALLIUM);
                output.accept(ModBlocks.POTTED_FERN);
                output.accept(ModBlocks.AZURE_BLUET);
                output.accept(ModBlocks.WHITE_TULIP);
                output.accept(ModBlocks.GOLD_TULIP);
                output.accept(ModBlocks.PINK_TULIP);
                output.accept(ModBlocks.RED_TULIP);
                // Farmhouse Decorations
                output.accept(ModBlocks.ACOUSTIC_GUITAR);
                output.accept(ModBlocks.ANCIENT_TV);
                output.accept(ModBlocks.ANCIENT_TV_WITH_LEGS);
                output.accept(ModBlocks.ANTIQUE_OVEN);
                output.accept(ModBlocks.BATHROOM_CABINET);
                output.accept(ModBlocks.BATHROOM_COUNTER);
                output.accept(ModBlocks.BATHROOM_SINK);
                output.accept(ModBlocks.BATHTUB);
                output.accept(ModBlocks.BIG_BATHROOM_MIRROR);
                output.accept(ModBlocks.CHAINED_KEROSENE_LANTERN);
                output.accept(ModBlocks.CIGAR_BOX);
                output.accept(ModBlocks.COAT_HANGER);
                output.accept(ModBlocks.COFFEE_TABLE);
                output.accept(ModBlocks.CUCKOO_CLOCK);
                output.accept(ModBlocks.DESK);
                output.accept(ModBlocks.DINING_CHAIR);
                output.accept(ModBlocks.DINING_TABLE_MIDDLE);
                output.accept(ModBlocks.DINING_TABLE_SIDE);
                output.accept(ModBlocks.DOOR_MAT);
                output.accept(ModBlocks.DOUBLE_BED);
                output.accept(ModBlocks.DRESSER);
                output.accept(ModBlocks.FRIDGE);
                output.accept(ModBlocks.GRAMOPHONE);
                output.accept(ModBlocks.KEROSENE_LANTERN);
                output.accept(ModBlocks.KITCHEN_ANTIQUE_OVEN);
                output.accept(ModBlocks.KITCHEN_ANTIQUE_OVEN_DECORATED);
                output.accept(ModBlocks.KITCHEN_CORNER_CABINET);
                output.accept(ModBlocks.KITCHEN_CORNER_WALL_CABINET);
                output.accept(ModBlocks.KITCHEN_COUNTER);
                output.accept(ModBlocks.KITCHEN_DRAWERS);
                output.accept(ModBlocks.KITCHEN_HALFWALL_SHELF);
                output.accept(ModBlocks.KITCHEN_SINK);
                output.accept(ModBlocks.KITCHEN_WALL_CABINET);
                output.accept(ModBlocks.KITCHEN_WALL_SHELF);
                output.accept(ModBlocks.LAMP);
                output.accept(ModBlocks.LIQUOR_CABINET);
                output.accept(ModBlocks.NIGHT_STAND);
                output.accept(ModBlocks.PHONE);
                output.accept(ModBlocks.PIANO);
                output.accept(ModBlocks.PIANO_WITH_STOOL);
                output.accept(ModBlocks.PILE_OF_BOOKS);
                output.accept(ModBlocks.RADIATOR);
                output.accept(ModBlocks.RADIO);
                output.accept(ModBlocks.RED_WINE_BOTTLE);
                output.accept(ModBlocks.ROCKING_CHAIR);
                output.accept(ModBlocks.ROUND_TABLE);
                output.accept(ModBlocks.RUG_1X_3);
                output.accept(ModBlocks.RUG_3X_3);
                output.accept(ModBlocks.SCREEN_CLOTH_1);
                output.accept(ModBlocks.SCREEN_CLOTH_2);
                output.accept(ModBlocks.SCREEN_WOOD_1);
                output.accept(ModBlocks.SCREEN_WOOD_2);
                output.accept(ModBlocks.SEASONING_RACK);
                output.accept(ModBlocks.SINGLE_BED);
                output.accept(ModBlocks.SMALL_BATHROOM_MIRROR);
                output.accept(ModBlocks.SOFA_LEFT);
                output.accept(ModBlocks.SOFA_MIDDLE);
                output.accept(ModBlocks.SOFA_RIGHT);
                output.accept(ModBlocks.STANDING_LAMP);
                output.accept(ModBlocks.TALL_BOOKSHELF);
                output.accept(ModBlocks.TOOL_BOARD);
                output.accept(ModBlocks.TOWEL_HANGER);
                output.accept(ModBlocks.TYPEWRITER);
                output.accept(ModBlocks.WALL_SHELF);
                output.accept(ModBlocks.WARDROBE);
                output.accept(ModBlocks.WINE_BOTTLE);
                output.accept(ModBlocks.WINE_GLASS);
                output.accept(ModBlocks.WORKBENCH);
            })
            .build();


    public static void registerCustomTab() {
        // Register the group.
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }
}
