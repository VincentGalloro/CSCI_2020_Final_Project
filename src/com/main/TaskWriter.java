package com.main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskWriter {

    private OutputStream out;
    
    public TaskWriter(OutputStream out){
        this.out = out;
    }
    
    private void write(byte[] b){
        String header = b.length + ":";
        try {
            out.write(header.getBytes());
            out.write(b);
        } catch (IOException ex) {
            Logger.getLogger(TaskWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void write(String s){ write(s.getBytes()); }
    private void write(long l){ write(""+l); }
    private void write(boolean b){ 
        try { 
            out.write(new byte[]{(byte)(b ? 0 : 1)});
        } catch (IOException ex) {}
    }
    
    public void write(Task t){
        write(t.getName());
        write(t.getNotes());
        write(t.getDueDate().getTime());
        write(t.isCompleted());
        write(t.getPriority().ordinal());
    }
    
    public void flush(){ 
        try {
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(TaskWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}