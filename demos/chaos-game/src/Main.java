import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.core.exceptions.CloudCreateException;
import xyz.iotacb.cloud.core.exceptions.CloudInitializeException;
import xyz.iotacb.cloud.utilities.math.Vector;

public class Main {
	
	public static void main(String...strings) throws CloudInitializeException, CloudCreateException {
		Display display = new Display(Vector.createVector(1920, 1080), "Sierpinski Triangle", false, true);
		
		display.setScreen(Panel.class);
		display.setFrameLock(60);
		
		display.start();
	}

}
