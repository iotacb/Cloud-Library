package xyz.iotacb.cloud.utilities.math;

import xyz.iotacb.cloud.utilities.math.Random;

public class Vector3 {
	
	public double x, y, z;
	
	void setupVector(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3() {}
	
	/**
	 * This will create a vector with just the x axe
	 * @param x
	 */
	public Vector3(final double x) {
		setupVector(x, 0, 0);
	}
	
	/**
	 * This will create a vector with the x and y axes
	 * @param x
	 * @param y
	 */
	public Vector3(final double x, final double y) {
		setupVector(x, y, 0);
	}
	
	/**
	 * This will create a vector with the x, y and z axes
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(final double x, final double y, final double z) {
		setupVector(x, y, z);
	}
	
	/**
	 * This will create a vector with the values from a other vector
	 * @param vector
	 */
	public Vector3(final Vector3 vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	/**
	 * Get the x axe from the vector
	 * @return
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get the y axe from the vector
	 * @return
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Get the z axe from the vector
	 * @return
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Get the integer casted x axe from the vector
	 * @return
	 */
	public int getXInt() {
		return (int)x;
	}
	
	/**
	 * Get the integer casted y axe from the vector
	 * @return
	 */
	public int getYInt() {
		return (int)y;
	}
	
	/**
	 * Get the integer casted z axe from the vector
	 * @return
	 */
	public int getZInt() {
		return (int)z;
	}
	
	/**
	 * Get the float casted x axe from the vector
	 * @return
	 */
	public float getXFloat() {
		return (float)x;
	}
	
	/**
	 * Get the float casted y axe from the vector
	 * @return
	 */
	public float getYFloat() {
		return (float)y;
	}
	
	/**
	 * Get the float casted z axe from the vector
	 * @return
	 */
	public float getZFloat() {
		return (float)z;
	}
	
	/**
	 * This will generate a vector with a random x axe which will be inside of the given range
	 * @param xMin
	 * @param xMax
	 * @return
	 */
	public static Vector3 random1D(final double xMin, final double xMax) {
		double x = Random.randomDouble(xMin, xMax);
		return new Vector3(x);
	}
	
	/**
	 * This will generate a vector with random x and y axes which will be inside of the given ranges
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return
	 */
	public static Vector3 random2D(final double xMin, final double xMax, final double yMin, final double yMax) {
		double x = Random.randomDouble(xMin, xMax);
		double y = Random.randomDouble(yMin, yMax);
		return new Vector3(x, y);
	}
	
	/**
	 * This will generate a vector with random x, y and y axes which will be inside of the given ranges
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @param zMin
	 * @param zMax
	 * @return
	 */
	public static Vector3 random3D(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		double x = Random.randomDouble(xMin, xMax);
		double y = Random.randomDouble(yMin, yMax);
		double z = Random.randomDouble(zMin, zMax);
		return new Vector3(x, y, z);
	}
	
	/**
	 * Set the x, y and z axes of the vector
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector3 set(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**
	 * Set the x and y axes of the vector
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector3 set(final double x, final double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Set the x axe of the vector
	 * @param x
	 * @return
	 */
	public Vector3 set(final double x) {
		this.x = x;
		return this;
	}
	
	/**
	 * Set the vector to the values of a other vector
	 * @param vector
	 * @return
	 */
	public Vector3 set(final Vector3 vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	/**
	 * Set the given vector to the values of a other vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static Vector3 set(final Vector3 vector, final Vector3 vector2) {
		return vector.set(vector2);
	}
	
	/**
	 * Add values to the x, y and z axes of the vector
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector3 add(final double x, final double y, final double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	/**
	 * Add values to the x and y axes of the vector
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector3 add(final double x, final double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	/**
	 * Add a value to the x axe of the vector
	 * @param x
	 * @return
	 */
	public Vector3 add(final double x) {
		this.x += x;
		return this;
	}
	
	/**
	 * Add the values of a other vector to the vector
	 * @param vector
	 * @return
	 */
	public Vector3 add(final Vector3 vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	/**
	 * Add the values of a other vector to the given vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static Vector3 add(final Vector3 vector, final Vector3 vector2) {
		return vector.add(vector2);
	}
	
	/**
	 * Subtract values from the x, y and z axes of the vector
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector3 sub(final double x, final double y, final double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	/**
	 * Subtract values from the x and y axes of the vector
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector3 sub(final double x, final double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	/**
	 * Subtract a value form the x axe of the vector
	 * @param x
	 * @return
	 */
	public Vector3 sub(final double x) {
		this.x -= x;
		return this;
	}
	
	/**
	 * Subtract the values of a other vector from the vector
	 * @param vector
	 * @return
	 */
	public Vector3 sub(final Vector3 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	/**
	 * Subtract the values of a other vector from the given vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static Vector3 sub(final Vector3 vector, final Vector3 vector2) {
		return vector.sub(vector2);
	}
	
	/**
	 * Multiply the x, y and z axes with the given values
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector3 mult(final double x, final double y, final double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	/**
	 * Multiply the x and y axes with the given values
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector3 mult(final double x, final double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	/**
	 * Multiply the x axe with the given value
	 * @param x
	 * @return
	 */
	public Vector3 mult(final double x) {
		this.x *= x;
		return this;
	}
	
	/**
	 * Multiply all axes of the vector with the given value
	 * @param n
	 * @return
	 */
	public Vector3 multAll(final double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	/**
	 * Multiply the x, y and z axes of the vector with the values of a other vector
	 * @param vector
	 * @return
	 */
	public Vector3 mult(final Vector3 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	/**
	 * Multiply the x, y and z axes of the given vector with the values of a other vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static Vector3 mult(final Vector3 vector, final Vector3 vector2) {
		return vector.mult(vector2);
	}
	
	/**
	 * Divide the x, y and z axes of the vector with the given values
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector3 div(final double x, final double y, final double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	/**
	 * Divide the x and y axes of the vector with the given values
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector3 div(final double x, final double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	/**
	 * Divide the x axe of the vector with the given value
	 * @param x
	 * @return
	 */
	public Vector3 div(final double x) {
		this.x /= x;
		return this;
	}
	
	/**
	 * Divide all the axes of the vector with the given value
	 * @param n
	 * @return
	 */
	public Vector3 divAll(final double n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}
	
	/**
	 * Divide all the axes of the vector with the values of a other vector
	 * @param vector
	 * @return
	 */
	public Vector3 div(final Vector3 vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	/**
	 * Divide all the axes of a given vector with the values of a other vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static Vector3 div(final Vector3 vector, final Vector3 vector2) {
		return vector.div(vector2);
	}
	
	/**
	 * Dot the x, y and z axes with the given values
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public double dot(final double x, final double y, final double z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	/**
	 * Dot the x and y axes with the given values
	 * @param x
	 * @param y
	 * @return
	 */
	public double dot(final double x, final double y) {
		return this.x * x + this.y * y;
	}

	/**
	 * Dot all the axes of the vector with the values of a other vector
	 * @param vector
	 * @return
	 */
	public double dot(final Vector3 vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	/**
	 * Dot all the axes of a given vector with the values of a other vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double dot(final Vector3 vector, final Vector3 vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}

	/**
	 * Cross the vector with a other vector
	 * @param vector
	 * @return
	 */
	public Vector3 cross(final Vector3 vector) {
		double xCross = this.y * vector.z - vector.y * this.z;
		double yCross = this.z * vector.x - vector.z * this.x;
		double zCross = this.x * vector.y - vector.x * this.y;
		
		return new Vector3(xCross, yCross, zCross);
	}
	
	/**
	 * Cross a given vector with a other vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static Vector3 cross(final Vector3 vector, final Vector3 vector2) {
		double xCross = vector.y * vector2.z - vector2.y * vector.z;
		double yCross = vector.z * vector2.x - vector2.z * vector.x;
		double zCross = vector.x * vector2.y - vector2.x * vector.y;
		
		return new Vector3(xCross, yCross, zCross);
	}

	/**
	 * Create a vector from an angle
	 * @param angle
	 * @return
	 */
	public static Vector3 fromAngle(final double angle) {
		double yaw = Math.cos(angle);
		double pitch = Math.sin(angle);
		return new Vector3(yaw, pitch);
	}
	
	/**
	 * Get the magnitude of the vector
	 * @return
	 */
	public double mag() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Get the square root of the magnitude of the vector
	 * @return
	 */
	public double magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	/**
	 * Make a copy of the vector
	 * @return
	 */
	public Vector3 copy() {
		return new Vector3(this);
	}
	
	/**
	 * Make a copy of a given vector
	 * @param vector
	 * @return
	 */
	public static Vector3 copy(final Vector3 vector) {
		return vector.copy();
	}
	
	/**
	 * Normalize the vector
	 * @return
	 */
	public Vector3 normalize() {
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
	public Vector3 limit(final double max) {
		if (mag() > max * max) {
			normalize();
			multAll(max);
		}
		return this;
	}
	
	/**
	 * Constrain the x, y and z axes inside of given ranges
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @param zMin
	 * @param zMax
	 * @return
	 */
	public Vector3 constrain(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		this.z = (this.z < zMin ? zMin : this.z > zMax ? zMax : this.z);
		return this;
	}
	
	/**
	 * Constrain the x and y axes inside of given ranges
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return
	 */
	public Vector3 constrain(final double xMin, final double xMax, final double yMin, final double yMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		return this;
	}
	
	/**
	 * Constrain the x axe inside of the given range
	 * @param xMin
	 * @param xMax
	 * @return
	 */
	public Vector3 constrain(final double xMin, final double xMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		return this;
	}
	
	/**
	 * Constrain all axes inside of a given range
	 * @param min
	 * @param max
	 * @return
	 */
	public Vector3 constrain(final Vector3 min, final Vector3 max) {
		return constrain(min.x, max.x, min.y, max.y, min.z, max.z);
	}

	/**
	 * Create a mirrored version of the vector
	 * @return
	 */
	public Vector3 mirror() {
		return new Vector3(this.x * -1, this.y * -1, this.z * -1);
	}
	
	/**
	 * Create a mirrored version of the given vector
	 * @param vector
	 * @return
	 */
	public static Vector3 mirror(final Vector3 vector) {
		return vector.mirror();
	}
	
	/**
	 * Set the magnitude of the vector
	 * @param mag
	 * @return
	 */
	public Vector3 setMag(final double mag) {
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
	 * Get the heading direction of the given vector
	 * @param vector
	 * @return
	 */
	public static double heading(final Vector3 vector) {
		return Math.atan2(vector.y, vector.x);
	}

	/**
	 * Get the scalar of the vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double getScalar(final Vector3 vector, final Vector3 vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	/**
	 * Get the scalar of the vector and a other vector
	 * @param vector
	 * @return
	 */
	public double getScalar(final Vector3 vector) {
		return getScalar(this, vector);
	}

	/**
	 * Get the distance between to vectors
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static double dist(final Vector3 vector, final Vector3 vector2) {
		double diffX = vector.x - vector2.x;
		double diffY = vector.y - vector2.y;
		double diffZ = vector.z - vector2.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	/**
	 * Get the distance between the vector and a other vector
	 * @param vector
	 * @return
	 */
	public double dist(final Vector3 vector) {
		return dist(this, vector);
	}

	/**
	 * Make all axes of the vector positive
	 * @return
	 */
	public Vector3 makePositive() {
		double x = Math.abs(this.x);
		double y = Math.abs(this.y);
		double z = Math.abs(this.z);
		return new Vector3(x, y, z);
	}
	
	/**
	 * Make all axes of the vector negative
	 * @return
	 */
	public Vector3 makeNegative() {
		double x = Math.abs(this.x) * -1;
		double y = Math.abs(this.y) * -1;
		double z = Math.abs(this.z) * -1;
		return new Vector3(x, y, z);
	}
	
	/**
	 * Get the center of the vector
	 * @return
	 */
	public Vector3 getCenter() {
		return new Vector3(this.x / 2, this.y / 2, this.z / 2);
	}
	
	/**
	 * Get the center of a given vector
	 * @param vector
	 * @return
	 */
	public static Vector3 getCenter(final Vector3 vector) {
		return vector.getCenter();
	}
	
	/**
	 * Check if the x axe is outside of the given range
	 * @param minX
	 * @param maxX
	 * @return
	 */
	public boolean outOfRange(final double minX, final double maxX) {
		return (this.x < minX || this.x > maxX);
	}
	
	/**
	 * Check if the x and y axes are outside of the given ranges
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 * @return
	 */
	public boolean outOfRange(final double minX, final double maxX, final double minY, final double maxY) {
		return(outOfRange(minX, maxX) || outOfRange(minY, maxY));
	}
	
	/**
	 * Check if the x, y and z axes are outside of the given ranges
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 * @param minZ
	 * @param maxZ
	 * @return
	 */
	public boolean outOfRange(final double minX, final double maxX, final double minY, final double maxY, final double minZ, final double maxZ) {
		return(outOfRange(minX, maxX) || outOfRange(minY, maxY) || outOfRange(minZ, maxZ));
	}
	
	/**
	 * Check if the vector is outside of the given range
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean outOfRange(final Vector3 min, final Vector3 max) {
		return (outOfRange(min.x, max.x, max.z, max.y));
	}

	/**
	 * Randomize the x, y and z axes of the vector inside the given ranges
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
	
	/**
	 * Randomize the x and y axes of the vector inside the given ranges
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 */
	public void randomize(final double minX, final double maxX, final double minY, final double maxY) {
		double x = Random.randomDouble(minX, maxX);
		double y = Random.randomDouble(minY, maxY);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Randomize the x axe of the vector inside the given range
	 * @param minX
	 * @param maxX
	 */
	public void randomize(final double minX, final double maxX) {
		double x = Random.randomDouble(minX, maxX);
		this.x = x;
	}
	
	/**
	 * Randomize the vector inside the given range
	 * @param min
	 * @param max
	 */
	public void randomize(final Vector3 min, final Vector3 max) {
		double x = Random.randomDouble(min.x, max.x);
		double y = Random.randomDouble(min.y, max.y);
		double z = Random.randomDouble(min.z, max.z);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Create a vector with the same value for all axes
	 * @param value
	 * @return
	 */
	public static Vector3 createVector(final double value) {
		return new Vector3(value, value, value);
	}
	
	/**
	 * Create a vector with x and y axes
	 * @param x
	 * @param y
	 * @return
	 */
	public static Vector3 createVector(final double x, final double y) {
		return new Vector3(x, y, 0);
	}
	
	/**
	 * Create a vector with x, y and z axes
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Vector3 createVector(final double x, final double y, final double z) {
		return new Vector3(x, y, z);
	}

	/**
	 * Create an empty vector
	 * @return
	 */
	public static Vector3 empty() {
		return new Vector3(0, 0, 0);
	}
	
	/**
	 * Check if the vector has the same values of a other vector
	 * @param vector
	 * @return
	 */
	public boolean isSame(final Vector3 vector) {
		return (this.x == vector.x && this.y == vector.y && this.z == vector.z);
	}
	
	/**
	 * Check if the given vector has the same values of a other vector
	 * @param vector
	 * @param vector2
	 * @return
	 */
	public static boolean isSame(final Vector3 vector, final Vector3 vector2) {
		return vector.isSame(vector2);
	}
	
	/**
	 * Get the vector values as a String format
	 */
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
