package com.main.pages;

import com.main.TaskPaneGenerator;
import com.main.TaskPoolQuery;
import com.main.TaskPool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//This class displays the GUI for the homepage of the app
public class HomePage{
    
    private final String name;
    private final TaskPool taskPool;
    private final TaskPoolQuery taskPoolQuery;
    private BorderPane mainPane;
    private GridPane tasksPane;

    public HomePage(String name){
        this.name = name;
        taskPool = new TaskPool(name);
        taskPool.load();
        taskPoolQuery = new TaskPoolQuery(taskPool);
        mainPane = new BorderPane();
        tasksPane = new GridPane();
    }
    
    public void start(Stage primaryStage){
        //This pane holds the pane for buttons
        BorderPane holdButtonsPane = new BorderPane();
        Button btSendTask = new Button("Send Friend Task");
        Button btReceiveTask = new Button("Receive Friend Task");
        ImageView settingsButton = new ImageView(new Image("file:images/settingsButton2.png"));
        ImageView addTaskButton = new ImageView(new Image("file:images/addTaskButton.png"));
        
        //These buttons deal with the fuctionality of each main task featues
        addTaskButton.setOnMousePressed(e -> {
            CreateTaskPage ctp = new CreateTaskPage(taskPool, this::refresh);  
            Stage stage = new Stage();
            ctp.start(stage);
        });
        btSendTask.setOnMousePressed(e -> {
            SendTaskPage stp = new SendTaskPage(name, taskPoolQuery.getTasks());
            Stage stage = new Stage();
            stp.start(stage);
        });
        btReceiveTask.setOnMousePressed(e -> {
            ReceiveTaskPage rtp = new ReceiveTaskPage(taskPool, this::refresh);
            Stage stage = new Stage();
            rtp.start(stage);
        });
	settingsButton.setOnMousePressed(e -> {
            SettingsPage sp = new SettingsPage(taskPoolQuery, this::refresh);
            Stage stage = new Stage();
            sp.start(stage);
        });
        
        //This pane has the settings and add new task buttons
        GridPane buttonsPane = new GridPane(); 
        buttonsPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        buttonsPane.setHgap(10);
        buttonsPane.add(btSendTask, 0, 0);
        buttonsPane.add(btReceiveTask, 1, 0);
        buttonsPane.add(settingsButton, 2, 0);
        buttonsPane.add(addTaskButton, 3, 0);
        holdButtonsPane.setRight(buttonsPane);

        //This is the pane to hold tasks
        Label lblTaskHeader = new Label("Tasks ("+name+")");
        tasksPane.setHalignment(lblTaskHeader, HPos.CENTER);
        lblTaskHeader.setAlignment(Pos.CENTER);
        lblTaskHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 40");
        tasksPane.add(lblTaskHeader, 0, 0);
        
        refresh();
        
        //Add GridPanes to BorderPane
        mainPane.setBottom(holdButtonsPane);
                
        Scene scene = new Scene(mainPane, 450, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //This function updates the GUI everytime a task has a change in any state
    public void refresh(){
        Platform.runLater(() -> {
            TaskPaneGenerator tpg = new TaskPaneGenerator(taskPoolQuery.getTasks(), this::refresh);
            tasksPane.add(tpg.generateTaskPane(), 0, 1);
            mainPane.setCenter(tasksPane);
        });
    }
    
    //This function saves the current tasks to the disk
    public void stop(){
        taskPool.save();
    }
}
