
package com.main.filters;

import com.main.Task;
import com.main.TaskPriority;

public class PriorityFilter implements Filter{

    private TaskPriority priority;
    
    public PriorityFilter(TaskPriority priority){
        this.priority = priority;
    }
    
    public boolean shouldAllow(Task t) {
        return t.getPriority().equals(priority);
    }
}
