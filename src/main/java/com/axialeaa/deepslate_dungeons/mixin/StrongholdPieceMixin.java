package com.axialeaa.deepslate_dungeons.mixin;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsGamerules;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces$StrongholdPiece")
public abstract class StrongholdPieceMixin extends StructurePieceImplMixin {

    @Override
    public void placeBlockImpl(WorldGenLevel worldGenLevel, BlockState blockState, int i, int j, int k, BoundingBox boundingBox, Operation<Void> original) {
        GameRules gameRules = worldGenLevel.getLevel().getGameRules();

        if (this.boundingBox.minY() <= gameRules.get(DeepslateDungeonsGamerules.STRONGHOLD_Y_THRESHOLD))
            blockState = DeepslateDungeons.deepStrongholdVariant(gameRules, blockState.getBlock()).withPropertiesOf(blockState);

        super.placeBlockImpl(worldGenLevel, blockState, i, j, k, boundingBox, original);
    }

}
