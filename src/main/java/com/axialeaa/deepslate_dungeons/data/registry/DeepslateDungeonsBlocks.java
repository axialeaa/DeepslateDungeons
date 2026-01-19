package com.axialeaa.deepslate_dungeons.data.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static net.minecraft.world.level.block.Blocks.*;
import static net.minecraft.world.level.block.Blocks.CHISELED_DEEPSLATE;

public interface DeepslateDungeonsBlocks {

    Block INFESTED_COBBLED_DEEPSLATE = infestedDeepslate(Blocks.COBBLED_DEEPSLATE, SoundType.DEEPSLATE);
    Block INFESTED_DEEPSLATE_BRICKS = infestedDeepslate(Blocks.DEEPSLATE_BRICKS, SoundType.DEEPSLATE_BRICKS);
    Block INFESTED_CRACKED_DEEPSLATE_BRICKS = infestedDeepslate(Blocks.CRACKED_DEEPSLATE_BRICKS, SoundType.DEEPSLATE_BRICKS);
    Block INFESTED_CHISELED_DEEPSLATE = infestedDeepslate(Blocks.CHISELED_DEEPSLATE, SoundType.DEEPSLATE_BRICKS);

    private static Block infestedDeepslate(Block of, SoundType soundType) {
        String path = "infested_" + BuiltInRegistries.BLOCK.getKey(of).getPath();
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).sound(soundType);

        return register(path, properties1 -> new InfestedBlock(of, properties1), properties);
    }

    private static Block register(String path, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        Identifier id = DeepslateDungeons.id(path);

        Block block = function.apply(properties.setId(ResourceKey.create(Registries.BLOCK, id)));
        block = Registry.register(BuiltInRegistries.BLOCK, id, block);

        Items.registerBlock(block);

        return block;
    }

    static void init() {}

    class LootProvider extends FabricBlockLootTableProvider {

        public LootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {
            this.otherWhenSilkTouch(INFESTED_COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE);
            this.otherWhenSilkTouch(INFESTED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS);
            this.otherWhenSilkTouch(INFESTED_CRACKED_DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
            this.otherWhenSilkTouch(INFESTED_CHISELED_DEEPSLATE, Blocks.CHISELED_DEEPSLATE);
        }

    }

    class TagProvider extends FabricTagProvider.BlockTagProvider {

        public TagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
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

    class ModelProvider extends FabricModelProvider {

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

}
