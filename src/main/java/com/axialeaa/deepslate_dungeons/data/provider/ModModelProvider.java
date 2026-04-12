package com.axialeaa.deepslate_dungeons.data.provider;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import static com.axialeaa.deepslate_dungeons.data.registry.ModBlocks.*;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generators) {
        infestedDeepslate(generators, Blocks.COBBLED_DEEPSLATE, INFESTED_COBBLED_DEEPSLATE);
        infestedDeepslate(generators, Blocks.DEEPSLATE_BRICKS, INFESTED_DEEPSLATE_BRICKS);
        infestedDeepslate(generators, Blocks.CRACKED_DEEPSLATE_BRICKS, INFESTED_CRACKED_DEEPSLATE_BRICKS);
        infestedDeepslate(generators, Blocks.CHISELED_DEEPSLATE, INFESTED_CHISELED_DEEPSLATE);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generators) {}

    private static void infestedDeepslate(BlockModelGenerators generators, Block from, Block block) {
        Identifier id = ModelLocationUtils.getModelLocation(from);

        generators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(id)));
        generators.registerSimpleItemModel(block, id);
    }

}