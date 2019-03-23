
package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    
    private final String name;
    private String notes;
    private BufferedImage thumbnail;
    private File attachedFile;
    private ArrayList<File> attachedFiles;
    private Date dueDate, completionDate;
    private boolean isCompleted;
    private TaskPriority priority;
    
    public Task(String name){
        this.name = name;
        this.notes = "";
        this.priority = TaskPriority.LOW;
        this.dueDate = new Date();
    }
    
    public void setNotes(String notes){ this.notes = notes; }
    public void setThumbnail(BufferedImage thumbnail){ this.thumbnail = thumbnail; }
    public void setAttachedFile(File attachedFile){ this.attachedFiles.add(attachedFile); }
    public void setDueDate(Date dueDate){ this.dueDate = dueDate; }
    public void setTaskPriority(TaskPriority priority){ this.priority = priority; }
    
    public void completeTask(){
        this.isCompleted = true;
        this.completionDate = new Date();
    }
    public void uncompleteTask(){
        this.isCompleted = false;
    }
    
    public String getName(){ return name; }
    public String getNotes(){ return notes; }
    public BufferedImage getThumbnail(){ return thumbnail; }
    public ArrayList<File> getAttachedFiles(){ return attachedFiles; }
    public Date getDueDate(){ return dueDate; }
    public Date getCompletionDate(){ return completionDate; }
    public boolean isCompleted(){ return isCompleted; }
    public TaskPriority getPriority(){ return priority; }
    
    public String toString(){
        return "Task: "+name+"  Due Date: "+dueDate+"  Priority: "+priority+"  Notes: "+notes;
    }
}
