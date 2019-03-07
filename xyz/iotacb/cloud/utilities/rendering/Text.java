package xyz.iotacb.cloud.utilities.rendering;

import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;

import java.awt.Color;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Text {
	
	float size;
	
	public Text(final float textSize) {
		this.size = textSize;
	}
	
	/**
	 * Draws a text
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	public void drawText(final int x, final int y, final String text, Color color) {
		if (color == null) color = Color.white;
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(text.length() * 270);
		
		float sizeBuffer = 1 + (size * 0.1F);
		
		int quads = stb_easy_font_print(x, y, text, null, buffer);
		
		Render.push();
		{
			Render.start();
			{
				Render.color(color);
				Render.scale(sizeBuffer, sizeBuffer);
				GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, buffer);
				GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
				Render.scale(1, 1);
				Render.color(Color.white);
			}
			Render.end();
		}
		Render.pop();
	}
	
	/**
	 * Draws a white text
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawText(final int x, final int y, final String text) {
		drawText(x, y, text, null);
	}
	
	/**
	 * Draws a text
	 * @param vector
	 * @param text
	 * @param color
	 */
	public void drawText(final VectorI vector, final String text, final Color color) {
		drawText(vector.x, vector.y, text, color);
	}
	
	/**
	 * Draws a white text
	 * @param vector
	 * @param text
	 */
	public void drawText(final VectorI vector, final String text) {
		drawText(vector.x, vector.y, text);
	}

}
