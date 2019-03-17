package com.github.timia2109.jcron.examples;

import com.github.timia2109.jcron.CronTask;

public class SimpleCronTask implements CronTask {

    // Store the next execution time
    private long execution_time;

    public SimpleCronTask() {
        this.execution_time = System.currentTimeMillis() + (1000*60); // In 1 Minute
    }

    @Override
    public boolean restart() {
        // Should this task restart? In this case not
        return false;
    }

    @Override
    public long getNextTime() {
        // return the next execution time (this MUST be a fix value)
        return execution_time;
    }

    @Override
    public void exec() {
        // Do the stuff you want
        System.out.println("Wuhuu the SimpleCronTime has executed");
    }
}
