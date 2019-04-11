package de.iotacb.cloud.utilities.math;

public class Vector {
	
	public double x, y, z;
	
	void setupVector(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector() {}
	
	public Vector(final double x) {
		setupVector(x, 0, 0);
	}
	
	public Vector(final double x, final double y) {
		setupVector(x, y, 0);
	}

	public Vector(final double x, final double y, final double z) {
		setupVector(x, y, z);
	}
	
	public Vector(final Vector vector) {
		setupVector(vector.x, vector.y, vector.z);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}

	public int getXInt() {
		return (int)x;
	}
	
	public int getYInt() {
		return (int)y;
	}
	
	public int getZInt() {
		return (int)z;
	}

	public float getXFloat() {
		return (float)x;
	}
	
	public float getYFloat() {
		return (float)y;
	}

	public float getZFloat() {
		return (float)z;
	}

	public static Vector random1D(final double xMin, final double xMax) {
		double x = Random.randomDouble(xMin, xMax);
		return new Vector(x);
	}

	public static Vector random2D(final double xMin, final double xMax, final double yMin, final double yMax) {
		double x = Random.randomDouble(xMin, xMax);
		double y = Random.randomDouble(yMin, yMax);
		return new Vector(x, y);
	}

	public Vector set(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector set(final double x, final double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector set(final double x) {
		this.x = x;
		return this;
	}
	
	public Vector set(final Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	public static Vector set(final Vector vector, final Vector vector2) {
		return vector.set(vector2);
	}
	
	public Vector add(final double x, final double y, final double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public Vector add(final double x, final double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector add(final double x) {
		this.x += x;
		return this;
	}
	
	public Vector add(final Vector vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	public static Vector add(final Vector vector, final Vector vector2) {
		return vector.add(vector2);
	}
	
	public Vector sub(final double x, final double y, final double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector sub(final double x, final double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector sub(final double x) {
		this.x -= x;
		return this;
	}
	
	public Vector sub(final Vector vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	public static Vector sub(final Vector vector, final Vector vector2) {
		return vector.sub(vector2);
	}

	public Vector mult(final double x, final double y, final double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector mult(final double x, final double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	public Vector mult(final double x) {
		this.x *= x;
		return this;
	}
	
	public Vector multAll(final double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	public Vector mult(final Vector vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	public static Vector mult(final Vector vector, final Vector vector2) {
		return vector.mult(vector2);
	}

	public Vector div(final double x, final double y, final double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public Vector div(final double x, final double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	public Vector div(final double x) {
		this.x /= x;
		return this;
	}
	
	public Vector divAll(final double n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}
	
	public Vector div(final Vector vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public static Vector div(final Vector vector, final Vector vector2) {
		return vector.div(vector2);
	}
	
	public double dot(final double x, final double y, final double z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public double dot(final double x, final double y) {
		return this.x * x + this.y * y;
	}

	public double dot(final Vector vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}
	
	public static double dot(final Vector vector, final Vector vector2) {
		return vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z;
	}

	public Vector cross(final Vector vector) {
		double xCross = this.y * vector.z - vector.y * this.z;
		double yCross = this.z * vector.x - vector.z * this.x;
		double zCross = this.x * vector.y - vector.x * this.y;

		return new Vector(xCross, yCross, zCross);
	}
	
	public static Vector cross(final Vector vector, final Vector vector2) {
		double xCross = vector.y * vector2.z - vector2.y * vector.z;
		double yCross = vector.z * vector2.x - vector2.z * vector.x;
		double zCross = vector.x * vector2.y - vector2.x * vector.y;
		
		return new Vector(xCross, yCross, zCross);
	}

	public static Vector fromAngle(final double angle) {
		double yaw = Math.cos(angle);
		double pitch = Math.sin(angle);
		return new Vector(yaw, pitch);
	}
	
	public double mag() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public double magSq() {
		return (this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public Vector copy() {
		return new Vector(this);
	}
	
	public static Vector copy(final Vector vector) {
		return vector.copy();
	}
	
	public Vector normalize() {
		double mag = mag();
		
		if (mag != 0 && mag != 1) {
			div(mag);
		}
		
		return this;
	}
	
	public Vector limit(final double max) {
		if (mag() > max * max) {
			normalize();
			multAll(max);
		}
		return this;
	}
	
	public Vector clamp(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		this.z = (this.z < zMin ? zMin : this.z > zMax ? zMax : this.z);
		return this;
	}
	
	public Vector clamp(final double xMin, final double xMax, final double yMin, final double yMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		this.y = (this.y < yMin ? yMin : this.y > yMax ? yMax : this.y);
		return this;
	}
	
	public Vector clamp(final double xMin, final double xMax) {
		this.x = (this.x < xMin ? xMin : this.x > xMax ? xMax : this.x);
		return this;
	}
	
	public Vector clamp(final Vector min, final Vector max) {
		return clamp(min.x, max.x, min.y, max.y, min.z, max.z);
	}

	public Vector mirror() {
		return new Vector(this.x * -1, this.y * -1, this.z * -1);
	}
	
	public static Vector mirror(final Vector vector) {
		return vector.mirror();
	}
	
	public Vector mag(final double mag) {
		normalize();
		mult(mag);
		return this;
	}
	
	public double heading() {
		return Math.atan2(this.y, this.x);
	}
	
	public static double heading(final Vector vector) {
		return Math.atan2(vector.y, vector.x);
	}

	public static double scalar(final Vector vector, final Vector vector2) {
		return (vector.x * vector2.x + vector.y * vector2.y + vector.z * vector2.z);
	}
	
	public double scalar(final Vector vector) {
		return scalar(this, vector);
	}

	public static double dist(final Vector vector, final Vector vector2) {
		double diffX = vector.x - vector2.x;
		double diffY = vector.y - vector2.y;
		double diffZ = vector.z - vector2.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(final Vector vector) {
		return dist(this, vector);
	}

	public Vector positive() {
		double x = Math.abs(this.x);
		double y = Math.abs(this.y);
		double z = Math.abs(this.z);
		return new Vector(x, y, z);
	}
	
	public Vector negative() {
		double x = Math.abs(this.x) * -1;
		double y = Math.abs(this.y) * -1;
		double z = Math.abs(this.z) * -1;
		return new Vector(x, y, z);
	}
	
	public Vector center() {
		return new Vector(this.x / 2, this.y / 2, this.z / 2);
	}
	
	public static Vector center(final Vector vector) {
		return vector.center();
	}
	
	public boolean outOfRange(final double minX, final double maxX) {
		return (this.x < minX || this.x > maxX);
	}
	
	public boolean outOfRange(final double minX, final double maxX, final double minY, final double maxY) {
		return(outOfRange(minX, maxX) || outOfRange(minY, maxY));
	}
	
	public boolean outOfRange(final double minX, final double maxX, final double minY, final double maxY, final double minZ, final double maxZ) {
		return(outOfRange(minX, maxX) || outOfRange(minY, maxY) || outOfRange(minZ, maxZ));
	}
	
	public boolean outOfRange(final Vector min, final Vector max) {
		return (outOfRange(min.x, max.x, max.z, max.y));
	}

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
	
	public void randomize(final Vector min, final Vector max) {
		double x = Random.randomDouble(min.x, max.x);
		double y = Random.randomDouble(min.y, max.y);
		double z = Random.randomDouble(min.z, max.z);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vector create(final double value) {
		return new Vector(value, value, value);
	}
	
	public static Vector create(final double x, final double y) {
		return new Vector(x, y, 0);
	}
	
	public static Vector create(final double x, final double y, final double z) {
		return new Vector(x, y, z);
	}

	public static Vector empty() {
		return new Vector(0, 0, 0);
	}
	
	public boolean isSame(final Vector vector) {
		return (this.x == vector.x && this.y == vector.y && this.z == vector.z);
	}
	
	public Vector clear() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	public static boolean isSame(final Vector vector, final Vector vector2) {
		return vector.isSame(vector2);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}