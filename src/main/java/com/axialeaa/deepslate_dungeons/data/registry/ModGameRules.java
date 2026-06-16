package com.axialeaa.deepslate_dungeons.data.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public final class ModGameRules {

    public static final GameRuleCategory CATEGORY = GameRuleCategory.register(DeepslateDungeons.id("gamerules"));

    public static final GameRule<Integer> STRONGHOLD_ROOM_Y_THRESHOLD = registerUnboundedInt("stronghold_room_y_threshold", 0);
    public static final GameRule<Integer> DUNGEON_ORIGIN_Y_THRESHOLD = registerUnboundedInt("dungeon_origin_y_threshold", 0);
    public static final GameRule<Boolean> STRONGHOLD_CONVERT_OAK = registerBoolean("stronghold_convert_oak", true);
    public static final GameRule<Boolean> STRONGHOLD_CONVERT_TORCHES = registerBoolean("stronghold_convert_torches", false);
    public static final GameRule<Boolean> CHISELED_BOOKSHELVES_IN_LIBRARY = registerBoolean("chiseled_bookshelves_in_library", false);

    public static GameRule<Boolean> registerBoolean(String path, boolean defaultValue) {
        return register(GameRuleBuilder.forBoolean(defaultValue), path);
    }

    public static GameRule<Integer> registerUnboundedInt(String path, int defaultValue) {
        return register(GameRuleBuilder.forInteger(defaultValue), path);
    }

    public static <T> GameRule<T> register(GameRuleBuilder<T> builder, String path) {
        return builder.category(CATEGORY).buildAndRegister(DeepslateDungeons.id(path));
    }

    public static void init() {}

}
