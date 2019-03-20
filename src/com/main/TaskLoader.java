
package com.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskLoader {
    
    private final ArrayList<Filter> filters;
    private final TaskPool taskPool;
    
    public TaskLoader(TaskPool taskPool){
        this.taskPool = taskPool;
        this.filters = new ArrayList<>();
    }
    
    public void clearFilters(){ filters.clear(); }
    public void addFilter(Filter f){ filters.add(f); }
    
    private ArrayList<Task> getTasks(){
        Stream<Task> taskStream = taskPool.getTasks();
        for(Filter f : filters){
            taskStream = taskStream.filter(f::shouldAllow);
        }
        ArrayList<Task> ts = taskStream.collect(Collectors.toCollection(ArrayList::new));
        
        //temporary function for testing purposes
        Collections.shuffle(ts);
        
        return ts;
    }
    
    public TaskDisplay createDisplay(){
        return new TaskDisplay(getTasks());
    }
}
