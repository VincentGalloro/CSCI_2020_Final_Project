
package com.main;

import javafx.scene.paint.Color;
public enum TaskPriority {
    LOW (Color.GREEN),
    MEDIUM (Color.BLUE),
    HIGH (Color.RED);
    
    public Color color;
    
    TaskPriority(Color c){
        this.color = c;
    }
}
