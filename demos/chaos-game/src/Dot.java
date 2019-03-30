import java.awt.Color;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.entity.Entity;
import xyz.iotacb.cloud.utilities.collisions.Bounds;
import xyz.iotacb.cloud.utilities.math.Random;
import xyz.iotacb.cloud.utilities.math.Vector;
import xyz.iotacb.cloud.utilities.rendering.Render;

public class Dot extends Entity {
	
	TriangleDot triangleDot;
	Dot lastDot;
	int size;
	Color color;
	double time;
	Vector mouse;

	public Dot(Display display, TriangleDot triangleDot, Dot lastDot) {
		super(display);
		time = Random.randomDouble(1, 100);
		mouse = Vector.createVector(display.mouseX, display.mouseY);
		if (lastDot == null) {
			size = 10;
			color = Color.red;
			location.randomize(0, 1, 60, screenDimensions.y - 60);
			location.set(screenDimensions.getCenter().x);
		} else {
			size = 10;
			color = Color.white;
			location.set((triangleDot.location.x + lastDot.location.x) / 2, (triangleDot.location.y + lastDot.location.y) / 2);
		}
	}

	@Override
	public void draw() {
		double x = 0;
		double y = 0;
		if (location.dist(mouse) < 110) {
			x = Math.cos(time) * 20;
			y = Math.sin(time) * 20;
		}
		Render.color(color);
		Render.drawCircle(location.x + x, location.y + y, size);
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
		time += 0.05;
		mouse.set(display.mouseX - 10, display.mouseY - 10);
	}

}
