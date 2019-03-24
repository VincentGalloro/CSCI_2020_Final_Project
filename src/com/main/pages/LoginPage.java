
package com.main.pages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginPage extends Application{
    
    private TextField nameField;
    private HomePage hp;
    
    public LoginPage(){
        this.nameField = new TextField();
    }
    
    public void start(Stage stage){
        StackPane pane = new StackPane();
        
        ImageView splash = new ImageView(new Image("file:Images/splash2.png"));
        
        Button b = new Button("Login");
        b.setFont(Font.font(20));
        nameField.setFont(Font.font(20));
        nameField.setPrefColumnCount(14);
        
        b.setOnMouseClicked(e -> login(stage));
        nameField.setOnKeyPressed(e -> { if(e.getCode()==KeyCode.ENTER){ login(stage); } });
        
        HBox box = new HBox();
        box.getChildren().addAll(nameField, b);
        box.setSpacing(10);
        //box.setAlignment(Pos.CENTER);
        box.setLayoutY(220);
        box.setLayoutX(145);
        Pane p1 = new Pane(box);
        
        pane.getChildren().addAll(splash, p1);
        
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
