
package com.main;

import java.awt.Color;

public enum TaskPriority {
    LOW (Color.GREEN),
    MEDIUM (Color.BLUE),
    HIGH (Color.RED);
    
    public Color color;
    
    TaskPriority(Color c){
        this.color = c;
    }
}
