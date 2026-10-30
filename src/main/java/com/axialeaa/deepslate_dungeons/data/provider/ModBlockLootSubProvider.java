package com.axialeaa.deepslate_dungeons.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static com.axialeaa.deepslate_dungeons.registry.ModBlocks.*;
import static net.minecraft.world.level.block.Blocks.*;

public class ModBlockLootSubProvider extends FabricBlockLootSubProvider {

    public ModBlockLootSubProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.otherWhenSilkTouch(INFESTED_COBBLED_DEEPSLATE.get(), COBBLED_DEEPSLATE);
        this.otherWhenSilkTouch(INFESTED_DEEPSLATE_BRICKS.get(), DEEPSLATE_BRICKS);
        this.otherWhenSilkTouch(INFESTED_CRACKED_DEEPSLATE_BRICKS.get(), CRACKED_DEEPSLATE_BRICKS);
        this.otherWhenSilkTouch(INFESTED_CHISELED_DEEPSLATE.get(), CHISELED_DEEPSLATE);
    }

}