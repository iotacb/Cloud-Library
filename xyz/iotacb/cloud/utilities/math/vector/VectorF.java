package xyz.iotacb.cloud.utilities.math.vector;

import java.util.concurrent.ThreadLocalRandom;

public class VectorF {
	
	public float x, y, z;
	
	static ThreadLocalRandom random = ThreadLocalRandom.current();
	
	void setupVector(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public VectorF() {}
	
	public VectorF(final float x) {
		setupVector(x, 0, 0);
	}
	
	public VectorF(final float x, final float y) {
		setupVector(x, y, 0);
	}
	
	public VectorF(final float x, final float y, final float z) {
		setupVector(x, y, z);
	}
	
	public VectorF(final VectorF vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public VectorF(final VectorI vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public VectorF(final VectorD vector) {
		setupVector((float)vector.x, (float)vector.y, (float)vector.z);
	}
	
	public static VectorF random1D(final float xMin, final float xMax) {
		float x = (float)random.nextDouble(xMin, xMax);
		return new VectorF(x);
	}
	
	public static VectorF random2D(final float xMin, final float xMax, final float yMin, final float yMax) {
		float x = (float)random.nextDouble(xMin, xMax);
		float y = (float)random.nextDouble(yMin, yMax);
		return new VectorF(x, y);
	}
	
	public static VectorF random3D(final float xMin, final float xMax, final float yMin, final float yMax, final float zMin, final float zMax) {
		float x = (float)random.nextDouble(xMin, xMax);
		float y = (float)random.nextDouble(yMin, yMax);
		float z = (float)random.nextDouble(zMin, zMax);
		return new VectorF(x, y, z);
	}
	
	public VectorF set(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public VectorF set(final float x, final float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public VectorF set(final float x) {
		this.x = x;
		return this;
	}
	
	public VectorF set(final VectorF vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	public VectorF add(final float x, final float y, final float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public VectorF add(final float x, final float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public VectorF add(final float x) {
		this.x += x;
		return this;
	}
	
	public VectorF add(final VectorF vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	public VectorF sub(final float x, final float y, final float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	public VectorF sub(final float x, final float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public VectorF sub(final float x) {
		this.x -= x;
		return this;
	}
	
	public VectorF sub(final VectorF vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	public VectorF mult(final float x, final float y, final float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	public VectorF mult(final float x, final float y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	public VectorF mult(final float x) {
		this.x *= x;
		return this;
	}
	
	public VectorF multAll(final float n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	public VectorF mult(final VectorF vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	public VectorF div(final float x, final float y, final float z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	public VectorF div(final float x, final float y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	public VectorF div(final float x) {
		this.x /= x;
		return this;
	}
	
	public VectorF divAll(final float n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}
	
	public VectorF div(final VectorF vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public float dot(final float x, final float y, final float z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public float dot(final float x, final float y) {
		return this.x * x + this.y * y;
	}
	
	public float dot(final VectorF vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static float dot(final VectorF vector, final VectorF vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}
	
	public VectorF cross(final VectorF vector) {
		float xCross = this.y * vector.z - vector.y * this.z;
		float yCross = this.z * vector.x - vector.z * this.x;
		float zCross = this.x * vector.y - vector.x * this.y;
		
		return new VectorF(xCross, yCross, zCross);
	}
	
	public static VectorF cross(final VectorF vector, final VectorF vector2) {
		float xCross = vector.y * vector2.z - vector2.y * vector.z;
		float yCross = vector.z * vector2.x - vector2.z * vector.x;
		float zCross = vector.x * vector2.y - vector2.x * vector.y;
		
		return new VectorF(xCross, yCross, zCross);
	}
	
	public static VectorF fromAngle(final float angle) {
		float yaw = (float)Math.cos(angle);
		float pitch = (float)Math.sin(angle);
		return new VectorF(yaw, pitch);
	}
	
	public float mag() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public float magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public VectorF copy() {
		return new VectorF(this);
	}
	
	public VectorF normalize() {
		float mag = mag();
		
		if (mag != 0 && mag != 1) {
			div(mag);
		}
		
		return this;
	}
	
	public VectorF limit(final float max) {
		if (mag() > max * max) {
			normalize();
			multAll(max);
		}
		return this;
	}
	
	public VectorF constrain(final float xMin, final float xMax, final float yMin, final float yMax, final float zMin, final float zMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		this.z = (this.z < zMin ? zMin : this.z > zMax ? zMax : this.z);
		return this;
	}
	
	public VectorF constrain(final float xMin, final float xMax, final float yMin, final float yMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		return this;
	}
	
	public VectorF constrain(final float xMin, final float xMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		return this;
	}
	
	public VectorF constrain(final VectorF min, final VectorF max) {
		return constrain(min.x, max.x, min.y, max.y, min.z, max.z);
	}
	
	public VectorF setMag(final float mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	public float heading() {
		return (float)Math.atan2(this.y, this.x);
	}
	
	public static float heading(final VectorF vector) {
		return (float)Math.atan2(vector.y, vector.x);
	}
	
	public static float diff(final VectorF vector, final VectorF vector2) {
		float xDiff = Math.abs(vector2.x - vector.x);
		float yDiff = Math.abs(vector2.y - vector.y);
		float zDiff = Math.abs(vector2.z - vector.z);
		
		return (float)Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
	}
	
	public float diff(final VectorF vector) {
		return diff(this, vector);
	}
	
	public static float getScalar(final VectorF vector, final VectorF vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public float getScalar(final VectorF vector) {
		return getScalar(this, vector);
	}
	
	public VectorF makePositive() {
		float x = Math.abs(this.x);
		float y = Math.abs(this.y);
		float z = Math.abs(this.z);
		return new VectorF(x, y, z);
	}
	
	public VectorF makeNegative() {
		float x = Math.abs(this.x) * -1;
		float y = Math.abs(this.y) * -1;
		float z = Math.abs(this.z) * -1;
		return new VectorF(x, y, z);
	}
	
	public VectorI makeInteger() {
		return new VectorI(this);
	}
	
	public VectorD makeDouble() {
		return new VectorD(this);
	}
	
	public static VectorF createVector(final int value) {
		return new VectorF(value, value, value);
	}
	
	public static VectorF createVector(final float value) {
		return new VectorF(value, value, value);
	}
	
	public static VectorF createVector(final double value) {
		return new VectorF((float)value, (float)value, (float)value);
	}
	
	public static VectorF empty() {
		return new VectorF(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
