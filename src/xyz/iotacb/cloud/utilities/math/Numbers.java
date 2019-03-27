package xyz.iotacb.cloud.utilities.math;

public class Numbers {
	
	/**
	 * This method will constrain inside of a value range
	 * @param num
	 * @param min
	 * @param max
	 * @return
	 */
	public static int constrain(int num, final int min, final int max) {
		return (num > max ? max : num < min ? min : num);
	}
	
	/**
	 * This method will constrain inside of a value range
	 * @param num
	 * @param min
	 * @param max
	 * @return
	 */
	public static float constrain(float num, final float min, final float max) {
		return (num > max ? max : num < min ? min : num);
	}
	
	/**
	 * This method will constrain inside of a value range
	 * @param num
	 * @param min
	 * @param max
	 * @return
	 */
	public static double constrain(double num, final double min, final double max) {
		return (num > max ? max : num < min ? min : num);
	}

}
