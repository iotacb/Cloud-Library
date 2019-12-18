package de.iotacb.cloud.utilities.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_POINT_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_POLYGON_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Color;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Vec;

public class Render {
	
	public static Window window;
	
	public static void push() {
		glPushMatrix();
	}
	
	public static void pop() {
		glPopMatrix();
	}
	
	public static void enable(int glTarget) {
		glEnable(glTarget);
	}
	
	public static void disable(int glTarget) {
		glDisable(glTarget);
	}
	
	public static void start() {
		push();
		enable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		disable(GL_LIGHTING);
		disable(GL_TEXTURE_2D);
		disable(GL_CULL_FACE);
	}
	
    public static void stop() {
        enable(GL_CULL_FACE);
        enable(GL_TEXTURE_2D);
        enable(GL_LIGHTING);
        disable(GL_BLEND);
        pop();
    }
    
    public static void startSmooth() {
    	enable(GL_POLYGON_SMOOTH);
    	enable(GL_LINE_SMOOTH);
    	enable(GL_POINT_SMOOTH);
    	enable(GL_SMOOTH);
    }
    
    public static void endSmooth() {
    	disable(GL_SMOOTH);
    	disable(GL_POINT_SMOOTH);
    	disable(GL_LINE_SMOOTH);
    	disable(GL_POLYGON_SMOOTH);
    }
    
    public static void begin(int glMode) {
    	glBegin(glMode);
    }
    
    public static void end() {
        glEnd();
    }
    
    public static void vertex(double x, double y) {
        glVertex2d(x, y);
    }
    
    public static void vertex(Vec location) {
        vertex(location.x, location.y);
    }
    
    public static void translate(double x, double y) {
        glTranslated(x, y, 0);
    }
    
    public static void translate(Vec location) {
        translate(location.x, location.y);
    }
    
    public static void scale(double x, double y) {
        glScaled(x, y, 0);
    }
    
    public static void scale(Vec scaling) {
        scale(scaling.x, scaling.y);
    }
    
    public static void rotate(double x, double y, double z, double angle) {
        glRotated(angle, x, y, z);
    }
    
    public static void color(double red, double green, double blue, double alpha) {
    	glColor4d(red, green, blue, alpha);
    }
    
    public static void color(double red, double green, double blue) {
        color(red, green, blue, 1);
    }
    
    public static void color(Color color) {
        color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }
    
    public static void lineWidth(double width) {
    	glLineWidth((float)width);
    }
    
    public static void rect(double x, double y, double width, double height, boolean filled, Color color) {
    	start();
    	if (color != null)
    		color(color);
    	begin(filled ? GL_TRIANGLE_FAN : GL_LINES);
    	{
    		vertex(x, y);
    		vertex(x + width, y);
    		vertex(x + width, y + height);
    		vertex(x, y + height);
    		if (!filled)  {
    			vertex(x, y);
    			vertex(x, y + height);
    			vertex(x + width, y);
    			vertex(x + width, y + height);
    		}
    	}
    	end();
    	stop();
    }
    
    public static void rect(Vec location, Vec size, boolean filled, Color color) {
    	rect(location.x, location.y, size.x, size.y, filled, color);
    }
    
    public static void rect(double x, double y, Vec size, boolean filled, Color color) {
    	rect(x, y, size.x, size.y, filled, color);
    }
    
    public static void rect(Vec location, double width, double height, boolean filled, Color color) {
    	rect(location.x, location.y, width, height, filled, color);
    }
    
    public static void rect(double x, double y, double width, double height, boolean filled) {
    	rect(x, y, width, height, filled, null);
    }
    
    public static void rect(Vec location, Vec size, boolean filled) {
    	rect(location, size, filled, null);
    }
    
    public static void rect(double x, double y, Vec size, boolean filled) {
    	rect(x, y, size.x, size.y, filled);
    }
    
    public static void rect(Vec location, double width, double height, boolean filled) {
    	rect(location.x, location.y, width, height, filled);
    }
    
    public static void rect(double x, double y, double width, double height, Color color) {
    	rect(x, y, width, height, true, color);
    }
    
    public static void rect(Vec location, Vec size, Color color) {
    	rect(location, size, true, color);
    }
    
    public static void rect(double x, double y, Vec size, Color color) {
    	rect(x, y, size.x, size.y, color);
    }
    
    public static void rect(Vec location, double width, double height, Color color) {
    	rect(location.x, location.y, width, height, color);
    }
    
    public static void rect(double x, double y, double width, double height) {
    	rect(x, y, width, height, true, null);
    }
    
    public static void rect(Vec location, Vec size) {
    	rect(location, size, true, null);
    }
    
    public static void rect(double x, double y, Vec size) {
    	rect(x, y, size.x, size.y);
    }
    
    public static void rect(Vec location, double width, double height) {
    	rect(location.x, location.y, width, height);
    }
    
