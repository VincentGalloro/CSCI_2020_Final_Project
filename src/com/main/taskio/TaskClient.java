
package com.main.taskio;

import com.main.Task;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskClient implements Runnable{
    
    private final ArrayList<Task> tasks;
    private final Consumer<String> log;
    
    public TaskClient(ArrayList<Task> tasks, Consumer<String> log){
        this.tasks = tasks;
        this.log = log;
    }

    public void run() {
        Socket socket = new Socket();
        //socket.connect(new SocketAddress("localhost", 8000));
    }
}
