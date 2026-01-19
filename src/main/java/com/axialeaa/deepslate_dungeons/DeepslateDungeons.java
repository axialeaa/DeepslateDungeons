package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsBlocks;
import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsGamerules;
import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsLoot;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeepslateDungeons implements ModInitializer {

	public static final String MOD_ID = "deepslate-dungeons";
	public static final String MOD_NAME = "Deepslate Dungeons";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} initialized! Deep are the dungeons that bear the dragons...", MOD_NAME);

		DeepslateDungeonsBlocks.init();
		DeepslateDungeonsGamerules.init();
		DeepslateDungeonsLoot.init();

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.addAfter(Items.INFESTED_DEEPSLATE, DeepslateDungeonsBlocks.INFESTED_COBBLED_DEEPSLATE);
			entries.addAfter(DeepslateDungeonsBlocks.INFESTED_COBBLED_DEEPSLATE, DeepslateDungeonsBlocks.INFESTED_DEEPSLATE_BRICKS);
			entries.addAfter(DeepslateDungeonsBlocks.INFESTED_DEEPSLATE_BRICKS, DeepslateDungeonsBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS);
			entries.addAfter(DeepslateDungeonsBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS, DeepslateDungeonsBlocks.INFESTED_CHISELED_DEEPSLATE);
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

}