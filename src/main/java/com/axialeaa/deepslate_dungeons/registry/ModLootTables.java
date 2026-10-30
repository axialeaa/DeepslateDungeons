package com.axialeaa.deepslate_dungeons.registry;

import com.axialeaa.deepslate_dungeons.DeepslateDungeons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public final class ModLootTables {

    public static final ResourceKey<LootTable> STRONGHOLD_LIBRARY_BOOKSHELF = create("chests/stronghold_library_bookshelf");

	private ModLootTables() {}

	public static ResourceKey<LootTable> create(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, DeepslateDungeons.id(path));
    }

}
