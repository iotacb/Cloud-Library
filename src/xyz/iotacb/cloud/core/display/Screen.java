package xyz.iotacb.cloud.core.display;

import java.util.ArrayList;
import java.util.List;

import xyz.iotacb.cloud.core.gui.Button;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public abstract class Screen {
	
	public VectorI screenDimensions;
	
	public Display display;
	
	public List<Button> buttonList;
	
	public Screen(final Display display) {
		this.display = display;
		this.screenDimensions = display.displayDimensions;
		this.buttonList = new ArrayList<Button>();
	}
	
	/**
	 * Methods which will be called in the display class
	 */
	public abstract void init();
	public abstract void update();
	public abstract void draw();
	
	public void drawButtons() {
		this.buttonList.forEach(b -> {
			b.draw();
			b.hovered = b.isHovered();
			if (b.hovered) {
				if (display.keyHandler.isMouseButtonDown(0)) {
					if (!b.clicked) {
						buttonActions(b);
						b.clicked = true;
					}
				} else {
					b.clicked = false;
				}
			}
		});
	}
	
	public void buttonActions(final Button button) {}

}
