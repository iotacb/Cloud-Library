package xyz.iotacb.cloud.utilities.math.vector;

import xyz.iotacb.cloud.utilities.math.Random;

public class VectorF {
	
	public float x, y, z;
	
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
	
	/**
	 * Generate a random one dimensional vector
	 * @param xMin
	 * @param xMax
	 * @return
	 */
	public static VectorF random1D(final float xMin, final float xMax) {
		float x = Random.randomFloat(xMin, xMax);
		return new VectorF(x);
	}
	
	/**
	 * Generate a random two dimensional vector
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return
	 */
	public static VectorF random2D(final float xMin, final float xMax, final float yMin, final float yMax) {
		float x = Random.randomFloat(xMin, xMax);
		float y = Random.randomFloat(yMin, yMax);
		return new VectorF(x, y);
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
	public static VectorF random3D(final float xMin, final float xMax, final float yMin, final float yMax, final float zMin, final float zMax) {
		float x = Random.randomFloat(xMin, xMax);
		float y = Random.randomFloat(yMin, yMax);
		float z = Random.randomFloat(zMin, zMax);
		return new VectorF(x, y, z);
	}
	
	/**
	 * Set the x, y and z axis of the vector
	 * @param x
	 * @return
	 */
	public VectorF set(final float x, final float y, final float z) {
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
	public VectorF set(final float x, final float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Set the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorF set(final float x) {
		this.x = x;
		return this;
	}
	
	/**
	 * Set the vector to a another one
	 * @param vector
	 * @return
	 */
	public VectorF set(final VectorF vector) {
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
	public static VectorF set(final VectorF vector, final VectorF vector2) {
		return vector.set(vector2);
	}
	
	/**
	 * Add a the x, y and z axis
	 * @param x
	 * @return
	 */
	public VectorF add(final float x, final float y, final float z) {
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
	public VectorF add(final float x, final float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	/**
	 * Add a the x axis
	 * @param x
	 * @return
	 */
	public VectorF add(final float x) {
		this.x += x;
		return this;
	}
	
	/**
	 * Add a vector to a another one
	 * @param vector
	 * @return
	 */
	public VectorF add(final VectorF vector) {
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
	public static VectorF add(final VectorF vector, final VectorF vector2) {
		return vector.add(vector2);
	}
	
	/**
	 * Subtract the x,y and z axis of the vector
	 * @param x
	 * @return
	 */
	public VectorF sub(final float x, final float y, final float z) {
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
	public VectorF sub(final float x, final float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	/**
	 * Subtract the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorF sub(final float x) {
		this.x -= x;
		return this;
	}
	
	/**
	 * Subtract a vector with a another vector
	 * @param vector
	 * @return
	 */
	public VectorF sub(final VectorF vector) {
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
	public static VectorF sub(final VectorF vector, final VectorF vector2) {
		return vector.sub(vector2);
	}
	
	/**
	 * Multiply the x, y and axis of the vector
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public VectorF mult(final float x, final float y, final float z) {
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
	public VectorF mult(final float x, final float y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	/**
	 * Multiply the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorF mult(final float x) {
		this.x *= x;
		return this;
	}
	
	/**
	 * Multiply all axes of the vector
	 * @param n
	 * @return
	 */
	public VectorF multAll(final float n) {
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
	public VectorF mult(final VectorF vector) {
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
	public static VectorF mult(final VectorF vector, final VectorF vector2) {
		return vector.mult(vector2);
	}
	
	/**
	 * Divide the x, y and z axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorF div(final float x, final float y, final float z) {
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
	public VectorF div(final float x, final float y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	/**
	 * Divide the x axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorF div(final float x) {
		this.x /= x;
		return this;
	}
	
	/**
	 * Divide all axes of the vector
	 * @param n
	 * @return
	 */
	public VectorF divAll(final float n) {
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
	public VectorF div(final VectorF vector) {
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
	public static VectorF div(final VectorF vector, final VectorF vector2) {
		return vector.div(vector2);
	}
	
	/**
	 * Dot two locations with each other
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public float dot(final float x, final float y, final float z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public float dot(final float x, final float y) {
		return this.x * x + this.y * y;
	}
	//
	
	/**
	 * Dot two vectors with each other
	 * @param vector
	 * @return
	 */
	public float dot(final VectorF vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static float dot(final VectorF vector, final VectorF vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}
	//
	
	/**
	 * Cross two vectors with each other
	 * @param vector
	 * @param vector2
	 * @return
	 */
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
	//
	
	/**
	 * Generate a vector from an angle
	 * @param angle
	 * @return
	 */
	public static VectorF fromAngle(final float angle) {
		float yaw = (float)Math.cos(angle);
		float pitch = (float)Math.sin(angle);
		return new VectorF(yaw, pitch);
	}
	
	/**
	 * Get the magnitude of the vector
	 * @return
	 */
	public float mag() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Get the squared magnitude of the vector
	 * @return
	 */
	public float magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Make a copy of the vector
	 * @return
	 */
	public VectorF copy() {
		return new VectorF(this);
	}
	
	/**
	 * Normalize the vector
	 * @return
	 */
	public VectorF normalize() {
		float mag = mag();
		
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
	public VectorF limit(final float max) {
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
	//
	
	/**
	 * Mirror the vector axes
	 * @return
	 */
	public VectorF mirror() {
		return new VectorF(this.x * -1, this.y * -1, this.z * -1);
	}
	
	/**
	 * Set the magnitude of the vector
	 * @param mag
	 * @return
	 */
	public VectorF setMag(final float mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	/**
	 * Get the heading direction of the vector
	 * @return
	 */
	public float heading() {
		return (float)Math.atan2(this.y, this.x);
	}
	
	/**
	 * Get the heading direction of a vector
	 * @param vector
	 * @return
	 */
	public static float heading(final VectorF vector) {
		return (float)Math.atan2(vector.y, vector.x);
	}
	
	/**
	 * Get the diffference between two vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static float diff(final VectorF vector, final VectorF vector2) {
		float xDiff = Math.abs(vector2.x - vector.x);
		float yDiff = Math.abs(vector2.y - vector.y);
		float zDiff = Math.abs(vector2.z - vector.z);
		
		return (float)Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
	}
	
	public float diff(final VectorF vector) {
		return diff(this, vector);
	}
	//
	
	/**
	 * Get the scalar between two vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static float getScalar(final VectorF vector, final VectorF vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public float getScalar(final VectorF vector) {
		return getScalar(this, vector);
	}
	//
	
	/**
	 * Get the distance to an another vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double dist(final VectorF vector, final VectorF vector2) {
		double diffX = vector.x - vector2.x;
		double diffY = vector.y - vector2.y;
		double diffZ = vector.z - vector2.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(final VectorF vector) {
		return dist(this, vector);
	}
	//
	
	/**
	 * Make all axes of the vector positive
	 * @return
	 */
	public VectorF makePositive() {
		float x = Math.abs(this.x);
		float y = Math.abs(this.y);
		float z = Math.abs(this.z);
		return new VectorF(x, y, z);
	}
	
	/**
	 * Make all axes of the vector negative
	 * @return
	 */
	public VectorF makeNegative() {
		float x = Math.abs(this.x) * -1;
		float y = Math.abs(this.y) * -1;
		float z = Math.abs(this.z) * -1;
		return new VectorF(x, y, z);
	}
	
	/**
	 * Convert the Float vector to a Integer vector
	 * @return
	 */
	public VectorI makeInteger() {
		return new VectorI(this);
	}
	
	/**
	 * Convert the Float vector to a Double vector
	 * @return
	 */
	public VectorD makeDouble() {
		return new VectorD(this);
	}
	
	/**
	 * Get the center of a vector
	 * @return
	 */
	public VectorF getCenter() {
		return new VectorF(this.x / 2, this.y / 2, this.z / 2);
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
	
	public boolean outOfRange(final VectorF min, final VectorF max) {
		return (outOfRange(min.x, max.x, max.z, max.y));
	}
	//
	
	/**
	 * Randomize the vector
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 * @param minZ
	 * @param maxZ
	 */
	public void randomize(final float minX, final float maxX, final float minY, final float maxY, final float minZ, final float maxZ) {
		float x = Random.randomFloat(minX, maxX);
		float y = Random.randomFloat(minY, maxY);
		float z = Random.randomFloat(minZ, maxZ);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void randomize(final float minX, final float maxX, final float minY, final float maxY) {
		float x = Random.randomFloat(minX, maxX);
		float y = Random.randomFloat(minY, maxY);
		this.x = x;
		this.y = y;
	}
	
	public void randomize(final float minX, final float maxX) {
		float x = Random.randomFloat(minX, maxX);
		this.x = x;
	}
	
	public void randomize(final VectorF min, final VectorF max) {
		float x = Random.randomFloat(min.x, max.x);
		float y = Random.randomFloat(min.y, max.y);
		float z = Random.randomFloat(min.z, max.z);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	//
	
	/**
	 * Create a vector with same axes
	 * @param value
	 * @return
	 */
	public static VectorF createVector(final int value) {
		return new VectorF(value, value, value);
	}
	
	public static VectorF createVector(final float value) {
		return new VectorF(value, value, value);
	}
	
	public static VectorF createVector(final double value) {
		return new VectorF((float)value, (float)value, (float)value);
	}
	//
	
	/**
	 * Create a empty vector
	 * @return
	 */
	public static VectorF empty() {
		return new VectorF(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
