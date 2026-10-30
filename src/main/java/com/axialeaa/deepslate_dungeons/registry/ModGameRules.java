package com.axialeaa.deepslate_dungeons.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import survivalblock.atmosphere.registrar.GameRuleRegistrant;

public final class ModGameRules {

    private static final GameRuleCategory CATEGORY = GameRuleCategory.register(DeepslateDungeons.id("gamerule_category"));
	private static final GameRuleRegistrant REGISTRANT = new GameRuleRegistrant(DeepslateDungeons.MOD_ID).specifyDefaultCategory(CATEGORY);

    public static final GameRule<Integer> STRONGHOLD_ROOM_Y_THRESHOLD = REGISTRANT.register("stronghold_room_y_threshold", GameRuleBuilder.forInteger(0));
    public static final GameRule<Integer> DUNGEON_ORIGIN_Y_THRESHOLD = REGISTRANT.register("dungeon_origin_y_threshold", GameRuleBuilder.forInteger(0));
    public static final GameRule<Boolean> STRONGHOLD_CONVERT_OAK = REGISTRANT.registerBoolean("stronghold_convert_oak", true);
    public static final GameRule<Boolean> STRONGHOLD_CONVERT_TORCHES = REGISTRANT.registerBoolean("stronghold_convert_torches", false);
    public static final GameRule<Boolean> CHISELED_BOOKSHELVES_IN_LIBRARY = REGISTRANT.registerBoolean("chiseled_bookshelves_in_library", false);

	private ModGameRules() {}

	public static void init() {}

}
