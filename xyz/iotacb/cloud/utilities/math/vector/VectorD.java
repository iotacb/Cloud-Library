package xyz.iotacb.cloud.utilities.math.vector;

import java.util.concurrent.ThreadLocalRandom;

public class VectorD {
	
	public double x, y, z;
	
	static ThreadLocalRandom random = ThreadLocalRandom.current();
	
	void setupVector(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public VectorD() {}
	
	public VectorD(final double x) {
		setupVector(x, 0, 0);
	}
	
	public VectorD(final double x, final double y) {
		setupVector(x, y, 0);
	}
	
	public VectorD(final double x, final double y, final double z) {
		setupVector(x, y, z);
	}
	
	public VectorD(final VectorD vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public VectorD(final VectorI vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public VectorD(final VectorF vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public static VectorD random1D(final double xMin, final double xMax) {
		double x = random.nextDouble(xMin, xMax);
		return new VectorD(x);
	}
	
	public static VectorD random2D(final double xMin, final double xMax, final double yMin, final double yMax) {
		double x = random.nextDouble(xMin, xMax);
		double y = random.nextDouble(yMin, yMax);
		return new VectorD(x, y);
	}
	
	public static VectorD random3D(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		double x = random.nextDouble(xMin, xMax);
		double y = random.nextDouble(yMin, yMax);
		double z = random.nextDouble(zMin, zMax);
		return new VectorD(x, y, z);
	}
	
	public VectorD set(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public VectorD set(final double x, final double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public VectorD set(final double x) {
		this.x = x;
		return this;
	}
	
	public VectorD set(final VectorD vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	public VectorD add(final double x, final double y, final double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public VectorD add(final double x, final double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public VectorD add(final double x) {
		this.x += x;
		return this;
	}
	
	public VectorD add(final VectorD vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	public VectorD sub(final double x, final double y, final double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	public VectorD sub(final double x, final double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public VectorD sub(final double x) {
		this.x -= x;
		return this;
	}
	
	public VectorD sub(final VectorD vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	public VectorD mult(final double x, final double y, final double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	public VectorD mult(final double x, final double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	public VectorD mult(final double x) {
		this.x *= x;
		return this;
	}
	
	public VectorD multAll(final double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	public VectorD mult(final VectorD vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	public VectorD div(final double x, final double y, final double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	public VectorD div(final double x, final double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	public VectorD div(final double x) {
		this.x /= x;
		return this;
	}
	
	public VectorD divAll(final double n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}
	
	public VectorD div(final VectorD vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public double dot(final double x, final double y, final double z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public double dot(final double x, final double y) {
		return this.x * x + this.y * y;
	}
	
	public double dot(final VectorD vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static double dot(final VectorD vector, final VectorD vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}
	
	public VectorD cross(final VectorD vector) {
		double xCross = this.y * vector.z - vector.y * this.z;
		double yCross = this.z * vector.x - vector.z * this.x;
		double zCross = this.x * vector.y - vector.x * this.y;
		
		return new VectorD(xCross, yCross, zCross);
	}
	
	public static VectorD cross(final VectorD vector, final VectorD vector2) {
		double xCross = vector.y * vector2.z - vector2.y * vector.z;
		double yCross = vector.z * vector2.x - vector2.z * vector.x;
		double zCross = vector.x * vector2.y - vector2.x * vector.y;
		
		return new VectorD(xCross, yCross, zCross);
	}
	
	public static VectorD fromAngle(final double angle) {
		double yaw = Math.cos(angle);
		double pitch = Math.sin(angle);
		return new VectorD(yaw, pitch);
	}
	
	public double mag() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public double magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public VectorD copy() {
		return new VectorD(this);
	}
	
	public VectorD normalize() {
		double mag = mag();
		
		if (mag != 0 && mag != 1) {
			div(mag);
		}
		
		return this;
	}
	
	public VectorD limit(final double max) {
		if (mag() > max * max) {
			normalize();
			multAll(max);
		}
		return this;
	}
	
	public VectorD constrain(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		this.z = (this.z < zMin ? zMin : this.z > zMax ? zMax : this.z);
		return this;
	}
	
	public VectorD constrain(final double xMin, final double xMax, final double yMin, final double yMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		return this;
	}
	
	public VectorD constrain(final double xMin, final double xMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		return this;
	}
	
	public VectorD constrain(final VectorD min, final VectorD max) {
		return constrain(min.x, max.x, min.y, max.y, min.z, max.z);
	}
	
	public VectorD setMag(final double mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	public double heading() {
		return Math.atan2(this.y, this.x);
	}
	
	public static double heading(final VectorD vector) {
		return Math.atan2(vector.y, vector.x);
	}
	
	public static double diff(final VectorD vector, final VectorD vector2) {
		double xDiff = Math.abs(vector2.x - vector.x);
		double yDiff = Math.abs(vector2.y - vector.y);
		double zDiff = Math.abs(vector2.z - vector.z);
		
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
	}
	
	public double diff(final VectorD vector) {
		return diff(this, vector);
	}
	
	public static double getScalar(final VectorD vector, final VectorD vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public double getScalar(final VectorD vector) {
		return getScalar(this, vector);
	}
	
	public static double dist(final VectorD vector, final VectorD vector2) {
		double diffX = vector.x - vector2.x;
		double diffY = vector.y - vector2.y;
		double diffZ = vector.z - vector2.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(final VectorD vector) {
		return dist(this, vector);
	}
	
	public VectorD makePositive() {
		double x = Math.abs(this.x);
		double y = Math.abs(this.y);
		double z = Math.abs(this.z);
		return new VectorD(x, y, z);
	}
	
	public VectorD makeNegative() {
		double x = Math.abs(this.x) * -1;
		double y = Math.abs(this.y) * -1;
		double z = Math.abs(this.z) * -1;
		return new VectorD(x, y, z);
	}
	
	public VectorI makeInteger() {
		return new VectorI(this);
	}
	
	public VectorF makeFloat() {
		return new VectorF(this);
	}
	
	public static VectorD createVector(final int value) {
		return new VectorD(value, value, value);
	}
	
	public static VectorD createVector(final float value) {
		return new VectorD(value, value, value);
	}
	
	public static VectorD createVector(final double value) {
		return new VectorD(value, value, value);
	}
	
	public static VectorD empty() {
		return new VectorD(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