    public static void rectCentered(double x, double y, double width, double height, boolean filled, Color color) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, filled, color);
    }
    
    public static void rectCentered(Vec location, Vec size, boolean filled, Color color) {
    	rect((location.x - size.x / 2), (location.y - size.y / 2), size.x, size.y, filled, color);
    }
    
    public static void rectCentered(double x, double y, Vec size, boolean filled, Color color) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, size.x, size.y, filled, color);
    }
    
    public static void rectCentered(Vec location, double width, double height, boolean filled, Color color) {
    	rect((location.x - width / 2), (location.y - height / 2), width, height, filled, color);
    }
    
    public static void rectCentered(double x, double y, double width, double height, boolean filled) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, filled, null);
    }
    
    public static void rectCentered(Vec location, Vec size, boolean filled) {
    	rect((location.x - size.x / 2), (location.y - size.y / 2), size, filled, null);
    }
    
    public static void rectCentered(double x, double y, Vec size, boolean filled) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, size.x, size.y, filled);
    }
    
    public static void rectCentered(Vec location, double width, double height, boolean filled) {
    	rect(location.x - width / 2, location.y - height / 2, width, height, filled);
    }
    
    public static void rectCentered(double x, double y, double width, double height, Color color) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, true, color);
    }
    
    public static void rectCentered(Vec location, Vec size, Color color) {
    	rect((location.x - size.x / 2), (location.y - size.y / 2), size, true, color);
    }
    
    public static void rectCentered(double x, double y, Vec size, Color color) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, size.x, size.y, color);
    }
    
    public static void rectCentered(Vec location, double width, double height, Color color) {
    	rect(location.x - width / 2, location.y - height / 2, width, height, color);
    }
    
    public static void rectCentered(double x, double y, double width, double height) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, true, null);
    }
    
    public static void rectCentered(Vec location, Vec size) {
    	rect((location.x - size.x / 2), (location.y - size.y / 2), size, true, null);
    }
    
    public static void rectCentered(double x, double y, Vec size) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, size.x, size.y);
    }
    
    public static void rectCentered(Vec location, double width, double height) {
    	rect(location.x - width / 2, location.y - height / 2, width, height);
    }
    
    public static void polygon(double x, double y, double sideLength, double amountOfSides, boolean filled, Color color) {
    	sideLength /= 2;
    	start();
    	if (color != null)
    		color(color);
    	begin(filled ? GL_TRIANGLE_FAN : GL_LINES);
    	{
    		for (double i = 0; i <= amountOfSides; i++) {
    			double angle = i * Maths.TAU / amountOfSides;
    			vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
    		}
    	}
    	end();
    	stop();
    }
    
    public static void polygon(Vec location, double sideLength, int amountOfSides, boolean filled, Color color) {
    	polygon(location.x, location.y, sideLength, amountOfSides, filled, color);
    }
    
    public static void polygon(double x, double y, double sideLength, int amountOfSides, boolean filled) {
    	polygon(x, y, sideLength, amountOfSides, filled, null);
    }
    
    public static void polygon(Vec location, double sideLength, int amountOfSides, boolean filled) {
    	polygon(location, sideLength, amountOfSides, filled, null);
    }
    
    public static void polygon(double x, double y, double sideLength, int amountOfSides, Color color) {
    	polygon(x, y, sideLength, amountOfSides, true, color);
    }
    
    public static void polygon(Vec location, double sideLength, int amountOfSides, Color color) {
    	polygon(location, sideLength, amountOfSides, true, color);
    }
    
    public static void polygon(double x, double y, double sideLength, int amountOfSides) {
    	polygon(x, y, sideLength, amountOfSides, true, null);
    }
    
    public static void polygon(Vec location, double sideLength, int amountOfSides) {
    	polygon(location, sideLength, amountOfSides, true, null);
    }
    
    public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, boolean filled, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, filled, color);
    }
    
    public static void polygonCentered(Vec location, double sideLength, int amountOfSides, boolean filled, Color color) {
    	polygon(location.x - sideLength / 2, location.y - sideLength / 2, sideLength, amountOfSides, filled, color);
    }
    
    public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, boolean filled) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, filled, null);
    }
    
    public static void polygonCentered(Vec location, double sideLength, int amountOfSides, boolean filled) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, amountOfSides, filled, null);
    }
    
    public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, true, color);
    }
    
    public static void polygonCentered(Vec location, double sideLength, int amountOfSides, Color color) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, amountOfSides, true, color);
    }
    
    public static void polygonCentered(double x, double y, double sideLength, int amountOfSides) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, true, null);
    }
    
    public static void polygonCentered(Vec location, double sideLength, int amountOfSides) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, amountOfSides, true, null);
    }
    
    public static void circle(double x, double y, double radius, boolean filled, Color color) {
    	polygon(x, y, radius, 360, filled, color);
    }
    
    public static void circle(Vec location, double radius, boolean filled, Color color) {
    	polygon(location, radius, 360, filled, color);
    }
    
    public static void circle(double x, double y, double radius, boolean filled) {
    	polygon(x, y, radius, 360, filled);
    }
    
    public static void circle(Vec location, double radius, boolean filled) {
    	polygon(location, radius, 360, filled);
    }
    
    public static void circle(double x, double y, double radius, Color color) {
    	polygon(x, y, radius, 360, color);
    }
    
    public static void circle(Vec location, double radius, Color color) {
    	polygon(location, radius, 360, color);
    }
    
    public static void circle(double x, double y, double radius) {
    	polygon(x, y, radius, 360);
    }
    
    public static void circle(Vec location, double radius) {
    	polygon(location, radius, 360);
    }
    
    public static void circleCentered(double x, double y, double radius, boolean filled, Color color) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360, filled, color);
    }
    
    public static void circleCentered(Vec location, double radius, boolean filled, Color color) {
    	polygon((location.x - radius / 2), (location.y - radius / 2), radius, 360, filled, color);
    }
    
    public static void circleCentered(double x, double y, double radius, boolean filled) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360, filled);
    }
    
    public static void circleCentered(Vec location, double radius, boolean filled) {
    	polygon((location.x - radius / 2), (location.y - radius / 2), radius, 360, filled);
    }
    
    public static void circleCentered(double x, double y, double radius, Color color) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360, color);
    }
    
    public static void circleCentered(Vec location, double radius, Color color) {
    	polygon((location.x - radius / 2), (location.y - radius / 2), radius, 360, color);
    }
    
    public static void circleCentered(double x, double y, double radius) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360);
    }
    
    public static void circleCentered(Vec location, double radius) {
    	polygon((location.x - radius / 2), (location.y - radius / 2), radius, 360);
    }
    
    public static void triangle(double x, double y, double sideLength, boolean filled, Color color) {
    	polygon(x, y, sideLength, 3, filled, color);
    }
    
    public static void triangle(Vec location, double sideLength, boolean filled, Color color) {
    	polygon(location, sideLength, 3, filled, color);
    }
    
    public static void triangle(double x, double y, double sideLength, boolean filled) {
    	polygon(x, y, sideLength, 3, filled);
    }
    
    public static void triangle(Vec location, double sideLength, boolean filled) {
    	polygon(location, sideLength, 3, filled);
    }
    
    public static void triangle(double x, double y, double sideLength, Color color) {
    	polygon(x, y, sideLength, 3, color);
    }
    
    public static void triangle(Vec location, double sideLength, Color color) {
    	polygon(location, sideLength, 3, color);
    }
    
    public static void triangle(double x, double y, double sideLength) {
    	polygon(x, y, sideLength, 3);
    }
    
    public static void triangle(Vec location, double sideLength) {
    	polygon(location, sideLength, 3);
    }
    
    public static void triangleCentered(double x, double y, double sideLength, boolean filled, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3, filled, color);
    }
    
    public static void triangleCentered(Vec location, double sideLength, boolean filled, Color color) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, 3, filled, color);
    }
    
    public static void triangleCentered(double x, double y, double sideLength, boolean filled) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3, filled);
    }
    
    public static void triangleCentered(Vec location, double sideLength, boolean filled) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, 3, filled);
    }
    
    public static void triangleCentered(double x, double y, double sideLength, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3, color);
    }
    
    public static void triangleCentered(Vec location, double sideLength, Color color) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, 3, color);
    }
    
    public static void triangleCentered(double x, double y, double sideLength) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3);
    }
    
    public static void triangleCentered(Vec location, double sideLength) {
    	polygon((location.x - sideLength / 2), (location.y - sideLength / 2), sideLength, 3);
    }
    
    public static void line(double firstX, double firstY, double secondX, double secondY, int lineWidth, Color color) {
    	start();
    	if (color != null)
    		color(color);
    	lineWidth(lineWidth <= 0 ? 1 : lineWidth);
    	begin(GL_LINES);
    	{
    		vertex(firstX, firstY);
    		vertex(secondX, secondY);
    	}
    	end();
    	stop();
    }
    
    public static void line(Vec firstLocation, Vec secondLocation, int lineWidth, Color color) {
    	line(firstLocation.x, firstLocation.y, secondLocation.x, secondLocation.y, lineWidth, color);
    }
    
    public static void line(double firstX, double firstY, double secondX, double secondY, int lineWidth) {
    	line(firstX, firstY, secondX, secondY, lineWidth, null);
    }
    
    public static void line(Vec firstLocation, Vec secondLocation, int lineWidth) {
    	line(firstLocation.x, firstLocation.y, secondLocation.x, secondLocation.y, lineWidth, null);
    }
    
    public static void line(double firstX, double firstY, double secondX, double secondY, Color color) {
    	line(firstX, firstY, secondX, secondY, 0, color);
    }
    
    public static void line(Vec firstLocation, Vec secondLocation, Color color) {
    	line(firstLocation.x, firstLocation.y, secondLocation.x, secondLocation.y, 0, color);
    }
    
    public static void line(double firstX, double firstY, double secondX, double secondY) {
    	line(firstX, firstY, secondX, secondY, 0, null);
    }
    
    public static void line(Vec firstLocation, Vec secondLocation) {
    	line(firstLocation.x, firstLocation.y, secondLocation.x, secondLocation.y, 0, null);
    }

}
