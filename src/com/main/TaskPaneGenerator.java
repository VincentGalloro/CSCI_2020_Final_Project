package com.main;

import com.main.pages.SingleTaskPage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskPaneGenerator {
    
    private ArrayList<Task> tasks;
    private Runnable refresher;

    public TaskPaneGenerator(ArrayList<Task> tasks, Runnable refresher){
        this.tasks = tasks;
        this.refresher = refresher;
    }
    
    public ScrollPane generateTaskPane(){
        //aabid implement this function!!
        //you can change pane to a more relevant type if you want
        ScrollPane paneGenerator = new ScrollPane();
        paneGenerator.setFitToWidth(true);

        paneGenerator.setPadding(new Insets(15, 15, 15, 15));

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPrefWidth(410);

        //Add each task to pane
        for(Task t: tasks){
            vBox.getChildren().add(generateSingleTaskPane(t));
        }
        paneGenerator.setContent(vBox);

        return paneGenerator; //also change this line
    }
    
    public HBox generateSingleTaskPane(Task t){
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 5, 5));
        Label lblTaskName = new Label("Task: " + t.getName());
        lblTaskName.setStyle("-fx-font-weight: bold");
        lblTaskName.setMinWidth(200);
        lblTaskName.setMaxWidth(200);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(t.getDueDate());
        Label lblDueDate = new Label("Due date: " + date);
        lblDueDate.setMaxWidth(200);
        lblDueDate.setMinWidth(200);
        lblDueDate.setStyle("-fx-font-weight: bold");
        Button btEdit = new Button("Edit");
        linkEditButton(t, btEdit);
        CheckBox taskState = new CheckBox("");
        taskState.setSelected(t.isCompleted());
        linkTaskStateCB(t, taskState);
        vBox.getChildren().addAll(lblTaskName, lblDueDate);
        hBox.getChildren().addAll(vBox, btEdit, taskState);
        hBox.setAlignment(Pos.CENTER);
        String color = t.getPriority().cName;
        hBox.setStyle("-fx-border-color: black; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: "+color);
        
        return hBox;
    }
    
    public void linkEditButton(Task t, Button btEdit){
        btEdit.setOnMousePressed(e -> {
            SingleTaskPage dtp = new SingleTaskPage(t, refresher);  
            Stage stage = new Stage();
            dtp.start(stage);
        });
    }
    
    public void linkTaskStateCB(Task t, CheckBox taskState){
        taskState.setOnMousePressed(e -> {
            if(taskState.isSelected()){
                System.out.println("unchecked");
                t.uncompleteTask();
            }
            else{
                System.out.println("checked");
                t.completeTask();
            }
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    refresher.run();
                } catch (InterruptedException ex) {
                    Logger.getLogger(TaskPaneGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        });
    }
}
