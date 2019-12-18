package de.iotacb.cloud.utilities.color;

import java.awt.Color;

import de.iotacb.cloud.utilities.math.Randoms;

public class Colors {
	
	public static Color random() {
		return new Color(Randoms.randomInteger(0, 255), Randoms.randomInteger(0, 255), Randoms.randomInteger(0, 255));
	}
	
	public static Color setAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	
	public static Color rainbow(long milliseconds) {
		double hue = System.currentTimeMillis() % milliseconds;
		hue /= milliseconds;
		return Color.getHSBColor((float) hue, 1, 1);
	}

}
