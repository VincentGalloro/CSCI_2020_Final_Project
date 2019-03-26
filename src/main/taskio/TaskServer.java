package main.taskio;

import main.Task;
import main.TaskPool;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

//this class uses a server socket to receive task objects from another user
public class TaskServer implements Runnable{

    private Consumer<String> log;
    private TaskPool taskPool;
    private ServerSocket server;
    private Runnable refresher;
    
    public TaskServer(TaskPool taskPool, Consumer<String> log, Runnable refresher){
        this.taskPool = taskPool;
        //the log allows the server to write to the text area of the page
        this.log = log;
        this.refresher = refresher;
    }

    public void run() {
        //set up the server
        try {
            server = new ServerSocket(12836);
            log.accept("Server started: "+server.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            log.accept("Server could not start. Exiting");
            return;
        }
        
        try {
            while(true){
                //connect with a client
                Socket client = server.accept();
                log.accept("Received connection from client");

                //get the client's username
                DataInputStream sin = new DataInputStream(client.getInputStream());
                String name = sin.readUTF();
                log.accept("Connected to: "+name);

                //get the amount of tasks the user wants to send
                int taskCount = sin.readInt();
                log.accept(name+" is sending "+taskCount+" tasks");

                //read in all of the tasks one by one
                TaskReader reader = new TaskReader(sin);
                for(int i = 0; i < taskCount; i++){
                    Task t = reader.loadTask();
                    taskPool.addTask(t);
                    refresher.run();
                    log.accept("Received Task from "+name+": "+t.getName());
                }

                sin.close();
                //say bye so that the user knows everything went well
                log.accept("Bye "+name);

            }
        } catch (IOException ex) {
            log.accept("Something went wrong. Closing server");
        }
    }
    
    //if interupted, close the server
    public void stop(){
        try {
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}