package com.axialeaa.deepslate_dungeons;

import com.axialeaa.deepslate_dungeons.data.registry.ModLootTables;
import com.axialeaa.deepslate_dungeons.mixin.bookshelf.LootTableAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public final class ChiseledBookShelfHelper {

    public static BlockState getBlockState(Direction facing, Mirror mirror, Rotation rotation) {
        BlockState blockState = Blocks.CHISELED_BOOKSHELF.defaultBlockState().setValue(ChiseledBookShelfBlock.FACING, facing);

        if (mirror != Mirror.NONE)
            blockState = blockState.mirror(mirror);

        if (rotation != Rotation.NONE)
            blockState = blockState.rotate(rotation);

        return blockState;
    }

    public static void generate(WorldGenLevel level, MinecraftServer server, BlockState state, BoundingBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, WorldPosGetter getter, RandomSource random) {
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos blockPos = getter.get(x, y, z);

                    if (box.isInside(blockPos)) {
                        level.setBlock(blockPos, state, Block.UPDATE_CLIENTS);
                        level.getBlockEntity(blockPos, BlockEntityTypes.CHISELED_BOOKSHELF)
                            .ifPresent(blockEntity -> setLoot(level, server, blockPos, blockEntity, random));
                    }
                }
            }
        }
    }

    private static void setLoot(WorldGenLevel level, MinecraftServer server, BlockPos pos, ChiseledBookShelfBlockEntity blockEntity, RandomSource random) {
        LootTable lootTable = server.reloadableRegistries().getLootTable(ModLootTables.STRONGHOLD_LIBRARY_BOOKSHELF);

        if (lootTable instanceof LootTableAccessor accessor) {
            LootParams.Builder lootParams = new LootParams.Builder(level.getLevel()).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
            ObjectArrayList<ItemStack> randomItems = lootTable.getRandomItems(lootParams.create(LootContextParamSets.CHEST), pos.asLong());

            List<Integer> availableSlots = accessor.invokeGetAvailableSlots(blockEntity, random);
            accessor.invokeShuffleAndSplitItems(randomItems, availableSlots.size(), random);

            setLoot(level, pos, blockEntity, randomItems, availableSlots);
        }
    }

    private static void setLoot(WorldGenLevel level, BlockPos pos, ChiseledBookShelfBlockEntity blockEntity, ObjectArrayList<ItemStack> randomItems, List<Integer> availableSlots) {
        BlockState blockState = blockEntity.getBlockState();

        if (availableSlots.isEmpty())
            return;

        for (ItemStack itemStack : randomItems) {
            int index = availableSlots.removeLast();

            if (blockEntity.acceptsItemType(itemStack)) {
                blockEntity.getItems().set(index, itemStack);
                blockState = blockState.setValue(ChiseledBookShelfBlock.SLOT_OCCUPIED_PROPERTIES.get(index), true);
            }
        }

        level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
    }

    /**
     * @see net.minecraft.world.level.levelgen.structure.StructurePiece#getWorldPos(int, int, int)
     */
    @FunctionalInterface
    public interface WorldPosGetter {

        BlockPos get(int x, int y, int z);

    }

}
