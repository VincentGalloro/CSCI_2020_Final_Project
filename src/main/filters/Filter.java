
package main.filters;

import main.Task;

public interface Filter {
    
    public boolean shouldAllow(Task t);
}
