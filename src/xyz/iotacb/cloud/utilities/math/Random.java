package xyz.iotacb.cloud.utilities.math;

public class Random {

	/**
	 * This will generate a random integer from zero up to the boundary
	 * @param boundary
	 * @return
	 */
	public static int randomInt(final int boundary) {
		return (int) randomFloat(boundary);
	}
	
	/**
	 * This will generate a random float from zero up to the boundary
	 * @param boundary
	 * @return
	 */
	public static float randomFloat(final float boundary) {
		return new java.util.Random().nextFloat() * boundary;
	}
	
	/**
	 * This will generate a random double from zero up to the boundary
	 * @param boundary
	 * @return
	 */
	public static double randomDouble(final double boundary) {
		return new java.util.Random().nextDouble() * boundary;
	}
	
	/**
	 * This will generate a random integer which will be inside of the given range
	 * @param boundary
	 * @return
	 */
	public static int randomInt(final int minValue, final int maxValue) {
		return (int) randomFloat(minValue, maxValue);
	}
	
	/**
	 * This will generate a random integer which will be inside of the given range
	 * @param boundary
	 * @return
	 */
	public static float randomFloat(final float minValue, final float maxValue) {
		return minValue + (new java.util.Random().nextFloat() * ((maxValue - minValue) + 1));
	}
	
	/**
	 * This will generate a random integer which will be inside of the given range
	 * @param boundary
	 * @return
	 */
	public static double randomDouble(final double minValue, final double maxValue) {
		return minValue + (new java.util.Random().nextDouble() * ((maxValue - minValue) + 1));
	}
	
	/**
	 * This will generate a random boolean
	 * @return
	 */
	public static boolean randomBoolean() {
		return new java.util.Random().nextBoolean();
	}
	
}
