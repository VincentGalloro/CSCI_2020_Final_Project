package main.pages;

import main.Task;
import main.taskio.TaskClient;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//this page displays a text area and runs the server socket
public class SendTaskPage{

    private TextArea area;
    private TaskClient client;
    
    public SendTaskPage(String name, List<Task> tasks){
        client = new TaskClient(name, tasks, this::writeLine);
    }
    
    //set up the UI
    public void start(Stage stage){
        Pane pane = new Pane();
        
        area = new TextArea();
        area.setFont(Font.font(20));
        area.setEditable(false);
        
        pane.getChildren().add(area);
        
        stage.setScene(new Scene(pane));
        stage.show();
        
        new Thread(client).start();
    }
    
    //allow a line to be written to the text area
    public void writeLine(String s){
        Platform.runLater(() -> area.setText(area.getText() + s + "\n"));
    }
}