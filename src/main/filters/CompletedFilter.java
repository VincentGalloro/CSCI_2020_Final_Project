package main.filters;

import main.Task;

//this filter checks if a task is completed
public class CompletedFilter implements Filter{

    private final boolean isCompleted;
    
    public CompletedFilter(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
    
    //allow the task if it's completion status matches
    public boolean shouldAllow(Task t) {
        return t.isCompleted() == isCompleted;
    }
}