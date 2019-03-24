package com.main;

import com.main.pages.SingleTaskPage;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    
    public VBox generateSingleTaskPane(Task t){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 5, 5));
        Label lblTaskName = new Label("Task: " + t.getName());
        lblTaskName.setStyle("-fx-font-weight: bold");
        Label lblDueDate = new Label("Due date: " + t.getDueDate());
        lblDueDate.setStyle("-fx-font-weight: bold");
        Button btEdit = new Button("Edit");
        linkEditButton(t, btEdit);
        vBox.getChildren().addAll(lblTaskName, lblDueDate, btEdit);
        vBox.setStyle("-fx-border-color: black; -fx-background-color: ALICEBLUE; -fx-border-radius: 10");

        return vBox;
    }
    
    public void linkEditButton(Task t, Button btEdit){
        btEdit.setOnMousePressed(e -> {
            SingleTaskPage dtp = new SingleTaskPage(t, refresher);  
            Stage stage = new Stage();
            dtp.start(stage);
        });
    }
}
