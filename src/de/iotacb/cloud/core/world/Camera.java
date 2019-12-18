package de.iotacb.cloud.core.world;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;

public class Camera {
	
	Window window;
	
	double xOffset, yOffset;
	
	public Camera(Window window, double xOffset, double yOffset) {
		this.window = window;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void move(double x, double y) {
		xOffset += x;
		yOffset += y;
	}
	
	public void centerOnEntity(Entity entity, Vec entitySize) {
		xOffset = entity.location.x - window.getWindowWidth() / 2 + entitySize.x / 2;
		yOffset = entity.location.y - window.getWindowHeight() / 2 + entitySize.y / 2;
	}
	
	public double getxOffset() {
		return xOffset;
	}
	
	public double getyOffset() {
		return yOffset;
	}
	
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}
	
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

}
