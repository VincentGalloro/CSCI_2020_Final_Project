
package main.filters;

import main.Task;
import main.TaskPriority;

//this filter allows tasks to pass that match the priority level
public class PriorityFilter implements Filter{

    private TaskPriority priority;
    
    public PriorityFilter(TaskPriority priority){
        this.priority = priority;
    }
    
    //check the tasks priority to see if it should pass
    public boolean shouldAllow(Task t) {
        return t.getPriority().equals(priority);
    }
}
