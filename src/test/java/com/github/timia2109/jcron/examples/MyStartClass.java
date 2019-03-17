package com.github.timia2109.jcron.examples;

import com.github.timia2109.jcron.JCron;

public class MyStartClass {
    public static void main(String[] args) {
        SimpleCronTask simpleCronTask = new SimpleCronTask();
        SimpleIntervalTask simpleIntervalTask = new SimpleIntervalTask();

        JCron.getInstance().addTask(simpleCronTask, simpleIntervalTask);
        JCron.getInstance().start();
    }
}
