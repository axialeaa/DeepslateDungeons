package com.axialeaa.deepslate_dungeons.util;

import com.axialeaa.deepslate_dungeons.registry.ModBlocks;
import com.axialeaa.deepslate_dungeons.registry.ModGameRules;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gamerules.GameRules;

import java.util.Optional;

import static net.minecraft.world.level.block.Blocks.*;

/**
 * Roadmap: make this data-driven at some point!
 */
public final class Conversions {

    private static final ImmutableMap<Block, Block> STRONGHOLD_STONE_VARIANT_MAP = ImmutableMap.of(
        COBBLESTONE, COBBLED_DEEPSLATE,
        COBBLESTONE_STAIRS, COBBLED_DEEPSLATE_STAIRS,
        SMOOTH_STONE_SLAB, POLISHED_DEEPSLATE_SLAB,

        STONE_BRICKS, DEEPSLATE_BRICKS,
        STONE_BRICK_SLAB, DEEPSLATE_BRICK_SLAB,
        STONE_BRICK_STAIRS, DEEPSLATE_BRICK_STAIRS,
        MOSSY_STONE_BRICKS, CRACKED_DEEPSLATE_BRICKS,
        CRACKED_STONE_BRICKS, CRACKED_DEEPSLATE_BRICKS,
        INFESTED_STONE_BRICKS, ModBlocks.INFESTED_DEEPSLATE_BRICKS.get()
    );

    private static final ImmutableMap<Block, Block> STRONGHOLD_OAK_VARIANT_MAP = ImmutableMap.of(
        OAK_PLANKS, DARK_OAK_PLANKS,
        OAK_FENCE, DARK_OAK_FENCE,
        OAK_DOOR, DARK_OAK_DOOR
    );

    private static final ImmutableMap<Block, Block> STRONGHOLD_TORCH_VARIANT_MAP = ImmutableMap.of(
        TORCH, SOUL_TORCH,
        WALL_TORCH, SOUL_WALL_TORCH
    );

	private Conversions() {}

	private static Optional<Block> getStoneVariant(Block block) {
		return Optional.ofNullable(STRONGHOLD_STONE_VARIANT_MAP.get(block));
	}

	private static Optional<Block> getOakVariant(Block block) {
		return Optional.ofNullable(STRONGHOLD_OAK_VARIANT_MAP.get(block));
	}

	private static Optional<Block> getTorchVariant(Block block) {
		return Optional.ofNullable(STRONGHOLD_TORCH_VARIANT_MAP.get(block));
	}

	public static Block getDeepStrongholdVariant(GameRules gameRules, Block block) {
		Optional<Block> oak = getOakVariant(block);
		Optional<Block> torch = getTorchVariant(block);

        if (oak.isPresent() && gameRules.get(ModGameRules.STRONGHOLD_CONVERT_OAK))
			return oak.get();

		if (torch.isPresent() && gameRules.get(ModGameRules.STRONGHOLD_CONVERT_TORCHES))
			return torch.get();

        return getStoneVariant(block).orElse(block);
    }

}
