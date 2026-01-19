package com.axialeaa.deepslate_dungeons.data.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public interface DeepslateDungeonsLoot {

    ResourceKey<LootTable> STRONGHOLD_LIBRARY_BOOKSHELF = create("chests/stronghold_library_bookshelf");

    private static ResourceKey<LootTable> create(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, DeepslateDungeons.id(path));
    }

    static void init() {}

    @NullMarked
    class Provider extends SimpleFabricLootTableProvider {

        private static final int EMPTY_ITEM_WEIGHT = 200;
        private static final int BOOK_ITEM_WEIGHT = 49;
        private static final int ENCHANTED_BOOK_ITEM_WEIGHT = 1;

        private final CompletableFuture<HolderLookup.Provider> registryLookup;

        public Provider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(output, registryLookup, LootContextParamSets.CHEST);
            this.registryLookup = registryLookup;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
            this.registryLookup.thenAccept(provider -> biConsumer.accept(STRONGHOLD_LIBRARY_BOOKSHELF, strongholdLibraryBookshelf(provider)));
        }

        private static LootTable.Builder strongholdLibraryBookshelf(HolderLookup.Provider provider) {
            return LootTable.lootTable()
                .setRandomSequence(STRONGHOLD_LIBRARY_BOOKSHELF.identifier())
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(ChiseledBookShelfBlockEntity.MAX_BOOKS_IN_STORAGE))
                    .add(EmptyLootItem.emptyItem().setWeight(EMPTY_ITEM_WEIGHT))
                    .add(LootItem.lootTableItem(Items.BOOK).setWeight(BOOK_ITEM_WEIGHT))
                    .add(LootItem.lootTableItem(Items.BOOK).setWeight(ENCHANTED_BOOK_ITEM_WEIGHT)
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(provider, ConstantValue.exactly(30.0F)))
                    )
                );
        }

    }

}
