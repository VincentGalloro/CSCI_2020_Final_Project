package com.main.pages;

import com.main.Task;
import com.main.taskio.TaskClient;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SendTaskPage{

    private TextArea area;
    private TaskClient client;
    
    public SendTaskPage(List<Task> tasks){
        client = new TaskClient(tasks, this::writeLine);
    }
    
    public void start(Stage stage){
        Pane pane = new Pane();
        
        area = new TextArea();
        area.setFont(Font.font(20));
        area.setEditable(false);
        
        pane.getChildren().add(area);
        
        stage.setScene(new Scene(pane));
        stage.show();
        
        new Thread(client).start();
    }
    
    public void writeLine(String s){
        Platform.runLater(() -> area.setText(area.getText() + s + "\n"));
    }
    
    /*public static void main(String[] args){
        Application.launch(args);
    }*/
}