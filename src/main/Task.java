
package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

//The task object is the core of the program. Most parts of the UI/backend interface with
//this object. It stores all the details of one task
public class Task {
    
    private String name;
    private String notes;
    private final ArrayList<File> attachedFiles;
    private Date dueDate, completionDate;
    private boolean isCompleted;
    private TaskPriority priority;
    
    public Task(String name){
        this.name = name;
        this.notes = "";
        this.priority = TaskPriority.LOW;
        this.dueDate = new Date();
        this.attachedFiles = new ArrayList<>();
    }
    
    //setter functions
    public void setTaskName(String name){this.name = name;}
    public void setNotes(String notes){ this.notes = notes; }
    public void addAttachedFile(File attachedFile){ this.attachedFiles.add(attachedFile); }
    public void setDueDate(Date dueDate){ this.dueDate = dueDate; }
    public void setTaskPriority(TaskPriority priority){ this.priority = priority; }
    
    //the isCompleted setter also marks the date that the task was completed
    public void completeTask(){
        this.isCompleted = true;
        this.completionDate = new Date();
    }
    public void uncompleteTask(){
        this.isCompleted = false;
    }
    
    //getter functions
    public String getName(){ return name; }
    public String getNotes(){ return notes; }
    public ArrayList<File> getAttachedFiles(){ return attachedFiles; }
    public Date getDueDate(){ return dueDate; }
    public Date getCompletionDate(){ return completionDate; }
    public boolean isCompleted(){ return isCompleted; }
    public TaskPriority getPriority(){ return priority; }
    
    //toString function
    public String toString(){
        return "Task: "+name+"  Due Date: "+dueDate+"  Priority: "+priority+"  Notes: "+notes;
    }
}