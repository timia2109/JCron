package com.github.timia2109.jcron;

/**
 * A IntervaledObject to create tasks like "every 5 minutes"
 */
public abstract class IntervaledObject extends TimeInterval implements CronTask {

    /**
     * Constructor with start time = now.
     * @param interval The interval in mills
     */
    protected IntervaledObject(long interval) {
        super(System.currentTimeMillis(), interval);
    }

    /**
     * Constructor with adjustable start time and interval
     * @param starttime Timestamp when the task should first run
     * @param interval The interval in mills
     */
    protected IntervaledObject(long starttime, long interval) {
        super(starttime, interval);
    }

    @Override
    public boolean restart() {
        toNext();
        return true;
    }

    @Override
    public long getNextTime() {
        return getTime();
    }

    @Override
    public abstract void exec();
}
