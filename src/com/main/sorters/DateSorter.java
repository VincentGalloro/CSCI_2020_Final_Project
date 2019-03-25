
package com.main.sorters;

import com.main.Task;
import java.util.Comparator;

public class DateSorter implements Sorter{

    public Comparator<Task> getComparator() {
        return (t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate());
    }
}
