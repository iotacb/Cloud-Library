package de.iotacb.homepi2.gui.keyboard;

import de.iotacb.cloud.core.gui.Button;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Vec;

public class Key extends Button {
	
	KeyboardRenderer kr;

	public Key(Window window, String title, Vec size, Vec location, KeyboardRenderer kr) {
		super(window, title, size, location);
		this.kr = kr;
	}

	@Override
	public void click() {
		kr.addToInput(getTitle());
	}
	
}
