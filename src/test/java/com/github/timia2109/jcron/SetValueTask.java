package com.github.timia2109.jcron;

public class SetValueTask<T> implements CronTask {

    private T value;
    private T expectedValue;
    private long time;

    public SetValueTask(long target, T prevalue, T result) {
        value = prevalue;
        expectedValue = result;
        time = target;
    }

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
        value = expectedValue;
    }

    public T getValue() {
        return value;
    }

}
