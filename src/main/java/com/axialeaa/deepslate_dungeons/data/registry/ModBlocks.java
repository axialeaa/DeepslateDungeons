package com.axialeaa.deepslate_dungeons.data.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public final class ModBlocks {

    public static final Block INFESTED_COBBLED_DEEPSLATE = infestedDeepslate(References.INFESTED_COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE, SoundType.DEEPSLATE);
    public static final Block INFESTED_DEEPSLATE_BRICKS = infestedDeepslate(References.INFESTED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, SoundType.DEEPSLATE_BRICKS);
    public static final Block INFESTED_CRACKED_DEEPSLATE_BRICKS = infestedDeepslate(References.INFESTED_CRACKED_DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS, SoundType.DEEPSLATE_BRICKS);
    public static final Block INFESTED_CHISELED_DEEPSLATE = infestedDeepslate(References.INFESTED_CHISELED_DEEPSLATE, Blocks.CHISELED_DEEPSLATE, SoundType.DEEPSLATE_BRICKS);

    public static Block infestedDeepslate(BlockItemId reference, Block host, SoundType soundType) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).sound(soundType);
        return register(reference, p -> new InfestedBlock(host, p), properties);
    }

    public static <T extends Block> T register(BlockItemId reference, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = reference.block();
        T block = function.apply(properties.setId(key));

        Registry.register(BuiltInRegistries.BLOCK, key, block);
        registerBlockItem(reference, block);

        return block;
    }

    public static void registerBlockItem(BlockItemId reference, Block block) {
        ResourceKey<Item> key = reference.item();
        Item.Properties properties = new Item.Properties()
            .setId(key)
            .useBlockDescriptionPrefix();

        Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, properties));
    }

    public static void init() {}

    public static final class References {

        public static final BlockItemId INFESTED_COBBLED_DEEPSLATE = create("infested_cobbled_deepslate");
        public static final BlockItemId INFESTED_DEEPSLATE_BRICKS = create("infested_deepslate_bricks");
        public static final BlockItemId INFESTED_CRACKED_DEEPSLATE_BRICKS = create("infested_cracked_deepslate_bricks");
        public static final BlockItemId INFESTED_CHISELED_DEEPSLATE = create("infested_chiseled_deepslate");

        public static BlockItemId create(String path) {
            Identifier id = DeepslateDungeons.id(path);
            return BlockItemId.create(id, id);
        }

    }

}
