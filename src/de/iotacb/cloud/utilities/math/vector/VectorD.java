package de.iotacb.cloud.utilities.math.vector;

import de.iotacb.cloud.utilities.math.Randoms;

public class VectorD {

	public double x, y, z;

	void createVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public VectorD() {}
	
	public VectorD(double x) {
		createVector(x, 0, 0);
	}

	public VectorD(double x, double y) {
		createVector(x, y, 0);
	}

	public VectorD(double x, double y, double z) {
		createVector(x, y, z);
	}
	
	public VectorD(VectorD vector) {
		createVector(vector.x, vector.y, vector.z);
	}
	
	public VectorD(VectorI vector) {
		createVector(vector.x, vector.y, vector.z);
	}

	public static VectorD random(double minX, double maxX) {
		return new VectorD(Randoms.randomDouble(minX, maxX));
	}

	public static VectorD random(double minX, double maxX, double minY, double maxY) {
		return new VectorD(Randoms.randomDouble(minX, maxX), Randoms.randomDouble(minY, maxY));
	}

	public static VectorD random(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		return new VectorD(Randoms.randomDouble(minX, maxX), Randoms.randomDouble(minY, maxY),
				Randoms.randomDouble(minZ, maxZ));
	}
	
	public VectorD randomize(double minX, double maxX) {
		this.x = Randoms.randomDouble(minX, maxX);
		return this;
	}
	
	public VectorD randomize(double minX, double maxX, double minY, double maxY) {
		this.x = Randoms.randomDouble(minX, maxX);
		this.y = Randoms.randomDouble(minY, maxY);
		return this;
	}
	
	public VectorD randomize(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		this.x = Randoms.randomDouble(minX, maxX);
		this.y = Randoms.randomDouble(minY, maxY);
		this.z = Randoms.randomDouble(minZ, maxZ);
		return this;
	}

	public VectorD set(double x) {
		this.x = x;
		return this;
	}

	public VectorD set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public VectorD set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public VectorD set(VectorD vectorD) {
		this.x = vectorD.x;
		this.y = vectorD.y;
		this.z = vectorD.z;
		return this;
	}

	public VectorD add(double x) {
		this.x += x;
		return this;
	}

	public VectorD add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public VectorD add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public VectorD add(VectorD vectorD) {
		this.x += vectorD.x;
		this.y += vectorD.y;
		this.z += vectorD.z;
		return this;
	}

	public VectorD sub(double x) {
		this.x -= x;
		return this;
	}

	public VectorD sub(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public VectorD sub(double x, double y, double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public VectorD sub(VectorD vectorD) {
		this.x -= vectorD.x;
		this.y -= vectorD.y;
		this.z -= vectorD.z;
		return this;
	}

	public VectorD mul(double x) {
		this.x *= x;
		return this;
	}

	public VectorD mul(double x, double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public VectorD mul(double x, double y, double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public VectorD mul(VectorD vectorD) {
		this.x *= vectorD.x;
		this.y *= vectorD.y;
		this.z *= vectorD.z;
		return this;
	}

	public VectorD div(double x) {
		this.x /= x;
		return this;
	}

	public VectorD div(double x, double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}

	public VectorD div(double x, double y, double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public VectorD div(VectorD vectorD) {
		this.x /= vectorD.x;
		this.y /= vectorD.y;
		this.z /= vectorD.z;
		return this;
	}
	
	public VectorD scale(double n) {
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

	public double dot(VectorD vectorD) {
		return this.x * vectorD.x + this.y * vectorD.y + this.z * vectorD.z;
	}

	public VectorD cross(double x, double y, double z) {
		double crossX = this.y * z - this.z * y;
		double crossY = this.z * x - this.x * z;
		double crossZ = this.x * y - this.y * x;
		return new VectorD(crossX, crossY, crossZ);
	}

	public VectorD cross(VectorD vectorD) {
		return this.cross(vectorD.x, vectorD.y, vectorD.z);
	}

	public VectorD fromAngle(double angle) {
		return new VectorD(Math.cos(angle), Math.sin(angle));
	}
	
	public VectorD mag(double magnitude) {
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

	public VectorD normalize() {
		double mag = this.mag();

		if (mag != 0 && mag != 1) {
			this.div(mag);
		}

		return this;
	}

	public VectorD limit(double max) {
		if (this.mag() > max * max) {
			this.normalize();
			this.mul(max, max, max);
		}
		
		return this;
	}
	
	public VectorD clamp(double minX, double maxX) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		return this;
	}
	
	public VectorD clamp(double minX, double maxX, double minY, double maxY) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		this.y = (this.y < minY ? minY : this.y > maxY ? maxY : this.y);
		return this;
	}
	
	public VectorD clamp(double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
		this.x = (this.x < minX ? minX : this.x > maxX ? maxX : this.x);
		this.y = (this.y < minY ? minY : this.y > maxY ? maxY : this.y);
		this.z = (this.z < minZ ? minZ : this.z > maxZ ? maxZ : this.z);
		return this;
	}
	
	public VectorD clamp(VectorD min, VectorD max) {
		this.x = (this.x < min.x ? min.x : this.x > max.x ? max.x : this.x);
		this.y = (this.y < min.y ? min.y : this.y > max.y ? max.y : this.y);
		this.z = (this.z < min.z ? min.z : this.z > max.z ? max.z : this.z);
		return this;
	}
	
	public VectorD copy() {
		return new VectorD(this);
	}
	
	public VectorD mirror() {
		return new VectorD(this.x * -1, this.y * -1, this.z * -1);
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
	
	public double dist(VectorD vectorD) {
		double diffX = this.x - vectorD.x;
		double diffY = this.y - vectorD.y;
		double diffZ = this.z - vectorD.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
	
	public VectorD positive() {
		return new VectorD(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
	}
	
	public VectorD negative() {
		VectorD vectorD = this.positive();
		return new VectorD(vectorD.x * -1, vectorD.y * -1, vectorD.z * -1);
	}
	
	public VectorD center() {
		return new VectorD(this.x / 2, this.y / 2, this.z / 2);
	}
	
	public VectorD clear() {
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
	
	public boolean inRange(VectorD min, VectorD max) {
		return inRange(min.x, max.x, min.y, max.y, min.z, max.z);
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
	
	public VectorD getVector() {
		return this;
	}
	
	public VectorI getVectorInt() {
		return new VectorI(this);
	}
	
	public VectorD makeDoubleVector(VectorI vector) {
		return new VectorD(vector);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", this.x, this.y, this.z);
	}

}
