package xyz.iotacb.cloud.utilities.rendering;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LINE_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Color;

import xyz.iotacb.cloud.utilities.math.vector.VectorD;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Render {
	
	/**
	 * Push matrix
	 */
	public static void push() {
		glPushMatrix();
	}
	
	/**
	 * Pop matrix
	 */
	public static void pop() {
		glPopMatrix();
	}
	
	/**
	 * Translate 
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void translate(final double x, final int y, final int z) {
		glTranslated(x, y, z);
	}
	
	/**
	 * Translate 
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void translate(final double x, final double y) {
		glTranslated(x, y, 0);
	}
	
	/**
	 * Translate 
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void translate(final VectorD vector) {
		glTranslated(vector.x, vector.y, vector.z);
	}
	
	/**
	 * Scale
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void scale(final double x, final double y, final double z) {
		glScaled(x, y, z);
	}
	
	/**
	 * Scale
	 * @param x
	 * @param y
	 */
	public static void scale(final double x, final double y) {
		glScaled(x, y, 0);
	}
	
	/**
	 * Scale
	 * @param vector
	 */
	public static void scale(final VectorD vector) {
		glScaled(vector.x, vector.y, vector.z);
	}
	
	/**
	 * Rotate
	 * @param x
	 * @param y
	 * @param z
	 * @param angle
	 */
	public static void rotate(final double x, final double y, final double z, final double angle) {
		glRotated(angle, x, y, z);
	}
	
	/**
	 * Rotate
	 * @param vector
	 * @param angle
	 */
	public static void rotate(final VectorI vector, final double angle) {
		glRotated(angle, vector.x, vector.y, vector.z);
	}
	
	/**
	 * Start rendering
	 */
	public static void start() {
		push();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_LINE_SMOOTH);
		glDisable(GL_LIGHTING);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_CULL_FACE);
	}
	
	/**
	 * End rendering
	 */
	public static void end() {
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_LIGHTING);
		glDisable(GL_LINE_SMOOTH);
		glDisable(GL_BLEND);
		pop();
	}
	
	/**
	 * Sets the current color
	 * @param color
	 */
	public static void color(final Color color) {
		float red = color.getRed() / 255.0F;
		float green = color.getGreen() / 255.0F;
		float blue = color.getBlue() / 255.0F;
		float alpha = color.getAlpha() / 255.0F;
		glColor4f(red, green, blue, alpha);
	}
	
	/**
	 * Draws a rectangle
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void drawRectangle(final int x, final int y, final int width, final int height) {
		start();
		glBegin(GL_QUADS);
		{
			glVertex2d(x, y);
			glVertex2d(x + width, y);
			glVertex2d(x + width, y + height);
			glVertex2d(x, y + height);
			glVertex2d(x, y);
		}
		glEnd();
		end();
	}
	
	/**
	 * Draws a rectangle
	 * @param axes
	 * @param dimensions
	 */
	public static void drawRectangle(final VectorI axes, final VectorI dimensions) {
		drawRectangle(axes.x, axes.y, dimensions.x, dimensions.y);
	}
	
	/**
	 * Draws a circle
	 * @param x
	 * @param y
	 * @param radius
	 */
	public static void drawCircle(final int x, final int y, final double radius) {
		start();
		glBegin(GL_TRIANGLE_FAN);
		{
			for (int i = 0; i < 360; i++) {
				double x2 = (Math.sin((i * Math.PI) / 180)) * radius;
				double y2 = (Math.cos((i * Math.PI) / 180)) * radius;
				glVertex2d(x + x2, y + y2);
			}
		}
		glEnd();
		end();
	}
	
	/**
	 * Draws a circle
	 * @param axes
	 * @param radius
	 */
	public static void drawCircle(final VectorI axes, final double radius) {
		drawCircle(axes.x, axes.y, radius);
	}
}
