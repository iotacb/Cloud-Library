package de.iotacb.cloud.utilities.math;

public class Vector {

	public double x, y, z;

	void createVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector() {}

	public Vector(double x) {
		createVector(x, 0, 0);
	}

	public Vector(double x, double y) {
		createVector(x, y, 0);
	}

	public Vector(double x, double y, double z) {
		createVector(x, y, z);
	}
	
	public Vector(Vector vector) {
		createVector(vector.x, vector.y, vector.z);
	}

	public static Vector random(double minX, double maxX) {
		return new Vector(Randoms.randomDouble(minX, maxX));
	}

	public static Vector random(double minX, double maxX, double minY, double maxY) {
		return new Vector(Randoms.randomDouble(minX, maxX), Randoms.randomDouble(minY, maxY));
	}

	public static Vector random(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		return new Vector(Randoms.randomDouble(minX, maxX), Randoms.randomDouble(minY, maxY),
				Randoms.randomDouble(minZ, maxZ));
	}
	
	public Vector randomize(double minX, double maxX) {
		this.x = Randoms.randomDouble(minX, maxX);
		return this;
	}
	
	public Vector randomize(double minX, double maxX, double minY, double maxY) {
		this.x = Randoms.randomDouble(minX, maxX);
		this.y = Randoms.randomDouble(minY, maxY);
		return this;
	}
	
	public Vector randomize(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		this.x = Randoms.randomDouble(minX, maxX);
		this.y = Randoms.randomDouble(minY, maxY);
		this.z = Randoms.randomDouble(minZ, maxZ);
		return this;
	}

	public Vector set(double x) {
		this.x = x;
		return this;
	}

	public Vector set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector set(Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}

	public Vector add(double x) {
		this.x += x;
		return this;
	}

	public Vector add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector add(Vector vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}

	public Vector sub(double x) {
		this.x -= x;
		return this;
	}

	public Vector sub(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector sub(double x, double y, double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector sub(Vector vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}

	public Vector mul(double x) {
		this.x *= x;
		return this;
	}

	public Vector mul(double x, double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public Vector mul(double x, double y, double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector mul(Vector vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}

	public Vector div(double x) {
		this.x /= x;
		return this;
	}

	public Vector div(double x, double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}

	public Vector div(double x, double y, double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public Vector div(Vector vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public Vector scale(double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}

	public double dot(double x, double y) {
		return this.x * x + this.y * y;
	}

	public double dot(double x, double y, double z) {
		return this.x * x + this.y * y + this.z * z;
	}

	public double dot(Vector vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	public Vector cross(double x, double y, double z) {
		double crossX = this.y * z - this.z * y;
		double crossY = this.z * x - this.x * z;
		double crossZ = this.x * y - this.y * x;
		return new Vector(crossX, crossY, crossZ);
	}

	public Vector cross(Vector vector) {
		return this.cross(vector.x, vector.y, vector.z);
	}

	public Vector fromAngle(double angle) {
		return new Vector(Math.cos(angle), Math.sin(angle));
	}
	
	public Vector mag(double magnitude) {
		normalize();
		mul(magnitude, magnitude, magnitude);
		return this;
	}

	public double mag() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public double magSqrt() {
		return Math.sqrt(this.mag());
	}

	public Vector normalize() {
		double mag = this.mag();

		if (mag != 0 && mag != 1) {
			this.div(mag);
		}

		return this;
	}

	public Vector limit(double max) {
		if (this.mag() > max * max) {
			this.normalize();
			this.mul(max, max, max);
		}
		
		return this;
	}
	
	public Vector clamp(double minX, double maxX) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		return this;
	}
	
	public Vector clamp(double minX, double maxX, double minY, double maxY) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		this.y = (this.y < minY ? minY : this.y > maxY ? maxY : this.y);
		return this;
	}
	
	public Vector clamp(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		this.y = (this.y < minY ? minY : this.y > maxY ? maxY : this.y);
		this.z = (this.z < minZ ? minZ : this.z > maxZ ? maxZ : this.z);
		return this;
	}
	
	public Vector clamp(Vector min, Vector max) {
		this.x = (this.x < min.x ? min.x : this.x > max.x ? max.x : this.x);
		this.y = (this.y < min.y ? min.y : this.y > max.y ? max.y : this.y);
		this.z = (this.z < min.z ? min.z : this.z > max.z ? max.z : this.z);
		return this;
	}
	
	public Vector copy() {
		return new Vector(this);
	}
	
	public Vector mirror() {
		return new Vector(this.x * -1, this.y * -1, this.z * -1);
	}
	
	public double direction() {
		return Math.atan2(this.y, this.x);
	}
	
	public double dist(double x) {
		double diffX = this.x - x;
		return Math.sqrt(diffX * diffX);
	}
	
	public double dist(double x, double y) {
		double diffX = this.x - x;
		double diffY = this.y - y;
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}
	
	public double dist(double x, double y, double z) {
		double diffX = this.x - x;
		double diffY = this.y - y;
		double diffZ = this.z - z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(Vector vector) {
		double diffX = this.x - vector.x;
		double diffY = this.y - vector.y;
		double diffZ = this.z - vector.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public Vector positive() {
		return new Vector(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
	}
	
	public Vector negative() {
		Vector vector = this.positive();
		return new Vector(vector.x * -1, vector.y * -1, vector.z * -1);
	}
	
	public Vector center() {
		return new Vector(this.x / 2, this.y / 2, this.z / 2);
	}
	
	public Vector zero() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	public boolean same(double x) {
		return this.x == x;
	}
	
	public boolean same(double x, double y) {
		return this.x == x && this.y == y;
	}
	
	public boolean same(double x, double y, double z) {
		return this.x == x && this.y == y && this.z == z;
	}
	
	public boolean inRange(double minX, double maxX) {
		return this.x >= minX && this.x <= maxX;
	}
	
	public boolean inRange(double minX, double maxX, double minY, double maxY) {
		return this.inRange(minX, maxX) && this.inRange(minY, maxY);
	}
	
	public boolean inRange(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		return this.inRange(minX, maxX) && this.inRange(minY, maxY) && this.inRange(minZ, maxZ);
	}
	
	public boolean inRange(Vector min, Vector max) {
		return inRange(min.x, max.x, min.y, max.y, min.z, max.z);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}

}
