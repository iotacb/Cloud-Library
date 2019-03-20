package xyz.iotacb.cloud.utilities.math.vector;

import xyz.iotacb.cloud.utilities.math.Random;

public class VectorD {
	
	public double x, y, z;
	
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
	
	/**
	 * Generate a random one dimensional vector
	 * @param xMin
	 * @param xMax
	 * @return
	 */
	public static VectorD random1D(final double xMin, final double xMax) {
		double x = Random.randomDouble(xMin, xMax);
		return new VectorD(x);
	}
	
	/**
	 * Generate a random two dimensional vector
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return
	 */
	public static VectorD random2D(final double xMin, final double xMax, final double yMin, final double yMax) {
		double x = Random.randomDouble(xMin, xMax);
		double y = Random.randomDouble(yMin, yMax);
		return new VectorD(x, y);
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
	public static VectorD random3D(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		double x = Random.randomDouble(xMin, xMax);
		double y = Random.randomDouble(yMin, yMax);
		double z = Random.randomDouble(zMin, zMax);
		return new VectorD(x, y, z);
	}
	
	/**
	 * Set the x, y and z axis of the vector
	 * @param x
	 * @return
	 */
	public VectorD set(final double x, final double y, final double z) {
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
	public VectorD set(final double x, final double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Set the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorD set(final double x) {
		this.x = x;
		return this;
	}
	
	/**
	 * Set the vector to a another one
	 * @param vector
	 * @return
	 */
	public VectorD set(final VectorD vector) {
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
	public static VectorD set(final VectorD vector, final VectorD vector2) {
		return vector.sub(vector2);
	}
	
	/**
	 * Add a the x, y and z axis
	 * @param x
	 * @return
	 */
	public VectorD add(final double x, final double y, final double z) {
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
	public VectorD add(final double x, final double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	/**
	 * Add a the x axis
	 * @param x
	 * @return
	 */
	public VectorD add(final double x) {
		this.x += x;
		return this;
	}
	
	/**
	 * Add a vector to a another one
	 * @param vector
	 * @return
	 */
	public VectorD add(final VectorD vector) {
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
	public static VectorD add(final VectorD vector, final VectorD vector2) {
		return vector.add(vector2);
	}
	
	/**
	 * Subtract the x,y and z axis of the vector
	 * @param x
	 * @return
	 */
	public VectorD sub(final double x, final double y, final double z) {
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
	public VectorD sub(final double x, final double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	/**
	 * Subtract the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorD sub(final double x) {
		this.x -= x;
		return this;
	}
	
	/**
	 * Subtract a vector with a another vector
	 * @param vector
	 * @return
	 */
	public VectorD sub(final VectorD vector) {
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
	public static VectorD sub(final VectorD vector, final VectorD vector2) {
		return vector.sub(vector2);
	}
	
	/**
	 * Multiply the x, y and axis of the vector
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public VectorD mult(final double x, final double y, final double z) {
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
	public VectorD mult(final double x, final double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	/**
	 * Multiply the x axis of the vector
	 * @param x
	 * @return
	 */
	public VectorD mult(final double x) {
		this.x *= x;
		return this;
	}
	
	/**
	 * Multiply all axes of the vector
	 * @param n
	 * @return
	 */
	public VectorD multAll(final double n) {
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
	public VectorD mult(final VectorD vector) {
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
	public static VectorD mult(final VectorD vector, final VectorD vector2) {
		return vector.mult(vector2);
	}
	
	/**
	 * Divide the x, y and z axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorD div(final double x, final double y, final double z) {
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
	public VectorD div(final double x, final double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	/**
	 * Divide the x axis of the vector 
	 * @param x
	 * @return
	 */
	public VectorD div(final double x) {
		this.x /= x;
		return this;
	}
	
	/**
	 * Divide all axes of the vector
	 * @param n
	 * @return
	 */
	public VectorD divAll(final double n) {
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
	public VectorD div(final VectorD vector) {
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
	public static VectorD div(final VectorD vector, final VectorD vector2) {
		return vector.div(vector2);
	}
	
	/**
	 * Dot two locations with each other
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public double dot(final double x, final double y, final double z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public double dot(final double x, final double y) {
		return this.x * x + this.y * y;
	}
	//
	
	/**
	 * Dot two vectors with each other
	 * @param vector
	 * @return
	 */
	public double dot(final VectorD vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static double dot(final VectorD vector, final VectorD vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}
	//
	
	/**
	 * Cross two vectors with each other
	 * @param vector
	 * @param vector2
	 * @return
	 */
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
	//
	
	/**
	 * Generate a vector from an angle
	 * @param angle
	 * @return
	 */
	public static VectorD fromAngle(final double angle) {
		double yaw = Math.cos(angle);
		double pitch = Math.sin(angle);
		return new VectorD(yaw, pitch);
	}
	
	/**
	 * Get the magnitude of the vector
	 * @return
	 */
	public double mag() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Get the squared magnitude of the vector
	 * @return
	 */
	public double magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Make a copy of the vector
	 * @return
	 */
	public VectorD copy() {
		return new VectorD(this);
	}
	
	/**
	 * Normalize the vector
	 * @return
	 */
	public VectorD normalize() {
		double mag = mag();
		
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
	public VectorD limit(final double max) {
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
	//
	
	/**
	 * Mirror the vector axes
	 * @return
	 */
	public VectorD mirror() {
		return new VectorD(this.x * -1, this.y * -1, this.z * -1);
	}
	
	/**
	 * Set the magnitude of the vector
	 * @param mag
	 * @return
	 */
	public VectorD setMag(final double mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	/**
	 * Get the heading direction of the vector
	 * @return
	 */
	public double heading() {
		return Math.atan2(this.y, this.x);
	}
	
	/**
	 * Get the heading direction of a vector
	 * @param vector
	 * @return
	 */
	public static double heading(final VectorD vector) {
		return Math.atan2(vector.y, vector.x);
	}
	
	/**
	 * Get the diffference between two vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double diff(final VectorD vector, final VectorD vector2) {
		double xDiff = Math.abs(vector2.x - vector.x);
		double yDiff = Math.abs(vector2.y - vector.y);
		double zDiff = Math.abs(vector2.z - vector.z);
		
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
	}
	
	public double diff(final VectorD vector) {
		return diff(this, vector);
	}
	//
	
	/**
	 * Get the scalar between two vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double getScalar(final VectorD vector, final VectorD vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public double getScalar(final VectorD vector) {
		return getScalar(this, vector);
	}
	//
	
	/**
	 * Get the distance to an another vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double dist(final VectorD vector, final VectorD vector2) {
		double diffX = vector.x - vector2.x;
		double diffY = vector.y - vector2.y;
		double diffZ = vector.z - vector2.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(final VectorD vector) {
		return dist(this, vector);
	}
	//
	
	/**
	 * Make all axes of the vector positive
	 * @return
	 */
	public VectorD makePositive() {
		double x = Math.abs(this.x);
		double y = Math.abs(this.y);
		double z = Math.abs(this.z);
		return new VectorD(x, y, z);
	}
	
	/**
	 * Make all axes of the vector negative
	 * @return
	 */
	public VectorD makeNegative() {
		double x = Math.abs(this.x) * -1;
		double y = Math.abs(this.y) * -1;
		double z = Math.abs(this.z) * -1;
		return new VectorD(x, y, z);
	}
	
	/**
	 * Convert the Double vector to a Integer vector
	 * @return
	 */
	public VectorI makeInteger() {
		return new VectorI(this);
	}
	
	/**
	 * Convert the Double vector to a Float vector
	 * @return
	 */
	public VectorF makeFloat() {
		return new VectorF(this);
	}
	
	/**
	 * Get the center of a vector
	 * @return
	 */
	public VectorD getCenter() {
		return new VectorD(this.x / 2, this.y / 2, this.z / 2);
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
	
	public boolean outOfRange(final VectorD min, final VectorD max) {
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
	public void randomize(final double minX, final double maxX, final double minY, final double maxY, final double minZ, final double maxZ) {
		double x = Random.randomDouble(minX, maxX);
		double y = Random.randomDouble(minY, maxY);
		double z = Random.randomDouble(minZ, maxZ);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void randomize(final double minX, final double maxX, final double minY, final double maxY) {
		double x = Random.randomDouble(minX, maxX);
		double y = Random.randomDouble(minY, maxY);
		this.x = x;
		this.y = y;
	}
	
	public void randomize(final double minX, final double maxX) {
		double x = Random.randomDouble(minX, maxX);
		this.x = x;
	}
	
	public void randomize(final VectorD min, final VectorD max) {
		double x = Random.randomDouble(min.x, max.x);
		double y = Random.randomDouble(min.y, max.y);
		double z = Random.randomDouble(min.z, max.z);
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
	public static VectorD createVector(final int value) {
		return new VectorD(value, value, value);
	}
	
	public static VectorD createVector(final float value) {
		return new VectorD(value, value, value);
	}
	
	public static VectorD createVector(final double value) {
		return new VectorD(value, value, value);
	}
	//
	
	/**
	 * Create a empty vector
	 * @return
	 */
	public static VectorD empty() {
		return new VectorD(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
