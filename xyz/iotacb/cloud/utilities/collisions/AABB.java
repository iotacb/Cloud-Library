package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.utilities.math.vector.VectorD;
import xyz.iotacb.cloud.utilities.math.vector.VectorF;
import xyz.iotacb.cloud.utilities.math.vector.VectorI;

public class AABB {
	
	public double minX, minY, maxX, maxY;
	
	public AABB(final double minX, final double minY, final double maxX, final double maxY) {
		this.minX = Math.min(minX, maxX);
		this.minY = Math.min(minY, maxY);
		this.maxX = Math.max(minX, maxX);
		this.maxY = Math.max(minY, maxY);
	}
	
	public AABB(final float minX, final float minY, final float maxX, final float maxY) {
		this.minX = Math.min(minX, maxX);
		this.minY = Math.min(minY, maxY);
		this.maxX = Math.max(minX, maxX);
		this.maxY = Math.max(minY, maxY);
	}
	
	public AABB(final int minX, final int minY, final int maxX, final int maxY) {
		this.minX = Math.min(minX, maxX);
		this.minY = Math.min(minY, maxY);
		this.maxX = Math.max(minX, maxX);
		this.maxY = Math.max(minY, maxY);
	}
	
	public AABB(final VectorD min, final VectorD max) {
		this.minX = Math.min(min.x, max.x);
		this.minY = Math.min(min.y, max.y);
		this.maxX = Math.max(min.x, max.x);
		this.maxY = Math.max(min.y, max.y);
	}
	
	public AABB(final VectorF min, final VectorF max) {
		this.minX = Math.min(min.x, max.x);
		this.minY = Math.min(min.y, max.y);
		this.maxX = Math.max(min.x, max.x);
		this.maxY = Math.max(min.y, max.y);
	}
	
	public AABB(final VectorI min, final VectorI max) {
		this.minX = Math.min(min.x, max.x);
		this.minY = Math.min(min.y, max.y);
		this.maxX = Math.max(min.x, max.x);
		this.maxY = Math.max(min.y, max.y);
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s, %s)", minX, minY, maxX, maxY);
	}

}
