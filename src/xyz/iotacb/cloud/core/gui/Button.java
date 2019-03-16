package xyz.iotacb.cloud.core.gui;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.utilities.math.vector.VectorD;

public abstract class Button {
	
	public int id;
	public VectorD location, dimension;

	public String buttonText;
	
	public boolean hovered, clicked;
	
	public Display display;
	
	public Button(final Display display, final int id, final VectorD location, final VectorD dimensions, final String text) {
		this.id = id;
		this.location = location;
		this.dimension = dimensions;
		this.buttonText = text;
		this.display = display;
		init();
	}
	
	public Button(final int id, final double x, final double y, final double width, final double height, final String text) {
		this.id = id;
		this.location = new VectorD(x, y);
		this.dimension = new VectorD(width, height);
		this.buttonText = text;
		init();
	}
	
	public void init() {
		location.add(-dimension.x / 2, -dimension.y / 2);
	}
	
	public abstract void draw();
	
	public boolean isHovered() {
		return (display.mouseX > location.x && display.mouseX < location.x + dimension.x && display.mouseY > location.y && display.mouseY < location.y + dimension.y);
	}
	
}
