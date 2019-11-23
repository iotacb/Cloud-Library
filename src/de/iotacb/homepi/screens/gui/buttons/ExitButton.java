package de.iotacb.homepi.screens.gui.buttons;

import de.iotacb.cloud.core.window.Window;

public class ExitButton extends Button {
	
	public ExitButton(Window window, int x, int y, String title) {
		super(window, x, y, title);
	}
	
	@Override
	public void onClick() {
		System.exit(0);
		super.onClick();
	}
	
	@Override
	public void update() {
		super.update();
	}

}
