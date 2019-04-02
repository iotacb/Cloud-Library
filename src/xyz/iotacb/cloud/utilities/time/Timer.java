package xyz.iotacb.cloud.utilities.time;

public class Timer {
	
	private long time;
	
	public Timer() {
		time = System.currentTimeMillis();
	}
	
	/**
	 * Check if a amount of milliseconds have passed
	 * @param milliseconds
	 * @return
	 */
	public boolean isOver(final long milliseconds) {
		if (this.time + milliseconds <= System.currentTimeMillis()) {
			this.time = System.currentTimeMillis();
			return true;
		}
		return false;
	}

}
