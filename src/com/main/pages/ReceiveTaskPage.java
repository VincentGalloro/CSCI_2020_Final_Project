package com.main.pages;

import com.main.TaskPool;
import com.main.taskio.TaskServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReceiveTaskPage{

    private TextArea area;
    private TaskServer server;
    
    public ReceiveTaskPage(TaskPool taskPool){
        server = new TaskServer(taskPool, this::writeLine);
    }
    
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
    
    public void writeLine(String s){
        Platform.runLater(() -> area.setText(area.getText() + s + "\n"));
    }
    
    /*public static void main(String[] args){
        Application.launch(args);
    }*/
}