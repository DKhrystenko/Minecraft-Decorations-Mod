package danylo.mod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


/**
 * Blocks which have direction property and can be given custom collision boxes. The collision box rotates together
 * with the direction.
 */
public class CustomDirectionalBlock extends Block {
    protected final VoxelShape shape;
    private final Direction defaultDirection = Direction.NORTH;

    public CustomDirectionalBlock(VoxelShape shape, BlockBehaviour.Properties properties) {
        super(properties);
        this.shape = shape;
        // Set the default state with NORTH facing
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, defaultDirection));
    }


    /**
     * Calculates and returns a collision box based on the state of a block (so that the collision rotates together
     * with a block)
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return ShapeHelper.rotateShape(defaultDirection, facing, this.shape);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // Adding the property to this block (it doesn't exist before)
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // setting the blockState of this instance to the opposite of horizontal direction of the player when the block was placed
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING,
                        context.getHorizontalDirection().getOpposite());
    }

}
