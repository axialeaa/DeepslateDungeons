package com.axialeaa.deepslate_dungeons.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static com.axialeaa.deepslate_dungeons.data.registry.ModBlocks.*;

public class ModBlockLootSubProvider extends FabricBlockLootSubProvider {

    public ModBlockLootSubProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.otherWhenSilkTouch(INFESTED_COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE);
        this.otherWhenSilkTouch(INFESTED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS);
        this.otherWhenSilkTouch(INFESTED_CRACKED_DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        this.otherWhenSilkTouch(INFESTED_CHISELED_DEEPSLATE, Blocks.CHISELED_DEEPSLATE);
    }

}