package xyz.iotacb.cloud.utilities.time;

public class Timer {
	
	private long pastMS;
	
	/**
	 * Returns true when the delay is over
	 * @param milliseconds
	 * @return
	 */
	public boolean isOver(final long milliseconds) {
		if (System.currentTimeMillis() - pastMS > milliseconds) {
			pastMS = System.currentTimeMillis();
			return true;
		}
		return false;
	}

}
