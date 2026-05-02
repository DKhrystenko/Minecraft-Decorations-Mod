package danylo.mod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WallLamp extends CustomDirectionalBlock {
    public static final VoxelShape DEFAULT_BOX = Block.box(4, 7, 0, 13, 15, 7);
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public WallLamp(VoxelShape shape, Properties properties) {
        super(shape, properties);

        registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }


    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            // Skip if the player isn't allowed to modify the level.
            return InteractionResult.PASS;
        } else {
            // Get the current value of the "activated" property
            boolean activated = state.getValue(ACTIVATED);

            // Flip the value of activated and save the new blockstate
            level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, !activated));

            // Different sound for switch on/off
            if (!activated) {
                level.playSound(player, pos, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else {
                level.playSound(player, pos, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);

            }

            return InteractionResult.SUCCESS;
        }
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVATED);
    }

    public static int getLuminance(BlockState currentBlockState) {
        // Get the value of the "activated" property.
        boolean activated = currentBlockState.getValue(WallLamp.ACTIVATED);

        // Return a light level if activated = true
        return activated ? 12 : 0;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING,
                        context.getHorizontalDirection()); // no .getOpposite()
    }
}
