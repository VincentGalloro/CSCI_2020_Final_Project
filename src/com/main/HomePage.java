
package com.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class HomePage extends Application{
    
    private TextField nameField, notesField;
    
    public HomePage(){
        nameField = new TextField();
        notesField = new TextField();
    }
    
    public void start(Stage primaryStage){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        Button okButton = new Button("Create Task");
        okButton.setOnMousePressed(e -> {
            Task t = new Task("Task 1");
            System.out.println(t.toString());
        });
        
        pane.add(new Label("Task name: "), 0, 0);
        pane.add(nameField, 1, 0);
        pane.add(new Label("Due date: "), 0, 1);
        pane.add(notesField, 1, 1);
        pane.add(okButton, 1, 2);
        
        Scene scene = new Scene(pane, 500, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        Application.launch(args);
    }
}
