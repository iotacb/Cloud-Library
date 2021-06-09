package de.iotacb.cloud.core.entity;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Randoms;
import de.iotacb.cloud.utilities.math.Vec;

public abstract class Entity {
	
	public Vec location;
	
	public long entityId;
	
	public Window window;
	
	private void createEntity(final Window window, final long entityId, final float x, final float y) {
		this.window = window;
		this.entityId = (entityId <= -1 ? Randoms.randomLong(Long.MAX_VALUE) : entityId);
		this.location = new Vec(x, y);
		initialize();
	}
	
	public Entity(final Window window) {
		createEntity(window, -1, 0, 0);
	}
	
	public Entity(final Window window, final long entityId) {
		createEntity(window, entityId, 0, 0);
	}
	
	public Entity(final Window window, final float x, final float y) {
		createEntity(window, -1, x, y);
	}
	
	public Entity(final Window window, final Vec location) {
		createEntity(window, -1, location.x, location.y);
	}
	
	public Entity(final Window window, final long entityId, final float x, final float y) {
		createEntity(window, entityId, x, y);
	}
	
	public Entity(final Window window, final long entityId, final Vec location) {
		createEntity(window, entityId, location.x, location.y);
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	
	public final void setLocation(final float x, final float y) {
		location.set(x, y);
	}
	
	public final void setLocation(final Vec location) {
		this.location.set(location);
	}
	
	public final void moveTo(final float direction, final float speed, final int key) {
		if (window.getInput().getKey(key) || key == -1) {
			this.location.x += Maths.lengthDirX(speed, direction);
			this.location.y += Maths.lengthDirY(speed, direction);
		}
	}
	
	public final void moveAway(final float direction, final float speed, final int key) {
		if (window.getInput().getKey(key) || key == -1) {
			this.location.x -= Maths.lengthDirX(speed, direction);
			this.location.y -= Maths.lengthDirY(speed, direction);
		}
	}
	
	public final void moveToLocation(final float locationX, final float locationY, final float speed, final float threshold, final int key) {
		final float direction = Maths.direction(location.x, location.y, locationX, locationY) - 90;
		if (Maths.dist(location.x, location.y, locationX, locationY) >= threshold) moveTo(direction, speed, key);
	}
	
	public void moveToLocation(final Vec location, final float speed, final float threshold, final int key) {
		moveToLocation(location.x, location.y, speed, threshold, key);
	}
	
	public final void leaveLocation(final float locationX, final float locationY, final float speed, final float threshold, final int key) {
		final float direction = Maths.direction(location.x, location.y, locationX, locationY);
		if (Maths.dist(location.x, location.y, locationX, locationY) > threshold) moveAway(direction, speed, key);
	}
	
	public final void leaveLocation(final Vec location, final float speed, final float threshold, final int key) {
		leaveLocation(location.x, location.y, speed, threshold, key);
	}
	
	public final void moveWithKeys(final int keyLeft, final int keyRight, final int keyUp, final int keyDown, final float speed) {
		final int moveX = this.window.getInput().getKey(keyLeft) ? -1 : this.window.getInput().getKey(keyRight) ? 1 : 0;
		final int moveY = this.window.getInput().getKey(keyUp) ? -1 : this.window.getInput().getKey(keyDown) ? 1 : 0;
		
		final Vec input = new Vec(moveX, moveY);
		final Vec direction = input.normalize();
		final Vec velocity = direction.mul(speed, speed, speed);
		
		this.location.add(velocity);
	}
	
	public final void moveWASD(final float speed) {
		moveWithKeys(Keys.KEY_A, Keys.KEY_D, Keys.KEY_W, Keys.KEY_S, speed);
	}
	
	public final void moveArrows(final float speed) {
		moveWithKeys(Keys.KEY_LEFT, Keys.KEY_RIGHT, Keys.KEY_UP, Keys.KEY_DOWN, speed);
	}
	
    public final void moveWithGamepad(final float speed, final float threshold, final int moveStick) {
    	float stickX = moveStick == 0 ? window.getInput().getLeftJoystickX() : window.getInput().getRightJoystickX();
    	float stickY = moveStick == 0 ? window.getInput().getLeftJoystickY() : window.getInput().getRightJoystickY();
    	
    	stickX = Math.abs(stickX) > threshold ? stickX : 0;
    	stickY = Math.abs(stickY) > threshold ? stickY : 0;
    	
    	final Vec input = new Vec(stickX, stickY);
    	final Vec velocity = input.mul(speed, speed, speed);
		
		this.location.add(velocity);
    }
    
    public final float getX() {
		return location.x;
	}
    
    public final float getY() {
		return location.y;
	}
    
    public final Vec getLocation() {
		return location;
	}
    
}
