package danylo.mod.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


public class PigmentBucket extends CustomDirectionalBlock {
    public static final VoxelShape DEFAULT_BOX = Block.box(0,0,0, 16, 9, 16);
    public static final VoxelShape RED_BOX = Block.box(3,0,1, 11, 9, 9);
    public static final VoxelShape BLUE_BOX = Block.box(7,0,2, 15, 9, 10);
    public static final IntegerProperty BUCKET_FORM = IntegerProperty.create("bucket_form", 0, 2);


    protected PigmentBucket(Builder builder) {
        super(builder);

        registerDefaultState(defaultBlockState().setValue(BUCKET_FORM, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BUCKET_FORM);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(interactionHand);

        if (!player.getAbilities().mayBuild || !stack.is(ModBlocks.PIGMENT_BUCKET.asItem())) {
            // Skip if the player isn't allowed to modify or the item in hand isn't instance of PIGMENT_BUCKET
            return InteractionResult.PASS;
        } else {
            // Get the current value of the "BUCKET_FORM" property
            int bucket_form = blockState.getValue(BUCKET_FORM);

            // Cycling the forms
            bucket_form = switch (bucket_form) {
                case 0 -> 1;
                case 1 -> 2;
                case 2 -> 0;
                default -> throw new IllegalStateException("Unexpected value: " + bucket_form);
            };


            level.setBlockAndUpdate(blockPos, blockState.setValue(BUCKET_FORM, bucket_form));
            level.playSound(player, blockPos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // Collision box depends on the form
        int bucket_form = state.getValue(BUCKET_FORM);
        VoxelShape baseShape = switch (bucket_form) {
            case 0 -> DEFAULT_BOX;
            case 1 -> RED_BOX;
            case 2 -> BLUE_BOX;
            default -> throw new IllegalStateException("Unexpected value: " + bucket_form);
        };

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return ShapeHelper.rotateShape(Direction.NORTH, facing, baseShape);
    }
}
