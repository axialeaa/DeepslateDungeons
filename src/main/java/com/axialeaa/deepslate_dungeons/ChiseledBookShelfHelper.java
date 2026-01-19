package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.DeepslateDungeonsLoot;
import com.axialeaa.deepslate_dungeons.mixin.LootTableAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

public class ChiseledBookShelfHelper {

    public static void fillBookshelfLoot(WorldGenLevel worldGenLevel, MinecraftServer server, BlockPos blockPos, BlockState blockState, ChiseledBookShelfBlockEntity blockEntity, RandomSource randomSource) {
        LootTable lootTable = server.reloadableRegistries().getLootTable(DeepslateDungeonsLoot.STRONGHOLD_LIBRARY_BOOKSHELF);
        LootParams.Builder lootParams = new LootParams.Builder(worldGenLevel.getLevel()).withParameter(LootContextParams.ORIGIN, blockPos.getCenter());

        ObjectArrayList<ItemStack> randomItems = lootTable.getRandomItems(lootParams.create(LootContextParamSets.CHEST), randomSource);

        if (lootTable instanceof LootTableAccessor lootTableAccessor) {
            List<Integer> availableSlots = lootTableAccessor.invokeGetAvailableSlots(blockEntity, randomSource);
            lootTableAccessor.invokeShuffleAndSplitItems(randomItems, availableSlots.size(), randomSource);

            setLoot(worldGenLevel, blockPos, blockState, blockEntity, randomItems, availableSlots);
        }
    }

    private static void setLoot(WorldGenLevel worldGenLevel, BlockPos blockPos, BlockState blockState, ChiseledBookShelfBlockEntity blockEntity, ObjectArrayList<ItemStack> randomItems, List<Integer> availableSlots) {
        for (ItemStack itemStack : randomItems) {
            if (availableSlots.isEmpty())
                continue;

            int index = availableSlots.removeLast();

            if (blockEntity.acceptsItemType(itemStack)) {
                blockEntity.getItems().set(index, itemStack);
                blockState = blockState.setValue(ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(index), true);
            }
        }

        worldGenLevel.setBlock(blockPos, blockState, Block.UPDATE_CLIENTS);
    }

}
