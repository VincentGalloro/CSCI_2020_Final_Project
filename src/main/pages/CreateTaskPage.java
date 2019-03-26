
package main.pages;

import main.Task;
import main.TaskPool;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//this page displays the basic popup which lets you enter a task name
//after entering, it launches the SingleTaskPage window
public class CreateTaskPage{
    
    private final TaskPool taskPool;
    private TextField nameField;
    private Runnable refresher;
    
    public CreateTaskPage(TaskPool taskPool, Runnable refresher){
        this.taskPool = taskPool;
        this.refresher = refresher;
        nameField = new TextField();
    }
    
    //set up the UI
    public void start(Stage stage){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        Button okButton = new Button("Create Task");
        
        //this runnable will create the task, add it to the pool
        //and launch the single task page with the task
        Runnable create = () -> {
            Task t = new Task(nameField.getText());
            taskPool.addTask(t);
            refresher.run();
            
            Stage s = new Stage();
            SingleTaskPage stp = new SingleTaskPage(t, refresher);
            stp.start(stage);
        };
        
        //call create if "create" is clicked, or enter is pressed
        okButton.setOnMouseClicked(e -> create.run());
        nameField.setOnKeyPressed(e -> { if(e.getCode() == KeyCode.ENTER){ create.run(); } });
        
        pane.add(new Label("Task name: "), 0, 0);
        pane.add(nameField, 1, 0);
        pane.add(okButton, 1, 1);
        
        stage.setScene(new Scene(pane));
        stage.show();
    }
}