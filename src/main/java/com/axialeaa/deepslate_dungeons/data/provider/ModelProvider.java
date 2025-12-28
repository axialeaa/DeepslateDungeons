package com.axialeaa.deepslate_dungeons.data.provider;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import static com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks.*;
import static net.minecraft.world.level.block.Blocks.*;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators generators) {
        infestedDeepslate(generators, COBBLED_DEEPSLATE, INFESTED_COBBLED_DEEPSLATE);
        infestedDeepslate(generators, DEEPSLATE_BRICKS, INFESTED_DEEPSLATE_BRICKS);
        infestedDeepslate(generators, CRACKED_DEEPSLATE_BRICKS, INFESTED_CRACKED_DEEPSLATE_BRICKS);
        infestedDeepslate(generators, CHISELED_DEEPSLATE, INFESTED_CHISELED_DEEPSLATE);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators generators) {}

    private static void infestedDeepslate(BlockModelGenerators generators, Block from, Block block) {
        Identifier id = ModelLocationUtils.getModelLocation(from);

        generators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(id)));
        generators.registerSimpleItemModel(block, id);
    }

}
