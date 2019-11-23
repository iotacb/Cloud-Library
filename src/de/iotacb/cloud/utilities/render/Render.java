package de.iotacb.cloud.utilities.render;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import de.iotacb.cloud.core.window.Window;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.vector.VectorI;

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
    
    public static void vertex(VectorI location) {
        vertex(location.x, location.y);
    }
    
    public static void translate(double x, double y) {
        glTranslated(x, y, 0);
    }
    
    public static void translate(VectorI location) {
        translate(location.x, location.y);
    }
    
    public static void scale(double x, double y) {
        glScaled(x, y, 0);
    }
    
    public static void scale(VectorI scaling) {
        scale(scaling.x, scaling.y);
    }
    
    public static void rotate(double x, double y, double z, double angle) {
        glRotated(angle, x, y, z);
    }
    
    public static void rotate(VectorI axis, double angle) {
        rotate(axis.x, axis.y, axis.z, angle);
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
    
    public static void rect(int x, int y, int width, int height, boolean filled, Color color) {
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
    
    public static void rect(VectorI location, VectorI size, boolean filled, Color color) {
    	rect((int)location.x, (int)location.y, (int)size.x, (int)size.y, filled, color);
    }
    
    public static void rect(int x, int y, VectorI size, boolean filled, Color color) {
    	rect(x, y, (int)size.x, (int)size.y, filled, color);
    }
    
    public static void rect(VectorI location, int width, int height, boolean filled, Color color) {
    	rect((int)location.x, (int)location.y, width, height, filled, color);
    }
    
    public static void rect(int x, int y, int width, int height, boolean filled) {
    	rect(x, y, width, height, filled, null);
    }
    
    public static void rect(VectorI location, VectorI size, boolean filled) {
    	rect(location, size, filled, null);
    }
    
    public static void rect(int x, int y, VectorI size, boolean filled) {
    	rect(x, y, (int)size.x, (int)size.y, filled);
    }
    
    public static void rect(VectorI location, int width, int height, boolean filled) {
    	rect((int)location.x, (int)location.y, width, height, filled);
    }
    
    public static void rect(int x, int y, int width, int height, Color color) {
    	rect(x, y, width, height, true, color);
    }
    
    public static void rect(VectorI location, VectorI size, Color color) {
    	rect(location, size, true, color);
    }
    
    public static void rect(int x, int y, VectorI size, Color color) {
    	rect(x, y, (int)size.x, (int)size.y, color);
    }
    
    public static void rect(VectorI location, int width, int height, Color color) {
    	rect((int)location.x, (int)location.y, width, height, color);
    }
    
    public static void rect(int x, int y, int width, int height) {
    	rect(x, y, width, height, true, null);
    }
    
    public static void rect(VectorI location, VectorI size) {
    	rect(location, size, true, null);
    }
    
    public static void rect(int x, int y, VectorI size) {
    	rect(x, y, (int)size.x, (int)size.y);
    }
    
    public static void rect(VectorI location, int width, int height) {
    	rect((int)location.x, (int)location.y, width, height);
    }
    
    public static void rectCentered(int x, int y, int width, int height, boolean filled, Color color) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, filled, color);
    }
    
    public static void rectCentered(VectorI location, VectorI size, boolean filled, Color color) {
    	rect((int)(location.x - size.x / 2), (int)(location.y - size.y / 2), (int)size.x, (int)size.y, filled, color);
    }
    
    public static void rectCentered(int x, int y, VectorI size, boolean filled, Color color) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, (int)size.x, (int)size.y, filled, color);
    }
    
    public static void rectCentered(VectorI location, int width, int height, boolean filled, Color color) {
    	rect((int)(location.x - width / 2), (int)(location.y - height / 2), width, height, filled, color);
    }
    
    public static void rectCentered(int x, int y, int width, int height, boolean filled) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, filled, null);
    }
    
    public static void rectCentered(VectorI location, VectorI size, boolean filled) {
    	rect((int)(location.x - size.x / 2), (int)(location.y - size.y / 2), size, filled, null);
    }
    
    public static void rectCentered(int x, int y, VectorI size, boolean filled) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, (int)size.x, (int)size.y, filled);
    }
    
    public static void rectCentered(VectorI location, int width, int height, boolean filled) {
    	rect((int)location.x - width / 2, (int)location.y - height / 2, width, height, filled);
    }
    
    public static void rectCentered(int x, int y, int width, int height, Color color) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, true, color);
    }
    
    public static void rectCentered(VectorI location, VectorI size, Color color) {
    	rect((int)(location.x - size.x / 2), (int)(location.y - size.y / 2), size, true, color);
    }
    
    public static void rectCentered(int x, int y, VectorI size, Color color) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, (int)size.x, (int)size.y, color);
    }
    
    public static void rectCentered(VectorI location, int width, int height, Color color) {
    	rect((int)location.x - width / 2, (int)location.y - height / 2, width, height, color);
    }
    
    public static void rectCentered(int x, int y, int width, int height) {
    	x -= width / 2;
    	y -= height / 2;
    	rect(x, y, width, height, true, null);
    }
    
    public static void rectCentered(VectorI location, VectorI size) {
    	rect((int)(location.x - size.x / 2), (int)(location.y - size.y / 2), size, true, null);
    }
    
    public static void rectCentered(int x, int y, VectorI size) {
    	x -= size.x / 2;
    	y -= size.y / 2;
    	rect(x, y, (int)size.x, (int)size.y);
    }
    
    public static void rectCentered(VectorI location, int width, int height) {
    	rect((int)location.x - width / 2, (int)location.y - height / 2, width, height);
    }
    
    public static void polygon(int x, int y, int sideLength, int amountOfSides, boolean filled, Color color) {
    	sideLength /= 2;
    	start();
    	if (color != null)
    		color(color);
    	begin(filled ? GL_TRIANGLE_FAN : GL_LINES);
    	{
    		for (int i = 0; i <= amountOfSides; i++) {
    			double angle = i * Maths.TAU / amountOfSides;
    			vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
    		}
    	}
    	end();
    	stop();
    }
    
    public static void polygon(VectorI location, int sideLength, int amountOfSides, boolean filled, Color color) {
    	polygon((int)location.x, (int)location.y, sideLength, amountOfSides, filled, color);
    }
    
    public static void polygon(int x, int y, int sideLength, int amountOfSides, boolean filled) {
    	polygon(x, y, sideLength, amountOfSides, filled, null);
    }
    
    public static void polygon(VectorI location, int sideLength, int amountOfSides, boolean filled) {
    	polygon(location, sideLength, amountOfSides, filled, null);
    }
    
    public static void polygon(int x, int y, int sideLength, int amountOfSides, Color color) {
    	polygon(x, y, sideLength, amountOfSides, true, color);
    }
    
    public static void polygon(VectorI location, int sideLength, int amountOfSides, Color color) {
    	polygon(location, sideLength, amountOfSides, true, color);
    }
    
    public static void polygon(int x, int y, int sideLength, int amountOfSides) {
    	polygon(x, y, sideLength, amountOfSides, true, null);
    }
    
    public static void polygon(VectorI location, int sideLength, int amountOfSides) {
    	polygon(location, sideLength, amountOfSides, true, null);
    }
    
    public static void polygonCentered(int x, int y, int sideLength, int amountOfSides, boolean filled, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, filled, color);
    }
    
    public static void polygonCentered(VectorI location, int sideLength, int amountOfSides, boolean filled, Color color) {
    	polygon((int)location.x - sideLength / 2, (int)location.y - sideLength / 2, sideLength, amountOfSides, filled, color);
    }
    
    public static void polygonCentered(int x, int y, int sideLength, int amountOfSides, boolean filled) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, filled, null);
    }
    
    public static void polygonCentered(VectorI location, int sideLength, int amountOfSides, boolean filled) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, amountOfSides, filled, null);
    }
    
    public static void polygonCentered(int x, int y, int sideLength, int amountOfSides, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, true, color);
    }
    
    public static void polygonCentered(VectorI location, int sideLength, int amountOfSides, Color color) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, amountOfSides, true, color);
    }
    
    public static void polygonCentered(int x, int y, int sideLength, int amountOfSides) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, amountOfSides, true, null);
    }
    
    public static void polygonCentered(VectorI location, int sideLength, int amountOfSides) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, amountOfSides, true, null);
    }
    
    public static void circle(int x, int y, int radius, boolean filled, Color color) {
    	polygon(x, y, radius, 360, filled, color);
    }
    
    public static void circle(VectorI location, int radius, boolean filled, Color color) {
    	polygon(location, radius, 360, filled, color);
    }
    
    public static void circle(int x, int y, int radius, boolean filled) {
    	polygon(x, y, radius, 360, filled);
    }
    
    public static void circle(VectorI location, int radius, boolean filled) {
    	polygon(location, radius, 360, filled);
    }
    
    public static void circle(int x, int y, int radius, Color color) {
    	polygon(x, y, radius, 360, color);
    }
    
    public static void circle(VectorI location, int radius, Color color) {
    	polygon(location, radius, 360, color);
    }
    
    public static void circle(int x, int y, int radius) {
    	polygon(x, y, radius, 360);
    }
    
    public static void circle(VectorI location, int radius) {
    	polygon(location, radius, 360);
    }
    
    public static void circleCentered(int x, int y, int radius, boolean filled, Color color) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360, filled, color);
    }
    
    public static void circleCentered(VectorI location, int radius, boolean filled, Color color) {
    	polygon((int)(location.x - radius / 2), (int)(location.y - radius / 2), radius, 360, filled, color);
    }
    
    public static void circleCentered(int x, int y, int radius, boolean filled) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360, filled);
    }
    
    public static void circleCentered(VectorI location, int radius, boolean filled) {
    	polygon((int)(location.x - radius / 2), (int)(location.y - radius / 2), radius, 360, filled);
    }
    
    public static void circleCentered(int x, int y, int radius, Color color) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360, color);
    }
    
    public static void circleCentered(VectorI location, int radius, Color color) {
    	polygon((int)(location.x - radius / 2), (int)(location.y - radius / 2), radius, 360, color);
    }
    
    public static void circleCentered(int x, int y, int radius) {
    	x -= radius / 2;
    	y -= radius / 2;
    	polygon(x, y, radius, 360);
    }
    
    public static void circleCentered(VectorI location, int radius) {
    	polygon((int)(location.x - radius / 2), (int)(location.y - radius / 2), radius, 360);
    }
    
    public static void triangle(int x, int y, int sideLength, boolean filled, Color color) {
    	polygon(x, y, sideLength, 3, filled, color);
    }
    
    public static void triangle(VectorI location, int sideLength, boolean filled, Color color) {
    	polygon(location, sideLength, 3, filled, color);
    }
    
    public static void triangle(int x, int y, int sideLength, boolean filled) {
    	polygon(x, y, sideLength, 3, filled);
    }
    
    public static void triangle(VectorI location, int sideLength, boolean filled) {
    	polygon(location, sideLength, 3, filled);
    }
    
    public static void triangle(int x, int y, int sideLength, Color color) {
    	polygon(x, y, sideLength, 3, color);
    }
    
    public static void triangle(VectorI location, int sideLength, Color color) {
    	polygon(location, sideLength, 3, color);
    }
    
    public static void triangle(int x, int y, int sideLength) {
    	polygon(x, y, sideLength, 3);
    }
    
    public static void triangle(VectorI location, int sideLength) {
    	polygon(location, sideLength, 3);
    }
    
    public static void triangleCentered(int x, int y, int sideLength, boolean filled, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3, filled, color);
    }
    
    public static void triangleCentered(VectorI location, int sideLength, boolean filled, Color color) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, 3, filled, color);
    }
    
    public static void triangleCentered(int x, int y, int sideLength, boolean filled) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3, filled);
    }
    
    public static void triangleCentered(VectorI location, int sideLength, boolean filled) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, 3, filled);
    }
    
    public static void triangleCentered(int x, int y, int sideLength, Color color) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3, color);
    }
    
    public static void triangleCentered(VectorI location, int sideLength, Color color) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, 3, color);
    }
    
    public static void triangleCentered(int x, int y, int sideLength) {
    	x -= sideLength / 2;
    	y -= sideLength / 2;
    	polygon(x, y, sideLength, 3);
    }
    
    public static void triangleCentered(VectorI location, int sideLength) {
    	polygon((int)(location.x - sideLength / 2), (int)(location.y - sideLength / 2), sideLength, 3);
    }
    
    public static void line(int firstX, int firstY, int secondX, int secondY, int lineWidth, Color color) {
    	start();
    	if (color != null)
    		color(color);
    	glLineWidth(lineWidth == 0 ? 1 : lineWidth);
    	begin(GL_LINES);
    	{
    		vertex(firstX, firstY);
    		vertex(secondX, secondY);
    	}
    	end();
    	stop();
    }
    
    public static void line(VectorI firstLocation, VectorI secondLocation, int lineWidth, Color color) {
    	line((int)firstLocation.x, (int)firstLocation.y, (int)secondLocation.x, (int)secondLocation.y, lineWidth, color);
    }
    
    public static void line(int firstX, int firstY, int secondX, int secondY, int lineWidth) {
    	line(firstX, firstY, secondX, secondY, lineWidth, null);
    }
    
    public static void line(VectorI firstLocation, VectorI secondLocation, int lineWidth) {
    	line((int)firstLocation.x, (int)firstLocation.y, (int)secondLocation.x, (int)secondLocation.y, lineWidth, null);
    }
    
    public static void line(int firstX, int firstY, int secondX, int secondY, Color color) {
    	line(firstX, firstY, secondX, secondY, 0, color);
    }
    
    public static void line(VectorI firstLocation, VectorI secondLocation, Color color) {
    	line((int)firstLocation.x, (int)firstLocation.y, (int)secondLocation.x, (int)secondLocation.y, 0, color);
    }
    
    public static void line(int firstX, int firstY, int secondX, int secondY) {
    	line(firstX, firstY, secondX, secondY, 0, null);
    }
    
    public static void line(VectorI firstLocation, VectorI secondLocation) {
    	line((int)firstLocation.x, (int)firstLocation.y, (int)secondLocation.x, (int)secondLocation.y, 0, null);
    }

}
