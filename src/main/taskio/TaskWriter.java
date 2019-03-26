package main.taskio;

import main.Task;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//this class uses file IO, can write a task object to a stream, either file or socket
public class TaskWriter {

    private DataOutputStream out;
    
    public TaskWriter(DataOutputStream out){
        this.out = out;
    }
    
    //write all of the attributes of the task one by one
    //it's important that the order of these do not change
    public void write(Task t){
        try {
            out.writeUTF(t.getName());
            out.writeUTF(t.getNotes());
            out.writeLong(t.getDueDate().getTime());
            out.writeBoolean(t.isCompleted());
            out.writeInt(t.getPriority().ordinal());
            out.writeInt(t.getAttachedFiles().size());
            for(File f : t.getAttachedFiles()){
                out.writeUTF(f.getName());
            }
        } catch (IOException ex) {
            Logger.getLogger(TaskWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}