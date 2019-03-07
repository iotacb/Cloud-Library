package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.utilities.math.vector.VectorD;

public class Collisions {
	
	/**
	 * Check if rectangle intersects with a circle
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
		return (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) < Math.pow(radius, 2);
	}
	
	public static boolean rectIntersectsCircle(final VectorD loc, final VectorD dimensions, final VectorD loc2, final double radius) {
		return rectIntersectsCircle(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, radius);
	}
	
	public static boolean rectIntersectsCircle(final AABB aabb, final VectorD loc2, final double radius) {
		return rectIntersectsCircle(aabb.minX, aabb.minY, aabb.maxX, aabb.maxY, loc2.x, loc2.y, radius);
	}
	
	//
	
	/**
	 * Check if circle intersects with a circle
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param radius
	 * @param radius2
	 * @return
	 */
	public static boolean circleIntersectsCircle(final double x, final double y, final double x2, final double y2, final double radius, final double radius2) {
		VectorD v = new VectorD(x, y);
		VectorD v1 = new VectorD(x2, y2);
		return (v.dist(v1) < radius + radius2);
	}
	
	public static boolean circleIntersectsCircle(final VectorD loc, final VectorD loc2, final double radius, final double radius2) {
		return circleIntersectsCircle(loc.x, loc.y, loc2.x, loc2.y, radius, radius2);
	}
	
	public static boolean circleIntersectsCircle(final AABB aabb, final double radius, final double radius2) {
		return circleIntersectsCircle(aabb.minX, aabb.minX, aabb.maxX, aabb.maxY, radius, radius2);
	}
	
	//
	
	/**
	 * Check if rectangle intersects with a rectangle
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
	
	public static boolean rectIntersectsRect(final VectorD loc, final VectorD dimensions, final VectorD loc2, final VectorD dimensions2) {
		return rectIntersectsRect(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, dimensions2.x, dimensions2.y);
	}
	
	public static boolean rectIntersectsRect(final AABB aabb, final AABB aabb2) {
		return rectIntersectsRect(aabb.minX, aabb.minY, aabb.maxX, aabb.maxY, aabb2.minX, aabb2.minY, aabb2.maxX, aabb2.maxY);
	}
	
	//
	
}
