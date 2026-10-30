package com.axialeaa.deepslate_dungeons.data.provider;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static com.axialeaa.deepslate_dungeons.registry.ModBlocks.*;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

	private static final ImmutableList<ResourceKey<Block>> BLOCKS = ImmutableList.of(
		INFESTED_COBBLED_DEEPSLATE.getKey(),
		INFESTED_DEEPSLATE_BRICKS.getKey(),
		INFESTED_CRACKED_DEEPSLATE_BRICKS.getKey(),
		INFESTED_CHISELED_DEEPSLATE.getKey()
	);

    public ModBlockTagsProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.builder(BlockTags.MINEABLE_WITH_PICKAXE).addAll(BLOCKS);
		this.builder(BlockTags.BLOCKS_MOTION_NO_LEAVES).addAll(BLOCKS);
    }

}