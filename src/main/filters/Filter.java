
package main.filters;

import main.Task;

//filter objects decide if tasks should be kept or filtered out
//this is used by the TaskPoolQuery class
public interface Filter {
    
    public boolean shouldAllow(Task t);
}
