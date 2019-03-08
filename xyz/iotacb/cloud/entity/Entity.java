package xyz.iotacb.cloud.entity;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.utilities.math.vector.VectorD;

public abstract class Entity {

	public VectorD location, screenDimensions;

	public Display display;

	public Entity(final Display display) {
		this.display = display;
		this.location = new VectorD();
		this.screenDimensions = display.displayDimensions.makeDouble();
		init();
	}

	public abstract void init();

	/**
	 * Will be called in the screen class
	 */
	public abstract void update();
	public abstract void draw();

	/**
	 * Refreshs the screen dimensions for the entity (Should be used when the window is resizeable)
	 */
	public void refreshEntity() {
		this.screenDimensions = display.displayDimensions.makeDouble();
	}

	/**
	 * Sets the default movement for the entity
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param speed
	 */
	public void moveEntityWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight,
			final VectorD speed) {
		location.add(
				(display.keyHandler.isKeyDown(keyLeft) ? -speed.x
						: display.keyHandler.isKeyDown(keyRight) ? speed.x : 0),
				(display.keyHandler.isKeyDown(keyUp) ? -speed.y
						: display.keyHandler.isKeyDown(keyDown) ? speed.y : 0));
	}
	
	/**
	 * Sets the default movement for the entity
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param speed
	 */
	public void moveEntityWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight,
			final double speed) {
		location.add(
				(display.keyHandler.isKeyDown(keyLeft) ? -speed
						: display.keyHandler.isKeyDown(keyRight) ? speed : 0),
				(display.keyHandler.isKeyDown(keyUp) ? -speed
						: display.keyHandler.isKeyDown(keyDown) ? speed : 0));
	}
	
}
