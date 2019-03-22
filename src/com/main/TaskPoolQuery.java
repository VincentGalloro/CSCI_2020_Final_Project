
package com.main;

import com.main.filters.Filter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskPoolQuery {
    
    private final ArrayList<Filter> filters;
    private final TaskPool taskPool;
    
    public TaskPoolQuery(TaskPool taskPool){
        this.taskPool = taskPool;
        this.filters = new ArrayList<>();
    }
    
    public void clearFilters(){ filters.clear(); }
    public void addFilter(Filter f){ filters.add(f); }
    
    public ArrayList<Task> getTasks(){
        Stream<Task> taskStream = taskPool.getTasks();
        for(Filter f : filters){
            taskStream = taskStream.filter(f::shouldAllow);
        }
        ArrayList<Task> ts = taskStream.collect(Collectors.toCollection(ArrayList::new));
        
        //temporary function for testing purposes
        Collections.shuffle(ts);
        
        return ts;
    }
}
