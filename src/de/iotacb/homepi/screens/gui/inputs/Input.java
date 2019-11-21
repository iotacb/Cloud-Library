package de.iotacb.homepi.screens.gui.inputs;

import java.awt.Color;
import java.security.acl.LastOwnerException;

import org.lwjgl.opengl.GL11;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.cloud.utilities.time.Timer;

public class Input extends Entity {

	public boolean hovered, lMouseDown, focused, keyPressed;
	boolean showCursor;

	int width = 300, height = 40, cursorOffset = 10, deleteSpeeder = 0;

	public String typedText = "";
	String egText;

	Text text;

	Timer cursorTimer, deleteTimer;

	public Input(Window window, int x, int y, String egText) {
		super(window);
		location.set(x, y);
		this.egText = egText;
	}

	@Override
	public void initialize() {
		this.text = new Text(10);
		this.cursorTimer = new Timer();
		this.deleteTimer = new Timer();
	}

	@Override
	public void update() {
		mouseClick();
		this.hovered = window.mouseX > location.x
				&& window.mouseX < location.x + width + (cursorOffset > 350 ? cursorOffset - 360 : 0)
				&& window.mouseY > location.y && window.mouseY < location.y + height;

		if (lMouseDown) {
			focused = hovered;
		}
		if (cursorTimer.havePassed(600)) {
			showCursor = !showCursor;
		}

		if (!focused) {
			showCursor = false;
		} else {
			updateKeys();
		}
	}

	@Override
	public void draw() {
		Render.rect((int) location.x, (int) location.y, width + (cursorOffset > 350 ? cursorOffset - 360 : 0), height,
				false, Colors.setAlpha(Color.white, hovered ? 200 : focused ? 230 : 100));
		Render.start();
		Render.stop();
		if (!focused) {
			if (typedText == "" || typedText.isEmpty()) {
				text.drawText((int) location.x + 10, (int) location.y + 15, egText, Colors.setAlpha(Color.white, 200));
			} else {
				text.drawText((int) location.x + 10, (int) location.y + 15, typedText, Color.white);
			}
		} else {
			text.drawText((int) location.x + 10, (int) location.y + 15, typedText, Color.white);
		}
		if (showCursor) {
			text.drawText((int) location.x + 10, (int) location.y + 15, typedText + "_", Color.white);
		}
	}

	public void reset() {
		typedText = "";
		cursorOffset = 10;
	}

	void mouseClick() {
		if (window.inputHandler.isMouseButtonDown(0)) {
			if (!lMouseDown) {
				lMouseDown = true;
			}
		} else {
			lMouseDown = false;
		}
	}

	void updateKeys() {
		if (!window.inputHandler.noKeyPressed()) {
			if (!keyPressed) {
				if ((window.inputHandler.lastKey >= 65 && window.inputHandler.lastKey <= 90)
						|| (window.inputHandler.lastKey >= 97 && window.inputHandler.lastKey <= 122)
						|| window.inputHandler.lastKey == 32) {
					String c = Character.toString((char) (window.inputHandler.lastKey == 90 ? 89
							: window.inputHandler.lastKey == 89 ? 90 : window.inputHandler.lastKey));
					typedText += c;
					cursorOffset += 10;
				}
				keyPressed = true;
			}
		} else {
			keyPressed = false;
		}

		if (window.inputHandler.isKeyDown(Keys.BACKSPACE)) {
			if (deleteTimer.havePassed(Maths.clamp(150 - deleteSpeeder, 10, 150))) {
				deleteSpeeder += 5;
				if (typedText.length() > 0) {
					typedText = typedText.substring(0, typedText.length() - 1);
				}
				cursorOffset -= 10;
			}
		} else {
			deleteSpeeder = 0;
		}
	}

}
