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
 * Blocks which have direction and can be given custom collision boxes. The collision box rotates together
 * with the direction.
 */
public class CustomDirectionalBlock extends Block {
    private static final Direction DEFAULT_DIRECTION = Direction.NORTH;
    private final VoxelShape shape;
    private final boolean faceTowardsPlayer;


    protected CustomDirectionalBlock(Builder builder) {
        super(builder.properties);
        this.shape = builder.shape;
        this.faceTowardsPlayer = builder.faceTowardsPlayer;
    }

    public static Builder builder(BlockBehaviour.Properties properties, VoxelShape shape) {
        return new Builder(properties, shape);
    }

    public static class Builder {
        // Future blocks might need new properties and builder allows to add them without breaking the old blocks

        private final BlockBehaviour.Properties properties;
        private final VoxelShape shape;
        private boolean faceTowardsPlayer = false;

        protected Builder(BlockBehaviour.Properties properties, VoxelShape shape) {
            this.properties = properties;
            this.shape = shape;
        }

        public Builder faceTowardsPlayer() {
            this.faceTowardsPlayer = true;
            return this;
        }

        public CustomDirectionalBlock build() {
            return new CustomDirectionalBlock(this);
        }

    }


    /**
     * Calculates and returns a collision box based on the state of a block (so that the collision rotates together
     * with a block)
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return ShapeHelper.rotateShape(DEFAULT_DIRECTION, facing, this.shape);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // Adding the property to this block (it doesn't exist before)
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction blockDirection = context.getHorizontalDirection();  // facing towards player

        if (!this.faceTowardsPlayer) {
            blockDirection = blockDirection.getOpposite();  // change to facing from the player
        }

        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, blockDirection);
    }
}
