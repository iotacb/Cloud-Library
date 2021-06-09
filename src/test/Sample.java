package test;

import de.iotacb.cloud.core.gui.Button;
import de.iotacb.cloud.core.scene.Scene;
import de.iotacb.cloud.core.window.Window;

public class Sample extends Scene {
	
	private Button button;

	public Sample(Window window) {
		super(window);
	}

	@Override
	public void initialize() {
		this.button = new Button(window, 210, 50, 200, 40, "Test");
		addEntity(button);
	}
	
	@Override
	public void draw() {
		super.draw();
	}
	
	@Override
	public void update() {
		super.update();
	}

}
