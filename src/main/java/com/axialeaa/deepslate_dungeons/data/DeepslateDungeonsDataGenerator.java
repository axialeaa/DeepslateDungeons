package com.axialeaa.deepslate_dungeons.data;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks;
import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsLoot;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DeepslateDungeonsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(DeepslateDungeonsBlocks.LootProvider::new);
        pack.addProvider(DeepslateDungeonsBlocks.TagProvider::new);
        pack.addProvider(DeepslateDungeonsBlocks.ModelProvider::new);
        pack.addProvider(DeepslateDungeonsLoot.Provider::new);
    }

}
