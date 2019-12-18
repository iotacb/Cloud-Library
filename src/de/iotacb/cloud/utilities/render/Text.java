package de.iotacb.cloud.utilities.render;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_POINT_BIT;
import static org.lwjgl.opengl.GL11.GL_POLYGON_STIPPLE_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;

import java.awt.Color;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBEasyFont;

import de.iotacb.cloud.utilities.math.Vec;

public class Text {
	
	int textSize = 1;
	
	public Text(int textSize) {
		this.textSize = textSize;
	}
	
	public void drawText(double x, double y, String text, Color color) {
		if (color == null) {
			color = Color.white;
		}
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(text.length() * 300);
		
		
		int quads = stb_easy_font_print(0, 0, text, null, buffer);
		
		double sizeBuffer = textSize * .14;
		
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
	
	public void drawText(double x, double y, String text) {
		drawText(x, y, text, null);
	}
	
	public void drawText(Vec location, String text, Color color) {
		drawText(location.x, location.y, text, color);
	}
	
	public void drawText(Vec location, String text) {
		drawText(location, text, null);
	}
	
	public int getWidth(String text) {
		return (int)(STBEasyFont.stb_easy_font_width(text) * (textSize * .1325));
	}
	
	public int getHWidth(String text) {
		return getWidth(text) / 2;
	}
	
	public int getHeight() {
		return textSize;
	}

}
