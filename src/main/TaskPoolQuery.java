
package main;

import main.filters.Filter;
import main.sorters.DateSorter;
import main.sorters.Sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//this class allows us to perform querys on the task pool
//we can apply filters based on priority, completion status, and more
//this class will return a custom list of filtered and sorter tasks form the pool
public class TaskPoolQuery {
    
    private final ArrayList<Filter> filters;
    private Sorter sorter;
    private final TaskPool taskPool;
    
    public TaskPoolQuery(TaskPool taskPool){
        this.taskPool = taskPool;
        this.filters = new ArrayList<>();
        sorter = new DateSorter(); //default sorter
    }
    
    //these functions are for customizing the filters
    public void clearFilters(){ filters.clear(); }
    public void addFilter(Filter f){ filters.add(f); }
    
    //this function is for customizing the sorter
    public void setSorter(Sorter s){ sorter = s; }
    
    //apply the filters and sorters and return the custom array
    public ArrayList<Task> getTasks(){
        Stream<Task> taskStream = taskPool.getTasks();
        for(Filter f : filters){
            taskStream = taskStream.filter(f::shouldAllow);
        }
        ArrayList<Task> ts = taskStream.collect(Collectors.toCollection(ArrayList::new));
        
        if(sorter != null){
            Collections.sort(ts, sorter.getComparator());
        }
        
        return ts;
    }
}
