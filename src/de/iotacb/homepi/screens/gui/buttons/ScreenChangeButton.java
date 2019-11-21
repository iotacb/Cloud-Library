package de.iotacb.homepi.screens.gui.buttons;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.window.World;

public class ScreenChangeButton extends Button {

	Class<? extends World> nextWorld;
	
	public ScreenChangeButton(Window window, int x, int y, String title, Class<? extends World> nextWorld) {
		super(window, x, y, title);
		this.nextWorld = nextWorld;
	}
	
	@Override
	public void onClick() {
		window.setWorld(nextWorld);
		super.onClick();
	}

}
