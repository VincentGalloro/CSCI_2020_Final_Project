
package main.sorters;

import main.Task;
import java.util.Comparator;

//this class sorts the tasks based on priority
public class PrioritySorter implements Sorter{
    
    public Comparator<Task> getComparator(){
        return (t1, t2) -> t2.getPriority().ordinal() - t1.getPriority().ordinal();
    }
}
