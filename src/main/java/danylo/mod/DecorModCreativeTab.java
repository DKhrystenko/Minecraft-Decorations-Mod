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
            })
            .build();


    public static void registerCustomTab() {
        // Register the group.
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
    }
}
