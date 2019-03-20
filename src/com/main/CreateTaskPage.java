
package com.main;

import com.main.HomePage.Refresher;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateTaskPage{
    
    private final TaskPool taskPool;
    private TextField nameField, notesField;
    private Refresher refresher;
    
    public CreateTaskPage(TaskPool taskPool, Refresher refresher){
        this.taskPool = taskPool;
        this.refresher = refresher;
        nameField = new TextField();
        notesField = new TextField();
        
        /*
        RandomTaskGenerator rtg = new RandomTaskGenerator();
        for(int i = 0; i < 20; i++){
            Task t = rtg.generateSingleTask();
            System.out.println(t);
            taskPool.addTask(t);
        }
        TaskLoader tl = new TaskLoader(taskPool);
        tl.addFilter(new PriorityFilter(TaskPriority.HIGH));
        TaskDisplay td = tl.createDisplay();
        System.out.println();
        td.printTasks();*/
    }
    
    public void start(Stage stage){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        Button okButton = new Button("Create Task");
        okButton.setOnMousePressed(e -> {
            Task t = createTask();
            taskPool.addTask(t);
            refresher.refresh();
        });
        
        pane.add(new Label("Task name: "), 0, 0);
        pane.add(nameField, 1, 0);
        pane.add(new Label("Due date: "), 0, 1);
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
}