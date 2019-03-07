package xyz.iotacb.cloud.utilities.math;

public class Random {

	public static int randomInt(final int boundary) {
		return (int) randomFloat(boundary);
	}
	
	public static float randomFloat(final float boundary) {
		return new java.util.Random().nextFloat() * boundary;
	}
	
	public static double randomDouble(final double boundary) {
		return new java.util.Random().nextDouble() * boundary;
	}
	
	public static int randomInt(final int minValue, final int maxValue) {
		return (int) randomFloat(minValue, maxValue);
	}
	
	public static float randomFloat(final float minValue, final float maxValue) {
		return minValue + (new java.util.Random().nextFloat() * ((maxValue - minValue) + 1));
	}
	
	public static double randomDouble(final double minValue, final double maxValue) {
		return minValue + (new java.util.Random().nextDouble() * ((maxValue - minValue) + 1));
	}
	
}
