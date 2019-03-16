package xyz.iotacb.cloud.utilities.math;

public class Random {

	/**
	 * Generates a random Integer
	 * @param boundary
	 * @return
	 */
	public static int randomInt(final int boundary) {
		return (int) randomFloat(boundary);
	}
	
	/**
	 * Generates a random Float
	 * @param boundary
	 * @return
	 */
	public static float randomFloat(final float boundary) {
		return new java.util.Random().nextFloat() * boundary;
	}
	
	/**
	 * Generates a random Double
	 * @param boundary
	 * @return
	 */
	public static double randomDouble(final double boundary) {
		return new java.util.Random().nextDouble() * boundary;
	}
	
	/**
	 * Generates a Integer in a range
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static int randomInt(final int minValue, final int maxValue) {
		return (int) randomFloat(minValue, maxValue);
	}
	
	/**
	 * Generates a Float in a range
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static float randomFloat(final float minValue, final float maxValue) {
		return minValue + (new java.util.Random().nextFloat() * ((maxValue - minValue) + 1));
	}
	
	/**
	 * Generates a Double in a range
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static double randomDouble(final double minValue, final double maxValue) {
		return minValue + (new java.util.Random().nextDouble() * ((maxValue - minValue) + 1));
	}
	
}
