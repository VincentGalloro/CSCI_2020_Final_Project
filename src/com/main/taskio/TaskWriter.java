package com.main.taskio;

import com.main.Task;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskWriter {

    private DataOutputStream out;
    
    public TaskWriter(DataOutputStream out){
        this.out = out;
    }
    
    public void write(Task t){
        try {
            out.writeUTF(t.getName());
            out.writeUTF(t.getNotes());
            out.writeLong(t.getDueDate().getTime());
            out.writeBoolean(t.isCompleted());
            out.writeInt(t.getPriority().ordinal());
        } catch (IOException ex) {
            Logger.getLogger(TaskWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}