
package com.main.taskio;

import com.main.Task;
import com.main.TaskPriority;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskReader {
    
    private DataInputStream in;
    
    public TaskReader(DataInputStream in){
        this.in = in;
    }
    
    public Task loadTask(){
        try {
            Task t = new Task(in.readUTF());
            t.setNotes(in.readUTF());
            Date dueDate = new Date();
            dueDate.setTime(in.readLong());
            t.setDueDate(dueDate);
            if(in.readBoolean()){ t.completeTask(); }
            t.setTaskPriority(TaskPriority.values()[in.readInt()]);
            
            return t;
        } catch (EOFException ex) {
            return null;
        } catch (IOException ex) {
            Logger.getLogger(TaskReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
