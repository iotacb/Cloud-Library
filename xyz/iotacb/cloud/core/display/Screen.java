package xyz.iotacb.cloud.core.display;

import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public abstract class Screen {
	
	public VectorI screenDimensions;
	
	public Display display;
	
	public Screen(final Display display) {
		this.display = display;
		this.screenDimensions = display.displayDimensions;
	}
	
	/**
	 * Methods which will be called in the display class
	 */
	public abstract void init();
	public abstract void update();
	public abstract void draw();

}
