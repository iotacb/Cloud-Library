package xyz.iotacb.cloud.utilities.color;

import java.awt.Color;

import xyz.iotacb.cloud.utilities.math.Random;

public class Colors {

	/**
	 * Generates a random color (Without alpha generation)
	 * @return
	 */
	public static Color random() {
		int red = Random.randomInt(0, 255);
		int green = Random.randomInt(0, 255);
		int blue = Random.randomInt(0, 255);
		return new Color(red, green, blue);
	}
	
	/**
	 * Change the alpha value of a color
	 * @param color
	 * @param alpha
	 * @return
	 */
	public static Color addAlpha(final Color color, final int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	
	/**
	 * Creates a rainbow effect
	 * @param offset
	 * @param fade
	 * @return
	 */
	public static Color rainbow(final long offset) {
		float colorHue = (System.nanoTime() + offset) / 1.0e10f % 1.0F;
		return new Color(Color.HSBtoRGB(colorHue, 1.0F, 1.0F));
	}
	
}
