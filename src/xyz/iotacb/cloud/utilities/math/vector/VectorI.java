package xyz.iotacb.cloud.utilities.math.vector;

import xyz.iotacb.cloud.utilities.math.Random;

public class VectorI {
	
	public int x, y, z;
	
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
	
	/**
	 * Generate a random one dimensional vector
	 * @param xMin
	 * @param xMax
	 * @return
	 */
	public static VectorI random1D(final int xMin, final int xMax) {
		int x = Random.randomInt(xMin, xMax);
		return new VectorI(x);
	}
	
	/**
	 * Generate a random two dimensional vector
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return
	 */
	public static VectorI random2D(final int xMin, final int xMax, final int yMin, final int yMax) {
		int x = Random.randomInt(xMin, xMax);
		int y = Random.randomInt(yMin, yMax);
		return new VectorI(x, y);
	}
	
	/**
	 * Generate a random three dimensional vector
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @param zMin
	 * @param zMax
	 * @return
	 */
	public static VectorI random3D(final int xMin, final int xMax, final int yMin, final int yMax, final int zMin, final int zMax) {
		int x = Random.randomInt(xMin, xMax);
		int y = Random.randomInt(yMin, yMax);
		int z = Random.randomInt(zMin, zMax);
		return new VectorI(x, y, z);
	}
	
