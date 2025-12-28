package com.axialeaa.modid;

import com.axialeaa.modid.network.ExampleS2CPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID = "mod-id";
	public static final String MOD_NAME = "Example Mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} initialized! Insert funny joke here.", MOD_NAME);
		PayloadTypeRegistry.configurationS2C().register(ExampleS2CPayload.TYPE, ExampleS2CPayload.STREAM_CODEC);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> payloadType(String path) {
		return new CustomPacketPayload.Type<>(id(path));
	}

	public static void meow() {
		LOGGER.info("Meow :3");
	}

}