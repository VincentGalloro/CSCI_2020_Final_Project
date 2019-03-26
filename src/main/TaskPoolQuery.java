
package main;

import main.filters.Filter;
import main.sorters.DateSorter;
import main.sorters.Sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskPoolQuery {
    
    private final ArrayList<Filter> filters;
    private Sorter sorter;
    private final TaskPool taskPool;
    
    public TaskPoolQuery(TaskPool taskPool){
        this.taskPool = taskPool;
        this.filters = new ArrayList<>();
        sorter = new DateSorter(); //default sorter
    }
    
    public void clearFilters(){ filters.clear(); }
    public void addFilter(Filter f){ filters.add(f); }
    
    public void setSorter(Sorter s){ sorter = s; }
    
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
