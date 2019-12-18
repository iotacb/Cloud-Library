package de.iotacb.cloud.core.window;

import org.lwjgl.glfw.GLFW;

public class FrameTimer {
	
	double lastLoopTime;
	
	float timeCount;
	
	int fps, fpsCount, ups, upsCount;
	
	public FrameTimer() {
		lastLoopTime = getTime();
	}
	
	public double getTime() {
		return GLFW.glfwGetTime();
	}
	
	public float getDelta() {
		double time = getTime();
		float delta = (float) (time - lastLoopTime);
		lastLoopTime = time;
		timeCount += delta;
		return delta;
	}
	
	public void updateFPS() {
		fpsCount++;
	}
	
	public void updateUPS() {
		upsCount++;
	}

	public void updateCounters() {
		if (timeCount > 1f) {
			fps = fpsCount;
			fpsCount = 0;
			
			ups = upsCount;
			upsCount = 0;
			
			timeCount -= 1f;
		}
	}
	
	public int getFPS() {
		return fps > 0 ? fps : fpsCount;
	}
	
	public int getUPS() {
		return ups > 0 ? ups : upsCount;
	}
	
	public double getLastLoopTime() {
		return lastLoopTime;
	}
	
}
