package xyz.iotacb.cloud.utilities.collisions;

import java.awt.Color;

import xyz.iotacb.cloud.entity.Entity;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.lists.CloudList;
import xyz.iotacb.cloud.utilities.math.Vector3;
import xyz.iotacb.cloud.utilities.rendering.Render;

public class Bounds {

	public Vector3 location, dimensions;
	private Entity myself;
	public Bounds[] bounds;

	/**
	 * This creates a new bounds for the given parameters, additionally there are five more bounds created, which surround the main bounds
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param myself
	 */
	public Bounds(final double x, final double y, final double width, final double height, final Entity myself) {
		this.location = new Vector3(x, y);
		this.dimensions = new Vector3(width, height);
		this.myself = myself;
		setupSurroundingBounds(location, dimensions, myself, true);
	}

	/**
	 * This creates a new bounds for the given parameters, additionally there are five more bounds created, which surround the main bounds
	 * @param location
	 * @param dimensions
	 * @param myself
	 */
	public Bounds(final Vector3 location, final Vector3 dimensions, final Entity myself) {
		this.location = location;
		this.dimensions = dimensions;
		this.myself = myself;
		setupSurroundingBounds(location, dimensions, myself, true);
	}
	
	/**
	 * Don't use this constructor, it is used to create the surrounding boundes.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param myself
	 * @param useit
	 */
	@Deprecated
	public Bounds(final double x, final double y, final double width, final double height, final Entity myself, final boolean useit) {
		this.location = new Vector3(x, y);
		this.dimensions = new Vector3(width, height);
		this.myself = myself;
	}

	/**
	 * Don't use this constructor, it is used to create the surrounding bounds.
	 * @param location
	 * @param dimensions
	 * @param myself
	 * @param useit
	 */
	@Deprecated
	public Bounds(final Vector3 location, final Vector3 dimensions, final Entity myself, final boolean useit) {
		this.location = location;
		this.dimensions = dimensions;
		this.myself = myself;
	}

	private void setupSurroundingBounds(final Vector3 location, final Vector3 dimensions, final Entity myself, final boolean useit) {
		int size = 10;
		this.bounds = new Bounds[] {
				new Bounds(location, dimensions, myself, useit), // All
				new Bounds(Vector3.createVector(location.x, location.y), Vector3.createVector(dimensions.x, size), myself, useit), // Top
				new Bounds(Vector3.createVector(location.x, location.y + size), Vector3.createVector(size, dimensions.y - size), myself, useit), // Left
				new Bounds(Vector3.createVector(location.x + size, location.y + dimensions.y - size), Vector3.createVector(dimensions.x - size, size), myself, useit), // Bottom
				new Bounds(Vector3.createVector(location.x + dimensions.x - size, location.y + size), Vector3.createVector(size, dimensions.y - (size * 2)), myself, useit) // Right
		};
	}

	/**
	 * This method will check if this bounds intersects with the given one in the parameter
	 * @param bounds
	 * @return
	 */
	public boolean intersects(final Bounds bounds) {
		return Collisions.rectIntersectsRect(location, dimensions, bounds.location, bounds.dimensions);
	}

	/**
	 * This method will check if this bounds intersects with the bounds of a entity from the given entity list
	 * @param entities
	 * @return
	 */
	public boolean intersects(final CloudList<Entity> entities) {
		for (Entity entity : entities) {
			if (myself.equals(entity))
				continue;
			if (myself.getBounds().intersects(entity.getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns the side which the bound collided with (0 = Entire bounds, 1 = Top side, 2 = Left side, 3 = Bottom side, 4 = Right side)
	 * @param bounds
	 * @return
	 */
	public int collisionSide(final Bounds bounds) {
		int side = 0;
		for (int i = 0; i < this.bounds.length; i++) {
			if (bounds.intersects(this.bounds[i])) {
				side = i;
			}
		}
		return side;
	}
	
	/**
	 * This method will render the surrounding bounds of the main bounds
	 */
	public void drawSurroundingBounds() {
		for (Bounds b : this.bounds) {
			Render.color(Colors.addAlpha(Color.green, 100));
			Render.drawRectangle(b.location, b.dimensions);
		}
	}

}