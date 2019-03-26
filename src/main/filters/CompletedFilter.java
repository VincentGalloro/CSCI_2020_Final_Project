package main.filters;

import main.Task;

public class CompletedFilter implements Filter{

    private final boolean isCompleted;
    
    public CompletedFilter(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
    
    public boolean shouldAllow(Task t) {
        return t.isCompleted() == isCompleted;
    }
}