
package main.taskio;

import main.Task;
import main.TaskPriority;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//this class uses file IO, can read a task object from a stream, either file or socket
public class TaskReader {
    
    private DataInputStream in;
    
    public TaskReader(DataInputStream in){
        this.in = in;
    }
    
    //read in all of the attributes of the task one by one and initialize the object
    //it's important that the order of these do not change
    public Task loadTask(){
        try {
            Task t = new Task(in.readUTF());
            t.setNotes(in.readUTF());
            Date dueDate = new Date();
            dueDate.setTime(in.readLong());
            t.setDueDate(dueDate);
            if(in.readBoolean()){ t.completeTask(); }
            t.setTaskPriority(TaskPriority.values()[in.readInt()]);
            int fCount = in.readInt();
            for(int i = 0; i < fCount; i++){
                t.addAttachedFile(new File(in.readUTF()));
            }
            return t;
        } catch (EOFException ex) {
            return null;
        } catch (IOException ex) {
            Logger.getLogger(TaskReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}