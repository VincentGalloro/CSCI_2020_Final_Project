
package main.sorters;

import main.Task;
import java.util.Comparator;

public interface Sorter {
    
    public Comparator<Task> getComparator();
}
