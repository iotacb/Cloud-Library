package xyz.iotacb.cloud.entity;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.utilities.input.Keys;
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
	 * Refreshs the screen dimensions for the entity (Should be used when the window
	 * is resizeable)
	 */
	public void refreshEntity() {
		this.screenDimensions = display.displayDimensions.makeDouble();
	}

	/**
	 * Sets the default movement for the entity
	 * 
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param speed
	 */
	public void moveEntityWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight,
			final VectorD speed) {
		if (display.keyHandler.isKeyDown(keyUp)) {
			location.add(0, -speed.y);
		}
		if (display.keyHandler.isKeyDown(keyLeft)) {
			location.add(-speed.x, 0);
		}
		if (display.keyHandler.isKeyDown(keyDown)) {
			location.add(0, speed.y);
		}
		if (display.keyHandler.isKeyDown(keyRight)) {
			location.add(speed.x, 0);
		}
	}

	/**
	 * Sets the default movement for the entity
	 * 
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param speed
	 */
	public void moveEntityWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight,
			final double speed) {
		moveEntityWithKeys(keyUp, keyLeft, keyDown, keyRight, new VectorD(speed, speed));
	}

	/**
	 * Sets the default movement for the entity
	 * 
	 * @param speed
	 */
	public void moveEntityWithWASD(final double speed) {
		moveEntityWithKeys(Keys.W, Keys.A, Keys.S, Keys.D, speed);
	}

	/**
	 * Sets the default movement for the entity
	 * 
	 * @param speed
	 */
	public void moveEntityWithArrows(final double speed) {
		moveEntityWithKeys(Keys.UP, Keys.LEFT, Keys.DOWN, Keys.RIGHT, speed);
	}

	/**
	 * Returns the yaw speed value from an angle
	 * 
	 * @param angle
	 * @return
	 */
	public double getYawSpeed(final double angle) {
		return Math.cos(Math.toRadians(angle - 90));
	}

	/**
	 * Returns the pitch speed value from an angle
	 * 
	 * @param angle
	 * @return
	 */
	public double getPitchSpeed(final double angle) {
		return Math.sin(Math.toRadians(angle - 90));
	}
	
	/**
	 * Move the entity into the direction of the given angle
	 * @param angle
	 * @param speed
	 */
	public void moveEntityToAngle(final double angle, final VectorD speed) {
		location.add(getYawSpeed(angle) * speed.x, getPitchSpeed(angle) * speed.y);
	}
	
	/**
	 * Move the entity into the direction of the given angle
	 * @param angle
	 * @param speed
	 */
	public void moveEntityToAngle(final double angle, final double speed) {
		moveEntityToAngle(angle, new VectorD(speed, speed));
	}

}
