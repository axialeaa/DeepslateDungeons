package com.axialeaa.deepslate_dungeons.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StructurePiece.class)
public abstract class StructurePieceImplMixin {

    @Shadow protected BoundingBox boundingBox;

    @WrapMethod(method = "placeBlock")
    public void placeBlockImpl(WorldGenLevel worldGenLevel, BlockState blockState, int i, int j, int k, BoundingBox boundingBox, Operation<Void> original) {
        original.call(worldGenLevel, blockState, i, j, k, boundingBox);
    }

}
