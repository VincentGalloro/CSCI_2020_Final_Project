package com.main;

import com.main.pages.SingleTaskPage;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskPaneGenerator {
    
    private ArrayList<Task> tasks;
    private Runnable refresher;
    private TableView<Task> Tasks;

    public TaskPaneGenerator(ArrayList<Task> tasks, Runnable refresher){
        this.tasks = tasks;
        this.refresher = refresher;
    }
    
    public BorderPane generateTaskPane(){
        //aabid implement this function!!
        //you can change pane to a more relevant type if you want
        BorderPane paneGenerator = new BorderPane();
       
        //create new table
        TableColumn<Task ,String> taskname = new TableColumn<>("Task");
        taskname.setPrefWidth(100);
        taskname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task ,Float> duedate = new TableColumn<>("Due Date");
        duedate.setPrefWidth(210);
        duedate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn <Task, Button> edit = new TableColumn<>("Action");
        edit.setPrefWidth(70);
        edit.setCellValueFactory(new PropertyValueFactory<>("button"));
        
        /*
        taskname.setStyle("-fx-background-color: 		PALEGOLDENROD");
        duedate.setStyle("-fx-background-color: 		PALEGOLDENROD");
        edit.setStyle("-fx-background-color: 		PALEGOLDENROD");
        */
        
        //add the columns to the table
        this.Tasks = new TableView<>();
        Tasks.setStyle("-fx-table-cell-border-color: transparent;");
        this.Tasks.getColumns().addAll(taskname, duedate, edit);
       
        //Link edit button to task
        for(Task t : tasks){
            linkEditButton(t);
        }
        paneGenerator.setLeft(Tasks);

        this.Tasks.setItems(GatherData.getAllTasks(tasks));
        return paneGenerator; //also change this line
    }
    
    public void linkEditButton(Task t){
        t.getButton().setOnMousePressed(e -> {
            SingleTaskPage dtp = new SingleTaskPage(t, refresher);  
            Stage stage = new Stage();
            dtp.start(stage);
        });
    }
}
