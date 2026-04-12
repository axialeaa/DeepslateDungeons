package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.ModBlocks;
import com.axialeaa.deepslate_dungeons.data.registry.ModGameRules;
import com.axialeaa.deepslate_dungeons.data.registry.ModLootTables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.axialeaa.deepslate_dungeons.data.registry.ModBlocks.*;

public class DeepslateDungeons implements ModInitializer {

	public static final String MOD_ID = "deepslate-dungeons";
	public static final String MOD_NAME = "Deepslate Dungeons";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} initialized! Deep are the dungeons that bear the dragons...", MOD_NAME);

		ModBlocks.init();
		ModGameRules.init();
		ModLootTables.init();

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.insertAfter(Items.INFESTED_DEEPSLATE, INFESTED_COBBLED_DEEPSLATE);
			entries.insertAfter(INFESTED_COBBLED_DEEPSLATE, INFESTED_DEEPSLATE_BRICKS);
			entries.insertAfter(INFESTED_DEEPSLATE_BRICKS, INFESTED_CRACKED_DEEPSLATE_BRICKS);
			entries.insertAfter(INFESTED_CRACKED_DEEPSLATE_BRICKS, INFESTED_CHISELED_DEEPSLATE);
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

}