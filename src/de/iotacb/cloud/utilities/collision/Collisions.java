package de.iotacb.cloud.utilities.collision;

import de.iotacb.cloud.entity.Entity;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Vector;

public class Collisions {

    /**
     * Check if two entities intersect with each other (Rectangle collision)
     *
     * @param entity1 The first entity
     * @param entity2 The second entity
     * @return If the two entities intersect with each other
     */
    public static boolean intersecting(final Entity entity1, final Entity entity2) {
        double tw = entity1.width;
        double th = entity1.height;
        double rw = entity2.width;
        double rh = entity2.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = entity1.x;
        double ty = entity1.y;
        double rx = entity2.x;
        double ry = entity1.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    /**
     * Check if two entities intersect with each other (Circular collision)
     *
     * @param entity1 The first entity
     * @param radius1 The second entity
     * @param entity2 The radius of the first entity
     * @param radius2 The radius of the second entity
     * @return If the two entities intersect with each other
     */
    public static boolean intersecting(final Entity entity1, final double radius1, final Entity entity2, final double radius2) {
        return (Vector.create(entity1.x, entity1.y).dist(Vector.create(entity2.x, entity2.y)) <= radius1 + radius2);
    }

    /**
     * Check if two entities intersect with each other (Rectangle and circular collision)
     *
     * @param entity1 The first entity
     * @param radius  The radius of the first entity
     * @param entity2 The second entity
     * @return If the two entities intersect with each other
     */
    public static boolean intersecting(final Entity entity1, final Entity entity2, final double radius) {
        double deltaX = entity2.x - Maths.max(entity1.x, Maths.min(entity2.x, entity1.x + entity1.width));
        double deltaY = entity2.y - Maths.max(entity1.y, Maths.min(entity2.y, entity1.y + entity1.height));
        return (Math.pow(deltaX, 2) + Math.pow(deltaY, 2) <= radius);
    }

    /**
     * Check if two rectangles intersect with each other
     *
     * @param x1      The x location of the first rectangle
     * @param y1      The y location of the first rectangle
     * @param width1  The width of the first rectangle
     * @param height1 The height of the first rectangle
     * @param x2      The x location of the second rectangle
     * @param y2      The y location of the second rectangle
     * @param width2  The width of the second rectangle
     * @param height2 The height of the second rectangle
     * @return If the two rectangles intersect with each other
     */
    public static boolean intersecting(final double x1, final double y1, final double width1, final double height1, final double x2, final double y2, final double width2, final double height2) {
        double tw = width1;
        double th = height1;
        double rw = width2;
        double rh = height2;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = x1;
        double ty = y1;
        double rx = x2;
        double ry = y2;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    /**
     * Check if two circles intersect with each other
     *
     * @param x1      The x location of the first circle
     * @param y1      The y location of the first circle
     * @param radius1 The radius of the first circle
     * @param x2      The x location of the second circle
     * @param y2      The y location of the second circle
     * @param radius2 The radius of the second circle
     * @return If the two circles intersect with each other
     */
    public static boolean intersecting(final double x1, final double y1, final double radius1, final double x2, final double y2, final double radius2) {
        return (Vector.create(x1, y1).dist(Vector.create(x2, y2)) <= radius1 + radius2);
    }

    /**
     * Check if a circle and a rectangle intersect with each other
     *
     * @param x1     The x location of the rectangle
     * @param y1     The y location of the rectangle
     * @param width  The width of the rectangle
     * @param height The height of the rectangle
     * @param x2     The x location of the circle
     * @param y2     The y location of the circle
     * @param radius The radius of the circle
     * @return If the circle and the rectangle intersect with each other
     */
    public static boolean intersecting(final double x1, final double y1, final double width, final double height, double x2, double y2, final double radius) {
        double xDelta = x2 - Math.max(x1, Math.min(x2, x1 + width));
        double yDelta = y2 - Math.max(y1, Math.min(y2, y1 + height));
        return (xDelta * xDelta + yDelta * yDelta) < (radius * radius);
    }

}
