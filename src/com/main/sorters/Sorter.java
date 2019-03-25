
package com.main.sorters;

import com.main.Task;
import java.util.Comparator;

public interface Sorter {
    
    public Comparator<Task> getComparator();
}
