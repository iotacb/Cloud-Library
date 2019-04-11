package de.iotacb.cloud.utilities.color;

import de.iotacb.cloud.utilities.math.Random;

import java.awt.*;

public class Colors {

    /**
     * Generate a random color
     * @return The generated color
     */
    public static Color random() {
        final int red = Random.randomInteger(0, 255);
        final int green = Random.randomInteger(0, 255);
        final int blue = Random.randomInteger(0, 255);
        return new Color(red, green, blue);
    }

    /**
     * Set the alpha value of a color
     * @param alpha The wanted alpha value
     * @param color The color which alpha should be changed
     * @return The new color
     */
    public static Color setAlpha(final int alpha, final Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    /**
     * Creates a rainbow color effect
     * @param offset Set a offset
     * @param speed The speed of the color change (Go negative for faster changing. Use 0 for default speed)
     * @return The rainbow color
     */
    public static Color rainbow(final long offset,  int speed) {
        if (speed == 0) speed = -15000;
        float hue = (System.currentTimeMillis() + offset) % speed;
        hue /= speed;
        return Color.getHSBColor(hue, 1, 1);
    }

}
