package com.axialeaa.deepslate_dungeons.data.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

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

}
