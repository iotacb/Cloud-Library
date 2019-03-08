package xyz.iotacb.cloud.utilities.time;

public class Timer {
	
	private long pastMS;
	
	/**
	 * Returns if a amount of milliseconds has passed
	 * @param milliseconds
	 * @return
	 */
	public boolean isPassed(final long milliseconds) {
		if (System.currentTimeMillis() - pastMS > milliseconds) {
			pastMS = System.currentTimeMillis();
			return true;
		}
		return false;
	}

}
