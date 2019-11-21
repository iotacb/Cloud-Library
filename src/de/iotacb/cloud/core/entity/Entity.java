package de.iotacb.cloud.core.entity;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.collision.Collision;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Randoms;
import de.iotacb.cloud.utilities.math.Vector;

public abstract class Entity {
	
	public int x, y, width, height, radius;
	public int windowWidth, windowHeight;
	
	public Vector location, size;
	
	public long entityId;
	
	public Window window;
	
	private void createEntity(Window window, long entityId, int x, int y, int width, int height, int radius) {
		this.window = window;
		this.entityId = (entityId <= -1 ? Randoms.randomLong(Long.MAX_VALUE) : entityId);
		this.x = x;
		this.y = y;
		this.location = new Vector(x, y);
		this.width = width;
		this.height = height;
		this.radius = radius;
		this.size = new Vector(width, height, radius);
		this.windowWidth = window.windowWidth;
		this.windowHeight = window.windowHeight;
		initialize();
	}
	
	public Entity(Window window) {
		createEntity(window, -1, 0, 0, 0, 0, 0);
	}
	
	public Entity(Window window, long entityId) {
		createEntity(window, entityId, 0, 0, 0, 0, 0);
	}
	
	public Entity(Window window, int x, int y) {
		createEntity(window, -1, x, y, 0, 0, 0);
	}
	
	public Entity(Window window, int x, int y, int width, int height) {
		createEntity(window, -1, x, y, width, height, 0);
	}
	
	public Entity(Window window, int x, int y, int width, int height, int radius) {
		createEntity(window, -1, x, y, width, height, radius);
	}
	
	public Entity(Window window, Vector location) {
		createEntity(window, -1, (int)location.x, (int)location.y, 0, 0, 0);
	}
	
	public Entity(Window window, Vector location, int width, int height) {
		createEntity(window, -1, (int)location.x, (int)location.y, width, height, 0);
	}
	
	public Entity(Window window, Vector location, int radius) {
		createEntity(window, -1, (int)location.x, (int)location.y, 0, 0, radius);
	}
	
	public Entity(Window window, Vector location, Vector size) {
		createEntity(window, -1, (int)location.x, (int)location.y, (int)size.x, (int)size.y, (int)size.z);
	}
	
	public Entity(Window window, int x, int y, Vector size) {
		createEntity(window, -1, x, y, (int)size.x, (int)size.y, (int)size.z);
	}
	
	public Entity(Window window, long entityId, int x, int y) {
		createEntity(window, entityId, x, y, 0, 0, 0);
	}
	
	public Entity(Window window, long entityId, int x, int y, int width, int height) {
		createEntity(window, entityId, x, y, width, height, 0);
	}
	
	public Entity(Window window, long entityId, int x, int y, int width, int height, int radius) {
		createEntity(window, entityId, x, y, width, height, radius);
	}
	
	public Entity(Window window, long entityId, int x, int y, int radius) {
		createEntity(window, entityId, x, y, 0, 0, radius);
	}
	
	public Entity(Window window, long entityId, Vector location) {
		createEntity(window, entityId, (int)location.x, (int)location.y, 0, 0, 0);
	}
	
	public Entity(Window window, long entityId, Vector location, int width, int height) {
		createEntity(window, entityId, (int)location.x, (int)location.y, width, height, 0);
	}
	
	public Entity(Window window, long entityId, Vector location, int radius) {
		createEntity(window, entityId, (int)location.x, (int)location.y, 0, 0, radius);
	}
	
	public Entity(Window window, long entityId, Vector location, Vector size) {
		createEntity(window, entityId, (int)location.x, (int)location.y, (int)size.x, (int)size.y, (int)size.z);
	}
	
	public Entity(Window window, long entityId, int x, int y, Vector size) {
		createEntity(window, entityId, x, y, (int)size.x, (int)size.y, (int)size.z);
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		this.location.x = x;
		this.location.y = y;
	}
	
	public void setLocation(Vector location) {
		this.x = (int)location.x;
		this.y = (int)location.y;
		this.location.set(location);
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.size.x = width;
		this.size.y = height;
	}
	
	public void setSize(int width, int height, int radius) {
		this.width = width;
		this.height = height;
		this.size.x = width;
		this.size.y = height;
		this.size.z = radius;
	}
	
