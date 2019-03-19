
package com.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class HomePage extends Application{
    
    private TextField nameField, notesField;
    
    public HomePage(){
        nameField = new TextField();
        notesField = new TextField();
    }
    
    public void start(Stage primaryStage){
        BorderPane mainPane = new BorderPane();
        
        //Pane that holds the pane for buttons
        BorderPane holdButtonsPane = new BorderPane();
        ImageView settingsButton = new ImageView(new Image("file:///C:/Users/Aabid Mitha/OneDrive/CSCI_2020_Final_Project/CSCI_2020_Final_Project/Images/settingsButton.png"));
        ImageView addTaskButton = new ImageView(new Image("file:///C:/Users/Aabid Mitha/OneDrive/CSCI_2020_Final_Project/CSCI_2020_Final_Project/Images/addTaskButton.png"));
        
        addTaskButton.setOnMousePressed(e -> {
            CreateTaskPage tap = new CreateTaskPage();  
            Stage stage = new Stage();
            tap.start(stage);
        });
        
        //Pane that has the settings and add new task buttons
        GridPane buttonsPane = new GridPane(); 
        buttonsPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        buttonsPane.setHgap(10);
        buttonsPane.add(settingsButton, 0, 0);
        buttonsPane.add(addTaskButton, 1, 0);
        holdButtonsPane.setRight(buttonsPane);
        
        //Add GridPanes to BorderPane
        mainPane.setBottom(holdButtonsPane);
        
        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args){
        Application.launch(args);
    }
}
