package de.iotacb.cloud.utilities.math;

public class Random {

    /**
     * Generates a random double between a min and max value
     * @param min The min value
     * @param max The max value
     * @return The random number
     */
    public static double randomDouble(final double min, final double max) {
        return min + (new java.util.Random().nextDouble() * ((max - min) + 1));
    }

    /**
     * Generates a random long between a min and max value
     * @param min The min value
     * @param max The max value
     * @return The random number
     */
    public static long randomLong(final long min, final long max) {
        return min + (new java.util.Random().nextLong() * ((max - min) + 1));
    }

    /**
     * Generates a random float between a min and max value
     * @param min The min value
     * @param max The max value
     * @return The random number
     */
    public static float randomFloat(final float min, final double max) {
        return (float) randomDouble(min, max);
    }

    /**
     * Generates a random integer between a min and max value
     * @param min The min value
     * @param max The max value
     * @return The random number
     */
    public static int randomInteger(final int min, final int max) {
        return (int) randomDouble(min, max);
    }

    /**
     * Generates a random double between 0 and a max value
     * @param max The max value
     * @return The random number
     */
    public static double randomDouble(final double max) {
        return randomDouble(0, max);
    }

    /**
     * Generates a random long between 0 and a max value
     * @param max The max value
     * @return The random number
     */
    public static long randomLong(final long max) {
        return randomLong(0, max);
    }

    /**
     * Generates a random float between 0 and a max value
     * @param max The max value
     * @return The random number
     */
    public static float randomFloat(final float max) {
        return randomFloat(0, max);
    }

    /**
     * Generates a random integer between 0 and a max value
     * @param max The max value
     * @return The random number
     */
    public static int randomInteger(final int max) {
        return randomInteger(0, max);
    }

    /**
     * Will choose a random object from the given array and returns it
     * @param objects The object array
     * @return The chosen object
     */
    public static Object choose(final Object...objects) {
        return (objects.length > 0 ? objects[randomInteger(0, objects.length - 1)] : null);
    }

}
