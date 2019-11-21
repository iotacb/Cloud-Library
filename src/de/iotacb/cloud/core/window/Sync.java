package de.iotacb.cloud.core.window;

import org.lwjgl.glfw.GLFW;

public class Sync {

	/**
	 * Code from
	 * https://github.com/LWJGL/lwjgl/blob/master/src/java/org/lwjgl/opengl/Sync.java
	 */

	private static long NANOS_IN_SECOND = 1000L * 1000L * 1000L;

	private static long nextFrame = 0;

	private static boolean initialised = false;

	private static RunningAvg sleepDurations = new RunningAvg(10);
	private static RunningAvg yieldDurations = new RunningAvg(10);

	private static void initialise() {
		initialised = true;

		sleepDurations.init(1000 * 1000);
		yieldDurations.init((int) (-(getTime() - getTime()) * 1.333));

		nextFrame = getTime();

		String osName = System.getProperty("os.name");

		if (osName.startsWith("Win")) {
			// On windows the sleep functions can be highly inaccurate by
			// over 10ms making in unusable. However it can be forced to
			// be a bit more accurate by running a separate sleeping daemon
			// thread.
			Thread timerAccuracyThread = new Thread(() -> {
				try {
					Thread.sleep(Long.MAX_VALUE);
				} catch (Exception e) {
					System.out.println("CLOUD: FAILED FPS SYNCING.");
				}
			});

			timerAccuracyThread.setName("LWJGL Timer");
			timerAccuracyThread.setDaemon(true);
			timerAccuracyThread.start();
		}
	}

	public static void sync(int fps) {
		if (fps <= 0)
			return;
		if (!initialised)
			initialise();
		Thread.yield();
		try {
			// sleep until the average sleep time is greater than the time remaining till
			// nextFrame
			for (long t0 = getTime(), t1; (nextFrame - t0) > sleepDurations.avg(); t0 = t1) {
				Thread.sleep(1);
				sleepDurations.add((t1 = getTime()) - t0); // update average sleep time
			}

			// slowly dampen sleep average if too high to avoid yielding too much
			sleepDurations.dampenForLowResTicker();

			// yield until the average yield time is greater than the time remaining till
			// nextFrame
			for (long t0 = getTime(), t1; (nextFrame - t0) > yieldDurations.avg(); t0 = t1) {
				Thread.yield();
				yieldDurations.add((t1 = getTime()) - t0); // update average yield time
			}
		} catch (InterruptedException e) {

		}

		// schedule next frame, drop frame(s) if already too late for next frame
		nextFrame = Math.max(nextFrame + NANOS_IN_SECOND / fps, getTime());
	}

	private static long getTime() {
		return (long) ((GLFW.glfwGetTime() * NANOS_IN_SECOND));
	}

	private static class RunningAvg {
		private final long[] slots;
		private int offset;

		private static final long DAMPEN_THRESHOLD = 10 * 1000L * 1000L; // 10ms
		private static final float DAMPEN_FACTOR = 0.9f; // don't change: 0.9f is exactly right!

		public RunningAvg(int slotCount) {
			this.slots = new long[slotCount];
			this.offset = 0;
		}

		public void init(long value) {
			while (this.offset < this.slots.length) {
				this.slots[this.offset++] = value;
			}
		}

		public void add(long value) {
			this.slots[this.offset++ % this.slots.length] = value;
			this.offset %= this.slots.length;
		}

		public long avg() {
			long sum = 0;
			for (int i = 0; i < this.slots.length; i++) {
				sum += this.slots[i];
			}
			return sum / this.slots.length;
		}

		public void dampenForLowResTicker() {
			if (this.avg() > DAMPEN_THRESHOLD) {
				for (int i = 0; i < this.slots.length; i++) {
					this.slots[i] *= DAMPEN_FACTOR;
				}
			}
		}
	}
}