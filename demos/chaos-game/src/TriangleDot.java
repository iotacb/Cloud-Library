import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.entity.Entity;
import xyz.iotacb.cloud.utilities.collisions.Bounds;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.rendering.Render;

public class TriangleDot extends Entity {
	
	int id;

	public TriangleDot(Display display, int id) {
		super(display);
	}

	@Override
	public void draw() {
		Render.color(Colors.rainbow(id * 1000));
		Render.drawCircle(location, 10);
	}

	@Override
	public Bounds getBounds() {
		return null;
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

}
