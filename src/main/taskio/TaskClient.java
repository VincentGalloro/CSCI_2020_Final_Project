
package main.taskio;

import main.Task;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

//the task client uses a client socket to send task objects
public class TaskClient implements Runnable{
    
    private final String name;
    private final List<Task> tasks;
    private final Consumer<String> log;
    
    public TaskClient(String name, List<Task> tasks, Consumer<String> log){
        this.name = name;
        this.tasks = tasks;
        //log allows this class to write to the textarea of the page
        this.log = log;
    }

    public void run() {
        Socket socket;
        //connect to the server
        try {
            socket = new Socket("localhost", 12836);
            log.accept("Connected to Server");
        } catch (IOException ex) {
            ex.printStackTrace();
            log.accept("Could not connect to Server");
            return;
        }
        
        DataOutputStream sout;
        try {
            //write the username of the person using this program
            sout = new DataOutputStream(socket.getOutputStream());
            sout.writeUTF(name);
            
            TaskWriter taskWriter = new TaskWriter(sout);
            
            //write the number of tasks that are going ot be sent
            sout.writeInt(tasks.size());
            //for each task, write it to the stream
            tasks.forEach(task -> {
                log.accept("Sending Task: "+task.getName());
                taskWriter.write(task);
            });
        } catch (IOException ex) {
            Logger.getLogger(TaskClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.accept("Closing");
    }
}