	public void setSize(Vector size) {
		this.width = (int)size.x;
		this.height = (int)size.y;
		this.radius = (int)size.z;
		this.size.set(size);
	}
	
	public void setLocationAndSize(int x, int y, int width, int height) {
		setLocation(x, y);
		setSize(width, height);
	}
	
	public void setLocationAndSize(int x, int y, int radius) {
		setLocation(x, y);
		setSize(0, 0, radius);
	}
	
	public void setLocationAndSize(Vector location, Vector size) {
		setLocation(location);
		setSize(size);
	}
	
	public void moveTo(double direction, double speed, int key) {
		if (window.inputHandler.isKeyDown(key) || key == -1) {
			this.x += Maths.lengthDirX(speed, direction);
			this.y += Maths.lengthDirY(speed, direction);
			this.location.x += Maths.lengthDirX(speed, direction);
			this.location.y += Maths.lengthDirY(speed, direction);
		}
	}
	
	public void moveAway(double direction, double speed, int key) {
		if (window.inputHandler.isKeyDown(key) || key == -1) {
			this.x -= Maths.lengthDirX(speed, direction);
			this.y -= Maths.lengthDirY(speed, direction);
			this.location.x -= Maths.lengthDirX(speed, direction);
			this.location.y -= Maths.lengthDirY(speed, direction);
		}
	}
	
	public void boostDirection(double direction, double boostDistance) {
		x += Maths.lengthDirX(boostDistance, direction);
		y += Maths.lengthDirY(boostDistance, direction);
	}
	
	public void moveToLocation(double locationX, double locationY, double speed, double threshold, int key) {
		double direction = Maths.direction(this.x, this.y, locationX, locationY);
		if (Maths.dist(this.x, this.y, locationX, locationY) >= threshold) moveTo(direction, speed, key);
	}
	
	public void moveToLocation(Vector location, double speed, double threshold, int key) {
		moveToLocation(location.x, location.y, speed, threshold, key);
	}
	
	public void leaveLocation(double locationX, double locationY, double speed, double threshold, int key) {
		double direction = Maths.direction(this.x,  this.y, locationX, locationY);
		if (Maths.dist(this.x, this.y, locationX, locationY) > threshold) moveAway(direction, speed, key);
	}
	
	public void leaveLocation(Vector location, double speed, double threshold, int key) {
		leaveLocation(location.x, location.y, speed, threshold, key);
	}
	
	public void moveWithKeys(int keyLeft, int keyRight, int keyUp, int keyDown, double speed) {
		int moveX = this.window.inputHandler.isKeyDown(keyLeft) ? -1 : this.window.inputHandler.isKeyDown(keyRight) ? 1 : 0;
		int moveY = this.window.inputHandler.isKeyDown(keyUp) ? -1 : this.window.inputHandler.isKeyDown(keyDown) ? 1 : 0;
		
		Vector input = new Vector(moveX, moveY);
		Vector direction = input.normalize();
		Vector velocity = direction.scale(speed);
		Vector moveAmount = velocity.scale(window.getDelta());
		
		this.x += moveAmount.x;
		this.y += moveAmount.y;
		this.location.x += moveAmount.x;
		this.location.y += moveAmount.y;
	}
	
	public void moveWASD(double speed) {
		moveWithKeys(Keys.A, Keys.D, Keys.W, Keys.S, speed);
	}
	
	public void moveArrows(double speed) {
		moveWithKeys(Keys.UP, Keys.RIGHT, Keys.UP, Keys.DOWN, speed);
	}
	
    public void moveWithGamepad(double speed, double threshold, int moveStick) {
    	double stickX = moveStick == 0 ? window.inputHandler.getLeftJoystickX() : window.inputHandler.getRightJoystickX();
    	double stickY = moveStick == 0 ? window.inputHandler.getLeftJoystickY() : window.inputHandler.getRightJoystickY();
    	
    	stickX = Math.abs(stickX) > threshold ? stickX : 0;
    	stickY = Math.abs(stickY) > threshold ? stickY : 0;
    	
		Vector input = new Vector(stickX, stickY);
		Vector velocity = input.scale(speed);
		Vector moveAmount = velocity.scale(window.getDelta());
		
		this.x += moveAmount.x;
		this.y += moveAmount.y;
		this.location.x += moveAmount.x;
		this.location.y += moveAmount.y;
    }
    
}
