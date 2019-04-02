package xyz.iotacb.cloud.entity;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.core.display.Screen;
import xyz.iotacb.cloud.utilities.collisions.Bounds;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.input.Keys;
import xyz.iotacb.cloud.utilities.lists.CloudList;
import xyz.iotacb.cloud.utilities.math.Random;
import xyz.iotacb.cloud.utilities.math.Vector;
import xyz.iotacb.cloud.utilities.rendering.Render;

public abstract class Entity {

	/**
	 * The location of the entity and the dimensions of the screen
	 */
	public Vector location, screenDimensions, gravity, acceleration;

	/**
	 * The display variable which is created in the main method of your application
	 */
	public Display display;
	
	/**
	 * ID of the entity
	 */
	public int id;
	
	/**
	 * Deadzone of the joysticks
	 */
	public double joystickThreshold = 0.1;

	/**
	 * Create the entity and set the display
	 * @param display
	 */
	public Entity(final Display display) {
		this.display = display;
		this.location = new Vector();
		makeID(display.currentScreen);
		this.screenDimensions = display.displayDimensions;
		this.display.currentScreen.entities.add(this);
		this.gravity = Vector.createVector(0, 2);
		this.acceleration = Vector.createVector(0, 0.98);
		init();
	}
	
	/**
	 * Creates a Entity ID
	 * @param screen
	 */
	void makeID(Screen screen) {
		for (Entity entity : screen.entities) {
			int id = Random.randomInt(99999999);
			while (entity.id == id) {
				id = Random.randomInt(99999999);
			}
			this.id = id;
		}
	}

	/**
	 * The initialize method which is called when the Entity variable is initialized
	 */
	public abstract void init();

	/**
	 * The update method of the entity, call this in the screen update method
	 */
	public abstract void update();

	/**
	 * The draw method of the entity, call this in the screen render method
	 */
	public abstract void draw();

	/**
	 * Get the bounds of the entity
	 * @return
	 */
	public abstract Bounds getBounds();

	/**
	 * Draw the bounds of the entity
	 */
	public void drawBounds() {
		Render.color(Colors.addAlpha(Colors.rainbow(0), 100));
		Render.drawRectangle(location, getBounds().dimensions);
	}
	
	/**
	 * Returns all other entities
	 * @return
	 */
	public CloudList<Entity> getOtherEntities() {
		CloudList<Entity> others = new CloudList<Entity>();
		for (Entity entity : display.currentScreen.entities) {
			if (entity.equals(this)) continue;
			others.add(entity);
		}
		return others;
	}

	/**
	 * Move the entity with custom keys and custom speed
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param speed
	 */
	public void moveEntityWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight,
			final Vector speed) {
		if (display.inputHandler.isKeyDown(keyUp)) {
			location.add(0, -speed.y);
		}
		if (display.inputHandler.isKeyDown(keyLeft)) {
			location.add(-speed.x, 0);
		}
		if (display.inputHandler.isKeyDown(keyDown)) {
			location.add(0, speed.y);
		}
		if (display.inputHandler.isKeyDown(keyRight)) {
			location.add(speed.x, 0);
		}
	}

	/**
	 * Move the entity with custom keys and custom speed
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param speed
	 */
	public void moveEntityWithKeys(final int keyUp, final int keyLeft, final int keyDown, final int keyRight,
			final double speed) {
		moveEntityWithKeys(keyUp, keyLeft, keyDown, keyRight, new Vector(speed, speed));
	}

	/**
	 * Move thr entity with the WASD keys and custom speed
	 * @param speed
	 */
	public void moveEntityWithWASD(final double speed) {
		moveEntityWithKeys(Keys.W, Keys.A, Keys.S, Keys.D, speed);
	}

	/**
	 * Move the entity with the arrow keys and custom speed
	 * @param speed
	 */
	public void moveEntityWithArrows(final double speed) {
		moveEntityWithKeys(Keys.UP, Keys.LEFT, Keys.DOWN, Keys.RIGHT, speed);
	}

	/**
	 * Get the x rotation of the given angle
	 * @param angle
	 * @return
	 */
	public double getYawRotation(final double angle) {
		return Math.cos(Math.toRadians(angle - 90));
	}

	/**
	 * Get the y rotation of the given angle
	 * @param angle
	 * @return
	 */
	public double getPitchRotation(final double angle) {
		return Math.sin(Math.toRadians(angle - 90));
	}

	/**
	 * Move the entity into the direction of the given angle with custom speed
	 * @param angle
	 * @param speed
	 */
	public void moveEntityToAngle(final double angle, final Vector speed) {
		location.add(getYawRotation(angle) * speed.x, getPitchRotation(angle) * speed.y);
	}

	/**
	 * Move the entity into the direction of the given angle with custom speed
	 * @param angle
	 * @param speed
	 */
	public void moveEntityToAngle(final double angle, final double speed) {
		moveEntityToAngle(angle, new Vector(speed, speed));
	}

	/**
	 * Move the entity with the gamepad and custom speed
	 * @param speed
	 */
	public void moveEntityWithGamepad(final double speed) {
		float x = display.inputHandler.getLeftJoystickX();
		float y = display.inputHandler.getLeftJoystickY();
		if (Math.abs(x) > joystickThreshold) {
			if (x < 0) {
				location.add(-speed * Math.abs(x));
			}
			if (x > 0) {
				location.add(speed * Math.abs(x));
			}
		}
		if (Math.abs(y) > joystickThreshold) {
			if (y < 0) {
				location.add(0, -speed * Math.abs(y));
			}
			if (y > 0) {
				location.add(0, speed * Math.abs(y));
			}
		}
	}

	/**
	 * Move the entity with the gamepad and custom speed
	 * @param speed
	 */
	public void moveEntityWithGamepad(final Vector speed) {
		moveEntityWithGamepad(speed.x);
	}
	
	/**
	 * Get informations about the entity in a String format
	 */
	@Override
	public String toString() {
		return String.format("Cloud-Entity#ID:%s#Location:%s", this.id, this.location);
	}

}
