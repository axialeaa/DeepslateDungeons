package com.axialeaa.deepslate_dungeons.mixin.bookshelf;

import com.axialeaa.deepslate_dungeons.ChiseledBookShelfHelper;
import com.axialeaa.deepslate_dungeons.data.registry.ModGameRules;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StrongholdPieces.Library.class)
public abstract class LibraryMixin extends StructurePiece {

    @Unique private static final int WEST_OUTER_X = 1;
    @Unique private static final int EAST_OUTER_X = 12;

    protected LibraryMixin(StructurePieceType structurePieceType, int i, BoundingBox boundingBox) {
        super(structurePieceType, i, boundingBox);
    }

    @WrapOperation(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/StrongholdPieces$Library;generateBox(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;IIIIIILnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Z)V"))
    private void convertOuterBookshelvesToChiseled(StrongholdPieces.Library instance, WorldGenLevel worldGenLevel, BoundingBox boundingBox, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState blockState, BlockState blockState2, boolean b, Operation<Void> original, @Local(argsOnly = true, name = "random") RandomSource random) {
        GameRules gameRules = worldGenLevel.getLevel().getGameRules();
        boolean enabled = gameRules.get(ModGameRules.CHISELED_BOOKSHELVES_IN_LIBRARY);

        if (isOuterBookshelf(minX) && blockState.is(Blocks.BOOKSHELF) && enabled) {
            this.generateBookshelfBox(worldGenLevel, boundingBox, minX, minY, minZ, maxX, maxY, maxZ, random);
            return;
        }

        original.call(instance, worldGenLevel, boundingBox, minX, minY, minZ, maxX, maxY, maxZ, blockState, blockState2, b);
    }

    @Unique
    private static boolean isOuterBookshelf(int x) {
        return x == EAST_OUTER_X || x == WEST_OUTER_X;
    }

    @Unique
    private void generateBookshelfBox(WorldGenLevel worldGenLevel, BoundingBox boundingBox, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, RandomSource randomSource) {
        MinecraftServer server = worldGenLevel.getServer();

        Direction facing = minX == WEST_OUTER_X ? Direction.EAST : Direction.WEST;
        BlockState blockState = ChiseledBookShelfHelper.getBlockState(facing, this.getMirror(), this.getRotation());

        ChiseledBookShelfHelper.generate(worldGenLevel, server, blockState, boundingBox, minX, minY, minZ, maxX, maxY, maxZ, this::getWorldPos, randomSource);
    }

}