package com.github.timia2109.jcron.executors;

import com.github.timia2109.jcron.CronTask;

public class SyncExecutor implements CronExecutor {
    @Override
    public void execute(CronTask task) {
        task.exec();
    }
}
