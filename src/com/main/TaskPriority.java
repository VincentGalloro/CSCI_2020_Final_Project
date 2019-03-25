
package com.main;

import javafx.scene.paint.Color;
public enum TaskPriority {
    LOW (Color.GREEN, "GREENYELLOW"),
    MEDIUM (Color.BLUE, "YELLOW"),
    HIGH (Color.RED, "RED");
    
    public Color color;
    public String cName;
    
    TaskPriority(Color c, String cName){
        this.color = c;
        this.cName = cName;
    }
}
