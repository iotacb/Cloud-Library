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

import xyz.iotacb.cloud.core.display.Display;
import xyz.iotacb.cloud.utilities.math.Vector3;

public class Render {
	
	/**
	 * Create a OpenGL push matrix
	 */
	public static void push() {
		glPushMatrix();
	}
	
	/**
	 * Destroy a OpenGL push matrix
	 */
	public static void pop() {
		glPopMatrix();
	}
	
	/**
	 * Add a OpenGL vertex
	 * @param x
	 * @param y
	 */
	public static void vertex(final double x, final double y) {
		glVertex2d(x, y);
	}
	
	/**
	 * Add a OpenGL vertex
	 * @param vector
	 */
	public static void vertex(final Vector3 vector) {
		vertex(vector.x, vector.y);
	}
	
	/**
	 * Start and set the rendering mode of OpenGL
	 * @param mode
	 */
	public static void begin(final int mode) {
		glBegin(mode);
	}
	
	/**
	 * End the rendering of OpenGL
	 */
	public static void end() {
		glEnd();
	}
	
	/**
	 * Translate the null point to the given location
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void translate(final double x, final int y, final int z) {
		glTranslated(x, y, z);
	}
	
	/**
	 * Translate the null point to the given location
	 * @param x
	 * @param y
	 */
	public static void translate(final double x, final double y) {
		glTranslated(x, y, 0);
	}
	
	/**
	 * Translate the null point to the given location
	 * @param vector
	 */
	public static void translate(final Vector3 vector) {
		glTranslated(vector.x, vector.y, vector.z);
	}
	
	/**
	 * Scale the OpenGL rendering
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void scale(final double x, final double y, final double z) {
		glScaled(x, y, z);
	}
	
	/**
	 * Scale the OpenGL rendering
	 * @param x
	 * @param y
	 */
	public static void scale(final double x, final double y) {
		glScaled(x, y, 0);
	}
	
	/**
	 * Scale the OpenGL rendering
	 * @param vector
	 */
	public static void scale(final Vector3 vector) {
		glScaled(vector.x, vector.y, vector.z);
	}
	
	/**
	 * Rotate the OpenGL rendering to the given angle
	 * @param x
	 * @param y
	 * @param z
	 * @param angle
	 */
	public static void rotate(final double x, final double y, final double z, final double angle) {
		glRotated(angle, x, y, z);
	}
	
	/**
	 * Rotate the OpenGL rendering to the given angle
	 * @param vector
	 * @param angle
	 */
	public static void rotate(final Vector3 vector, final double angle) {
		glRotated(angle, vector.x, vector.y, vector.z);
	}

	/**
	 * Start basic OpenGL rendering
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
	 * Stop basic OpenGL rendering
	 */
	public static void stop() {
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_LIGHTING);
		glDisable(GL_LINE_SMOOTH);
		glDisable(GL_BLEND);
		pop();
	}
	
	/**
	 * Set the rendering color of OpenGL
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
	 * Draw a rectangle at a location with dimensions
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void drawRectangle(final double x, final double y, final double width, final double height) {
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
		stop();
	}
	
	/**
	 * Draw a rectangle at a location with dimensions
	 * @param axes
	 * @param dimensions
	 */
	public static void drawRectangle(final Vector3 axes, final Vector3 dimensions) {
		drawRectangle(axes.x, axes.y, dimensions.x, dimensions.y);
	}

	/**
	 * Draw a circle at a location with a radius
	 * @param x
	 * @param y
	 * @param radius
	 */
	public static void drawCircle(final double x, final double y, final double radius) {
		start();
		glBegin(GL_TRIANGLE_FAN);
		{
			for (int i = 0; i < 360; i++) {
				double x2 = (Math.sin((i * Math.PI) / 180)) * radius;
				double y2 = (Math.cos((i * Math.PI) / 180)) * radius;
				glVertex2d(x + x2 + radius, y + y2 + radius);
			}
		}
		glEnd();
		stop();
	}
	
	/**
	 * Draw a circle at a location with a radius
	 * @param axes
	 * @param radius
	 */
	public static void drawCircle(final Vector3 axes, final double radius) {
		drawCircle(axes.x, axes.y, radius);
	}

	/**
	 * Draw a triangle at a location with the side length and a rotate angle
	 * @param x
	 * @param y
	 * @param sideLength
	 * @param angle
	 */
	public static void drawTriangle(final double x, final double y, final double sideLength, final double angle) {
		start();
		translate(x, y);
		rotate(0, 0, 1, angle-210);
		glBegin(GL_TRIANGLE_FAN);
		{
		       for (int i = 0; i <= 3; i++) {
		           double angle2 = i * 2 * Math.PI / 3;
		           glVertex2d(sideLength * Math.cos(angle2), sideLength * Math.sin(angle2));
		       }
		}
		glEnd();
		stop();
	}
	
	/**
	 * Draw a triangle at a location with the side length and a rotate angle
	 * @param axes
	 * @param sideLength
	 * @param angle
	 */
	public static void drawTriangle(final Vector3 axes, final double sideLength, final double angle) {
		drawTriangle(axes.x, axes.y, sideLength, angle);
	}

	/**
	 * Draw a polygon at a location with a custom amount of sides, sidelength and a rotate angle
	 * @param x
	 * @param y
	 * @param sides
	 * @param sideLength
	 * @param angle
	 */
	public static void drawPolygon(final double x, final double y, final double sides, double sideLength, final double angle) {
		start();
		translate(x, y);
		rotate(0, 0, 1, angle-210);
		glBegin(GL_TRIANGLE_FAN);
		{
		       for (int i = 0; i <= sides; i++) {
		           double angle2 = i * 2 * Math.PI / sides;
		           glVertex2d(sideLength * Math.cos(angle2), sideLength * Math.sin(angle2));
		       }
		}
		glEnd();
		stop();
	}
	
	/**
	 * Draw a polygon at a location with a custom amount of sides, sidelength and a rotate angle
	 * @param axes
	 * @param sides
	 * @param sideLength
	 * @param angle
	 */
	public static void drawPolygon(final Vector3 axes, final double sides, final double sideLength, final double angle) {
		drawPolygon(axes.x, axes.y, sides, sideLength, angle);
	}
	
	/**
	 * Check if something should and can be rendered (If it's on screen or not)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param display
	 * @return
	 */
	public static boolean canBeRendered(final double x, final double y, final double width, final double height, final Display display) {
		boolean can = true;
		if (x > display.displayDimensions.x || y > display.displayDimensions.y) {
			can = false;
		}
		if (x + width < 0 || y + height < 0) {
			can = false;
		}
		return can;
	}
	
}
