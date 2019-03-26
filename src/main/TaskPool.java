
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

//this class stores every current task, it is the central point for task storage
//this includes completed and uncompleted tasks
public class TaskPool {
    
    private final String name;
    private final ArrayList<Task> tasks;
    
    public TaskPool(String name){
        this.name = name;
        tasks = new ArrayList<>();
    }
    
    //write all of the tasks to a save file based on the username entered
    public void save(){
        try {
            DataOutputStream fout = new DataOutputStream(new FileOutputStream(new File("tasks_"+name+".txt")));
            TaskWriter tw = new TaskWriter(fout);
            getTasks().forEach(tw::write);
            fout.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
    }
    //load all of the tasks from a file based on the username enterd
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
    
    //adder function
    public void addTask(Task t){ tasks.add(t); }
    
    //returns a stream of tasks
    public Stream<Task> getTasks(){ return tasks.stream(); }
}
