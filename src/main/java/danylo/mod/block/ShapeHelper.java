package danylo.mod.block;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Helper class for rotating custom collision boxes depending on the direction.
 * No inheritance/instances allowed.
 */
public final class ShapeHelper {
    private ShapeHelper() {
        throw new UnsupportedOperationException("Helper class, no instances allowed.");
    }

    /**
     *
     * @param from initial direction
     * @param to target direction
     * @param shape collision box to be rotated
     * @return VoxelShape (rotated collision box)
     */
    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        // Only handle horizontal rotations
        if (from.getAxis() == Direction.Axis.Y || to.getAxis() == Direction.Axis.Y)
            return shape;

        int rotations = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        VoxelShape rotated = shape;
        for (int i = 0; i < rotations; i++) {
            rotated = rotate90Clockwise(rotated);
        }
        return rotated;
    }

    private static VoxelShape rotate90Clockwise(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{Shapes.empty()};
        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Rotate around Y axis: (x, z) -> (z, 1 - x)
            buffer[0] = Shapes.or(buffer[0],
                    Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX));
        });
        return buffer[0];
    }
}
