package de.iotacb.cloud.utilities.render;

import static org.lwjgl.stb.STBEasyFont.stb_easy_font_height;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_width;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.nio.ByteBuffer;

import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Vector;
import org.lwjgl.BufferUtils;

public class Text {

	float size = 1;
	String text;

	public Text(final float textSize)
	{
		this.size = Maths.clamp(textSize, 0, Integer.MAX_VALUE);
		this.text = "";
	}

	/**
	 * Draw a text
	 * @param x The x location of the text
	 * @param y The y location of the text
	 * @param text The text that should be rendered
	 * @param color The color of the text
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
	 * Draw a text
	 * @param vector The location of the text as a vector
	 * @param text The text that should be rendered
	 * @param color The color of the text
	 */
	public void drawText(final Vector vector, final String text, final Color color) {
		drawText(vector.x, vector.y, text, color);
	}

	/**
	 * Draw a text with white color
	 * @param x The x location of the text
	 * @param y The y location of the text
	 * @param text The text that should be rendered
	 */
	public void drawText(final double x, final double y, final String text) {
		drawText(x, y, text, null);
	}

	/**
	 * Draw a text with white color
	 * @param vector The location of the text as a vector
	 * @param text The text that should be rendered
	 */
	public void drawText(final Vector vector, final String text) {
		drawText(vector.x, vector.y, text);
	}

	/**
	 * Draw a text with shadow
	 * @param x The x location of the text
	 * @param y The y location of the text
	 * @param text The text that should be rendered
	 * @param shadowSpace The spacing between the text and the shadow
	 * @param shadowColor The color of the shadow
	 * @param textColor The color of the text
	 */
	public void drawTextWithShadow(final double x, final double y, final String text, final double shadowSpace, final Color shadowColor, final Color textColor) {
		drawText(x + shadowSpace, y + shadowSpace, text, shadowColor);
		drawText(x, y, text, textColor);
	}

	/**
	 * Draw a text with shadow
	 * @param vector The location of the text as a vector
	 * @param text The text that should be rendered
	 * @param shadowSpace The spacing between the text and the shadow
	 * @param shadowColor The color of the shadow
	 * @param textColor The color of the text
	 */
	public void drawTextWithShadow(final Vector vector, final String text, final double shadowSpace, final Color shadowColor, final Color textColor) {
		drawTextWithShadow(vector.x, vector.y, text, shadowSpace, shadowColor, textColor);
	}

	/**
	 * Draw a white text with a color
	 * @param x The x location of the text
	 * @param y The y location of the text
	 * @param text The text that should be rendered
	 * @param shadowSpace The spacing between the text and the text
	 * @param shadowColor The color of the shadow
	 */
	public void drawTextWithShadow(final double x, final double y, final String text, final double shadowSpace, final Color shadowColor) {
		drawTextWithShadow(x, y, text, shadowSpace, shadowColor, null);
	}

	/**
	 * Draw a white text with shadow
	 * @param vector The location of the text as a vector
	 * @param text The text that should be rendered
	 * @param shadowSpace The spacing between the text and the shadow
	 * @param shadowColor The color of the shadow
	 */
	public void drawTextWithShadow(final Vector vector, final String text, final double shadowSpace, final Color shadowColor) {
		drawTextWithShadow(vector, text, shadowSpace, shadowColor, null);
	}

	/**
	 * Get the width of a text (Still buggy)
	 * @param text The text which width should be returned
	 * @return The width of the text
	 */
	public double getTextWidth(final String text) {
		return ((stb_easy_font_width(text) + 1) * (size * 0.1)) * 2;
	}

	/**
	 * Get the height of a text (Still buggy)
	 * @param text The text which height should be returned
	 * @return The height of the text
	 */
	public double getTextHeight(final String text) {
		return  ((stb_easy_font_height(text) + 1) * (size * 0.1)) * 2;
	}

	/**
	 * Get the width of a text (Still buggy)
	 * @return The width of the text
	 */
	public double getTextWidth() {
		return ((stb_easy_font_width(text) + 1) * (size * 0.1)) * 2;
	}

	/**
	 * Get the height of a text (Still buggy)
	 * @return The height of the text
	 */
	public double getTextHeight() {
		return ((stb_easy_font_height(text) + 1) * (size * 0.1)) * 2;
	}

}
