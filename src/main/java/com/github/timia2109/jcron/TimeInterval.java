package com.github.timia2109.jcron;

/**
 * A TimeInterval calculates the next time from a start value and a interval time
 */
public class TimeInterval implements IntervallSchedule{

    private long    startpoint,
                    interval,
                    step;

    /**
     * Constructor of TimeInterval
     * @param start The start time (as Timestamp)
     * @param interval Length of the interval in mills
     */
    public TimeInterval(long start, long interval) {
        startpoint = start;
        this.interval = interval;
        step = 0;
    }

    /**
     * Increase the counter by 1
     */
    public void increase() {
        step++;
    }

    /**
     * Returns the next timestamp (read-only, call increase() first)
     * @return next timestamp
     */
    public long getTime() {
        return startpoint + (interval * step);
    }

    /**
     * Calculates the next timestamp that is in the future
     */
    public void toNext(){
        step = ((System.currentTimeMillis() - startpoint) / interval)+1;
    }
}
