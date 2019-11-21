package de.iotacb.cloud.utilities.time;

public class Timer {
	
	private long pastMS;
	
	public boolean havePassed(long milliseconds) {
		if (System.currentTimeMillis() - pastMS > milliseconds) {
			pastMS = System.currentTimeMillis();
			return true;
		}
		return false;
	}

}
