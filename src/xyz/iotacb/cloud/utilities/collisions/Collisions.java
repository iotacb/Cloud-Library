package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.utilities.math.Vector3;

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
		return (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) < radius;
	}
	
	/**
	 * This method will check if a rectangle intersects with a circle
	 * @param loc
	 * @param dimensions
	 * @param loc2
	 * @param radius
	 * @return
	 */
	public static boolean rectIntersectsCircle(final Vector3 loc, final Vector3 dimensions, final Vector3 loc2, final double radius) {
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
		Vector3 v = new Vector3(x, y);
		Vector3 v1 = new Vector3(x2, y2);
		return (v.dist(v1) < radius + radius2);
	}
	
	/**
	 * This method will check if a circle intersects with a circle
	 * @param loc
	 * @param loc2
	 * @param radius
	 * @param radius2
	 * @return
	 */
	public static boolean circleIntersectsCircle(final Vector3 loc, final Vector3 loc2, final double radius, final double radius2) {
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
		double aX = x, aX2 = x + width;
		double aY = y, aY2 = y + height;
		double bX = x2, bX2 = x2 + width2;
		double bY = y2, bY2 = y2 + height2;
		return (aX < bX2 && aX2 > bX && aY < bY2 && aY2 > bY);
	}
	
	/**
	 * This method will check if a rectangle intersects with a rectangle
	 * @param loc
	 * @param dimensions
	 * @param loc2
	 * @param dimensions2
	 * @return
	 */
	public static boolean rectIntersectsRect(final Vector3 loc, final Vector3 dimensions, final Vector3 loc2, final Vector3 dimensions2) {
		return rectIntersectsRect(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, dimensions2.x, dimensions2.y);
	}
	
}
