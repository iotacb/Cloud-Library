package de.iotacb.cloud.utilities.math;

public class Vec {

	public float x, y, z;
	
	void init(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec() {
	}

	public Vec(float x) {
		init(x, 0, 0);
	}

	public Vec(float x, float y) {
		init(x, y, 0);
	}
	
	public Vec(float x, float y, float z) {
		init(x, y, z);
	}
	
	public Vec(Vec vec) {
		init(vec.x, vec.y, vec.z);
	}
	
	public static Vec random() {
		float rand_number = (float) (Math.random() * Maths.TAU);
		return new Vec((float)Math.cos(rand_number), (float)Math.sin(rand_number));
	}
	
	public static Vec random(float xMin, float xMax) {
		float x = Randoms.randomFloat(xMin, xMax);
		return new Vec(x, 0);
	}
	
	public static Vec random(float xMin, float xMax, float yMin, float yMax) {
		float x = Randoms.randomFloat(xMin, xMax);
		float y = Randoms.randomFloat(yMin, yMax);
		return new Vec(x, y);
	}
	
	public static Vec random(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
		float x = Randoms.randomFloat(xMin, xMax);
		float y = Randoms.randomFloat(yMin, yMax);
		float z = Randoms.randomFloat(zMin, zMax);
		return new Vec(x, y, z);
	}
	
	public Vec randomize(float xMin, float xMax) {
		set(Vec.random(xMin, xMax));
		return this;
	}
	
	public Vec randomize(float xMin, float xMax, float yMin, float yMax) {
		set(Vec.random(xMin, xMax, yMin, yMax));
		return this;
	}
	
	public Vec randomize(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
		set(Vec.random(xMin, xMax, yMin, yMax, zMin, zMax));
		return this;
	}
	
	public Vec set(float x) {
		this.x = x;
		return this;
	}
	
	public Vec set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vec set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public Vec set(Vec vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		return this;
	}
	
	public Vec add(float x) {
		this.x += x;
		return this;
	}
	
	public Vec add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vec add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public Vec add(Vec vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}
	
	public Vec sub(float x) {
		this.x -= x;
		return this;
	}
	
	public Vec sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vec sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	public Vec sub(Vec vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}
	
	public Vec mul(float x) {
		this.x *= x;
		return this;
	}
	
	public Vec mul(float x, float y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	public Vec mul(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	public Vec mul(Vec vec) {
		this.x *= vec.x;
		this.y *= vec.y;
		this.z *= vec.z;
		return this;
	}
	
	public Vec div(float x) {
		this.x /= x;
		return this;
	}
	
	public Vec div(float x, float y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	public Vec div(float x, float y, float z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	public Vec div(Vec vec) {
		this.x /= vec.x;
		this.y /= vec.y;
		this.z /= vec.z;
		return this;
	}
	
	public float dot(float x) {
		return this.x * x;
	}
	
	public float dot(float x, float y) {
		return this.x * x + this.y * y;
	}
	
	public float dot(float x, float y, float z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public float dot(Vec vec) {
		return dot(vec.x, vec.y, vec.z);
	}
	
	public float cross(float x, float y) {
		return this.x * y - this.y * x;
	}
	
	public Vec cross(float x, float y, float z) {
		float xCross = this.y * z - this.z * y;
		float yCross = this.z * x - this.x * z;
		float zCross = this.x * y - this.y * x;
		return new Vec(xCross, yCross, zCross);
	}
	
	public Vec cross(Vec vec) {
		return cross(vec.x, vec.y, vec.z);
	}
	
	public Vec fromAngle(float angle) {
		float x = (float)Math.cos(angle);
		float y = (float)Math.sin(angle);
		float z = (float)Math.tan(angle);
		return new Vec(x, y, z);
	}
	
	public Vec normalize() {
		float magnitude = getMagnitude();
		if (magnitude != 0 && magnitude != 1) {
			div(magnitude, magnitude, magnitude);
		}
		return this;
	}
	
	public Vec limit(float limit) {
		if (getMagnitude() > limit * limit) {
			normalize();
			mul(limit, limit, limit);
		}
		return this;
	}
	
	public float getMagnitude() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	public float getMagnitudeSq() {
		return x * x + y * y + z * z;
	}
	
	public Vec setMagnitude(float magnitude) {
		normalize();
		mul(magnitude, magnitude, magnitude);
		return this;
	}
	
	public Vec clamp(float xMin, float xMax) {
		this.x = (x < xMin ? xMin : x > xMax ? xMax : x);
		return this;
	}
	
	public Vec clamp(float xMin, float xMax, float yMin, float yMax) {
		this.x = (x < xMin ? xMin : x > xMax ? xMax : x);
		this.y = (y < yMin ? yMin : y > yMax ? yMax : y);
		return this;
	}
	
	public Vec clamp(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
		this.x = (x < xMin ? xMin : x > xMax ? xMax : x);
		this.y = (y < yMin ? yMin : y > yMax ? yMax : y);
		this.z = (z < zMin ? zMin : z > zMax ? zMax : z);
		return this;
	}
	
	public Vec clamp(Vec min, Vec max) {
		this.x = (x < min.x ? min.x : x > max.x ? max.x : x);
		this.y = (y < min.y ? min.y : y > max.y ? max.y : y);
		this.z = (z < min.z ? min.z : z > max.z ? max.z : z);
		return this;
	}
	
	@Override
	public Vec clone() {
		return new Vec(this);
	}
	
	public Vec mirror() {
		return new Vec(x * -1, y * -1, z * -1);
	}
	
	public Vec pos() {
		return new Vec(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public Vec neg() {
		return new Vec(Math.abs(x) * -1, Math.abs(y) * -1, Math.abs(z) * -1);
	}
	
	public Vec center() {
		return new Vec(x / 2, y / 2, z / 2);
	}
	
	public Vec zero() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	public float direction() {
		return (float)Math.atan2(y, x);
	}
	
	public float distance(float x) {
		float xDiff = this.x - x;
		return (float)Math.sqrt(xDiff * xDiff);
	}
	
	public float distance(float x, float y) {
		float xDiff = this.x - x;
		float yDiff = this.y - y;
		return (float)Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}
	
	public float distance(float x, float y, float z) {
		float xDiff = this.x - x;
		float yDiff = this.y - y;
		float zDiff = this.z - z;
		return (float)Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff + zDiff);
	}
	
	public float distance(Vec vec) {
		return distance(vec.x, vec.y, vec.z);
	}
	
	public boolean equal(float x) {
		return this.x == x;
	}
	
	public boolean equal(float x, float y) {
		return this.x == x && this.y == y;
	}
	
	public boolean equal(float x, float y, float z) {
		return this.x == x && this.y == y && this.z == z;
	}
	
	public boolean equal(Vec vec) {
		return equal(vec.x, vec.y, vec.z);
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getZ() {
		return this.z;
	}
	
	public int getXI() {
		return (int)this.x;
	}
	
	public int getYI() {
		return (int)this.y;
	}
	
	public int getZI() {
		return (int)this.z;
	}
	
	@Override
	public String toString() {
		return String.format("{%s, %s, %s}", x, y, z);
	}

}
