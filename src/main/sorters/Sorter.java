
package main.sorters;

import main.Task;
import java.util.Comparator;

//sorter classes contain an implementation of a custom comparator
//this is used by the taks pool query to sort lists of tasks
public interface Sorter {
    
    public Comparator<Task> getComparator();
}
