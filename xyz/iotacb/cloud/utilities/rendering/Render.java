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
import xyz.iotacb.cloud.utilities.math.vector.VectorF;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class Render {
	
	/**
	 * Push a rendering matrix
	 */
	public static void push() {
		glPushMatrix();
	}
	
	/**
	 * Pop a rendering matrix
	 */
	public static void pop() {
		glPopMatrix();
	}
	
	/**
	 * Translate to a location
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void translate(final double x, final int y, final int z) {
		glTranslated(x, y, z);
	}
	
	public static void translate(final double x, final double y) {
		glTranslated(x, y, 0);
	}
	
	public static void translate(final VectorD vector) {
		glTranslated(vector.x, vector.y, vector.z);
	}
	
	public static void translate(final float x, final float y, final float z) {
		glTranslated(x, y, z);
	}
	
	public static void translate(final float x, final float y) {
		glTranslated(x, y, 0);
	}
	
	public static void translate(final VectorF vector) {
		glTranslated(vector.x, vector.y, vector.z);
	}
	
	public static void translate(final int x, final int y, final int z) {
		glTranslated(x, y, z);
	}
	
	public static void translate(final int x, final int y) {
		glTranslated(x, y, 0);
	}
	
	public static void translate(final VectorI vector) {
		glTranslated(vector.x, vector.y, vector.z);
	}
	//
	
	/**
	 * Scale the rendering
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void scale(final double x, final double y, final double z) {
		glScaled(x, y, z);
	}
	
	public static void scale(final double x, final double y) {
		glScaled(x, y, 0);
	}
	
	public static void scale(final VectorD vector) {
		glScaled(vector.x, vector.y, vector.z);
	}
	
	public static void scale(final float x, final float y, final float z) {
		glScaled(x, y, z);
	}
	
	public static void scale(final float x, final float y) {
		glScaled(x, y, 0);
	}
	
	public static void scale(final VectorF vector) {
		glScaled(vector.x, vector.y, vector.z);
	}
	
	public static void scale(final int x, final int y, final int z) {
		glScaled(x, y, z);
	}
	
	public static void scale(final int x, final int y) {
		glScaled(x, y, 0);
	}
	
	public static void scale(final VectorI vector) {
		glScaled(vector.x, vector.y, vector.z);
	}
	//
	
	/**
	 * Rotate the rendering
	 * @param x
	 * @param y
	 * @param z
	 * @param angle
	 */
	public static void rotate(final double x, final double y, final double z, final double angle) {
		glRotated(angle, x, y, z);
	}
	
	public static void rotate(final VectorD vector, final double angle) {
		glRotated(angle, vector.x, vector.y, vector.z);
	}
	
	public static void rotate(final float x, final float y, final float z, final float angle) {
		glRotated(angle, x, y, z);
	}
	
	public static void rotate(final VectorF vector, final float angle) {
		glRotated(angle, vector.x, vector.y, vector.z);
	}
	
	public static void rotate(final int x, final int y, final int z, final int angle) {
		glRotated(angle, x, y, z);
	}
	
	public static void rotate(final VectorI vector, final int angle) {
		glRotated(angle, vector.x, vector.y, vector.z);
	}
	//
	
	/**
	 * Setup for default rendering
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
	 * Reset the rendering settings
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
	 * Set the rendering color
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
	 * Draw a rectangle at a location
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
	
	public static void drawRectangle(final VectorI axes, final VectorI dimensions) {
		drawRectangle(axes.x, axes.y, dimensions.x, dimensions.y);
	}
	
	public static void drawRectangle(final double x, final double y, final double width, final double height) {
		drawRectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public static void drawRectangle(final VectorD axes, final VectorD dimensions) {
		drawRectangle(axes.x, axes.y, dimensions.x, dimensions.y);
	}
	
	public static void drawRectangle(final float x, final float y, final float width, final float height) {
		drawRectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public static void drawRectangle(final VectorF axes, final VectorF dimensions) {
		drawRectangle(axes.x, axes.y, dimensions.x, dimensions.y);
	}
	//
	
	/**
	 * Draw a circle at a location
	 * @param x
	 * @param y
	 * @param radius
	 */
	public static void drawCircle(final int x, final int y, final int radius) {
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
	
	public static void drawCircle(final VectorI axes, final int radius) {
		drawCircle(axes.x, axes.y, radius);
	}
	
	public static void drawCircle(final double x, final double y, final double radius) {
		drawCircle((int)x, (int)y, radius);
	}
	
	public static void drawCircle(final VectorD axes, final double radius) {
		drawCircle(axes.x, axes.y, radius);
	}
	
	public static void drawCircle(final float x, final float y, final float radius) {
		drawCircle((int)x, (int)y, radius);
	}
	
	public static void drawCircle(final VectorF axes, final float radius) {
		drawCircle(axes.x, axes.y, radius);
	}
	//
}
