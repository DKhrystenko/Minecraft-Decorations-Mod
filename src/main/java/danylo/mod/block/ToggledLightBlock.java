package danylo.mod.block;


import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import java.util.function.ToIntFunction;

/**
 * Blocks that can act as a light source and can be toggled(on/off) on RMB click.
 */
// TODO: Make toggle sound(toggleOn, toggleOff) customizable
public class ToggledLightBlock extends CustomDirectionalBlock {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public ToggledLightBlock(CustomDirectionalBlock.Builder builder) {
        super(builder);

        registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }

    /**
     * Produces a light-level function for use in Properties.lightLevel(...).
     * Call this when building the Properties for a specific block registration.
     */
    public static ToIntFunction<BlockState> luminance(int value) {
        if (value < 0 || value > 15) {
            throw new IllegalArgumentException("Luminance must be between 0 and 15");
        }

        return state -> state.getValue(ACTIVATED) ? value : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVATED);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        }

        boolean activated = state.getValue(ACTIVATED);
        level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, !activated));

        level.playSound(player, pos,
                activated ? SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF : SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
                SoundSource.BLOCKS, 1.0F, 1.0F);

        return InteractionResult.SUCCESS;
    }
}
