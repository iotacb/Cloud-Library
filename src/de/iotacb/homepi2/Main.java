package de.iotacb.homepi2;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.homepi2.menus.main.MainMenu;
import de.iotacb.homepi2.trash.Trasher;

public class Main {
	
	public static void main(String...strings) throws Exception {
		Window window = new Window(800, 400, "HomePI 2.0");
		window.initGpio();
		window.setVSync(false);
//		window.setFPSCap(60);
		
		window.setWorld(MainMenu.class);
		
		if (!System.getProperty("os.name").contains("Windows")) {
			Trasher trasher = new Trasher(window);
			trasher.start();
		}
		
		window.show();
	}

}
