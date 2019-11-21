package de.iotacb.homepi;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.homepi.screens.main.MainScreen;

public class Main {
	
	public static void main(String...strings) throws Exception {
		Window window = new Window(600, 600, "HomePi", false, true);
		window.setFPSCap(144);
		window.setWorld(MainScreen.class);
		window.show();
	}

}
