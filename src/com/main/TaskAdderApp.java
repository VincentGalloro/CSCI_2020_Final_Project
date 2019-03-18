
package com.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TaskAdderApp extends Application{
    
    private TextField nameField, notesField;
    
    public TaskAdderApp(){
        nameField = new TextField();
        notesField = new TextField();
    }
    
    public void start(Stage stage){
        GridPane pane = new GridPane();
        
        Button okButton = new Button("Create Task");
        okButton.setOnMousePressed(e -> {
            Task t = createTask();
            System.out.println(t.toString());
        });
        
        pane.add(new Label("Enter task name: "), 0, 0);
        pane.add(nameField, 1, 0);
        pane.add(new Label("Enter task name: "), 0, 1);
        pane.add(notesField, 1, 1);
        pane.add(okButton, 1, 2);
        
        stage.setScene(new Scene(pane));
        stage.show();
    }
    
    public Task createTask(){
        Task t = new Task(nameField.getText());
        t.setNotes(notesField.getText());
        return t;
    }
    
    public static void main(String[] args){
        Application.launch(args);
    }
}
