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
	
	private void createEntity(Window window, long entityId, double x, double y) {
		this.window = window;
		this.entityId = (entityId <= -1 ? Randoms.randomLong(Long.MAX_VALUE) : entityId);
		this.location = new Vec(x, y);
		initialize();
	}
	
	public Entity(Window window) {
		createEntity(window, -1, 0, 0);
	}
	
	public Entity(Window window, long entityId) {
		createEntity(window, entityId, 0, 0);
	}
	
	public Entity(Window window, double x, double y) {
		createEntity(window, -1, x, y);
	}
	
	public Entity(Window window, Vec location) {
		createEntity(window, -1, location.x, location.y);
	}
	
	public Entity(Window window, long entityId, double x, double y) {
		createEntity(window, entityId, x, y);
	}
	
	public Entity(Window window, long entityId, Vec location) {
		createEntity(window, entityId, location.x, location.y);
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	
	public void setLocation(double x, double y) {
		location.set(x, y);
	}
	
	public void setLocation(Vec location) {
		this.location.set(location);
	}
	
	public void moveTo(double direction, double speed, int key) {
		if (window.getInput().getKey(key) || key == -1) {
			this.location.x += Maths.lengthDirX(speed, direction);
			this.location.y += Maths.lengthDirY(speed, direction);
		}
	}
	
	public void moveAway(double direction, double speed, int key) {
		if (window.getInput().getKey(key) || key == -1) {
			this.location.x -= Maths.lengthDirX(speed, direction);
			this.location.y -= Maths.lengthDirY(speed, direction);
		}
	}
	
	public void moveToLocation(double locationX, double locationY, double speed, double threshold, int key) {
		double direction = Maths.direction(location.x, location.y, locationX, locationY) - 90;
		if (Maths.dist(location.x, location.y, locationX, locationY) >= threshold) moveTo(direction, speed, key);
	}
	
	public void moveToLocation(Vec location, double speed, double threshold, int key) {
		moveToLocation(location.x, location.y, speed, threshold, key);
	}
	
	public void leaveLocation(double locationX, double locationY, double speed, double threshold, int key) {
		double direction = Maths.direction(location.x, location.y, locationX, locationY);
		if (Maths.dist(location.x, location.y, locationX, locationY) > threshold) moveAway(direction, speed, key);
	}
	
	public void leaveLocation(Vec location, double speed, double threshold, int key) {
		leaveLocation(location.x, location.y, speed, threshold, key);
	}
	
	public void moveWithKeys(int keyLeft, int keyRight, int keyUp, int keyDown, double speed) {
		int moveX = this.window.getInput().getKey(keyLeft) ? -1 : this.window.getInput().getKey(keyRight) ? 1 : 0;
		int moveY = this.window.getInput().getKey(keyUp) ? -1 : this.window.getInput().getKey(keyDown) ? 1 : 0;
		
		Vec input = new Vec(moveX, moveY);
		Vec direction = input.normalize();
		Vec velocity = direction.mul(speed, speed, speed);
		Vec moveAmount = velocity.clone();
		
		this.location.add(moveAmount);
	}
	
	public void moveWASD(double speed) {
		moveWithKeys(Keys.KEY_A, Keys.KEY_D, Keys.KEY_W, Keys.KEY_S, speed);
	}
	
	public void moveArrows(double speed) {
		moveWithKeys(Keys.KEY_LEFT, Keys.KEY_RIGHT, Keys.KEY_UP, Keys.KEY_DOWN, speed);
	}
	
    public void moveWithGamepad(double speed, double threshold, int moveStick) {
    	double stickX = moveStick == 0 ? window.getInput().getLeftJoystickX() : window.getInput().getRightJoystickX();
    	double stickY = moveStick == 0 ? window.getInput().getLeftJoystickY() : window.getInput().getRightJoystickY();
    	
    	stickX = Math.abs(stickX) > threshold ? stickX : 0;
    	stickY = Math.abs(stickY) > threshold ? stickY : 0;
    	
    	Vec input = new Vec(stickX, stickY);
    	Vec velocity = input.mul(speed, speed, speed);
    	Vec moveAmount = velocity.clone();
		
		this.location.add(moveAmount);
    }
    
    public double getX() {
		return location.x;
	}
    
    public double getY() {
		return location.y;
	}
    
}
