package de.iotacb.cloud.core.gui;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.input.Keys;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.Text;
import de.iotacb.cloud.utilities.time.Timer;

public abstract class InputField extends Entity {

	String demoText, typedText;

	Vec size;

	int cursorDelay, boxExtender;

	boolean hovered, focused, showCursor;

	Timer cursorShowTimer, typeTimer, delTimer;

	Text demoDrawText, typedDrawText;

	public InputField(Window window, String demoText) {
		super(window);
		this.demoText = demoText;
		this.size = new Vec(200, 40);
		this.cursorDelay = 250;
	}

	public InputField(Window window, String demoText, int cursorDelay) {
		super(window);
		this.demoText = demoText;
		this.size = new Vec(200, 40);
		this.cursorDelay = cursorDelay;
	}

	public InputField(Window window, String demoText, Vec size) {
		super(window);
		this.demoText = demoText;
		this.size = size;
		this.cursorDelay = 250;
	}

	public InputField(Window window, String demoText, Vec size, Vec location) {
		super(window);
		this.demoText = demoText;
		this.size = size;
		this.cursorDelay = 250;
		this.location = location;
	}

	public InputField(Window window, String demoText, int cursorDelay, Vec size) {
		super(window);
		this.demoText = demoText;
		this.size = size;
		this.cursorDelay = cursorDelay;
	}

	public InputField(Window window, String demoText, int cursorDelay, Vec size, Vec location) {
		super(window);
		this.demoText = demoText;
		this.size = size;
		this.cursorDelay = cursorDelay;
		this.location = location;
	}

	@Override
	public void initialize() {
		this.demoDrawText = new Text(10);
		this.typedDrawText = new Text(10);

		this.cursorShowTimer = new Timer();
		this.typeTimer = new Timer();
		this.delTimer = new Timer();

		this.typedText = "";
	}

	@Override
	public void update() {
		this.hovered = window.getMouseLocation().x > location.x && window.getMouseLocation().x < location.x + size.x
				&& window.getMouseLocation().y > location.y && window.getMouseLocation().y < location.y + size.y;

		if (window.getInput().getMouseButtonDown(0)) {
			if (hovered) {
				focused = true;
				click();
			} else {
				focused = false;
			}
		}

		if (cursorShowTimer.havePassed(cursorDelay)) {
			showCursor = !showCursor;
		}

		if (focused) {
			if (window.getInput().getKey(Keys.KEY_BACKSPACE)) {
				if (delTimer.havePassed(100)) {
					if (typedText.length() > 0) {
						delLetter();
					}
				}
			}

			if (!window.getInput().noKeyPressed()) {
				int lastKey = window.getInput().lastKey;
				if (typeTimer.havePassed(100)) {
					if ((lastKey >= 65 && lastKey <= 90) || (lastKey >= 97 && lastKey <= 122) || lastKey == 32) {
						addLetter(Character.toString((char) (lastKey == 90 ? 89 : lastKey == 89 ? 90 : lastKey)));
					}
				}
				type(lastKey);
			}
		}
	}

	@Override
	public void draw() {
		Render.rect(location.x, location.y,
				size.x + (typedText.length() > 22 ? boxExtender : 0), size.y,
				Colors.setAlpha(Color.white, focused ? 150 : hovered ? 120 : 100));
		if (!focused && typedText.isEmpty()) {
			demoDrawText.drawText(location.x + 10, location.y + 15, demoText,
					Colors.setAlpha(Color.white, 200));
		} else {
			typedDrawText.drawText(location.x + 10, location.y + 15,
					typedText + (showCursor ? "_" : ""));
		}
	}
	
	public void clear() {
		typedText = "";
	}

	public abstract void click();

	public abstract void type(int key);
	
	public void addLetter(String letter) {
		typedText += letter;
		if (typedText.length() > 22) {
			boxExtender += 10;
		}
	}
	
	public void delLetter() {
		if (typedText.length() > 0) {
			typedText = typedText.substring(0, typedText.length() - 1);
			if (typedText.length() > 22) {
				boxExtender -= 10;
			}
		}
	}

	public int getCursorDelay() {
		return cursorDelay;
	}

	public boolean isHovered() {
		return hovered;
	}

	public boolean isFocused() {
		return focused;
	}

	public String getTypedText() {
		return typedText;
	}

}
