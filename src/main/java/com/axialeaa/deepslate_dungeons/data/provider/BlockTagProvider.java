package com.axialeaa.deepslate_dungeons.data.provider;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
            DeepslateDungeonsBlocks.INFESTED_COBBLED_DEEPSLATE,
            DeepslateDungeonsBlocks.INFESTED_DEEPSLATE_BRICKS,
            DeepslateDungeonsBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS,
            DeepslateDungeonsBlocks.INFESTED_CHISELED_DEEPSLATE
        );
    }

}