package de.iotacb.cloud.core.gui;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;

public class GuiComponent extends Entity {
	
	private Vec size;

	public GuiComponent(Window window, float x, float y, float width, float height) {
		super(window, x, y);
		this.size = new Vec(width, height);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw() {
	}
	
	public boolean isHovered() {
		final int mouseX = window.getMouseX();
		final int mouseY = window.getMouseY();
		return (mouseX > getX() && mouseX < getX() + size.x) && (mouseY > getY() && mouseY < getY() + size.y);
	}
	
	public Vec getSize() {
		return size;
	}
	
	public int getWidth() {
		return size.getXI();
	}
	
	public int getHeight() {
		return size.getYI();
	}

}
