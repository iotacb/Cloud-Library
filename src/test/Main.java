package test;

import de.iotacb.cloud.core.window.Window;

public class Main {
	
	public static void main(final String...strings) throws Exception {
		final Window window = new Window(1280, 720, "Joana");
		window.setScene(Sample.class);
		window.setFPSCap(60);
		window.show();
	}

}
