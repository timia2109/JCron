package com.github.timia2109.jcron;

public interface IntervallSchedule {
    public void increase();
    public long getTime();
    public void toNext();
}