	/**
	 * Set the x, y and z axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI set(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**
	 * Set the x and y axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI set(final int x, final int y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Set the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI set(final int x) {
		this.x = x;
		return this;
	}
	
	/**
	 * Set the vector to a another one
	 * @param vector
	 * @return
	 */
	public VectorI set(final VectorI vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	/**
	 * Set the vector to a another one
	 * @param vector
	 * @return
	 */
	public static VectorI set(final VectorI vector, final VectorI vector2) {
		return vector.set(vector2);
	}
	
	/**
	 * Add a the x, y and z axis
	 * @param x
	 * @return
	 */
	public VectorI add(final int x, final int y, final int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	/**
	 * Add a the x and y axis
	 * @param x
	 * @return
	 */
	public VectorI add(final int x, final int y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	/**
	 * Add a the x axis
	 * @param x
	 * @return
	 */
	public VectorI add(final int x) {
		this.x += x;
		return this;
	}
	
	/**
	 * Add a vector to a another one
	 * @param vector
	 * @return
	 */
	public VectorI add(final VectorI vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	/**
	 * Add a vector to a another one
	 * @param vector
	 * @return
	 */
	public static VectorI add(final VectorI vector, final VectorI vector2) {
		return vector.add(vector2);
	}
	
	/**
	 * Subtract the x,y and z axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI sub(final int x, final int y, final int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	/**
	 * Subtract the x and y axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI sub(final int x, final int y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	/**
	 * Subtract the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI sub(final int x) {
		this.x -= x;
		return this;
	}
	
	/**
	 * Subtract a vector with a another vector
	 * @param vector
	 * @return
	 */
	public VectorI sub(final VectorI vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	/**
	 * Subtract a vector with a another vector
	 * @param vector
	 * @return
	 */
	public static VectorI sub(final VectorI vector, final VectorI vector2) {
		return vector.sub(vector2);
	}
	
	/**
	 * Multiply the x, y and axis of the vector
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public VectorI mult(final int x, final int y, final int z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	/**
	 * Multiply the x and y axis of the vector
	 * @param x
	 * @param y
	 * @return
	 */
	public VectorI mult(final int x, final int y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	/**
	 * Multiply the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorI mult(final int x) {
		this.x *= x;
		return this;
	}
	
	/**
	 * Multiply all axes of the vector
	 * @param n
	 * @return
	 */
	public VectorI multAll(final int n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	/**
	 * Multiply the vector with a another vector
	 * @param x
	 * @return
	 */
	public VectorI mult(final VectorI vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	/**
	 * Multiply the vector with a another vector
	 * @param x
	 * @return
	 */
	public static VectorI mult(final VectorI vector, final VectorI vector2) {
		return vector.mult(vector2);
	}
	
	/**
	 * Divide the x, y and z axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorI div(final int x, final int y, final int z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	/**
	 * Divide the x and y axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorI div(final int x, final int y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	/**
	 * Divide the x axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorI div(final int x) {
		this.x /= x;
		return this;
	}
	
	/**
	 * Divide all axes of the vector
	 * @param n
	 * @return
	 */
	public VectorI divAll(final int n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}
	
	/**
	 * Divide the vector with a another one
	 * @param vector
	 * @return
	 */
	public VectorI div(final VectorI vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	/**
	 * Divide the vector with a another one
	 * @param vector
	 * @return
	 */
	public static VectorI div(final VectorI vector, final VectorI vector2) {
		return vector.div(vector2);
	}
	
	/**
	 * Dot two locations with each other
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public int dot(final int x, final int y, final int z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public int dot(final int x, final int y) {
		return this.x * x + this.y * y;
	}
	//
	
	/**
	 * Dot two vectors with each other
	 * @param vector
	 * @return
	 */
	public int dot(final VectorI vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static int dot(final VectorI vector, final VectorI vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}
	//
	
	/**
	 * Cross two vectors with each other
	 * @param vector
	 * @param vector2
	 * @return
	 */
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
	//
	
	/**
	 * Generate a vector from an angle
	 * @param angle
	 * @return
	 */
	public static VectorI fromAngle(final int angle) {
		int yaw = (int)Math.cos(angle);
		int pitch = (int)Math.sin(angle);
		return new VectorI(yaw, pitch);
	}
	
	/**
	 * Get the magnitude of the vector
	 * @return
	 */
	public int mag() {
		return (int)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Get the squared magnitude of the vector
	 * @return
	 */
	public int magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Make a copy of the vector
	 * @return
	 */
	public VectorI copy() {
		return new VectorI(this);
	}
	
	/**
	 * Normalize the vector
	 * @return
	 */
	public VectorI normalize() {
		int mag = mag();
		
		if (mag != 0 && mag != 1) {
			div(mag);
		}
		
		return this;
	}
	
	/**
	 * Limit the vector
	 * @param max
	 * @return
	 */
	public VectorI limit(final int max) {
		if (mag() > max * max) {
			normalize();
			multAll(max);
		}
		return this;
	}
	
	/**
	 * Lock the vector axes in a range
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @param zMin
	 * @param zMax
	 * @return
	 */
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
	//
	
	/**
	 * Mirror the vector axes
	 * @return
	 */
	public VectorI mirror() {
		return new VectorI(this.x * -1, this.y * -1, this.z * -1);
	}
	
	/**
	 * Set the magnitude of the vector
	 * @param mag
	 * @return
	 */
	public VectorI setMag(final int mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	/**
	 * Get the heading direction of the vector
	 * @return
	 */
	public int heading() {
		return (int)Math.atan2(this.y, this.x);
	}
	
	/**
	 * Get the heading direction of a vector
	 * @param vector
	 * @return
	 */
	public static int heading(final VectorI vector) {
		return (int)Math.atan2(vector.y, vector.x);
	}
	
	/**
	 * Get the diffference between two vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static int diff(final VectorI vector, final VectorI vector2) {
		int xDiff = Math.abs(vector2.x - vector.x);
		int yDiff = Math.abs(vector2.y - vector.y);
		int zDiff = Math.abs(vector2.z - vector.z);
		
		return (int)Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
	}
	
	public int diff(final VectorI vector) {
		return diff(this, vector);
	}
	//
	
	/**
	 * Get the scalar between two vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static int getScalar(final VectorI vector, final VectorI vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public int getScalar(final VectorI vector) {
		return getScalar(this, vector);
	}
	//
	
	/**
	 * Get the distance to an another vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double dist(final VectorI vector, final VectorI vector2) {
		double diffX = vector.x - vector2.x;
		double diffY = vector.y - vector2.y;
		double diffZ = vector.z - vector2.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(final VectorI vector) {
		return dist(this, vector);
	}
	//
	
	/**
	 * Make all axes of the vector positive
	 * @return
	 */
	public VectorI makePositive() {
		int x = Math.abs(this.x);
		int y = Math.abs(this.y);
		int z = Math.abs(this.z);
		return new VectorI(x, y, z);
	}
	
	/**
	 * Make all axes of the vector negative
	 * @return
	 */
	public VectorI makeNegative() {
		int x = Math.abs(this.x) * -1;
		int y = Math.abs(this.y) * -1;
		int z = Math.abs(this.z) * -1;
		return new VectorI(x, y, z);
	}
	
	/**
	 * Convert the Integer vector to a Double vector
	 * @return
	 */
	public VectorD makeDouble() {
		return new VectorD(this);
	}
	
	/**
	 * Convert the Integer vector to a Float vector
	 * @return
	 */
	public VectorF makeFloat() {
		return new VectorF(this);
	}
	
	/**
	 * Get the center of a vector
	 * @return
	 */
	public VectorI getCenter() {
		return new VectorI(this.x / 2, this.y / 2, this.z / 2);
	}
	
	/**
	 * Checks if the location is out of range
	 * @param minX
	 * @param maxX
	 * @return
	 */
	public boolean outOfRange(final double minX, final double maxX) {
		return (this.x < minX || this.x > maxX);
	}
	
	public boolean outOfRange(final double minX, final double maxX, final double minY, final double maxY) {
		return(outOfRange(minX, maxX) || outOfRange(minY, maxY));
	}
	
	public boolean outOfRange(final double minX, final double maxX, final double minY, final double maxY, final double minZ, final double maxZ) {
		return(outOfRange(minX, maxX) || outOfRange(minY, maxY) || outOfRange(minZ, maxZ));
	}
	
	public boolean outOfRange(final VectorI min, final VectorI max) {
		return (outOfRange(min.x, max.x, max.z, max.y));
	}
	//
	
	/**
	 * Create a vector with same axes
	 * @param value
	 * @return
	 */
	public static VectorI createVector(final int value) {
		return new VectorI(value, value, value);
	}
	
	public static VectorI createVector(final float value) {
		return new VectorI((int)value, (int)value, (int)value);
	}
	
	public static VectorI createVector(final double value) {
		return new VectorI((int)value, (int)value, (int)value);
	}
	//
	
	/**
	 * Create a empty vector
	 * @return
	 */
	public static VectorI empty() {
		return new VectorI(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
