
package com.main.pages;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPage extends Application{
    
    private TextField nameField;
    private HomePage hp;
    
    public LoginPage(){
        this.nameField = new TextField();
    }
    
    public void start(Stage stage){
        Pane pane = new Pane();
        
        Label l = new Label("Enter your name");
        Button b = new Button("Login");
        
        b.setOnMouseClicked(e -> login(stage));
        nameField.setOnKeyPressed(e -> { if(e.getCode()==KeyCode.ENTER){ login(stage); } });
        
        HBox box = new HBox();
        box.getChildren().addAll(l, nameField, b);
        box.setSpacing(10);
        box.setPrefSize(400, 180);
        box.setAlignment(Pos.CENTER);
        
        pane.getChildren().add(box);
        
        stage.setScene(new Scene(pane));
        stage.show();
    }
    
    public void login(Stage stage){
        stage.close();
        hp = new HomePage(nameField.getText());
        hp.start(new Stage());
    }
    
    public void stop(){
        if(hp != null){
            hp.stop();
        }
    }
    
    public static void main(String[] args){
        Application.launch(args);
    }
}
