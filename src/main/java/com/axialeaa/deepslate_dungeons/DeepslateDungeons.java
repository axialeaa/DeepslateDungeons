package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks;
import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsGamerules;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks.*;

public class DeepslateDungeons implements ModInitializer {

	public static final String MOD_ID = "deepslate-dungeons";
	public static final String MOD_NAME = "Deepslate Dungeons";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static final ImmutableMap<Block, Block> STRONGHOLD_STONE_VARIANT_MAP = ImmutableMap.of(
		Blocks.COBBLESTONE, Blocks.COBBLED_DEEPSLATE,
		Blocks.COBBLESTONE_STAIRS, Blocks.COBBLED_DEEPSLATE_STAIRS,
		Blocks.SMOOTH_STONE_SLAB, Blocks.POLISHED_DEEPSLATE_SLAB,

		Blocks.STONE_BRICKS, Blocks.DEEPSLATE_BRICKS,
		Blocks.STONE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_SLAB,
		Blocks.STONE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_STAIRS,
		Blocks.MOSSY_STONE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
		Blocks.CRACKED_STONE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS,
		Blocks.INFESTED_STONE_BRICKS, INFESTED_DEEPSLATE_BRICKS
	);

	public static final ImmutableMap<Block, Block> STRONGHOLD_OAK_VARIANT_MAP = ImmutableMap.of(
		Blocks.OAK_PLANKS, Blocks.DARK_OAK_PLANKS,
		Blocks.OAK_FENCE, Blocks.DARK_OAK_FENCE,
		Blocks.OAK_DOOR, Blocks.DARK_OAK_DOOR
	);

	public static final ImmutableMap<Block, Block> STRONGHOLD_TORCH_VARIANT_MAP = ImmutableMap.of(
		Blocks.TORCH, Blocks.SOUL_TORCH,
		Blocks.WALL_TORCH, Blocks.SOUL_WALL_TORCH
	);

	@Override
	public void onInitialize() {
		LOGGER.info("{} initialized! Insert funny joke here.", MOD_NAME);

		DeepslateDungeonsBlocks.init();
		DeepslateDungeonsGamerules.init();

		ItemGroupEvents.MODIFY_ENTRIES_ALL.register((tab, entries) -> {
			entries.addAfter(Items.INFESTED_DEEPSLATE, INFESTED_COBBLED_DEEPSLATE);
			entries.addAfter(INFESTED_COBBLED_DEEPSLATE, INFESTED_DEEPSLATE_BRICKS);
			entries.addAfter(INFESTED_DEEPSLATE_BRICKS, INFESTED_CRACKED_DEEPSLATE_BRICKS);
			entries.addAfter(INFESTED_CRACKED_DEEPSLATE_BRICKS, INFESTED_CHISELED_DEEPSLATE);
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static Block deepStrongholdVariant(GameRules gameRules, Block block) {
		Map<Block, Block> map = STRONGHOLD_STONE_VARIANT_MAP;

		if (map.containsKey(block)) return map.get(block);

		if (gameRules.get(DeepslateDungeonsGamerules.STRONGHOLD_CONVERT_OAK))
			map = STRONGHOLD_OAK_VARIANT_MAP;

		if (map.containsKey(block)) return map.get(block);

		if (gameRules.get(DeepslateDungeonsGamerules.STRONGHOLD_CONVERT_TORCHES))
			map = STRONGHOLD_TORCH_VARIANT_MAP;

		return map.getOrDefault(block, block);
	}

}