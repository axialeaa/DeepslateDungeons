package com.axialeaa.deepslate_dungeons.data;

import com.axialeaa.deepslate_dungeons.data.provider.ModBlockLootSubProvider;
import com.axialeaa.deepslate_dungeons.data.provider.ModBlockTagsProvider;
import com.axialeaa.deepslate_dungeons.data.provider.ModChestLootSubProvider;
import com.axialeaa.deepslate_dungeons.data.provider.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DeepslateDungeonsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ModBlockLootSubProvider::new);
        pack.addProvider(ModBlockTagsProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModChestLootSubProvider::new);
    }

}
