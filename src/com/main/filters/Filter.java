
package com.main.filters;

import com.main.Task;

public interface Filter {
    
    public boolean shouldAllow(Task t);
}
