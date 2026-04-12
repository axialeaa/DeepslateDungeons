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
public class StructurePieceImplMixin {

    @Shadow protected BoundingBox boundingBox;

    @WrapMethod(method = "placeBlock")
    public void placeBlockImpl(WorldGenLevel level, BlockState blockState, int x, int y, int z, BoundingBox chunkBB, Operation<Void> original) {
        original.call(level, blockState, x, y, z, chunkBB);
    }

}
