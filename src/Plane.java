
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.core.display.Screen;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.input.Keys;
import xyz.iotacb.cloud.utilities.math.vector.VectorD;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;
import xyz.iotacb.cloud.utilities.rendering.Render;
import xyz.iotacb.cloud.utilities.rendering.Text;

public class Plane extends Screen {

	public Plane(Display display) {
		super(display);
	}
	
	Text modeText = new Text(10), speedText = new Text(10), infoText = new Text(10), moveText = new Text(10), vectorText = new Text(10), watermark = new Text(10);
	
	double l = 0, speeder = 0.01;
	int mode = 0, xOffset = 0, yOffset = 0;
	
	Color color = Colors.random();
	
	boolean showInfo = false;

	@Override
	public void draw() {
		if (showInfo) {
			modeText.drawText(2, 2, "Mode: " + mode + " Press SPACE for next Mode");
			speedText.drawText(2, 20, "Speed: " + Math.round(speeder * 1000.0) / 1000.0 + " Press W or S to change speed");
			moveText.drawText(2, 38, "Move with arrow keys");
		} else {
			infoText.drawText(2, 2, "Press I to see controls and more infos");
		}
		vectorText.drawText(2, (!showInfo ? 20 : 56), "Location: " + new VectorI(xOffset, yOffset).toString() + " Press R to reset");
		watermark.drawText(screenDimensions.x - watermark.getTextWidth(), screenDimensions.y - watermark.getTextHeight() + 5, "Made with Cloud Engine");
		Render.start();
		Render.translate(screenDimensions.getCenter().makeDouble().x + xOffset, screenDimensions.getCenter().y + yOffset);
		Render.scale(2, 1);
		glBegin(GL_LINES);
		for (int i = 0; i < 100; i++) {
			Render.color(color);
			if (mode == 0) {
				glVertex2d(Math.cos(i + l) * 100, Math.tan(i + l) * 100);
				glVertex2d(Math.cos(i + l) * 100, Math.cos(i + l) * 100);
			} else if (mode == 1) {
				glVertex2d(Math.sin(i + l) * 100, Math.cos(i - l) * 100);
				glVertex2d(Math.sin(i - l) * 100, Math.cos(i + l) * 100);
			} else if (mode == 2) {
				glVertex2d(Math.cos(i + l) * 100, Math.sin(i - l) * 100);
				glVertex2d(Math.sin(i) * 100, Math.tan(i + l) * 100);
			} else if (mode == 3) {
				glVertex2d(Math.cos(i - l) * 100, Math.sin(i + l) * 100);
				glVertex2d(Math.cos(i + l) * 100, Math.sin(i + l) * 100);
			} else if (mode == 4) {
				glVertex2d(Math.tan(i + l) * 100, Math.sin(i + l) * 100);
				glVertex2d(Math.cos(i + l) * 100, Math.tan(i + l) * 100);
			} else if (mode == 5) {
				glVertex2d(Math.sin(i + l) * 100, 100 - Math.cos(i + l) * 100);
				glVertex2d(20 - Math.sin(i / 180.0) * 100, -Math.cos(i / 10) * 100 - (Math.sin(l) * 200));
			} else if (mode == 6) {
				glVertex2d(Math.sin(i + l) * 100 + (new VectorD(xOffset).x / 10), 100 - Math.cos(i + l) * 100 - yOffset);
				glVertex2d(Math.sin(i + l) * 100 - (new VectorD(xOffset).x / 10), -Math.cos(i / 10) * 100 - (Math.sin(l) * 100) + yOffset + 100);
			}
		}
		l+=speeder;
		glEnd();
		Render.end();
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		if (display.keyHandler.isKeyPressed(Keys.SPACE)) {
			color = Colors.random();
			if (mode < 6) {
				mode++;
			} else {
				mode = 0;
			}
		}
		
		if (display.keyHandler.isKeyDown(Keys.W)) {
			speeder += 0.00002;
		} else if (display.keyHandler.isKeyDown(Keys.S)) {
			speeder -= 0.00002;
		} else if (display.keyHandler.isKeyDown(Keys.LEFT)) {
			xOffset -= 5;
		} else if (display.keyHandler.isKeyDown(Keys.RIGHT)) {
			xOffset += 5;
		} else if (display.keyHandler.isKeyDown(Keys.UP)) {
			yOffset -= 5;
		} else if (display.keyHandler.isKeyDown(Keys.DOWN)) {
			yOffset += 5;
		} else if (display.keyHandler.isKeyDown(Keys.R)) {
			xOffset = 0;
			yOffset = 0;
		}
		
		if (display.keyHandler.isKeyPressed(Keys.I)) {
			showInfo = !showInfo;
		}
		
	}

}
