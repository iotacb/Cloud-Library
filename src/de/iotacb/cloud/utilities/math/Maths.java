package de.iotacb.cloud.utilities.math;

public class Maths {

    public static double PI = Math.PI, TWO_PI = Math.PI * 2;

    /**
     * Returns 1 if the value is larger than 0 and 0 if the value is lower than zero
     * @param value The value
     * @return The result

     */
    public static int sign(final double value) {
        return (value > 0 ? 1 : 0);
    }

    /**
     * Returns the larger of the two values
     * @param value1 The first value
     * @param value2 The second value
     * @return The largest one

     */
    public static double max(final double value1, final double value2) {
        return (value1 > value2 ? value1 : value2);
    }

    /**
     * Returns the larger of the two values
     * @param value1 The first value
     * @param value2 The second value
     * @return The largest one
     */
    public static float max(final float value1, final float value2) {
        return (value1 > value2 ? value1 : value2);
    }

    /**
     * Returns the larger of the two values
     * @param value1 The first value
     * @param value2 The second value
     * @return The largest one
     */
    public static int max(final int value1, final int value2) {
        return (value1 > value2 ? value1 : value2);
    }

    /**
     * Returns the smallest of the two values
     * @param value1 The first value
     * @param value2 The second value
     * @return The smallest one
     */
    public static double min(final double value1, final double value2) {
        return (value1 < value2 ? value1 : value2);
    }

    /**
     * Returns the smallest of the two values
     * @param value1 The first value
     * @param value2 The second value
     * @return The smallest one

     */
    public static float min(final float value1, final float value2) {
        return (value1 < value2 ? value1 : value2);
    }

    /**
     * Returns the smallest of the two values
     * @param value1 The first value
     * @param value2 The second value
     * @return The smallest one

     */
    public static int min(final int value1, final int value2) {
        return (value1 < value2 ? value1 : value2);
    }

    /**
     * Clamps the value between a min and max value
     * @param value The value that should be clamped
     * @param min The min value
     * @param max The max value
     * @return The clamped value
     */
    public static double clamp(final double value, final double min, final double max) {
        return (value < min ? min : value > max ? max : value);
    }

    /**
     * Clamps the value between a min and max value
     * @param value The value that should be clamped
     * @param min The min value
     * @param max The max value
     * @return The clamped value
     */
    public static float clamp(final float value, final float min, final float max) {
        return (value < min ? min : value > max ? max : value);
    }

    /**
     * Clamps the value between a min and max value
     * @param value The value that should be clamped
     * @param min The min value
     * @param max The max value
     * @return The clamped value
     */
    public static int clamp(final int value, final int min, final int max) {
        return (value < min ? min : value > max ? max : value);
    }

    /**
     * Calculates the direction from one point to a other
     * @param x1 The location on the first x axe
     * @param y1 The location on the first y axe
     * @param x2 The location on the second x axe
     * @param y2 The location on the second y axe
     * @return The calculated direction
     */
    public static double pointDirection(final double x1, final double y1, final double x2, final double y2) {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Calculates the direction from one point to a other
     * @param location1 The location of the first point as a two dimensional vector
     * @param location2 The location of the second point as a two dimensional vector
     * @return The calculated direction
     */
    public static double pointDirection(final Vector location1, final Vector location2) {
        double angle = Math.toDegrees(Math.atan2(location2.y - location1.y, location2.x - location1.x));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Returns the x axe of the vector determined by the indicated length and direction
     * @param value The length away of the point
     * @param direction The direction of the point
     * @return The horizontal x-component
     */
    public static double lengthDirX(final double value, final double direction) {
        return Math.cos(Math.toRadians(direction)) * value;
    }

    /**
     * Returns the y axe of the vector determined by the indicated length and direction
     * @param value The length away of the point
     * @param direction The direction of the point
     * @return The y axe of the vector
     */
    public static double lengthDirY(final double value, final double direction) {
        return Math.sin(Math.toRadians(direction)) * value;
    }

    /**
     * Returns the x axe of the vector determined by the indicated length and direction
     * @param value The length away of the point
     * @param direction The direction of the point
     * @return The horizontal x-component
     */
    public static float lengthDirX(final float value, final float direction) {
        return lengthDirX(value, direction);
    }

    /**
     * Returns the y axe of the vector determined by the indicated length and direction
     * @param value The length away of the point
     * @param direction The direction of the point
     * @return The y axe of the vector
     */
    public static float lengthDirY(final float value, final float direction) {
        return lengthDirY(value, direction);
    }

    /**
     * Returns the x axe of the vector determined by the indicated length and direction
     * @param value The length away of the point
     * @param direction The direction of the point
     * @return The horizontal x-component
     */
    public static int lengthDirX(final int value, final int direction) {
        return lengthDirX(value, direction);
    }

    /**
     * Returns the y axe of the vector determined by the indicated length and direction
     * @param value The length away of the point
     * @param direction The direction of the point
     * @return The y axe of the vector
     */
    public static int lengthDirY(final int value, final int direction) {
        return lengthDirY(value, direction);
    }

    /**
     * Get the distance between two points
     * @param x1 The location on the first x axe
     * @param y1 The location on the first y axe
     * @param x2 The location on the second x axe
     * @param y2 The location on the second y axe
     * @return The distance
     */
    public static double dist(final double x1, final double y1, final double x2, final double y2) {
        double xDiff = x1 - x2;
        double yDiff = y1 - y2;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * Get the distance between two points
     * @param location1 The location of the first point as a two dimensional vector
     * @param location2 The location of the second point as a two dimensional vector
     * @return The distance
     */
    public static double dist(final Vector location1, final Vector location2) {
        return dist(location1.x, location1.y, location2.x, location2.y);
    }

    /**
     * Returns the linear interpolation of two input values by the given amount.
     * @param value1 The first value
     * @param value2 The second value
     * @param interpolation The amount to interpolate
     * @return The interpolated number
     */
    public static double lerp(final double value1, final double value2, final double interpolation) {
        return (value1 * (1.0 - interpolation)) + (value2 * interpolation);
    }

    /**
     * Calculates the average of a amount of values
     * @param values The values which average should be calculated
     * @return The average of the values
     *
     */
    public static double average(final double...values) {
        double average = 0;
        for (double value : values) {
            average += value;
        }
        return (values.length > 0 ? average / values.length : average);
    }

}
