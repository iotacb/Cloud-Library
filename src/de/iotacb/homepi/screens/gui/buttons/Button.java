package de.iotacb.homepi.screens.gui.buttons;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.Text;

public class Button extends Entity {
	
	public boolean hovered, lMouseDown;
	
	public String title;
	
	Text text;
	
	int width = 200, height = 40;

	public Button(Window window, int x, int y, String title) {
		super(window);
		location.set(x, y);
		this.title = title;
	}

	@Override
	public void initialize() {
		this.text = new Text(10);
	}

	@Override
	public void update() {
		mouseClick();
		this.hovered = window.mouseX > location.x && window.mouseX < location.x + width && window.mouseY > location.y && window.mouseY < location.y + height;
		
		if (hovered && lMouseDown) {
			onClick();
		}
	}

	@Override
	public void draw() {
		Render.rect((int)location.x, (int)location.y, width, height, Colors.setAlpha(Color.white, hovered ? 200 : 100));
		text.drawText((int)location.x + width / 2 - text.getHWidth(title), (int)location.y + 15, title, Color.white);
	}
	
	public void onClick() {}
	
	public void setLocation(int x, int y) {
		location.set(x, y);
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

}
