package de.iotacb.homepi2.gui;

import de.iotacb.cloud.core.gui.Button;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.core.world.World;
import de.iotacb.cloud.utilities.math.Vec;

public class WorldChangeButton extends Button {
	
	Class<? extends World> nextWorld;
	
	public WorldChangeButton(Window window, String title, Vec location, Class<? extends World> nextWorld) {
		super(window, title, new Vec(200, 40), location);
		this.nextWorld = nextWorld;
	}

	@Override
	public void click() {
		window.setWorld(nextWorld);
	}

}
