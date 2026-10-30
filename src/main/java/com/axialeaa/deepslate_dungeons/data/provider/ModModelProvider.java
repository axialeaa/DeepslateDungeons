package com.axialeaa.deepslate_dungeons.data.provider;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import survivalblock.atmosphere.registrar.wrapper.BlockPresenter;

import static com.axialeaa.deepslate_dungeons.registry.ModBlocks.*;
import static net.minecraft.client.data.models.BlockModelGenerators.*;
import static net.minecraft.world.level.block.Blocks.*;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        infestedDeepslate(generators, COBBLED_DEEPSLATE, INFESTED_COBBLED_DEEPSLATE);
        infestedDeepslate(generators, DEEPSLATE_BRICKS, INFESTED_DEEPSLATE_BRICKS);
        infestedDeepslate(generators, CRACKED_DEEPSLATE_BRICKS, INFESTED_CRACKED_DEEPSLATE_BRICKS);
        infestedDeepslate(generators, CHISELED_DEEPSLATE, INFESTED_CHISELED_DEEPSLATE);
    }

	private static void infestedDeepslate(BlockModelGenerators generators, Block host, BlockPresenter<InfestedBlock> block) {
		Identifier model = ModelLocationUtils.getModelLocation(host);

		generators.blockStateOutput.accept(createSimpleBlock(block.get(), plainVariant(model)));
		generators.registerSimpleItemModel(block.asItem(), model);
	}

    @Override
    public void generateItemModels(ItemModelGenerators generators) {}

}