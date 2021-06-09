package de.iotacb.cloud.core.gui;

import java.awt.Color;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.render.Render;
import de.iotacb.cloud.utilities.render.font.FontManager;

public class Button extends GuiComponent {

	private boolean isLeftButtonDown, isHovered;
	
	private Color color, hoveredColor;
	
	private String title;
	
	public Button(Window window, float x, float y, float width, float height, String title) {
		super(window, x, y, width, height);
		this.title = title;
		this.color = new Color(255, 255, 255, 200);
		this.hoveredColor = new Color(255, 255, 255, 10);
	}
	
	@Override
	public void update() {
		super.update();
		isHovered = isHovered();
		if (isHovered) {
			if (window.getInput().getMouseButtonDown(0) && !isLeftButtonDown) {
				isLeftButtonDown = true;
				click();
			}
		}
		
		if (!window.getInput().getMouseButton(0) && isLeftButtonDown) {
			isLeftButtonDown = false;
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		Render.rect(getLocation(), getSize(), isHovered ? hoveredColor : color);
		FontManager.instance.getNormalFont().drawCenteredStringWithShadow(title, (int)getX() + getWidth() / 2, (int)getY() + getHeight() / 2, Color.white);
	}
	
	public void click() {}

}
