package com.axialeaa.deepslate_dungeons.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import survivalblock.atmosphere.registrar.BlockRegistrant;
import survivalblock.atmosphere.registrar.annotation.ConstructBlock;
import survivalblock.atmosphere.registrar.annotation.ConstructItem;
import survivalblock.atmosphere.registrar.wrapper.BlockPresenter;

import static net.minecraft.world.level.block.Blocks.*;

@ConstructBlock
public final class ModBlocks {

	private static final BlockRegistrant REGISTRANT = new BlockRegistrant(DeepslateDungeons.MOD_ID);

	@ConstructItem
    public static final BlockPresenter<InfestedBlock>
		INFESTED_COBBLED_DEEPSLATE = infestedDeepslate(COBBLED_DEEPSLATE, SoundType.DEEPSLATE),
		INFESTED_DEEPSLATE_BRICKS = infestedDeepslate(DEEPSLATE_BRICKS, SoundType.DEEPSLATE_BRICKS),
		INFESTED_CRACKED_DEEPSLATE_BRICKS = infestedDeepslate(CRACKED_DEEPSLATE_BRICKS, SoundType.DEEPSLATE_BRICKS),
		INFESTED_CHISELED_DEEPSLATE = infestedDeepslate(CHISELED_DEEPSLATE, SoundType.DEEPSLATE_BRICKS);

	private ModBlocks() {}

	public static BlockPresenter<InfestedBlock> infestedDeepslate(Block host, SoundType soundType) {
		return REGISTRANT.registerAndPresent(
			p -> new InfestedBlock(host, p),
			BlockBehaviour.Properties.of()
				.mapColor(MapColor.DEEPSLATE)
				.sound(soundType)
		);
    }

    public static void init() {}

}
