package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;

import java.util.Optional;

import static com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsGamerules.*;

public class ConversionHelper {

    private static final ImmutableMap<Block, Block> STRONGHOLD_STONE_VARIANT_MAP = ImmutableMap.of(
        Blocks.COBBLESTONE, Blocks.COBBLED_DEEPSLATE,
        Blocks.COBBLESTONE_STAIRS, Blocks.COBBLED_DEEPSLATE_STAIRS,
        Blocks.SMOOTH_STONE_SLAB, Blocks.POLISHED_DEEPSLATE_SLAB,

        Blocks.STONE_BRICKS, Blocks.DEEPSLATE_BRICKS,
        Blocks.STONE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_SLAB,
        Blocks.STONE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_STAIRS,
        Blocks.MOSSY_STONE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
        Blocks.CRACKED_STONE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
        Blocks.INFESTED_STONE_BRICKS, DeepslateDungeonsBlocks.INFESTED_DEEPSLATE_BRICKS
    );

    private static final ImmutableMap<Block, Block> STRONGHOLD_OAK_VARIANT_MAP = ImmutableMap.of(
        Blocks.OAK_PLANKS, Blocks.DARK_OAK_PLANKS,
        Blocks.OAK_FENCE, Blocks.DARK_OAK_FENCE,
        Blocks.OAK_DOOR, Blocks.DARK_OAK_DOOR
    );

    private static final ImmutableMap<Block, Block> STRONGHOLD_TORCH_VARIANT_MAP = ImmutableMap.of(
        Blocks.TORCH, Blocks.SOUL_TORCH,
        Blocks.WALL_TORCH, Blocks.SOUL_WALL_TORCH
    );

    public static Block getDeepStrongholdVariant(GameRules gameRules, Block block) {
        return Optional.ofNullable(STRONGHOLD_STONE_VARIANT_MAP.get(block))
            .or(() -> Optional.ofNullable(gameRules.get(STRONGHOLD_CONVERT_OAK) ? STRONGHOLD_OAK_VARIANT_MAP.get(block) : null))
            .or(() -> Optional.ofNullable(gameRules.get(STRONGHOLD_CONVERT_TORCHES) ? STRONGHOLD_TORCH_VARIANT_MAP.get(block) : null))
            .orElse(block);
    }

}
