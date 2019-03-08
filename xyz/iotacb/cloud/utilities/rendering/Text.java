package xyz.iotacb.cloud.utilities.rendering;

import static org.lwjgl.stb.STBEasyFont.stb_easy_font_height;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_width;

import java.awt.Color;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import xyz.iotacb.cloud.utilities.math.vector.VectorD;
import xyz.iotacb.cloud.utilities.math.vector.VectorF;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Text {

	float size = 1; // Text size
	
	String text = ""; // Current text
	
	public Text(final float textSize) {
		this.size = textSize;
	}

	/**
	 * Draw a String at a location
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	public void drawText(final int x, final int y, final String text, Color color) {
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
			GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
			GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, buffer);
			GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
			Render.color(Color.white);
		}
		Render.end();
	}

	public void drawText(final int x, final int y, final String text) {
		drawText(x, y, text, null);
	}

	public void drawText(final VectorI vector, final String text, final Color color) {
		drawText(vector.x, vector.y, text, color);
	}

	public void drawText(final VectorI vector, final String text) {
		drawText(vector.x, vector.y, text);
	}
	
	public void drawText(final double x, final double y, final String text, Color color) {
		drawText((int)x, (int)y, text, color);
	}

	public void drawText(final double x, final double y, final String text) {
		drawText(x, y, text, null);
	}

	public void drawText(final VectorD vector, final String text, final Color color) {
		drawText(vector.x, vector.y, text, color);
	}

	public void drawText(final VectorD vector, final String text) {
		drawText(vector.x, vector.y, text);
	}
	
	public void drawText(final float x, final float y, final String text, Color color) {
		drawText((int)x, (int)y, text, color);
	}

	public void drawText(final float x, final float y, final String text) {
		drawText(x, y, text, null);
	}

	public void drawText(final VectorF vector, final String text, final Color color) {
		drawText(vector.x, vector.y, text, color);
	}

	public void drawText(final VectorF vector, final String text) {
		drawText(vector.x, vector.y, text);
	}
	//
	
	/**
	 * Returns the width of a rendered String
	 * @return
	 */
	public int getTextWidth() {
		return (int) ((stb_easy_font_width(text) + 1) * (size * 0.1F)) * 2;
	}
	
	/**
	 * Returns the height of a rendered String
	 * @return
	 */
	public int getTextHeight() {
		return (int) ((stb_easy_font_height(text) + 1) * (size * 0.1F)) * 2;
	}

}
