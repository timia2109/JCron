package com.github.timia2109.jcron.examples;

import com.github.timia2109.jcron.IntervaledObject;

public class SimpleIntervalTask extends IntervaledObject {

    private int tick;

    public SimpleIntervalTask() {
        // Start now and run 10 secounds
        super(System.currentTimeMillis(), 10*1000);

        tick = 0;
    }

    @Override
    public void exec() {
        // Do the stuff you want
        System.out.printf("Tick %d from the SimpleIntervalTask\n", ++tick);
    }
}
