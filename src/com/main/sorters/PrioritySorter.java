
package com.main.sorters;

import com.main.Task;
import java.util.Comparator;

public class PrioritySorter implements Sorter{
    
    public Comparator<Task> getComparator(){
        return (t1, t2) -> t2.getPriority().ordinal() - t1.getPriority().ordinal();
    }
}
