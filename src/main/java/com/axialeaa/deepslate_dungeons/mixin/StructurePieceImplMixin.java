package com.axialeaa.deepslate_dungeons.mixin;

import com.google.common.collect.ImmutableSet;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StructurePiece.class)
public class StructurePieceImplMixin {

    @Shadow protected BoundingBox boundingBox;

	@ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;builder()Lcom/google/common/collect/ImmutableSet$Builder;"))
	private static ImmutableSet.Builder<Block> addSoulTorchesToShapeCheckBlocks(ImmutableSet.Builder<Block> original) {
		return original.add(Blocks.SOUL_TORCH, Blocks.SOUL_WALL_TORCH);
	}

    @WrapMethod(method = "placeBlock")
    public void placeBlockImpl(WorldGenLevel level, BlockState blockState, int x, int y, int z, BoundingBox chunkBB, Operation<Void> original) {
        original.call(level, blockState, x, y, z, chunkBB);
    }

}
