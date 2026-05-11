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


public class Album extends CustomDirectionalBlock {
    public static final VoxelShape DEFAULT_BOX = Block.box(4,0,2, 12, 10, 9);
    public static final VoxelShape FORM_0 = DEFAULT_BOX;
    public static final VoxelShape FORM_1 = Block.box(3,0,2, 13, 10, 9);
    public static final IntegerProperty ALBUM_FORM = IntegerProperty.create("album_form", 0, 1);


    protected Album(Builder builder) {
        super(builder);

        registerDefaultState(defaultBlockState().setValue(ALBUM_FORM, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ALBUM_FORM);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(interactionHand);

        if (!player.getAbilities().mayBuild || !stack.is(ModBlocks.ALBUM.asItem())) {
            // Skip if the player isn't allowed to modify or the item in hand isn't instance of PIGMENT_BUCKET
            return InteractionResult.PASS;
        } else {
            // Get the current value of the "ALBUM_FORM" property
            int album_form = blockState.getValue(ALBUM_FORM);

            // Cycling the forms
            album_form = album_form == 0 ? 1 : 0;


            level.setBlockAndUpdate(blockPos, blockState.setValue(ALBUM_FORM, album_form));
            level.playSound(player, blockPos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // Collision box depends on the form
        int album_form = state.getValue(ALBUM_FORM);
        VoxelShape baseShape = switch (album_form) {
            case 0 -> FORM_0;
            case 1 -> FORM_1;
            default -> throw new IllegalStateException("Unexpected value: " + album_form);
        };

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return ShapeHelper.rotateShape(Direction.NORTH, facing, baseShape);
    }
}
