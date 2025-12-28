package com.axialeaa.deepslate_dungeons.data.provider;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class BlockLootProvider extends FabricBlockLootTableProvider {

    public BlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.otherWhenSilkTouch(DeepslateDungeonsBlocks.INFESTED_COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE);
        this.otherWhenSilkTouch(DeepslateDungeonsBlocks.INFESTED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS);
        this.otherWhenSilkTouch(DeepslateDungeonsBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        this.otherWhenSilkTouch(DeepslateDungeonsBlocks.INFESTED_CHISELED_DEEPSLATE, Blocks.CHISELED_DEEPSLATE);
    }

}