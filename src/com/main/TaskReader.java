
package com.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class TaskReader {
    
    private InputStream in;
    
    public TaskReader(InputStream in){
        this.in = in;
    }
    
    private Integer readHeader(){
        String header = "";
        try {
            while(true){
                int b = in.read();
                if(b == -1){ return null; }
                if(b == (byte)':'){ break; }
                header += (char)b;
            }
        } catch (IOException ex) {}
        return Integer.parseInt(header);
    }
    private byte[] read(int len){
        byte[] b = new byte[len];
        try {
            in.read(b);
        } catch (IOException ex) {}
        return b;
    }
    private String readString(int len){ return new String(read(len)); }
    private long readLong(int len){ return Long.parseLong(readString(len)); }
    private boolean readBoolean(){ return read(1)[0]==1; }
    
    public Task loadTask(){
        Integer nameHeader = readHeader();
        if(nameHeader == null){ return null; }
        
        Task t = new Task(readString(nameHeader));
        t.setNotes(readString(readHeader()));
        Date dueDate = new Date();
        dueDate.setTime(readLong(readHeader()));
        t.setDueDate(dueDate);
        if(readBoolean()){ t.completeTask(); }
        t.setTaskPriority(TaskPriority.values()[(int)readLong(readHeader())]);
        
        return t;
    }
}
