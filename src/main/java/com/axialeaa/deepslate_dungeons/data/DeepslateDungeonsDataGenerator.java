package com.axialeaa.deepslate_dungeons.data;

import com.axialeaa.deepslate_dungeons.data.provider.BlockLootProvider;
import com.axialeaa.deepslate_dungeons.data.provider.BlockTagProvider;
import com.axialeaa.deepslate_dungeons.data.provider.ModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DeepslateDungeonsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(BlockLootProvider::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(ModelProvider::new);
    }

}
