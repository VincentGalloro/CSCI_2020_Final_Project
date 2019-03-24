package com.main.filters;

import com.main.Task;

public class CompletedFilter implements Filter{

    private final boolean isCompleted;
    
    public CompletedFilter(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
    
    public boolean shouldAllow(Task t) {
        return t.isCompleted() == isCompleted;
    }
}