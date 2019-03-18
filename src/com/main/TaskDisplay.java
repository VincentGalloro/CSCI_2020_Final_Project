
package com.main;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class TaskDisplay {
    
    private ArrayList<Task> tasks;
    
    public TaskDisplay(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    
    public Pane generateDisplay(){
        //aabid implement this function!!
        //you can change pane to a more relevant type if you want
        
        //you can modify the follow too:
        for(Task t : tasks){
            Pane p = generateSingleTaskDisplay(t);
        }
        
        return null; //also change this line
    }
    
    public Pane generateSingleTaskDisplay(Task t){
        //aabid implement this function!!
        //again, you can change pane if you have a better type in mind
        return null;
    }
}
