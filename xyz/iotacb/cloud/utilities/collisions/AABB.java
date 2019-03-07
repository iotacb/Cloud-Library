package xyz.iotacb.cloud.utilities.collisions;

import xyz.iotacb.cloud.utilities.math.vector.VectorD;

public class AABB {
	
	public double minX, minY, maxX, maxY;
	
	public AABB(final double minX, final double minY, final double maxX, final double maxY) {
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
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s, %s)", minX, minY, maxX, maxY);
	}

}
