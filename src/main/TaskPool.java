
package main;

import main.taskio.TaskReader;
import main.taskio.TaskWriter;
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
    
    private final String name;
    private final ArrayList<Task> tasks;
    
    public TaskPool(String name){
        this.name = name;
        tasks = new ArrayList<>();
    }
    
    public void save(){
        try {
            DataOutputStream fout = new DataOutputStream(new FileOutputStream(new File("tasks_"+name+".txt")));
            TaskWriter tw = new TaskWriter(fout);
            getTasks().forEach(tw::write);
            fout.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
    }
    public void load(){
        File f = new File("tasks_"+name+".txt");
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
