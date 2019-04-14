package de.iotacb.cloud.utilities.render;

import de.iotacb.cloud.core.display.Display;
import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.Vector;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Render {

    public static Display display;

    /**
     * Start a OpenGL rendering matrix
     */
    public static void push() {
        glPushMatrix();
    }

    /**
     * End a OpenGL rendering matrix
     */
    public static void pop() {
        glPopMatrix();
    }

    /**
     * Enable a OpenGL target
     * @param target The target which should enabled
     */
    public static void enable(final int target) {
        glEnable(target);
    }

    /**
     * Disable a OpenGL target
     * @param target The target which should be enabled
     */
    public static void disable(final int target) {
        glDisable(target);
    }

    /**
     * Star basic OpenGL rendering
     */
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

    /**
     * Stop basic OpenGL rendering
     */
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

    /**
     * Begin OpenGL rendering with a OpenGL rendering mode
     * @param mode The rendering mode
     */
    public static void begin(final int mode) {
        glBegin(mode);
    }

    /**
     * End OpenGL rendering
     */
    public static void end() {
        glEnd();
    }

    /**
     * Draw a OpenGL vertex
     * @param x The x location of the vertex
     * @param y The y location of the vertex
     */
    public static void vertex(final double x, final double y) {
        glVertex2d(x, y);
    }

    /**
     * Draw a OpenGL vertex
     * @param location The location of the vertex as a vector
     */
    public static void vertex(final Vector location) {
        vertex(location.x, location.y);
    }

    /**
     * Translate vertices
     * @param x The x location where the vertices should be translated to
     * @param y The y location where the vertices should be translated to
     */
    public static void translate(final double x, final double y) {
        glTranslated(x, y, 0);
    }

    /**
     * Translate vertices
     * @param location The location where the vertices should be translated to as a vector
     */
    public static void translate(final Vector location) {
        translate(location.x, location.y);
    }

    /**
     * Rescale vertices
     * @param x The amount of scaling on the x axe
     * @param y The amount of scaling on the y axe
     */
    public static void scale(final double x, final double y) {
        glScaled(x, y, 0);
    }

    /**
     * Rescale vertices
     * @param scaling The amount of scaling as a vector
     */
    public static void scale(final Vector scaling) {
        scale(scaling.x, scaling.y);
    }

    /**
     * Rotate vertices on different axis
     * @param x The x axe
     * @param y The y axe
     * @param z The z axe
     * @param angle The angle of rotation
     */
    public static void rotate(final double x, final double y, final double z, final double angle) {
        glRotated(angle, x, y, z);
    }

    /**
     * Rotate vertices on different axis
     * @param axis The different axis as a vector
     * @param angle The angle of rotation
     */
    public static void rotate(final Vector axis, final double angle) {
        rotate(axis.x, axis.y, axis.z, angle);
    }

    /**
     * Set the current rendering color with alpha
     * @param red
     * @param green
     * @param blue
     * @param alpha
     */
    public static void color(final double red, final double green, final double blue, final double alpha) {
        glColor4d(red, green, blue, alpha);
    }

    /**
     * Set the rendering color
     * @param red
     * @param green
     * @param blue
     */
    public static void color(final double red, final double green, final double blue) {
        color(red, green, blue, 1);
    }

    /**
     * Set the rendering color
     * @param color
     */
    public static void color(final Color color) {
        color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }

    /**
     * Check if vertices should be rendered
     * @param x The x location of the vertices
     * @param y The y location of the vertices
     * @param width The width of the vertices
     * @param height The height of the vertices
     * @return If the vertices are inside the window borders
     */
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

    /**
     * Check if vertices should be rendered
     * @param location The location of the vertices
     * @param dimensions The dimensions of the vertices
     * @return If the vertices are inside the window borders
     */
    public static boolean shouldBeRendered(final Vector location, final Vector dimensions) {
        return shouldBeRendered(location.x, location.y, dimensions.x, dimensions.y);
    }

    /**
     * Draw a rectangle
     * @param x The x location of the rectangle
     * @param y The y location of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param outlined If the rectangle should be outlined
     * @param color The color of the rectangle
     */
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

    /**
     * Draw a rectangle
     * @param location The location of the rectangle as a vector
     * @param dimensions The dimensions of the rectangle as a vector
     * @param outlined If the rectangle should be outlined
     * @param color The color of the rectangle
     */
    public static void rectangle(final Vector location, final Vector dimensions, final boolean outlined, final Color color) {
        rectangle(location.x, location.y, dimensions.x, dimensions.y, outlined, color);
    }

    /**
     * Draw a white rectangle
     * @param x The x location of the rectangle
     * @param y The y location of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param outlined If the rectangle should be outlined
     */
    public static void rectangle(final double x, final double y, final double width, final double height, final boolean outlined) {
        rectangle(x, y, width, height, outlined, Color.white);
    }

    /**
     * Draw a white rectangle
     * @param location The location of the rectangle as a vector
     * @param dimensions The dimensions of the rectangle as a vector
     * @param outlined If the rectangle should be outlined
     */
    public static void rectangle(final Vector location, final Vector dimensions, final boolean outlined) {
        rectangle(location.x, location.y, dimensions.x, dimensions.y, outlined);
    }

    /**
     * Draw polygon
     * @param x The x location of the polygon
     * @param y The y location of the polygon
     * @param sides The amount of polygons
     * @param sideLength The length of the sides
     * @param outlined If the polygon should be outlined
     * @param color The color of the polygon
     */
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

    /**
     * Draw polygon
     * @param location The location of the polygon as a vector
     * @param sides The amount of polygons
     * @param sideLength The length of the sides
     * @param outlined If the polygon should be outlined
     * @param color The color of the polygon
     */
    public static void polygon(final Vector location, final int sides, final double sideLength, final boolean outlined, final Color color) {
        polygon(location.x, location.y, sides, sideLength, outlined, color);
    }

    /**
     * Draw a white polygon
     * @param x The x location of the polygon
     * @param y The y location of the polygon
     * @param sides The amount of polygons
     * @param sideLength The length of the sides
     * @param outlined If the polygon should be outlined
     */
    public static void polygon(final double x, final double y, final int sides, final double sideLength, final boolean outlined) {
        polygon(x, y, sides, sideLength, outlined, Color.white);
    }

    /**
     * Draw a white polygon
     * @param location The location of the polygon as a vector
     * @param sides The amount of polygons
     * @param sideLength The length of the sides
     * @param outlined If the polygon should be outlined
     */
    public static void polygon(final Vector location, final int sides, final double sideLength, final boolean outlined) {
        polygon(location.x, location.y, sides, sideLength, outlined);
    }

    /**
     * Draw a triangle
     * @param x The x location of the triangle
     * @param y The y location of the triangle
     * @param sideLength The length of the sides
     * @param outlined If the triangle should be outlined
     * @param color The color of the triangle
     */
    public static void triangle(final double x, final double y, final double sideLength, final boolean outlined, final Color color) {
        polygon(x, y, 3, sideLength, outlined, color);
    }

    /**
     * Draw triangle
     * @param location The location of the triangle
     * @param sideLength The length of the sides
     * @param outlined If the triangle should be outlined
     * @param color The color of the triangle
     */
    public static void triangle(final Vector location, final double sideLength, final boolean outlined, final Color color) {
        triangle(location.x, location.y, sideLength, outlined, color);
    }

    /**
     * Draw a white triangle
     * @param x The x location of the triangle
     * @param y The y location of the triangle
     * @param sideLength The length of the sides
     * @param outlined If the triangle should be outlined
     */
    public static void triangle(final double x, final double y, final double sideLength, final boolean outlined) {
        triangle(x, y, sideLength, outlined, Color.white);
    }

    /**
     * Draw a white triangle
     * @param location The location of the triangle as a vector
     * @param sideLength The length of the sides
     * @param outlined If the triangle should be outlined
     */
    public static void triangle(final Vector location, final double sideLength, final boolean outlined) {
        triangle(location.x, location.y, sideLength, outlined);
    }

    /**
     * Draw  circle
     * @param x The x location of the circle
     * @param y The y location of the circle
     * @param radius The radius of the circle
     * @param outlined If the circle should be outlined
     * @param color The color of the circle
     */
    public static void circle(final double x, final double y, final double radius, final boolean outlined, final Color color) {
        polygon(x, y, 360, radius, outlined, color);
    }

    /**
     * Draw a circle
     * @param location The location of the circle as a vector
     * @param radius The radius of the circle
     * @param outlined If the circle should be outlined
     * @param color The color of the circle
     */
    public static void circle(final Vector location, final double radius, final boolean outlined, final Color color) {
        circle(location.x, location.y, radius, outlined, color);
    }

    /**
     * Draw a white circle
     * @param x The x location of the circle
     * @param y The y location of the circle
     * @param radius The radius of the circle
     * @param outlined If the circle should be outlined
     */
    public static void circle(final double x, final double y, final double radius, final boolean outlined) {
        circle(x, y, radius, outlined, Color.white);
    }

    /**
     * Draw a white circle
     * @param location The location of the circle as a vector
     * @param radius The radius of the circle
     * @param outlined If the circle should be outlined
     */
    public static void circle(final Vector location, final double radius, final boolean outlined) {
        circle(location.x, location.y, radius, outlined);
    }

}
