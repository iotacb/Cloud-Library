package xyz.iotacb.cloud.utilities.math.vector;

import java.util.concurrent.ThreadLocalRandom;

public class VectorI {
	
	public int x, y, z;
	
	static ThreadLocalRandom random = ThreadLocalRandom.current();
	
	void setupVector(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public VectorI() {}
	
	public VectorI(final int x) {
		setupVector(x, 0, 0);
	}
	
	public VectorI(final int x, final int y) {
		setupVector(x, y, 0);
	}
	
	public VectorI(final int x, final int y, final int z) {
		setupVector(x, y, z);
	}
	
	public VectorI(final VectorI vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public VectorI(final VectorD vector) {
		setupVector((int)vector.x, (int)vector.y, (int)vector.z);
	}
	
	public VectorI(final VectorF vector) {
		setupVector((int)vector.x, (int)vector.y, (int)vector.z);
	}
	
	public static VectorI random1D(final int xMin, final int xMax) {
		int x = random.nextInt(xMin, xMax);
		return new VectorI(x);
	}
	
	public static VectorI random2D(final int xMin, final int xMax, final int yMin, final int yMax) {
		int x = random.nextInt(xMin, xMax);
		int y = random.nextInt(yMin, yMax);
		return new VectorI(x, y);
	}
	
	public static VectorI random3D(final int xMin, final int xMax, final int yMin, final int yMax, final int zMin, final int zMax) {
		int x = random.nextInt(xMin, xMax);
		int y = random.nextInt(yMin, yMax);
		int z = random.nextInt(zMin, zMax);
		return new VectorI(x, y, z);
	}
	
	public VectorI set(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public VectorI set(final int x, final int y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public VectorI set(final int x) {
		this.x = x;
		return this;
	}
	
	public VectorI set(final VectorI vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	public VectorI add(final int x, final int y, final int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public VectorI add(final int x, final int y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public VectorI add(final int x) {
		this.x += x;
		return this;
	}
	
	public VectorI add(final VectorI vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	public VectorI sub(final int x, final int y, final int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	public VectorI sub(final int x, final int y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public VectorI sub(final int x) {
		this.x -= x;
		return this;
	}
	
	public VectorI sub(final VectorI vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	public VectorI mult(final int x, final int y, final int z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	public VectorI mult(final int x, final int y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	public VectorI mult(final int x) {
		this.x *= x;
		return this;
	}
	
	public VectorI multAll(final int n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	public VectorI mult(final VectorI vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	public VectorI div(final int x, final int y, final int z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	public VectorI div(final int x, final int y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	public VectorI div(final int x) {
		this.x /= x;
		return this;
	}
	
	public VectorI divAll(final int n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}
	
	public VectorI div(final VectorI vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public int dot(final int x, final int y, final int z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public int dot(final int x, final int y) {
		return this.x * x + this.y * y;
	}
	
	public int dot(final VectorI vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static int dot(final VectorI vector, final VectorI vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}
	
	public VectorI cross(final VectorI vector) {
		int xCross = this.y * vector.z - vector.y * this.z;
		int yCross = this.z * vector.x - vector.z * this.x;
		int zCross = this.x * vector.y - vector.x * this.y;
		
		return new VectorI(xCross, yCross, zCross);
	}
	
	public static VectorI cross(final VectorI vector, final VectorI vector2) {
		int xCross = vector.y * vector2.z - vector2.y * vector.z;
		int yCross = vector.z * vector2.x - vector2.z * vector.x;
		int zCross = vector.x * vector2.y - vector2.x * vector.y;
		
		return new VectorI(xCross, yCross, zCross);
	}
	
	public static VectorI fromAngle(final int angle) {
		int yaw = (int)Math.cos(angle);
		int pitch = (int)Math.sin(angle);
		return new VectorI(yaw, pitch);
	}
	
	public int mag() {
		return (int)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public int magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public VectorI copy() {
		return new VectorI(this);
	}
	
	public VectorI normalize() {
		int mag = mag();
		
		if (mag != 0 && mag != 1) {
			div(mag);
		}
		
		return this;
	}
	
	public VectorI limit(final int max) {
		if (mag() > max * max) {
			normalize();
			multAll(max);
		}
		return this;
	}
	
	public VectorI constrain(final int xMin, final int xMax, final int yMin, final int yMax, final int zMin, final int zMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		this.z = (this.z < zMin ? zMin : this.z > zMax ? zMax : this.z);
		return this;
	}
	
	public VectorI constrain(final int xMin, final int xMax, final int yMin, final int yMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		return this;
	}
	
	public VectorI constrain(final int xMin, final int xMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		return this;
	}
	
	public VectorI constrain(final VectorI min, final VectorI max) {
		return constrain(min.x, max.x, min.y, max.y, min.z, max.z);
	}
	
	public VectorI setMag(final int mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	public int heading() {
		return (int)Math.atan2(this.y, this.x);
	}
	
	public static int heading(final VectorI vector) {
		return (int)Math.atan2(vector.y, vector.x);
	}
	
	public static int diff(final VectorI vector, final VectorI vector2) {
		int xDiff = Math.abs(vector2.x - vector.x);
		int yDiff = Math.abs(vector2.y - vector.y);
		int zDiff = Math.abs(vector2.z - vector.z);
		
		return (int)Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
	}
	
	public int diff(final VectorI vector) {
		return diff(this, vector);
	}
	
	public static int getScalar(final VectorI vector, final VectorI vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public int getScalar(final VectorI vector) {
		return getScalar(this, vector);
	}
	
	public VectorI makePositive() {
		int x = Math.abs(this.x);
		int y = Math.abs(this.y);
		int z = Math.abs(this.z);
		return new VectorI(x, y, z);
	}
	
	public VectorI makeNegative() {
		int x = Math.abs(this.x) * -1;
		int y = Math.abs(this.y) * -1;
		int z = Math.abs(this.z) * -1;
		return new VectorI(x, y, z);
	}
	
	public VectorD makeDouble() {
		return new VectorD(this);
	}
	
	public VectorF makeFloat() {
		return new VectorF(this);
	}
	
	public static VectorI createVector(final int value) {
		return new VectorI(value, value, value);
	}
	
	public static VectorI createVector(final float value) {
		return new VectorI((int)value, (int)value, (int)value);
	}
	
	public static VectorI createVector(final double value) {
		return new VectorI((int)value, (int)value, (int)value);
	}
	
	public static VectorI empty() {
		return new VectorI(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
