package com.axialeaa.deepslate_dungeons.mixin;

import com.axialeaa.deepslate_dungeons.data.registry.ModGameRules;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MonsterRoomFeature.class)
public class MonsterRoomFeatureMixin {

    @ModifyExpressionValue(method = "place", at = {
        @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;COBBLESTONE:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC),
        @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;MOSSY_COBBLESTONE:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.GETSTATIC)
    })
    private Block convertCobblestone(Block original, @Local(name = "origin") BlockPos origin, @Local(name = "level") WorldGenLevel level) {
        GameRules gameRules = level.getLevel().getGameRules();
        int threshold = gameRules.get(ModGameRules.DUNGEON_ORIGIN_Y_THRESHOLD);

        return origin.getY() > threshold ? original : Blocks.COBBLED_DEEPSLATE;
    }

}
