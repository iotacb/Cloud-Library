package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.utilities.math.Vector;

public class Collisions {
	
	/**
	 * This method will check if a rectangle intersects with a circle
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param x2
	 * @param y2
	 * @param radius
	 * @return
	 */
	public static boolean rectIntersectsCircle(final double x, final double y, final double width, final double height, final double x2, final double y2, final double radius) {
		double deltaX = x2 - Math.max(x, Math.min(x2, x + width));
		double deltaY = y2 - Math.max(y, Math.min(y2, y + height));
		return (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= radius;
	}
	
	/**
	 * This method will check if a rectangle intersects with a circle
	 * @param loc
	 * @param dimensions
	 * @param loc2
	 * @param radius
	 * @return
	 */
	public static boolean rectIntersectsCircle(final Vector loc, final Vector dimensions, final Vector loc2, final double radius) {
		return rectIntersectsCircle(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, radius);
	}

	/**
	 * This method will check if a circle intersects with a circle
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param radius
	 * @param radius2
	 * @return
	 */
	public static boolean circleIntersectsCircle(final double x, final double y, final double x2, final double y2, final double radius, final double radius2) {
		Vector v = new Vector(x, y);
		Vector v1 = new Vector(x2, y2);
		return (v.dist(v1) <= radius + radius2);
	}
	
	/**
	 * This method will check if a circle intersects with a circle
	 * @param loc
	 * @param loc2
	 * @param radius
	 * @param radius2
	 * @return
	 */
	public static boolean circleIntersectsCircle(final Vector loc, final Vector loc2, final double radius, final double radius2) {
		return circleIntersectsCircle(loc.x, loc.y, loc2.x, loc2.y, radius, radius2);
	}

	/**
	 * This method will check if a rectangle intersects with a rectangle
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param x2
	 * @param y2
	 * @param width2
	 * @param height2
	 * @return
	 */
	public static boolean rectIntersectsRect(final double x, final double y, final double width, final double height, final double x2, final double y2, final double width2, final double height2) {
		return ((x2 <= x && x2 + width2 > x) || (x2 <= x + width && x2 + width2 >= x + width)) && ((y2 <= y && y2 + height2 >= y) || (y2 <= y + height && y2 + height2 >= y + height));
	}
	
	/**
	 * This method will check if a rectangle intersects with a rectangle
	 * @param laoc
	 * @param dimensions
	 * @param loc2
	 * @param dimensions2
	 * @return
	 */
	public static boolean rectIntersectsRect(final Vector loc, final Vector dimensions, final Vector loc2, final Vector dimensions2) {
		return rectIntersectsRect(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, dimensions2.x, dimensions2.y);
	}
	
}
