package de.iotacb.homepi;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.vector.VectorI;
import de.iotacb.cloud.utilities.window.Samples;
import de.iotacb.cloud.utilities.window.WindowOptions;
import de.iotacb.homepi.screens.main.MainScreen;

public class Main {
	
	public static void main(String...strings) throws Exception {
		Window window = new Window(new VectorI(), "HomePi", !WindowOptions.WINDOW_RESIZABLE, WindowOptions.WINDOW_FULLSCREEN, WindowOptions.WINDOW_VSYNC, Samples.SAMPLE_0);
		window.setFPSCap(60);
		window.setWorld(MainScreen.class);
		window.show();
	}

}
