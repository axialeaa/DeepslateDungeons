package com.axialeaa.deepslate_dungeons.data.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public interface DeepslateDungeonsGamerules {

    GameRuleCategory CATEGORY = GameRuleCategory.register(DeepslateDungeons.id("config"));

    GameRule<Integer> STRONGHOLD_Y_THRESHOLD = registerUnboundedInt("stronghold_y_threshold", 0);
    GameRule<Integer> DUNGEON_ORIGIN_Y_THRESHOLD = registerUnboundedInt("dungeon_origin_y_threshold", 0);
    GameRule<Boolean> STRONGHOLD_CONVERT_OAK = registerBoolean("stronghold_convert_oak", true);
    GameRule<Boolean> STRONGHOLD_CONVERT_TORCHES = registerBoolean("stronghold_convert_torches", false);

    private static GameRule<Boolean> registerBoolean(String path, boolean defaultValue) {
        return register(GameRuleBuilder.forBoolean(defaultValue), path);
    }

    private static GameRule<Integer> registerUnboundedInt(String path, int defaultValue) {
        return register(GameRuleBuilder.forInteger(defaultValue), path);
    }

    private static <T> GameRule<T> register(GameRuleBuilder<T> builder, String path) {
        return builder.category(CATEGORY).buildAndRegister(DeepslateDungeons.id(path));
    }

    static void init() {}

}
