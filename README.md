# JCron
Simple lightweight schedule engine for Java.

I'm test this libary since a year and it works. So I make it public.


## Add to your project



## Examples
Create a simple cron task at a fixed time
```java
public class SimpleCronTask implements CronTask {

    // Store the next execution time
    private long execution_time;

    public SimpleCronTask() {
        this.execution_time = System.currentTimeMillis() + (1000*60); // In 1 Minute
    }

    @Override
    public boolean restart() {
        // Should this task restart? In this case not
        return false;
    }

    @Override
    public long getNextTime() {
        // return the next execution time (this MUST be a fix value)
        return execution_time;
    }

    @Override
    public void exec() {
        // Do the stuff you want
        System.out.println("Wuhuu the SimpleCronTime has executed");
    }
}
```

Create a simple intervaled task
```java
public class SimpleIntervalTask extends IntervaledObject {

    private int tick;

    public SimpleIntervalTask() {
        // Start now and run every minute
        super(System.currentTimeMillis(), 60*1000);

        tick = 0;
    }

    @Override
    public void exec() {
        // Do the stuff you want
        System.out.printf("Tick %d from the SimpleIntervalTask\n", ++tick);
    }
}
```

Add them to the cron system and start
```java
SimpleCronTask simpleCronTask = new SimpleCronTask();
SimpleIntervalTask simpleIntervalTask = new SimpleIntervalTask();

JCron.getInstance().addTask(simpleCronTask, simpleIntervalTask);
JCron.getInstance().start();
```