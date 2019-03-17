package com.github.timia2109.jcron.executors;

import com.github.timia2109.jcron.CronTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutor implements CronExecutor {

    private ExecutorService executorService;

    public ThreadPoolExecutor(int threads) {
        executorService = Executors.newFixedThreadPool(threads);
    }

    @Override
    public void execute(CronTask task) {
        executorService.execute(task::exec);
    }
}
