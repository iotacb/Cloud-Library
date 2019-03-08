package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.utilities.math.vector.VectorD;
import xyz.iotacb.cloud.utilities.math.vector.VectorF;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Collisions {
	
	/**
	 * Checks if a rectangular object intersects with a circular object
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
	
	public static boolean rectIntersectsCircle(final float x, final float y, final float width, final float height, final float x2, final float y2, final float radius) {
		double deltaX = x2 - Math.max(x, Math.min(x2, x + width));
		double deltaY = y2 - Math.max(y, Math.min(y2, y + height));
		return (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) < Math.pow(radius, 2);
	}
	
	public static boolean rectIntersectsCircle(final VectorF loc, final VectorF dimensions, final VectorF loc2, final float radius) {
		return rectIntersectsCircle(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, radius);
	}
	
	public static boolean rectIntersectsCircle(final AABB aabb, final VectorF loc2, final float radius) {
		return rectIntersectsCircle(aabb.minX, aabb.minY, aabb.maxX, aabb.maxY, loc2.x, loc2.y, radius);
	}
	
	public static boolean rectIntersectsCircle(final int x, final int y, final int width, final int height, final int x2, final int y2, final int radius) {
		double deltaX = x2 - Math.max(x, Math.min(x2, x + width));
		double deltaY = y2 - Math.max(y, Math.min(y2, y + height));
		return (Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) < Math.pow(radius, 2);
	}
	
	public static boolean rectIntersectsCircle(final VectorI loc, final VectorI dimensions, final VectorI loc2, final int radius) {
		return rectIntersectsCircle(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, radius);
	}
	
	public static boolean rectIntersectsCircle(final AABB aabb, final VectorI loc2, final int radius) {
		return rectIntersectsCircle(aabb.minX, aabb.minY, aabb.maxX, aabb.maxY, loc2.x, loc2.y, radius);
	}
	//
	
	/**
	 * Checks if a circular object intersects with a circular object
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
	
	public static boolean circleIntersectsCircle(final float x, final float y, final float x2, final float y2, final float radius, final float radius2) {
		VectorD v = new VectorD(x, y);
		VectorD v1 = new VectorD(x2, y2);
		return (v.dist(v1) < radius + radius2);
	}
	
	public static boolean circleIntersectsCircle(final VectorF loc, final VectorF loc2, final float radius, final float radius2) {
		return circleIntersectsCircle(loc.x, loc.y, loc2.x, loc2.y, radius, radius2);
	}
	
	public static boolean circleIntersectsCircle(final AABB aabb, final float radius, final float radius2) {
		return circleIntersectsCircle(aabb.minX, aabb.minX, aabb.maxX, aabb.maxY, radius, radius2);
	}
	
	public static boolean circleIntersectsCircle(final int x, final int y, final int x2, final int y2, final int radius, final int radius2) {
		VectorD v = new VectorD(x, y);
		VectorD v1 = new VectorD(x2, y2);
		return (v.dist(v1) < radius + radius2);
	}
	
	public static boolean circleIntersectsCircle(final VectorI loc, final VectorI loc2, final int radius, final int radius2) {
		return circleIntersectsCircle(loc.x, loc.y, loc2.x, loc2.y, radius, radius2);
	}
	
	public static boolean circleIntersectsCircle(final AABB aabb, final int radius, final int radius2) {
		return circleIntersectsCircle(aabb.minX, aabb.minX, aabb.maxX, aabb.maxY, radius, radius2);
	}
	//
	
	/**
	 * Checks if a rectangular object intersects with a rectangular object
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
	
	public static boolean rectIntersectsRect(final float x, final float y, final float width, final float height, final float x2, final float y2, final float width2, final float height2) {
		double aX = x, aX2 = x + width;
		double aY = y, aY2 = y + height;
		double bX = x2, bX2 = x2 + width2;
		double bY = y2, bY2 = y2 + height2;
		return (aX < bX2 && aX2 > bX && aY < bY2 && aY2 > bY);
	}
	
	public static boolean rectIntersectsRect(final VectorF loc, final VectorF dimensions, final VectorF loc2, final VectorF dimensions2) {
		return rectIntersectsRect(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, dimensions2.x, dimensions2.y);
	}
	
	public static boolean rectIntersectsRect(final int x, final int y, final int width, final int height, final int x2, final int y2, final int width2, final int height2) {
		double aX = x, aX2 = x + width;
		double aY = y, aY2 = y + height;
		double bX = x2, bX2 = x2 + width2;
		double bY = y2, bY2 = y2 + height2;
		return (aX < bX2 && aX2 > bX && aY < bY2 && aY2 > bY);
	}
	
	public static boolean rectIntersectsRect(final VectorI loc, final VectorI dimensions, final VectorI loc2, final VectorI dimensions2) {
		return rectIntersectsRect(loc.x, loc.y, dimensions.x, dimensions.y, loc2.x, loc2.y, dimensions2.x, dimensions2.y);
	}
	//
	
}
