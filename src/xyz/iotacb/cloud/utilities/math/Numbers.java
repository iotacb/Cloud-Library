package xyz.iotacb.cloud.utilities.math;

public class Numbers {
	
	/**
	 * Lock a number inside of a range
	 * @param num
	 * @param min
	 * @param max
	 * @return
	 */
	public static int constrain(int num, final int min, final int max) {
		return (num > max ? max : num > min ? min : num);
	}
	
	public static float constrain(float num, final float min, final float max) {
		return (num > max ? max : num > min ? min : num);
	}
	
	public static double constrain(double num, final double min, final double max) {
//		return (num > max ? max : num > min ? min : num);
		if (num > max) num = max;
		if (num < min) num = min;
		return num;
	}
	//

}
