package com.axialeaa.deepslate_dungeons.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(LootTable.class)
public interface LootTableAccessor {

    @Invoker("shuffleAndSplitItems")
    void invokeShuffleAndSplitItems(ObjectArrayList<ItemStack> objectArrayList, int i, RandomSource randomSource);

    @Invoker("getAvailableSlots")
    List<Integer> invokeGetAvailableSlots(Container container, RandomSource randomSource);

}
