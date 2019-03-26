package main;

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
import main.pages.SingleTaskPage;

//This class generates the pane that will display all the tasks on the homepage
public class TaskPaneGenerator {
    
    private ArrayList<Task> tasks;
    private Runnable refresher;

    public TaskPaneGenerator(ArrayList<Task> tasks, Runnable refresher){
        this.tasks = tasks;
        this.refresher = refresher;
    }
    
    //this function returns the list of tasks in a scrollpane
    public ScrollPane generateTaskPane(){
        ScrollPane paneGenerator = new ScrollPane();
        paneGenerator.setFitToWidth(true);

        paneGenerator.setPadding(new Insets(15, 15, 15, 15));

        VBox verticalList = new VBox();
        verticalList.setSpacing(20);
        verticalList.setPrefWidth(410);

        //Adds each task to the vertical task list
        for(Task t: tasks){
            verticalList.getChildren().add(generateSingleTaskPane(t));
        }
        paneGenerator.setContent(verticalList);

        return paneGenerator;
    }
    
    //This function returns a single task including the name of the task, due
    //date, and buttons to customize
    public HBox generateSingleTaskPane(Task t){
        HBox singleTask = new HBox();
        singleTask.setSpacing(30);
        VBox taskDetails = new VBox();
        taskDetails.setPadding(new Insets(5, 5, 5, 5));
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
        taskDetails.getChildren().addAll(lblTaskName, lblDueDate);
        singleTask.getChildren().addAll(taskDetails, btEdit, taskState);
        singleTask.setAlignment(Pos.CENTER);
        String color = t.getPriority().cName;
        singleTask.setStyle("-fx-border-color: black; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: "+color);
        
        return singleTask;
    }
    
    //This function links the edit button to the edit window
    public void linkEditButton(Task t, Button btEdit){
        btEdit.setOnMousePressed(e -> {
            SingleTaskPage dtp = new SingleTaskPage(t, refresher);  
            Stage stage = new Stage();
            dtp.start(stage);
        });
    }
    
    //This function links the task completion checkboxes with the state of the task
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
