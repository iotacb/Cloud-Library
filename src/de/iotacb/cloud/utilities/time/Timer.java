package de.iotacb.cloud.utilities.time;

public class Timer {

    private long pastMS;

    /**
     * Will return if a amount of milliseconds have passed
     * @param milliseconds The amount of milliseconds
     * @return Returns if the amount of milliseconds has passed
     */
    public boolean havePassed(final long milliseconds) {
        if (System.currentTimeMillis() - pastMS > milliseconds) {
            pastMS = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
