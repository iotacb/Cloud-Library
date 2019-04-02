package xyz.iotacb.cloud.utilities.rendering;

import static org.lwjgl.stb.STBEasyFont.stb_easy_font_height;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_width;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import xyz.iotacb.cloud.utilities.math.Vector;

public class Text {

	float size = 1;
	
	String text = "";
	
	/**
	 * Create the text and set the text size
	 * @param textSize
	 */
	public Text(final float textSize) {
		this.size = textSize;
	}

	/**
	 * Draw the text at a location and set the text color
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	public void drawText(final double x, final double y, final String text, Color color) {
		if (color == null)
			color = Color.white;
		
		this.text = text;

		ByteBuffer buffer = BufferUtils.createByteBuffer(text.length() * 270);

		float sizeBuffer = 1 + (size * 0.1F);

		int quads = stb_easy_font_print(0, 0, text, null, buffer);

		Render.start();
		{
			Render.translate(x, y);
			Render.color(color);
			Render.scale(sizeBuffer, sizeBuffer);
			glEnableClientState(GL_VERTEX_ARRAY);
			glVertexPointer(GL_POINT_BIT, GL_FLOAT, GL_POLYGON_STIPPLE_BIT, buffer);
			glDrawArrays(GL_QUADS, 0, quads * 4);
		}
		Render.stop();
	}

	/**
	 * Draw the text at a location with white color
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawText(final double x, final double y, final String text) {
		drawText(x, y, text, null);
	}

	/**
	 * Draw the text at a location and set the text color
	 * @param vector
	 * @param text
	 * @param color
	 */
	public void drawText(final Vector vector, final String text, final Color color) {
		drawText(vector.x, vector.y, text, color);
	}

	/**
	 * Draw the text at a location with white color
	 * @param vector
	 * @param text
	 */
	public void drawText(final Vector vector, final String text) {
		drawText(vector.x, vector.y, text);
	}

	/**
	 * Get the width of the text (Buggy, so don't be confused if it's not the right value)
	 * @return
	 */
	public int getTextWidth() {
		return (int) ((stb_easy_font_width(text) + 1) * (size * 0.1F)) * 2;
	}
	
	/**
	 * Get the height of the text (Buggy, so don't be confused if it's not the right value)
	 * @return
	 */
	public int getTextHeight() {
		return (int) ((stb_easy_font_height(text) + 1) * (size * 0.1F)) * 2;
	}

}
