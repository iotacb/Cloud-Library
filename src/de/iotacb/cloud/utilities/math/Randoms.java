package de.iotacb.cloud.utilities.math;

public class Randoms {
	
	public static double randomDouble(double minValue, double maxValue) {
		return minValue + (new java.util.Random().nextDouble() * ((maxValue - minValue) + 1));
	}
	
	public static long randomLong(long minValue, long maxValue) {
		return minValue + (new java.util.Random().nextLong() * ((maxValue - minValue) + 1));
	}
	
	public static float randomFloat(float minValue, float maxValue) {
		return (float) randomDouble(minValue, maxValue);
	}
	
	public static int randomInt(int minValue, int maxValue) {
		return (int) randomDouble(minValue, maxValue);
	}
	
	public static double randomDouble(double maxValue) {
		return randomDouble(0, maxValue);
	}
	
	public static long randomLong(long maxValue) {
		return randomLong(0, maxValue);
	}
	
	public static float randomFloat(float maxValue) {
		return randomFloat(0, maxValue);
	}
	
	public static int randomInt(int maxValue) {
		return randomInt(0, maxValue);
	}
	
	public static boolean randomBool() {
		return new java.util.Random().nextBoolean();
	}
	
	public static Object choose(final Object...objects) {
		return (objects.length > 0 ? objects[randomInt(objects.length - 1)] : null);
	}
	
	public static boolean chance(final double percentage) {
		return (randomDouble(1) > percentage);
	}

}
