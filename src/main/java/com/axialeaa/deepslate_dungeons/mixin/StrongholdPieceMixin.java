package com.axialeaa.deepslate_dungeons.mixin;

import com.axialeaa.deepslate_dungeons.util.Conversions;
import com.axialeaa.deepslate_dungeons.registry.ModGameRules;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @see net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces.StrongholdPiece
 */
@Mixin(targets = "net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces$StrongholdPiece")
public class StrongholdPieceMixin extends StructurePieceImplMixin {

    @Override
    public void placeBlockImpl(WorldGenLevel level, BlockState blockState, int x, int y, int z, BoundingBox chunkBB, Operation<Void> original) {
        GameRules gameRules = level.getLevel().getGameRules();

        if (this.boundingBox.minY() <= gameRules.get(ModGameRules.STRONGHOLD_ROOM_Y_THRESHOLD))
            blockState = Conversions.getDeepStrongholdVariant(gameRules, blockState.getBlock()).withPropertiesOf(blockState);

        super.placeBlockImpl(level, blockState, x, y, z, chunkBB, original);
    }

}
