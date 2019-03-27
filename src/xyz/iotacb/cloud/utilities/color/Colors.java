package xyz.iotacb.cloud.utilities.color;

import java.awt.Color;

import xyz.iotacb.cloud.utilities.math.Random;

public class Colors {

	/**
	 * This method will generate a random color without alpha channel
	 * @return
	 */
	public static Color random() {
		int red = Random.randomInt(0, 255);
		int green = Random.randomInt(0, 255);
		int blue = Random.randomInt(0, 255);
		return new Color(red, green, blue);
	}
	
	/**
	 * This method will add a alpha value to a given color in the parameter
	 * @param color
	 * @param alpha
	 * @return
	 */
	public static Color addAlpha(final Color color, final int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	
	/**
	 * This method will create a rainbow fading color
	 * @param offset
	 * @return
	 */
	public static Color rainbow(final int offset) {
		int speed = -15;
		float counter = (System.currentTimeMillis() + offset) % speed;
		counter /= speed;
		return Color.getHSBColor(counter, 1.0F, 1.0F);
	}
	
}
