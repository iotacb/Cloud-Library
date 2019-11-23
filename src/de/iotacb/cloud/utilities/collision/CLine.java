package de.iotacb.cloud.utilities.collision;

import java.awt.Color;

import de.iotacb.cloud.utilities.math.vector.VectorD;
import de.iotacb.cloud.utilities.render.Render;

public class CLine {
	
	public int x1, y1, x2, y2;
	
	public Color intersectingColor;
	
	public CLine(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x2;
		this.y2 = y2;
		
		this.intersectingColor = Color.red;
	}
	
	public void draw() {
		Render.line(x1, y1, x2, y2, intersectingColor);
	}
	
	public double calcFunction() {
		return x1 * calcPitch() + calcShift();
	}
	
	public double calcPitch() {
		double diffB = (y2 - y1);
		double diffM = (x2 - x1);
		if (diffB == 0 || diffM == 0) {
			return 1;
		}
		return (y2 - y1) / (x2 - x1);
	}
	
	public double calcShift() {
		return y1 - calcPitch() * x1;
	}
	
	public boolean intersecting(CLine line) {
		this.intersectingColor = line.calcPitch() == calcPitch() ? Color.red : Color.green;
		line.intersectingColor = line.calcPitch() == calcPitch() ? Color.red : Color.green;
		return line.calcPitch() != calcPitch();
	}
	
	public VectorD crossPoint(CLine line) {
		double diffB = (line.calcShift() - calcShift());
		double diffM = (calcPitch() - line.calcPitch());
		if (diffM == 0 || diffB == 0) {
			return new VectorD();
		}
		double x = diffB / diffM;
		double y = calcPitch() * x + calcShift();
		return new VectorD(x, y);
	}

}
