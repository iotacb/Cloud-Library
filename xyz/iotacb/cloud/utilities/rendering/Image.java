package xyz.iotacb.cloud.utilities.rendering;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Image {

	String path;

	int id, width, height;
	
	Image[] images;

	public Image(final String path) {
		this.path = path;
		this.images = null;
	}
	
	public Image(final Image[] images) {
		this.path = "";
		this.images = images;
	}

	/**
	 * Draws a image
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void draw(int x, int y, final int w, final int h) {
		Render.color(Color.white);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL11.GL_LIGHTING);
		BufferedImage bi;
		try {
			bi = ImageIO.read(new File(path));

			width = bi.getWidth();
			height = bi.getHeight();

			int[] pixels_raw = bi.getRGB(0, 0, width, height, null, 0, width);

			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

			for (int i = 0; i < pixels_raw.length; i++) {
				int pixel = pixels_raw[i];
				pixels.put((byte) ((pixel >> 16) & 255));
				pixels.put((byte) ((pixel >> 8) & 255));
				pixels.put((byte) (pixel & 255));
				pixels.put((byte) ((pixel >> 24) & 255));
			}

			pixels.flip();

			id = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, id);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int xDiff = Math.abs(width - w);
		int yDiff = Math.abs(height - h);

		x += width - xDiff;
		y += height - yDiff;

		glBegin(GL_QUADS);
		{
			glTexCoord2d(0, 1);
			glVertex2d(x + -w, y + h);
			glTexCoord2d(1, 1);
			glVertex2d(x + w, y + h);
			glTexCoord2d(1, 0);
			glVertex2d(x + w, y + -h);
			glTexCoord2d(0, 0);
			glVertex2d(x + -w, y + -h);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_LIGHTING);
		glDisable(GL_BLEND);
	}

	public void draw(final VectorI axes, final VectorI dimensions) {
		draw(axes.x, axes.y, dimensions.x, dimensions.y);
	}
	
	public void draw(int x, int y, final int w, final int h, final int index) {
		if (this.images.length > 0) {
			this.images[index].draw(x, y, w, h);
		}
	}

	public void draw(final VectorI axes, final VectorI dimensions, final int index) {
		if (this.images.length > 0) {
			this.images[index].draw(axes, dimensions);
		}
	}

}
