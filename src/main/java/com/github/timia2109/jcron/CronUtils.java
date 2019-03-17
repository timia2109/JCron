package com.github.timia2109.jcron;

/**
 * Provides static helper methods
 */
public class CronUtils {
    /**
     * Create a CronTask from Runnable
     * @param time Crontime
     * @param runnable Runnable
     * @return The created CronTask
     */
    public static CronTask taskFromRunnable(long time, Runnable runnable){
        return new CronTask() {
            @Override
            public boolean restart() {
                return false;
            }

            @Override
            public long getNextTime() {
                return time;
            }

            @Override
            public void exec() {
                runnable.run();
            }
        };
    }
}
