package de.iotacb.cloud.utilities.math.vector;

import de.iotacb.cloud.utilities.math.Randoms;

public class VectorI {

	public int x, y, z;

	void createVector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public VectorI() {}

	public VectorI(int x) {
		createVector(x, 0, 0);
	}

	public VectorI(int x, int y) {
		createVector(x, y, 0);
	}

	public VectorI(int x, int y, int z) {
		createVector(x, y, z);
	}
	
	public VectorI(VectorI vector) {
		createVector(vector.x, vector.y, vector.z);
	}
	
	public VectorI(VectorD vector) {
		createVector(vector.getXInt(), vector.getYInt(), vector.getZInt());
	}

	public static VectorI random(int minX, int maxX) {
		return new VectorI(Randoms.randomInt(minX, maxX));
	}

	public static VectorI random(int minX, int maxX, int minY, int maxY) {
		return new VectorI(Randoms.randomInt(minX, maxX), Randoms.randomInt(minY, maxY));
	}

	public static VectorI random(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
		return new VectorI(Randoms.randomInt(minX, maxX), Randoms.randomInt(minY, maxY),
				Randoms.randomInt(minZ, maxZ));
	}
	
	public VectorI randomize(int minX, int maxX) {
		this.x = Randoms.randomInt(minX, maxX);
		return this;
	}
	
	public VectorI randomize(int minX, int maxX, int minY, int maxY) {
		this.x = Randoms.randomInt(minX, maxX);
		this.y = Randoms.randomInt(minY, maxY);
		return this;
	}
	
	public VectorI randomize(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
		this.x = Randoms.randomInt(minX, maxX);
		this.y = Randoms.randomInt(minY, maxY);
		this.z = Randoms.randomInt(minZ, maxZ);
		return this;
	}

	public VectorI set(int x) {
		this.x = x;
		return this;
	}

	public VectorI set(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public VectorI set(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public VectorI set(VectorI vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}

	public VectorI add(int x) {
		this.x += x;
		return this;
	}

	public VectorI add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public VectorI add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public VectorI add(VectorI vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}

	public VectorI sub(int x) {
		this.x -= x;
		return this;
	}

	public VectorI sub(int x, int y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public VectorI sub(int x, int y, int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public VectorI sub(VectorI vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}

	public VectorI mul(int x) {
		this.x *= x;
		return this;
	}

	public VectorI mul(int x, int y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public VectorI mul(int x, int y, int z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public VectorI mul(VectorI vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}

	public VectorI div(int x) {
		this.x /= x;
		return this;
	}

	public VectorI div(int x, int y) {
		this.x /= x;
		this.y /= y;
		return this;
	}

	public VectorI div(int x, int y, int z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public VectorI div(VectorI vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public VectorI scale(int n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}

	public double dot(int x, int y) {
		return this.x * x + this.y * y;
	}

	public double dot(int x, int y, int z) {
		return this.x * x + this.y * y + this.z * z;
	}

	public double dot(VectorI vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	public VectorI cross(int x, int y, int z) {
		int crossX = this.y * z - this.z * y;
		int crossY = this.z * x - this.x * z;
		int crossZ = this.x * y - this.y * x;
		return new VectorI(crossX, crossY, crossZ);
	}

	public VectorI cross(VectorI vector) {
		return this.cross(vector.x, vector.y, vector.z);
	}

	public VectorI fromAngle(int angle) {
		return new VectorI((int)Math.cos(angle), (int)Math.sin(angle));
	}
	
	public VectorI mag(int magnitude) {
		normalize();
		scale(magnitude);
		return this;
	}

	public double mag() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public double magSqrt() {
		return Math.sqrt(this.mag());
	}

	public VectorI normalize() {
		int mag = (int) this.mag();

		if (mag != 0 && mag != 1) {
			div(mag);
		}

		return this;
	}

	public VectorI limit(int max) {
		if (mag() > max * max) {
			normalize();
			mul(max, max, max);
		}
		
		return this;
	}
	
	public VectorI clamp(int minX, int maxX) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		return this;
	}
	
	public VectorI clamp(int minX, int maxX, int minY, int maxY) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		this.y = (this.y < minY ? minY : this.y > maxY ? maxY : this.y);
		return this;
	}
	
	public VectorI clamp(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		this.y = (this.y < minY ? minY : this.y > maxY ? maxY : this.y);
		this.z = (this.z < minZ ? minZ : this.z > maxZ ? maxZ : this.z);
		return this;
	}
	
	public VectorI clamp(VectorI min, VectorI max) {
		this.x = (this.x < min.x ? min.x : this.x > max.x ? max.x : this.x);
		this.y = (this.y < min.y ? min.y : this.y > max.y ? max.y : this.y);
		this.z = (this.z < min.z ? min.z : this.z > max.z ? max.z : this.z);
		return this;
	}
	
	public VectorI copy() {
		return new VectorI(this);
	}
	
	public VectorI mirror() {
		return new VectorI(this.x * -1, this.y * -1, this.z * -1);
	}
	
	public double direction() {
		return Math.atan2(this.y, this.x);
	}
	
	public double dist(int x) {
		double diffX = this.x - x;
		return Math.sqrt(diffX * diffX);
	}
	
	public double dist(int x, int y) {
		double diffX = this.x - x;
		double diffY = this.y - y;
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}
	
	public double dist(int x, int y, int z) {
		double diffX = this.x - x;
		double diffY = this.y - y;
		double diffZ = this.z - z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public double dist(VectorI vector) {
		double diffX = this.x - vector.x;
		double diffY = this.y - vector.y;
		double diffZ = this.z - vector.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public VectorI positive() {
		return new VectorI(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
	}
	
	public VectorI negative() {
		VectorI vectorD = this.positive();
		return new VectorI(vectorD.x * -1, vectorD.y * -1, vectorD.z * -1);
	}
	
	public VectorI center() {
		return new VectorI(this.x / 2, this.y / 2, this.z / 2);
	}
	
	public VectorI clear() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	public boolean same(int x) {
		return this.x == x;
	}
	
	public boolean same(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public boolean same(int x, int y, int z) {
		return this.x == x && this.y == y && this.z == z;
	}
	
	public boolean inRange(int minX, int maxX) {
		return this.x >= minX && this.x <= maxX;
	}
	
	public boolean inRange(int minX, int maxX, int minY, int maxY) {
		return this.inRange(minX, maxX) && this.inRange(minY, maxY);
	}
	
	public boolean inRange(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
		return this.inRange(minX, maxX) && this.inRange(minY, maxY) && this.inRange(minZ, maxZ);
	}
	
	public boolean inRange(VectorI min, VectorI max) {
		return inRange(min.x, max.x, min.y, max.y, min.z, max.z);
	}
	
	public VectorI makeIntVector(VectorD vector) {
		return new VectorI(vector);
	}
	
	public VectorI getVector() {
		return this;
	}
	
	public VectorD getVectorDouble() {
		return new VectorD(this);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}

}
