
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
        duedate.setPrefWidth(100);
        duedate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        //add the columns to the table
        this.Tasks = new TableView<>();
        this.Tasks.getColumns().add(taskname);
        this.Tasks.getColumns().add(duedate);
        
        paneGenerator.setCenter(Tasks);
        
        VBox vBox = new VBox();
        for(Task t : tasks){
            vBox.getChildren().add(generateSingleTaskPane(t));
        }
        paneGenerator.setRight(vBox);
        this.Tasks.setItems(GatherData.getAllMarks(tasks));
        return paneGenerator; //also change this line
    }
    
    public GridPane generateSingleTaskPane(Task t){
        //aabid implement this function!!
        //again, you can change pane if you have a better type in mind
        GridPane taskPane = new GridPane();
        Button btEdit = new Button("Edit Task");
        btEdit.setOnMousePressed(e -> {
            SingleTaskPage dtp = new SingleTaskPage(t, refresher);  
            Stage stage = new Stage();
            dtp.start(stage);
        });
        btEdit.widthProperty();
        taskPane.add(btEdit, 0, 1);
        return taskPane;
    }
}
