package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.entity.Entity;
import xyz.iotacb.cloud.utilities.lists.CloudList;
import xyz.iotacb.cloud.utilities.math.Vector;

public class Bounds {

	public Vector location, dimensions;
	private Entity myself;
	
	/**
	 * Create the bounding box
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param myself
	 */
	public Bounds(final double x, final double y, final double width, final double height, final Entity myself) {
		this.location = Vector.createVector(x, y);
		this.dimensions = Vector.createVector(width, height);
		this.myself = myself;
	}
	
	/**
	 * Create the bounding box
	 * @param location
	 * @param dimensions
	 * @param myself
	 */
	public Bounds(final Vector location, final Vector dimensions, final Entity myself) {
		this.location = location;
		this.dimensions = dimensions;
		this.myself = myself;
	}
	
	/**
	 * Create the bounding box from a another one
	 * @param bounds
	 */
	public Bounds(final Bounds bounds) {
		this.location = bounds.location;
		this.dimensions = bounds.dimensions;
		this.myself = bounds.myself;
	}
	
	/**
	 * Checks if the bounding box intersects with a bounding box
	 * @param bounds
	 * @return
	 */
	public boolean intersects(final Bounds bounds) {
		return Collisions.rectIntersectsRect(location, dimensions, bounds.location, bounds.dimensions);
	}
	
	/**
	 * Checks if the bounding box intersects with a other bounding box of a entity
	 * @param entities
	 * @return
	 */
	public boolean intersects(final CloudList<Entity> entities) {
		boolean intersects = false;
		for (Entity entity : entities) {
			if (intersects(entity.getBounds())) {
				intersects = true;
			}
		}
		return intersects;
	}
	
	/**
	 * Checks on which side the hitbox collided
	 * -1 = None, 0 = Top, 1 = Left, 2 = Bottom, 3 = Right
	 * @param bounds
	 * @return
	 */
	public int getCollisionSide(final Bounds bounds) {
		int side = -1;
		Bounds own = copy();
		if (bounds.intersects(own)) {
			if (Math.abs(own.getCenterX() - bounds.getCenterX()) < Math.abs(own.getCenterY() - bounds.getCenterY())) {
				if (own.getCenterY() < bounds.getCenterY()) {
					side = 0; // Top
				}
				if (own.getCenterY() > bounds.getCenterY()) {
					side = 2; // Bottom
				}
			} else {
				if (own.getCenterX() < bounds.getCenterX()) {
					side = 1; // Left;
				}
				if (own.getCenterX() > bounds.getCenterX()) {
					side = 3; // Right;
				}
			}
		}
		return side;
	}
	
	/**
	 * Checks on which side the hitbox collided
	 * -1 = None, 0 = Top, 1 = Left, 2 = Bottom, 3 = Right
	 * @param entities
	 * @return
	 */
	public int getCollisionSide(final CloudList<Entity> entities) {
		int side = -1;
		Bounds own = copy();
		for (Entity entity : entities) {
			Bounds bounds = entity.getBounds().copy();
			if (bounds.intersects(own)) {
				if (Math.abs(own.getCenterX() - bounds.getCenterX()) < Math.abs(own.getCenterY() - bounds.getCenterY())) {
					if (own.getCenterY() < bounds.getCenterY()) {
						side = 0; // Top
					}
					if (own.getCenterY() > bounds.getCenterY()) {
						side = 2; // Bottom
					}
				} else {
					if (own.getCenterX() < bounds.getCenterX()) {
						side = 1; // Left;
					}
					if (own.getCenterX() > bounds.getCenterX()) {
						side = 3; // Right;
					}
				}
			}
		}
		return side;
	}
	
	/**
	 * Handle movement collision for a other entity
	 * @param other
	 */
	public void stopMovement(final Entity other) {
		Bounds own = this.myself.getBounds().copy();
		Bounds bounds = other.getBounds().copy();
		if (own.getCollisionSide(bounds) == 0) {
			this.myself.location.y = bounds.location.y - own.dimensions.y;
			this.myself.gravity.y = 0;
		}
		if (own.getCollisionSide(bounds) == 1) {
			this.myself.location.x = bounds.location.x - own.dimensions.x;
		}
		if (own.getCollisionSide(bounds) == 2) {
			this.myself.location.y = bounds.location.y + bounds.dimensions.y;
			this.myself.gravity.y = 0.1;
		}
		if (own.getCollisionSide(bounds) == 3) {
			this.myself.location.x = bounds.location.x + bounds.dimensions.x;
		}
	}
	
	/**
	 * Handle movment collision for multiple entities
	 * @param entities
	 */
	public void stopMovement(final CloudList<Entity> entities) {
		for (Entity entity : entities) {
			stopMovement(entity);
		}
	}
	
	/**
	 * Get the center x of the bounding box
	 * @return
	 */
	public double getCenterX() {
		return location.x + dimensions.getCenter().x;
	}
	
	/**
	 * Get the center y of the bounding box
	 * @return
	 */
	public double getCenterY() {
		return location.y + dimensions.getCenter().y;
	}
	
	/**
	 * Get the center x and y of the bounding box
	 * @return
	 */
	public Vector getCenter() {
		return Vector.createVector(getCenterX(), getCenterY());
	}
	
	public Bounds copy() {
		return new Bounds(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s, %s)", location.x, location.y, dimensions.x, dimensions.y);
	}
	
}
