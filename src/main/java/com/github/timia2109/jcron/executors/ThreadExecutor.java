package com.github.timia2109.jcron.executors;

import com.github.timia2109.jcron.CronTask;

public class ThreadExecutor implements CronExecutor {
    @Override
    public void execute(CronTask task) {
        new Thread(task::exec).start();
    }
}
