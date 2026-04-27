package danylo.mod.item;

import danylo.mod.DecorMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

/**
 * All new items are registered in this class.
 */
public class ModItems {
    // When called, this forces Java to compile this class
    public static void initialize() {
    }

    // Registers item to the game using given item name, item constructor and settings for the item
    public static <T extends Item> T registerItem(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DecorMod.MOD_ID, name));

        // Create the item instance
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);


        return item;

    }
}
