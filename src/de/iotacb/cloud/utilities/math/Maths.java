package de.iotacb.cloud.utilities.math;

import de.iotacb.cloud.utilities.math.vector.VectorD;
import de.iotacb.cloud.utilities.math.vector.VectorI;

public class Maths {

	public static double PI = Math.PI, TAU = Math.PI * 2;

	public static int sign(double value) {
		return (value > 0 ? 1 : 0);
	}

	public static double max(double firstValue, double secondValue) {
		return (firstValue > secondValue ? firstValue : secondValue);
	}

	public static int max(int firstValue, int secondValue) {
		return (firstValue > secondValue ? firstValue : secondValue);
	}

	public static double min(double firstValue, double secondValue) {
		return (firstValue < secondValue ? firstValue : secondValue);
	}

	public static int min(int firstValue, int secondValue) {
		return (firstValue < secondValue ? firstValue : secondValue);
	}

	public static double clamp(double value, double min, double max) {
		return (value > max ? max : value < min ? min : value);
	}

	public static int clamp(int value, int min, int max) {
		return (value > max ? max : value < min ? min : value);
	}

	public static double direction(double firstX, double firstY, double secondX, double secondY) {
		double angle = Math.toDegrees(Math.atan2(secondY - firstY, secondX - firstX)) + 90;
		if (angle < 0) {
			angle += 360;
		}
		return angle;
	}

	public static double direction(VectorD firstLocation, VectorD secondLocation) {
		double angle = Math
				.toDegrees(Math.atan2(secondLocation.y - firstLocation.y, secondLocation.x - firstLocation.x)) + 90;
		if (angle < 0) {
			angle += 360;
		}
		return angle;
	}
	
	public static double direction(VectorI firstLocation, VectorD secondLocation) {
		double angle = Math
				.toDegrees(Math.atan2(secondLocation.y - firstLocation.y, secondLocation.x - firstLocation.x)) + 90;
		if (angle < 0) {
			angle += 360;
		}
		return angle;
	}

	public static double lengthDirX(double value, double direction) {
		return Math.cos(Math.toRadians(direction)) * value;
	}

	public static double lengthDirY(double value, double direction) {
		return Math.sin(Math.toRadians(direction)) * value;
	}

	public static double dist(double firstX, double firstY, double secondX, double secondY) {
		double diffX = secondX - firstX;
		double diffY = secondY - firstY;
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}

	public static double dist(VectorD firstLocation, VectorD secondLocation) {
		double diffX = secondLocation.x - firstLocation.x;
		double diffY = secondLocation.y - firstLocation.y;
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}
	
	public static double dist(VectorI firstLocation, VectorI secondLocation) {
		double diffX = secondLocation.x - firstLocation.x;
		double diffY = secondLocation.y - firstLocation.y;
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}

	public static double length(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}

	public static double length(VectorD location) {
		return length(location.x, location.y);
	}
	
	public static double length(VectorI location) {
		return length(location.x, location.y);
	}

	public static double approach(double currentValue, double finalValue, double stepValue) {
		if (currentValue < finalValue) {
			currentValue += stepValue;
			if (currentValue > finalValue) {
				return finalValue;
			}
		} else {
			currentValue -= stepValue;
			if (currentValue < finalValue) {
				return finalValue;
			}
		}
		return currentValue;
	}

	public static double average(double... values) {
		double average = 0;
		for (double value : values) {
			average += value;
		}
		return (values.length > 0 ? average / values.length : average);
	}

	public static double wrap(double value, double min, double max) {
		if (value % 1 == 0) {
			while (value > max || value < min) {
				if (value > max) {
					value += min - max - 1;
				} else if (value < min) {
					value += max - min + 1;
				}
			}
			return value;
		} else {
			double oldValue = value + 1;
			while (value != oldValue) {
				oldValue = value;
				if (value < min) {
					value = max - (min - value);
				} else if (value > max) {
					value = min + (value - max);
				}
			}
			return value;
		}
	}

	public static double signedDistanceToCircle(int firstX, int firstY, int secondX, int secondY, int circleRadius) {
		double diffX = secondX - firstX;
		double diffY = secondY - firstY;
		return (length(diffX, diffY) - circleRadius / 2) * 2;
	}

	public static double signedDistanceToCircle(VectorD firstLocation, VectorD secondLocation, int circleRadius) {
		return signedDistanceToCircle((int) firstLocation.x, (int) firstLocation.y, (int) secondLocation.x,
				(int) secondLocation.y, circleRadius);
	}
	
	public static double signedDistanceToCircle(VectorI firstLocation, VectorI secondLocation, int circleRadius) {
		return signedDistanceToCircle(firstLocation.x, firstLocation.y, secondLocation.x,
				secondLocation.y, circleRadius);
	}


	public static double signedDistanceToRect(int firstX, int firstY, int secondX, int secondY, int width, int height) {
		double offsetX = Math.abs(firstX - secondX) - width / 2;
		double offsetY = Math.abs(firstY - secondY) - height / 2;

		double unsignedDistance = length(Math.max(offsetX, 0), Math.max(offsetY, 0)) * 2;
		double distanceInRect = Math.max(Math.min(offsetX, 0), Math.min(offsetY, 0)) * 2;

		return (unsignedDistance + distanceInRect);
	}

	public static double signedDistanceToRect(VectorD firstLocation, VectorD secondLocation, int width, int height) {
		return signedDistanceToRect((int) firstLocation.x, (int) firstLocation.y, (int) secondLocation.x,
				(int) secondLocation.y, width, height);
	}
	
	public static double signedDistanceToRect(VectorI firstLocation, VectorI secondLocation, int width, int height) {
		return signedDistanceToRect(firstLocation.x, firstLocation.y, secondLocation.x,
				secondLocation.y, width, height);
	}

	public static double signedDistanceToRect(VectorD firstLocation, VectorD secondLocation, VectorD size) {
		return signedDistanceToRect(firstLocation, secondLocation, (int) size.x, (int) size.y);
	}
	
	public static double signedDistanceToRect(VectorI firstLocation, VectorI secondLocation, VectorI size) {
		return signedDistanceToRect(firstLocation.getVectorDouble(), secondLocation.getVectorDouble(), size.getVectorDouble());
	}

	public static double map(double value, double istart, double istop, double ostart, double ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

}
