
package com.main.pages;

import com.main.Task;
import com.main.TaskPool;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateTaskPage{
    
    private final TaskPool taskPool;
    private TextField nameField;
    private Runnable refresher;
    
    public CreateTaskPage(TaskPool taskPool, Runnable refresher){
        this.taskPool = taskPool;
        this.refresher = refresher;
        nameField = new TextField();
    }
    
    public void start(Stage stage){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        Button okButton = new Button("Create Task");
        
        Runnable create = () -> {
            Task t = new Task(nameField.getText());
            taskPool.addTask(t);
            refresher.run();
            
            Stage s = new Stage();
            SingleTaskPage stp = new SingleTaskPage(t, refresher);
            stp.start(stage);
        };
        
        okButton.setOnMouseClicked(e -> create.run());
        nameField.setOnKeyPressed(e -> { if(e.getCode() == KeyCode.ENTER){ create.run(); } });
        
        pane.add(new Label("Task name: "), 0, 0);
        pane.add(nameField, 1, 0);
        pane.add(okButton, 1, 1);
        
        stage.setScene(new Scene(pane));
        stage.show();
    }
}