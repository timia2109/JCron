package com.github.timia2109.jcron;

import java.util.Comparator;

/**
 * A CronTask Comperator by next execution time
 */
public class CronTaskComparator implements Comparator<CronTask> {

    @Override
    public int compare(CronTask o1, CronTask o2) {
        return Long.compare(o1.getNextTime(), o2.getNextTime());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CronTaskComparator;
    }
}
