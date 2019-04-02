package xyz.iotacb.cloud.core.gui;

import java.awt.Color;

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.utilities.color.Colors;
import xyz.iotacb.cloud.utilities.math.Vector;
import xyz.iotacb.cloud.utilities.rendering.Render;
import xyz.iotacb.cloud.utilities.rendering.Text;

public class Button {
	
	/**
	 * ID of the button
	 */
	public int id;
	
	/**
	 * Location of the button and it's dimensions
	 */
	public Vector location, dimensions;

	String buttonText;
	
	boolean hovered, clicked;
	
	Display display;
	
	Text text = new Text(10);
	
	/**
	 * Create a button with a id at the given location and dimensions, and set the rendere text
	 * @param display
	 * @param id
	 * @param location
	 * @param dimensions
	 * @param text
	 */
	public Button(final Display display, final int id, final Vector location, final Vector dimensions, final String text) {
		this.id = id;
		this.location = location;
		this.dimensions = dimensions;
		this.buttonText = text;
		this.display = display;
		init();
	}
	
	/**
	 * Create a button with a id at the given location and dimensions, and set the rendere text
	 * @param display
	 * @param id
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 */
	public Button(final Display display, final int id, final double x, final double y, final double width, final double height, final String text) {
		this.id = id;
		this.location = new Vector(x, y);
		this.dimensions = new Vector(width, height);
		this.buttonText = text;
		this.display = display;
		init();
	}
	
	/**
	 * Call this to center the button onto the location
	 */
	public void init() {
		location.add(-dimensions.x / 2, -dimensions.y / 2);
	}
	
	/**
	 * This method will be called in the drawButtons method which you have to call in the screen class
	 */
	public void draw() {
		Render.start();
		Color color = Colors.addAlpha(Color.white, (isHovered() ? 230 : 200));
		Render.drawRectangle(location, dimensions, color);
		text.drawText(location.x + dimensions.x / 2 - text.getTextWidth() / 2, location.y + text.getTextHeight() / 2, buttonText, Color.black);
		Render.stop();
	}
	
	/**
	 * Check if the button is hovered
	 * @return
	 */
	public boolean isHovered() {
		return (display.mouseX > location.x && display.mouseX < location.x + dimensions.x && display.mouseY > location.y && display.mouseY < location.y + dimensions.y);
	}
	
	/**
	 * Set the button hovering state
	 * @param hovered
	 */
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	
	/**
	 * Check if the button has been clicked
	 * @return
	 */
	public boolean isClicked() {
		return clicked;
	}
	
	/**
	 * Set the button clicked state
	 * @param clicked
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	/**
	 * Get the current text of the button
	 * @return
	 */
	public String getButtonText() {
		return buttonText;
	}
	
	/**
	 * Change the button text
	 * @param buttonText
	 */
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
}
