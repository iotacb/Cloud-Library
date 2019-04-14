package de.iotacb.cloud.utilities.math;

public class Interpolation {

    /**
     * Math formulas from Robert Penner http://robertpenner.com/easing/
     */

    /**
     * Linear interpolation of a value
     * @param current The current value
     * @param to The final value
     * @param step The step amount
     * @return The interpolated value
     */
    public static double lerpLinear(final double current, final double to, final double step) {
        return (1 - step) * current + step * to;
    }

    /**
     * Cubic in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpCubicIn(double start, double end, double time) {
        double duration = 1;
        return end * (time /= duration) * time * time + start;
    }

    /**
     * Cubic out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpCubicOut(double start, double end, double time) {
        double duration = 1;
        return end * ((time /= duration - 1) * time * time + 1) + start;
    }

    /**
     * Cubic in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpCubic(double start, double end, double time) {
        double duration = 1;
        if ((time /= duration / 2) < 1) return (end / 2 * time * time * time + start);
        return (end / 2 * ((time -= 2) * time * time + 2) + start);
    }

    /**
     * Bounce out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpBounceOut(double start, double end, double time) {
        double duration = 1;
        if ((time /= duration) < 1 / 2.75) {
            return end * (7.5625 * time * time) + start;
        } else if (time < (2 / 2.75)) {
            return end * (7.5625 * (time -= (1.5 / 2.75)) * time + 0.75) + start;
        } else if (time < (2.5 / 2.75)) {
            return end * (7.5625 * (time -= (2.25 / 2.75)) * time + 0.9375) + start;
        } else {
            return end * (7.5625 * (time -= (2.625 / 2.75)) * time + 0.984375) + start;
        }
    }

    /**
     * Bounce in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpBounceIn(final double start, final double end, final double time) {
        double duration = 1;
        return end - lerpBounceOut(0, end, duration - time) + start;
    }

    /**
     * Bounce in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpBounce(double start, double end, double time) {
        double duration = 1;
        if (time < duration / 2) return lerpBounceIn(0, end,  time * 2) * 0.5 + start;
        return lerpBounceOut(0, end, time * 2 - duration) * 0.5 + end * 0.5 + start;
    }

    /**
     * Circ in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpCircIn(double start, double end, double time) {
        double duration = 1;
        return -end * (Math.sqrt(1 - (time /= duration) * time) - 1) + start;
    }

    /**
     * Circ out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpCircOut(double start, double end, double time) {
        double duration = 1;
        return end * Math.sqrt(1 - (time /= duration - 1) * time) + start;
    }

    /**
     * Circ in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpCirc(double start, double end, double time) {
        double duration = 1;
        if ((time /= duration / 2) < 1) return -end / 2 * (Math.sqrt(1 - time * time) - 1) + start;
        return end / 2 * (Math.sqrt(1 - (time -= 2) * time) + 1) + start;
    }

    /**
     * Expo in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpExpoIn(double start, double end, double time) {
        double duration = 1;
        return (time == 0) ? start : end * Math.pow(2, 10 * (time / duration - 1)) + start;
    }

    /**
     * Expo out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpExpoOut(double start, double end, double time) {
        double duration = 1;
        return (time == duration) ? start + end : end * (-Math.pow(2, -10 * time / duration) + 1) + start;
    }

    /**
     * Expo in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpExpo(double start, double end, double time) {
        double duration = 1;
        if (time == 0) return start;
        if (time == duration) return start + end;
        if ((time /= duration / 2) < 1) return end / 2 * Math.pow(2, 10 * (time - 1)) + start;
        return end / 2 * (-Math.pow(2, -10 * --time) + 2) + start;
    }

    /**
     * Quad in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuadIn(double start, double end, double time) {
        double duration = 1;
        return end * (time /= duration) * time + start;
    }

    /**
     * Quad out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuadOut(double start, double end, double time) {
        double duration = 1;
        return -end * (time /= duration) * (time - 2) + start;
    }

    /**
     * Quad in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuad(double start, double end, double time) {
        double duration = 1;
        if ((time /= duration / 2) < 1) return end / 2 * time * time + start;
        return -end / 2 * ((--time) * (time - 2) - 1) + start;
    }

    /**
     * Quart in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuartIn(double start, double end, double time) {
        double duration = 1;
        return end * (time /= duration) * time * time * time + start;
    }

    /**
     * Quart out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuartOut(double start, double end, double time) {
        double duration = 1;
        return -end * ((time /= duration - 1) * time * time * time - 1) + start;
    }

    /**
     * Quart in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuart(double start, double end, double time) {
        double duration = 1;
        if ((time /= duration / 2) < 1) return end / 2 * time * time * time * time + start;
        return -end / 2 * ((time -= 2) * time * time * time - 2) + start;
    }

    /**
     * Quint in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuintIn(double start, double end, double time) {
        double duration = 1;
        return end * (time /= duration) * time * time * time * time + start;
    }

    /**
     * Quint out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuintOut(double start, double end, double time) {
        double duration = 1;
        return end * ((time /= duration - 1) * time * time * time * time + 1) + start;
    }

    /**
     * Quint in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpQuint(double start, double end, double time) {
        double duration  = 1;
        if ((time /= duration / 2) < 1) return end / 2 * time * time * time * time * time + start;
        return end / 2 * ((time -= 2) * time * time * time * time + 2) + start;
    }

    /**
     * Sine in interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpSineIn(double start, double end, double time) {
        double duration = 1;
        return -end * Math.cos(time / duration * (Math.PI / 2)) + end + start;
    }

    /**
     * Sine out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpSineOut(double start, double end, double time) {
        double duration = 1;
        return end * Math.sin(time / duration * (Math.PI / 2)) + start;
    }

    /**
     * Sine in and out interpolation of a value
     * @param start The start value
     * @param end The end value
     * @param time The time of interpolation
     * @return The interpolated value
     */
    public static double lerpSine(double start, double end, double time) {
        double duration = 1;
        return  -end / 2 * (Math.cos(Math.PI * time / duration) - 1) + start;
    }
}
