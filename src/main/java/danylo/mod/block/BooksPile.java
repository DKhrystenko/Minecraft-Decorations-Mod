package danylo.mod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

/**
 * Placeable block. The block has multiple stages(see internal enum),
 *  which can be switched by RMB clicking while holding the block in hand.
 * The stages change the model of the block(the pile of books grows larger), and its collision box.
 */
public class BooksPile extends CustomDirectionalBlock {

    // Local enum with pile sizes
    private enum PileSize implements StringRepresentable {
        PILE_OF_2("pile_2"),
        PILE_OF_4("pile_4"),
        PILE_OF_6("pile_6"),
        PILE_OF_8("pile_8"),
        PILE_OF_8_WITH_1_VERTICAL("pile_8_1v");

        private final String name;

        PileSize(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    private static final EnumProperty<PileSize> PILE_SIZE = EnumProperty.create("pile_size", PileSize.class);

    public static final VoxelShape DEFAULT_BOX = Block.box(1, 0, 0, 13, 4, 13); // same as stage 1
    private static final VoxelShape PILE_2_BOX = DEFAULT_BOX;
    private static final VoxelShape PILE_4_BOX = Block.box(1, 0, 0, 13, 8, 13);
    private static final VoxelShape PILE_6_BOX = Block.box(1, 0, 0, 13, 12, 13);
    private static final VoxelShape PILE_8_BOX = Block.box(1, 0, 0, 13, 16, 13);
    private static final VoxelShape PILE_8_1v_BOX = Block.box(1, 0, 0, 13, 16, 16);


    public BooksPile(CustomDirectionalBlock.Builder builder) {
        super(builder);

        registerDefaultState(defaultBlockState().setValue(PILE_SIZE, PileSize.PILE_OF_2));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PILE_SIZE);
    }

    /**
     * On every RMB click changes the stage, which is used to display different models(small to large piles)
     */
    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(interactionHand);

        if (!player.getAbilities().mayBuild || !stack.is(ModBlocks.BOOKS.asItem())) {
            // Skip if the player isn't allowed to modify or the item in hand isn't instance of BOOKS
            return InteractionResult.PASS;
        } else {
            // Get the current value of the "pile_size" property
            PileSize pile_size = blockState.getValue(PILE_SIZE);

            // Cycling the pile sizes
            pile_size = switch (pile_size) {
                case PILE_OF_2 -> PileSize.PILE_OF_4;
                case PILE_OF_4 -> PileSize.PILE_OF_6;
                case PILE_OF_6 -> PileSize.PILE_OF_8;
                case PILE_OF_8 -> PileSize.PILE_OF_8_WITH_1_VERTICAL;
                case PILE_OF_8_WITH_1_VERTICAL -> PileSize.PILE_OF_2;
            };

            level.setBlockAndUpdate(blockPos, blockState.setValue(PILE_SIZE, pile_size));


            level.playSound(player, blockPos, SoundEvents.CANDLE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }
    }


    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // Collision box depends on the current pile size
        PileSize stage = state.getValue(PILE_SIZE);
        VoxelShape baseShape = switch (stage) {
            case PILE_OF_2 -> PILE_2_BOX;
            case PILE_OF_4 -> PILE_4_BOX;
            case PILE_OF_6 -> PILE_6_BOX;
            case PILE_OF_8 -> PILE_8_BOX;
            case PILE_OF_8_WITH_1_VERTICAL -> PILE_8_1v_BOX;
        };

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        return ShapeHelper.rotateShape(Direction.NORTH, facing, baseShape);
    }
}
