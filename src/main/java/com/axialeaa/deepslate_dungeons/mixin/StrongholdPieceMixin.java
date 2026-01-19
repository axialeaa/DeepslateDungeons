package com.axialeaa.deepslate_dungeons.mixin;

import com.axialeaa.deepslate_dungeons.ConversionHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.spongepowered.asm.mixin.Mixin;

import static com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsGamerules.*;

/**
 * @see net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces.StrongholdPiece
 */
@SuppressWarnings("JavadocReference")
@Mixin(targets = "net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces$StrongholdPiece")
public abstract class StrongholdPieceMixin extends StructurePieceImplMixin {

    @Override
    public void placeBlockImpl(WorldGenLevel worldGenLevel, BlockState blockState, int x, int y, int z, BoundingBox boundingBox, Operation<Void> original) {
        GameRules gameRules = worldGenLevel.getLevel().getGameRules();

        if (this.boundingBox.minY() <= gameRules.get(STRONGHOLD_Y_THRESHOLD))
            blockState = ConversionHelper.getDeepStrongholdVariant(gameRules, blockState.getBlock()).withPropertiesOf(blockState);

        super.placeBlockImpl(worldGenLevel, blockState, x, y, z, boundingBox, original);
    }

}
