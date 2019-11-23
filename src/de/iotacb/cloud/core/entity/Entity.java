package de.iotacb.cloud.core.entity;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Randoms;
import de.iotacb.cloud.utilities.math.vector.VectorD;
import de.iotacb.cloud.utilities.math.vector.VectorI;

public abstract class Entity {
	
	public VectorD location;
	
	public long entityId;
	
	public Window window;
	
	private void createEntity(Window window, long entityId, int x, int y) {
		this.window = window;
		this.entityId = (entityId <= -1 ? Randoms.randomLong(Long.MAX_VALUE) : entityId);
		this.location = new VectorD(x, y);
		initialize();
	}
	
	public Entity(Window window) {
		createEntity(window, -1, 0, 0);
	}
	
	public Entity(Window window, long entityId) {
		createEntity(window, entityId, 0, 0);
	}
	
	public Entity(Window window, int x, int y) {
		createEntity(window, -1, x, y);
	}
	
	public Entity(Window window, VectorI location) {
		createEntity(window, -1, (int)location.x, (int)location.y);
	}
	
	public Entity(Window window, long entityId, int x, int y) {
		createEntity(window, entityId, x, y);
	}
	
	public Entity(Window window, long entityId, VectorI location) {
		createEntity(window, entityId, (int)location.x, (int)location.y);
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw();
	
	public void setLocation(int x, int y) {
		this.location.x = x;
		this.location.y = y;
	}
	
	public void setLocation(VectorD location) {
		this.location.set(location);
	}
	
	public void moveTo(double direction, double speed, int key) {
		if (window.inputHandler.isKeyDown(key) || key == -1) {
			this.location.x += Maths.lengthDirX(speed, direction);
			this.location.y += Maths.lengthDirY(speed, direction);
		}
	}
	
	public void moveAway(double direction, double speed, int key) {
		if (window.inputHandler.isKeyDown(key) || key == -1) {
			this.location.x -= Maths.lengthDirX(speed, direction);
			this.location.y -= Maths.lengthDirY(speed, direction);
		}
	}
	
	public void moveToLocation(double locationX, double locationY, double speed, double threshold, int key) {
		double direction = Maths.direction(location.x, location.y, locationX, locationY);
		if (Maths.dist(location.x, location.y, locationX, locationY) >= threshold) moveTo(direction, speed, key);
	}
	
	public void moveToLocation(VectorI location, double speed, double threshold, int key) {
		moveToLocation(location.x, location.y, speed, threshold, key);
	}
	
	public void leaveLocation(double locationX, double locationY, double speed, double threshold, int key) {
		double direction = Maths.direction(location.x, location.y, locationX, locationY);
		if (Maths.dist(location.x, location.y, locationX, locationY) > threshold) moveAway(direction, speed, key);
	}
	
	public void leaveLocation(VectorD location, double speed, double threshold, int key) {
		leaveLocation(location.x, location.y, speed, threshold, key);
	}
	
	public void moveWithKeys(int keyLeft, int keyRight, int keyUp, int keyDown, double speed) {
		int moveX = this.window.inputHandler.isKeyDown(keyLeft) ? -1 : this.window.inputHandler.isKeyDown(keyRight) ? 1 : 0;
		int moveY = this.window.inputHandler.isKeyDown(keyUp) ? -1 : this.window.inputHandler.isKeyDown(keyDown) ? 1 : 0;
		
		VectorD input = new VectorD(moveX, moveY);
		VectorD direction = input.normalize();
		VectorD velocity = direction.scale(speed);
		VectorD moveAmount = velocity.scale(window.deltaTime);
		
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
    	
		VectorD input = new VectorD(stickX, stickY);
		VectorD velocity = input.scale(speed);
		VectorD moveAmount = velocity.scale(window.deltaTime);
		
		this.location.x += moveAmount.x;
		this.location.y += moveAmount.y;
    }
    
    public int getX() {
		return (int)location.x;
	}
    
    public int getY() {
		return (int)location.y;
	}
    
}
