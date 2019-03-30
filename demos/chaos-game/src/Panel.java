import java.awt.Color;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.core.display.Screen;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.input.Keys;
import xyz.iotacb.cloud.utilities.lists.CloudList;
import xyz.iotacb.cloud.utilities.math.Random;
import xyz.iotacb.cloud.utilities.rendering.Render;
import xyz.iotacb.cloud.utilities.rendering.Text;
import xyz.iotacb.cloud.utilities.time.Timer;

public class Panel extends Screen {

	TriangleDot A, B, C;
	int iterations, random, steps = 1, speed = 1000;

	CloudList<Dot> dots;
	Dot lastDot;

	Text fps = new Text(10);
	Text iter = new Text(10);
	Text esc = new Text(10);
	Text lastDize = new Text(10);
	Text speedText = new Text(10);
	
	Timer delay = new Timer();

	public Panel(Display display) {
		super(display);
	}

	@Override
	public void draw() {
		A.draw();
		B.draw();
		C.draw();
		dots.forEach(Dot::draw);

		fps.drawText(2, 4, String.format("FPS: %s", display.currentFPS));
		iter.drawText(2, 24, String.format("Iterations: %s		Loops per iteration: %s Press SPACE to increase", iterations, steps));
		esc.drawText(2, 42, "Press ESCAPE to reset.");
		lastDize.drawText(screenDimensions.x - lastDize.getTextWidth(), 4, String.format("Last throw: %s", random));
		speedText.drawText(screenDimensions.x - speedText.getTextWidth(), 24, String.format("Speed: %s", speed));
		
		if (lastDot == null) return;
		Render.color(Colors.addAlpha(Color.white, 20));
		Render.drawCircle(display.mouseX - 100, display.mouseY - 100, 100);
	}

	@Override
	public void init() {
		dots = new CloudList<Dot>();

		A = new TriangleDot(display, 0);
		A.location.set(screenDimensions.getCenter().x, 30);
		B = new TriangleDot(display, 1);
		B.location.set(30, screenDimensions.y - 30);
		C = new TriangleDot(display, 2);
		C.location.set(screenDimensions.x - 30, screenDimensions.y - 30);
	}

	@Override
	public void update() {
		if (display.inputHandler.isKeyPressed(Keys.ESCAPE)) {
			lastDot = null;
			dots.clear();
			iterations = 0;
			speed = 1000;
		}
		
		if (display.inputHandler.isKeyPressed(Keys.SPACE)) {
			steps++;
		}
		
		if (display.inputHandler.isMouseButtonDown(0) && lastDot == null) {
			Dot dot = new Dot(display, null, null);
			dot.location.set(display.mouseX - dot.size, display.mouseY - dot.size);
			lastDot = dot;
			dots.add(dot);
		}
		
//		speed += (display.inputHandler.isKeyDown(Keys.UP) ? 1 : display.inputHandler.isKeyDown(Keys.DOWN) ? -1 : 0);
		if (lastDot == null || iterations >= 6000)
			return;
		
		if (speed > 0) speed--;
		if (delay.isOver(speed)) {
			for (int i = 0; i < steps; i++) {
				this.iterations++;
				this.random = Random.randomInt(1, 6);
				if (this.random > 0 && this.random < 3) {
					Dot dot = new Dot(display, A, lastDot);
					dots.add(dot);
					lastDot = dot;
				} else if (this.random > 2 && this.random < 5) {
					Dot dot = new Dot(display, B, lastDot);
					dots.add(dot);
					lastDot = dot;
				} else if (this.random > 4 && this.random < 7) {
					Dot dot = new Dot(display, C, lastDot);
					dots.add(dot);
					lastDot = dot;
				}
			}
		}
		
		this.dots.forEach(Dot::update);
	}

}
