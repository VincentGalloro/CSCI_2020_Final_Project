package com.main.pages;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingsPage {

    public SettingsPage(){
        
    }
    
    public void start(Stage stage){
        Pane pane = new Pane();
        
        Label l = new Label("Settings");
        
        pane.getChildren().add(l);
        
        stage.setScene(new Scene(pane));
        stage.show();
    }
}