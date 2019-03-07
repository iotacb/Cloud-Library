package xyz.iotacb.cloud.core.display;

import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public abstract class Screen {
	
	public VectorI screenDimensions; // Stores screenDimensions
	
	public Display display;
	
	public Screen(final Display display) {
		this.display = display;
		this.screenDimensions = display.displayDimensions;
	}
	
	public Screen() {}
	
	public abstract void init(); // Will be called when the screen is set in the display
	public abstract void update(); // Will be called before the draw method
	public abstract void draw(); // Will be called after the update method

}
