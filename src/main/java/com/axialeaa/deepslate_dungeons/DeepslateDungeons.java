package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.ModBlocks;
import com.axialeaa.deepslate_dungeons.data.registry.ModGameRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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

		ModBlocks.init();
		ModGameRules.init();

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.insertAfter(
            Items.INFESTED_DEEPSLATE,
            ModBlocks.INFESTED_COBBLED_DEEPSLATE,
            ModBlocks.INFESTED_DEEPSLATE_BRICKS,
            ModBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS,
            ModBlocks.INFESTED_CHISELED_DEEPSLATE
        ));
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

    public static <T> ResourceKey<T> resourceKey(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return ResourceKey.create(registryKey, id(path));
    }

}