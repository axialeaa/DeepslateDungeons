package com.axialeaa.deepslate_dungeons.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static com.axialeaa.deepslate_dungeons.data.registry.ModBlocks.*;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
            INFESTED_COBBLED_DEEPSLATE,
            INFESTED_DEEPSLATE_BRICKS,
            INFESTED_CRACKED_DEEPSLATE_BRICKS,
            INFESTED_CHISELED_DEEPSLATE
        );
    }

}