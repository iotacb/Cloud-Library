package de.iotacb.cloud.utilities.color;

import java.awt.Color;

import de.iotacb.cloud.utilities.math.Randoms;

public class Colors {
	
	public static Color random() {
		return new Color(Randoms.randomInt(0, 255), Randoms.randomInt(0, 255), Randoms.randomInt(0, 255));
	}
	
	public static Color setAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	
	public static Color rainbow(double time) {
		double hue = System.currentTimeMillis() % time;
		hue /= time;
		return Color.getHSBColor((float) hue, 1, 1);
	}

}
