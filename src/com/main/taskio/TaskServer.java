package com.main.taskio;

import com.main.Task;
import com.main.TaskPool;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskServer implements Runnable{

    private Consumer<String> log;
    private TaskPool taskPool;
    private ServerSocket server;
    
    public TaskServer(TaskPool taskPool, Consumer<String> log){
        this.taskPool = taskPool;
        this.log = log;
    }

    public void run() {
        try {
            server = new ServerSocket(8000);
            log.accept("Server started: "+server.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            log.accept("Server could not start. Exiting");
            return;
        }
        
        try {
            while(true){
                Socket client = server.accept();
                log.accept("Received connection from client");

                DataInputStream sin = new DataInputStream(client.getInputStream());
                String name = sin.readUTF();
                log.accept("Connected to: "+name);

                int taskCount = sin.readInt();
                log.accept(name+" is sending "+taskCount+" tasks");

                TaskReader reader = new TaskReader(sin);
                for(int i = 0; i < taskCount; i++){
                    Task t = reader.loadTask();
                    taskPool.addTask(t);
                    log.accept("Received Task from "+name+": "+t.getName());
                }

                sin.close();

            }
        } catch (IOException ex) {
            log.accept("Something went wrong. Closing server");
        }
    }
    
    public void stop(){
        try {
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}