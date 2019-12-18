package de.iotacb.cloud.core.gui;

import java.awt.Color;

import de.iotacb.cloud.core.entity.Entity;
import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.color.Colors;
import de.iotacb.cloud.utilities.math.Vec;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.Text;

public abstract class Button extends Entity {

	public Vec size;

	String title;

	boolean hovered;

	Text btnTitle;

	public Button(Window window, String title) {
		super(window);
		this.title = title;
		this.size = new Vec(200, 40);
	}

	public Button(Window window, String title, Vec size) {
		super(window);
		this.size = size;
		this.title = title;
	}

	public Button(Window window, String title, Vec size, Vec location) {
		super(window);
		this.size = size;
		this.title = title;
		this.location = location;
	}

	@Override
	public void initialize() {
		this.btnTitle = new Text(10);

		this.size = new Vec(200, 40);
	}

	@Override
	public void update() {
		this.hovered = window.getMouseLocation().x > location.x - size.x / 2
				&& window.getMouseLocation().x < location.x + size.x / 2 && window.getMouseLocation().y > location.y - size.y / 2
				&& window.getMouseLocation().y < location.y + size.y / 2;
		if (window.getInput().getMouseButtonDown(0) && hovered) {
				click();
		}
	}

	@Override
	public void draw() {
		Render.rectCentered(location, size,
				Colors.setAlpha(Color.white, hovered ? 120 : window.getInput().getMouseButton(0) && hovered ? 200 : 100));
		btnTitle.drawText(location.x - btnTitle.getHWidth(title), location.y - 5, title);
	}

	public abstract void click();

	public String getTitle() {
		return title;
	}

	public boolean isHovered() {
		return hovered;
	}

}
