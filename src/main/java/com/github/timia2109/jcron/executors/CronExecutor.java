package com.github.timia2109.jcron.executors;

import com.github.timia2109.jcron.CronTask;

public interface CronExecutor {
    /**
     * Execute the Task
     * @param task Task to execute
     */
    public void execute(CronTask task);
}
