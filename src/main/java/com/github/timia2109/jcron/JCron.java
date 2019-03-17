package com.github.timia2109.jcron;

import com.github.timia2109.jcron.executors.CronExecutor;
import com.github.timia2109.jcron.executors.ThreadExecutor;

import java.util.*;
import java.util.List;

/**
 * Class that represent the JCron System <br>
 *     Only one Instance per JVM are allowed
 * @see JCron#getInstance()
 */
public class JCron implements Runnable {

    /** JVM Instance of JCron*/
    private static JCron INSTANCE = new JCron();

    /** CronTasks List */
    private List<CronTask> tasks;

    /** List of freezed Tasks */
    private List<CronTask> freezed;

    /** Should the CronSystem be active? */
    private boolean active;

    /** Thread of the JCron System */
    private Thread thread;

    /** Comperator for CronTasks */
    private CronTaskComparator cronTaskComparator;

    /** Executor of the CronTasks */
    private CronExecutor executor;

    protected JCron() {
        tasks = Collections.synchronizedList(new LinkedList<>());
        cronTaskComparator = new CronTaskComparator();
        executor = new ThreadExecutor();
    }

    public void reorder() {
        tasks.sort(cronTaskComparator);
    }

    /**
     * Set the Executor of this Croninstance
     * @param executor The new Executor
     */
    public void setExecutor(CronExecutor executor) {
        this.executor = executor;
    }

    /**
     * Adds new Entrys to the cron system
     * @param wakeup Check if there is a new next task. Interrupt the System and check for next execution
     * @param atasks The new Crontasks
     */
    public void addTask(boolean wakeup, CronTask... atasks) {
        Collections.addAll(tasks, atasks);
        reorder();

        if (wakeup)
            wakeup();
    }

    /**
     * Adds new Entrys and check for the next task
     * @param atasks The new Crontasks
     */
    public void addTask(CronTask... atasks){
        addTask(true, atasks);
    }

    /**
     * Wake up the cron thread and check for the next task to execute
     */
    public void wakeup() {
        if (thread != null && thread.getState() == Thread.State.TIMED_WAITING)
            thread.interrupt();
        else if (active)
            start();
    }

    /**
     * Removes the Task from the JCron system
     * @param task Task to remove
     */
    public void removeTask(CronTask task){
        tasks.remove(task);
    }

    /**
     * Exit the JCron System and removes all tasks
     */
    public void clear(){
        stop();
        tasks.clear();
    }

    /**
     * Checks if the JCron system is running
     * @return Cronsystem running?
     */
    public boolean isRunning() {
        return active && thread != null && thread.isAlive();
    }

    /**
     * Starts the cron system
     */
    public void start() {
        thread = new Thread(this);
        active = true;
        thread.start();
    }

    /**
     * Exit the cron system
     */
    public void stop() {
        active = false;
        wakeup();
        thread = null;
    }

    /**
     * Freeze the task (only on repeated tasks)
     * @param task The task which shoud be freezed
     */
    public void freeze(CronTask task) {
        tasks.remove(task);
        if (freezed == null)
            freezed = Collections.synchronizedList( new ArrayList<>(1) );

        freezed.add(task);
    }

    /**
     * Unfreeze the Task
     * @param task The task which should be unfreezed
     */
    public void unfreeze(CronTask task){
        if (freezed != null) {
            freezed.remove(task);
            if (freezed.isEmpty())
                freezed = null;
        }

        addTask( task );
    }

    /**
     * Wokes up all tasks
     */
    public void unfreezeAll() {
        if (freezed == null)
            return;

        tasks.addAll( freezed );
        wakeup();
        freezed = null;
    }

    /**
     * Return a copy of a list with all tasks
     * @return Copy of list with tasks
     */
    public List<CronTask> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Return the list with all freezed tasks (original)
     * @return List with freezed tasks
     */
    public List<CronTask> getFreezedItems() {
        return freezed;
    }

    @Override
    public void run() {
        while (!tasks.isEmpty() && active) {
            execute();
            waitToNext();
        }
    }

    /**
     * Execute tasks, if the time matches
     */
    protected void execute() {
        while (!tasks.isEmpty() && System.currentTimeMillis() >= tasks.get(0).getNextTime()) {
            CronTask task = tasks.get(0);
            tasks.remove(0);

            if (task != null) {
                executor.execute(task);

                if (task.restart())
                    addTask(false, task);
            }
        }
    }

    /**
     * Waits until the next task
     */
    protected void waitToNext() {
        if (!tasks.isEmpty()) {
            long diff = tasks.get(0).getNextTime() - System.currentTimeMillis();
            try {
                Thread.sleep(diff);
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Returns the JCron system for this JVM
     * @return JCron Instance
     */
    public static JCron getInstance(){return INSTANCE;}
}
