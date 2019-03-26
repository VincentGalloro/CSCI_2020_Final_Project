
package main.sorters;

import main.Task;
import java.util.Comparator;

//this class sorts the tasks based up due date
public class DateSorter implements Sorter{

    public Comparator<Task> getComparator() {
        return (t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate());
    }
}
