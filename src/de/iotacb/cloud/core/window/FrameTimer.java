package de.iotacb.cloud.core.window;

import org.lwjgl.glfw.GLFW;

public class FrameTimer {
	
	private double lastLoopTime;
	
	private float timeCount;
	
	private int fps, fpsCount, ups, upsCount;
	
	public FrameTimer() {
		lastLoopTime = getTime();
	}
	
	public final double getTime() {
		return GLFW.glfwGetTime();
	}
	
	public final float getDelta() {
		final double time = getTime();
		final float delta = (float) (time - lastLoopTime);
		lastLoopTime = time;
		timeCount += delta;
		return delta;
	}
	
	public final void updateFPS() {
		fpsCount++;
	}
	
	public final void updateUPS() {
		upsCount++;
	}

	public final void updateCounters() {
		if (timeCount > 1f) {
			fps = fpsCount;
			fpsCount = 0;
			
			ups = upsCount;
			upsCount = 0;
			
			timeCount -= 1f;
		}
	}
	
	public final int getFPS() {
		return fps > 0 ? fps : fpsCount;
	}
	
	public final int getUPS() {
		return ups > 0 ? ups : upsCount;
	}
	
	public final double getLastLoopTime() {
		return lastLoopTime;
	}
	
}
