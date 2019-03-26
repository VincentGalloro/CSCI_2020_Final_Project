package main.pages;

import main.TaskPool;
import main.taskio.TaskServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//this class display a text area and runs the client socket
public class ReceiveTaskPage{

    private TextArea area;
    private TaskServer server;
    
    public ReceiveTaskPage(TaskPool taskPool, Runnable refresher){
        server = new TaskServer(taskPool, this::writeLine, refresher);
    }
    
    //set up the UI
    public void start(Stage stage){
        Pane pane = new Pane();
        
        area = new TextArea();
        area.setFont(Font.font(20));
        area.setEditable(false);
        
        pane.getChildren().add(area);
        
        stage.setOnHiding(e -> server.stop());
        
        stage.setScene(new Scene(pane));
        stage.show();
        
        new Thread(server).start();
    }
    
    //allow a string to be written to the text area as a new line
    public void writeLine(String s){
        Platform.runLater(() -> area.setText(area.getText() + s + "\n"));
    }
}