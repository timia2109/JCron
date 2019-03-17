package com.github.timia2109.jcron;

public class TickTask implements CronTask {

    private int times;
    private TimeInterval intervall;
    private int ticks;

    public TickTask(int ticks) {
        times = 0;
        this.ticks = ticks;
        // Every Secound
        intervall = new TimeInterval(System.currentTimeMillis(), 1000);
    }

    @Override
    public boolean restart() {
        intervall.increase();
        return times < ticks;
    }

    @Override
    public long getNextTime() {
        return intervall.getTime();
    }

    @Override
    public void exec() {
        times++;
    }

    public int getTimes() {
        return times;
    }
}
