package com.axialeaa.deepslate_dungeons.data.provider;

import com.axialeaa.deepslate_dungeons.data.registry.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@NullMarked
public class ModChestLootSubProvider extends SimpleFabricLootTableSubProvider {

    private static final int EMPTY_ITEM_WEIGHT = 70;
    private static final int BOOK_ITEM_WEIGHT = 69;
    private static final int ENCHANTED_BOOK_ITEM_WEIGHT = 1;

    private static final NumberProvider ENCHANTING_LEVEL = ConstantValue.exactly(30.0F);

    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public ModChestLootSubProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup, LootContextParamSets.CHEST);
        this.registryLookup = registryLookup;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        this.registryLookup.thenAccept(provider -> biConsumer.accept(ModLootTables.STRONGHOLD_LIBRARY_BOOKSHELF, strongholdLibraryBookshelf(provider)));
    }

    private static LootTable.Builder strongholdLibraryBookshelf(HolderLookup.Provider provider) {
        LootPoolSingletonContainer.Builder<?> lootTableItem = LootItem.lootTableItem(Items.BOOK);

        return LootTable.lootTable()
            .setRandomSequence(ModLootTables.STRONGHOLD_LIBRARY_BOOKSHELF.identifier())
            .withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(ChiseledBookShelfBlockEntity.MAX_BOOKS_IN_STORAGE))
                .add(EmptyLootItem.emptyItem().setWeight(EMPTY_ITEM_WEIGHT))
                .add(lootTableItem.setWeight(BOOK_ITEM_WEIGHT))
                .add(lootTableItem.setWeight(ENCHANTED_BOOK_ITEM_WEIGHT)
                    .apply(EnchantWithLevelsFunction.enchantWithLevels(provider, ENCHANTING_LEVEL))
                )
            );
    }

}