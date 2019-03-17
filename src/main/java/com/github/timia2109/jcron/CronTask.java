package com.github.timia2109.jcron;

/**
 * Interface that represent a CronTask <br>
 *     The execution can be scheduled
 */
public interface CronTask {
    /**
     * Returns if this task should be repeated.
     * When it should repeated then calculate the next execution time in this method!
     * Runs always in JCron Thread
     *
     * @return true, if this task should be repeated!
     */
    public boolean restart();

    /**
     * Return the time mills when this task should executed.
     * This value should only changed in constructor, in method <code>restart()</code> or before calling <code>JCron.getInstance().reorder()</code><br>
     *     Run always in JCron Thread
     * @return Timemills for the next execution
     */
    public long getNextTime();

    /**
     * Executes the task. <br>
     *     May executed in new Thread or in JCron Thread
     */
    public void exec();
}
