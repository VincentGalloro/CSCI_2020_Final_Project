
package com.main;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskPool {
    
    private final ArrayList<Task> tasks;
    
    public TaskPool(){
        tasks = new ArrayList<>();
    }
    
    public void addTask(Task t){ tasks.add(t); }
    
    public Stream<Task> getTasks(){ return tasks.stream(); }
}
