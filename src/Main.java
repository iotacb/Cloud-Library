import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.core.exceptions.CloudCreateException;
import xyz.iotacb.cloud.core.exceptions.CloudInitializeException;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Main {

	public static void main(String[] args) throws CloudInitializeException, CloudCreateException {
		Display display = new Display(new VectorI(1280, 720), "Demo", false, true);
		display.setMainScreen(Plane.class);
		display.setSwapInterval(1);
		display.update();
	}

}
