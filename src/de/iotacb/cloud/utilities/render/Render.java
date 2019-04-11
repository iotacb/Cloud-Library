package de.iotacb.cloud.utilities.render;

import de.iotacb.cloud.core.display.Display;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Vector;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Render {

    public static Display display;

    public static void push() {
        glPushMatrix();
    }

    public static void pop() {
        glPopMatrix();
    }

    public static void enable(final int target) {
        glEnable(target);
    }

    public static void disable(final int target) {
        glDisable(target);
    }

    public static void start() {
        push();
        enable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        enable(GL_LINE_SMOOTH);
        enable(GL_POINT_SMOOTH);
        enable(GL_POLYGON_SMOOTH);
        disable(GL_LIGHTING);
        disable(GL_TEXTURE_2D);
        disable(GL_CULL_FACE);
    }

    public static void stop() {
        enable(GL_CULL_FACE);
        enable(GL_TEXTURE_2D);
        enable(GL_LIGHTING);
        disable(GL_POLYGON_SMOOTH);
        disable(GL_POINT_SMOOTH);
        disable(GL_LINE_SMOOTH);
        disable(GL_BLEND);
        pop();
    }

    public static void begin(final int mode) {
        glBegin(mode);
    }

    public static void end() {
        glEnd();
    }

    public static void vertex(final double x, final double y) {
        glVertex2d(x, y);
    }

    public static void vertex(final Vector location) {
        vertex(location.x, location.y);
    }

    public static void translate(final double x, final double y) {
        glTranslated(x, y, 0);
    }

    public static void translate(final Vector location) {
        translate(location.x, location.y);
    }

    public static void scale(final double x, final double y) {
        glScaled(x, y, 0);
    }

    public static void scale(final Vector location) {
        scale(location.x, location.y);
    }

    public static void rotate(final double x, final double y, final double z, final double angle) {
        glRotated(angle, x, y, z);
    }

    public static void rotate(final Vector location, final double angle) {
        rotate(location.x, location.y, location.z, angle);
    }

    public static void color(final double red, final double green, final double blue, final double alpha) {
        glColor4d(red, green, blue, alpha);
    }

    public static void color(final double red, final double green, final double blue) {
        color(red, green, blue, 1);
    }

    public static void color(final Color color) {
        color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }

    public static boolean shouldBeRendered(final double x, final double y, double width, double height) {
        boolean should = true;
        if (x + width < 0 || x > display.displayWidth) {
            should = false;
        }
        if (y + height < 0 || y > display.displayHeight) {
            should = false;
        }
        return should;
    }

    public static boolean shouldBeRendered(final Vector location, final Vector dimensions) {
        return shouldBeRendered(location.x, location.y, dimensions.x, dimensions.y);
    }

    public static void rectangle(final double x, final double y, final double width, final double height, final boolean outlined, final Color color) {
        if (!shouldBeRendered(x, y, width, height)) return;
        start();
        color(color);
        begin(outlined ? GL_LINE : GL_QUADS);
        {
            vertex(x, y);
            vertex(x + width, y);
            vertex(x + width, y + height);
            vertex(x, y + height);
            vertex(x, y);
        }
        end();
        stop();
    }

    public static void rectangle(final Vector location, final Vector dimensions, final boolean outlined, final Color color) {
        rectangle(location.x, location.y, dimensions.x, dimensions.y, outlined, color);
    }

    public static void rectangle(final double x, final double y, final double width, final double height, final boolean outlined) {
        rectangle(x, y, width, height, outlined, Color.white);
    }

    public static void rectangle(final Vector location, final Vector dimensions, final boolean outlined) {
        rectangle(location.x, location.y, dimensions.x, dimensions.y, outlined);
    }

    public static void polygon(final double x, final double y, final int sides, double sideLength, final boolean outlined, final Color color) {
        if (!shouldBeRendered(x, y, sideLength, sideLength)) return;
        start();
        color(color);
        begin(outlined ? GL_LINES : GL_TRIANGLE_FAN);
        {
            for (int i = 0; i <= sides; i++) {
                double angle = i * Maths.TWO_PI / sides;
                vertex(x + (sideLength * Math.cos(angle)) + sideLength / 2, y + (sideLength * Math.sin(angle)) + sideLength / 2);
            }
        }
        glEnd();
        stop();
    }

    public static void polygon(final Vector location, final int sides, final double sideLength, final boolean outlined, final Color color) {
        polygon(location.x, location.y, sides, sideLength, outlined, color);
    }

    public static void polygon(final double x, final double y, final int sides, final double sideLength, final boolean outlined) {
        polygon(x, y, sides, sideLength, outlined, Color.white);
    }

    public static void polygon(final Vector location, final int sides, final double sideLength, final boolean outlined) {
        polygon(location.x, location.y, sides, sideLength, outlined);
    }

    public static void triangle(final double x, final double y, final double sideLength, final boolean outlined, final Color color) {
        polygon(x, y, 3, sideLength, outlined, color);
    }

    public static void triangle(final Vector location, final double sideLength, final boolean outlined, final Color color) {
        triangle(location.x, location.y, sideLength, outlined, color);
    }

    public static void triangle(final double x, final double y, final double sideLength, final boolean outlined) {
        triangle(x, y, sideLength, outlined, Color.white);
    }

    public static void triangle(final Vector location, final double sideLength, final boolean outlined) {
        triangle(location.x, location.y, sideLength, outlined);
    }

    public static void circle(final double x, final double y, final double radius, final boolean outlined, final Color color) {
        polygon(x, y, 360, radius, outlined, color);
    }

    public static void circle(final Vector location, final double radius, final boolean outlined, final Color color) {
        circle(location.x, location.y, radius, outlined, color);
    }

    public static void circle(final double x, final double y, final double radius, final boolean outlined) {
        circle(x, y, radius, outlined, Color.white);
    }

    public static void circle(final Vector location, final double radius, final boolean outlined) {
        circle(location.x, location.y, radius, outlined);
    }

}
