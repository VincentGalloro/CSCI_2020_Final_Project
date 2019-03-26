
package main.pages;

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

//the login page is the starting point of the program
public class LoginPage extends Application{
    
    private TextField nameField;
    private HomePage hp;
    
    public LoginPage(){
        this.nameField = new TextField();
    }
    
    //set up the UI
    public void start(Stage stage){
        StackPane pane = new StackPane();
        
        ImageView splash = new ImageView(new Image("file:Images/splash2.png"));
        
        Button b = new Button("Login");
        b.setFont(Font.font(20));
        nameField.setFont(Font.font(20));
        nameField.setPrefColumnCount(14);
        
        //when login is clicked, or enter is pressed, call the login function
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
    
    //this function launches the homepage with the given username, and closes itself
    public void login(Stage stage){
        stage.close();
        hp = new HomePage(nameField.getText());
        hp.start(new Stage());
    }
    
    //when the program is closing, tell the homepage that the program is closing
    public void stop(){
        if(hp != null){
            hp.stop();
        }
    }
    
    //starting point of the program
    public static void main(String[] args){
        Application.launch(args);
    }
}
