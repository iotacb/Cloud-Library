package de.iotacb.cloud.utilities.collision;

import de.iotacb.cloud.utilities.math.Maths;
import de.iotacb.cloud.utilities.math.vector.VectorD;

public class Collision {
	
	public static boolean rect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		return (x1 <= x2 + w2 && x1 + w1 >= x2 && y1 <= y2 + h2 && y1 + h1 >= y2);
	}

	public static boolean circle(int x1, int y1, int r1, int x2, int y2, int r2) {
		r1 /= 2;
		r2 /= 2;
		return (Maths.dist(x1 + r1, y1 + r1, x2 + r2, y2 + r2) <= (r1 + r2));
	}
	
	public static boolean rectCircle(int x1, int y1, int w, int h, int x2, int y2, int r) {
		r /= 2;
		double dX = x2 + r - Math.max(x1, Math.min(x2 + r, x1 + w));
		double dY = y2 + r - Math.max(y1, Math.min(y2 + r, y1 + h));
		return (Math.pow(dX, 2) + Math.pow(dY, 2) <= Math.pow(r, 2));
	}
	

}
