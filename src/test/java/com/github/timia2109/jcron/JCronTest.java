package com.github.timia2109.jcron;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JCronTest {

    private static SetValueTask<Integer> in5SecoundsTask,
                                    in10SecoundsTask,
                                    freezedTask;

    private static TickTask tick10TimesTask;

    @BeforeAll
    static void prepare() {
        in5SecoundsTask = new SetValueTask<>(System.currentTimeMillis()+5000, 0, 5);
        in10SecoundsTask = new SetValueTask<>(System.currentTimeMillis()+10000, 0, 10);
        tick10TimesTask = new TickTask(10);
        freezedTask = new SetValueTask<>(System.currentTimeMillis()+1000, 0, 5);

        JCron.getInstance().addTask(in5SecoundsTask, in10SecoundsTask, tick10TimesTask, freezedTask);
        JCron.getInstance().start();
        JCron.getInstance().freeze(freezedTask);
    }

    @Test
    void in5SecoundsTask() throws InterruptedException {
        Thread.sleep(5000);
        assertEquals(5, in5SecoundsTask.getValue());
    }

    @Test
    void in10SecoundsTask() throws InterruptedException {
        Thread.sleep(10000);
        assertEquals(10, in10SecoundsTask.getValue());
    }

    @Test
    void ticks() throws InterruptedException{
        Thread.sleep(10000);
        assertEquals(tick10TimesTask.getTimes(), 10);
    }

    @Test
    void checkFreezed() throws InterruptedException {
        Thread.sleep(1000);
        assertEquals(0, freezedTask.getValue());
    }

}
