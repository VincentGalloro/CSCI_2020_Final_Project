
package com.main;

import com.main.taskio.TaskReader;
import com.main.taskio.TaskWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskPool {
    
    private final ArrayList<Task> tasks;
    
    public TaskPool(){
        tasks = new ArrayList<>();
    }
    
    public void save(){
        try {
            DataOutputStream fout = new DataOutputStream(new FileOutputStream(new File("task_saves.txt")));
            TaskWriter tw = new TaskWriter(fout);
            getTasks().forEach(tw::write);
            fout.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
    }
    public void load(){
        File f = new File("task_saves.txt");
        if(!f.exists()){ return; }
        try {
            DataInputStream fin = new DataInputStream(new FileInputStream(f));
            TaskReader tr = new TaskReader(fin);
            while(true){
                Task t = tr.loadTask();
                if(t == null){ break; }
                tasks.add(t);
            }
            fin.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
    }
    
    public void addTask(Task t){ tasks.add(t); }
    
    public Stream<Task> getTasks(){ return tasks.stream(); }
}
