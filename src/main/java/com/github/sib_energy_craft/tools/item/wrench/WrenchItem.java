package com.github.sib_energy_craft.tools.item.wrench;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;


/**
 * @since 0.0.5
 * @author sibmaks
 */
public class WrenchItem extends Item {

    public WrenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        if(world.isClient) {
            return ActionResult.PASS;
        }

        var side = context.getSide();
        var blockPos = context.getBlockPos();
        var blockState = world.getBlockState(blockPos);
        if(blockState.contains(Properties.FACING)) {
            var currentValue = blockState.get(Properties.FACING);
            if(currentValue == side) {
                blockState = blockState.with(Properties.FACING, side.getOpposite());
            } else {
                blockState = blockState.with(Properties.FACING, side);
            }
            return changeBlockState(context, world, blockPos, blockState);
        } else if(blockState.contains(Properties.HORIZONTAL_FACING)) {
            if(side.getAxis() != Direction.Axis.Y) {
                var currentValue = blockState.get(Properties.HORIZONTAL_FACING);
                if(currentValue == side) {
                    blockState = blockState.with(Properties.HORIZONTAL_FACING, side.getOpposite());
                } else {
                    blockState = blockState.with(Properties.HORIZONTAL_FACING, side);
                }
                return changeBlockState(context, world, blockPos, blockState);
            }
        } else if(blockState.contains(Properties.AXIS)) {
            var axis = blockState.get(Properties.AXIS);
            if(axis != side.getAxis()) {
                blockState = blockState.with(Properties.AXIS, side.getAxis());
                return changeBlockState(context, world, blockPos, blockState);
            }
        }
        return ActionResult.PASS;
    }

    @NotNull
    protected ActionResult changeBlockState(ItemUsageContext context,
                                            World world,
                                            BlockPos blockPos,
                                            BlockState blockState) {
        onUse(context);
        world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
        return ActionResult.SUCCESS;
    }

    /**
     * Called each time when wrench used on block
     *
     * @param context wrench usage context
     */
    protected void onUse(ItemUsageContext context) {
        var player = context.getPlayer();
        if(player != null && !player.isCreative()) {
            var stack = context.getStack();
            stack.damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
        }
    }

}
