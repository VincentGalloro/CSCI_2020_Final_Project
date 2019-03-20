import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class DisplayTask extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: rgb(253,255,226)");
        root.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.8));
        root.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.8));
        root.setPadding(new Insets(5, 5, 5, 5));


        //TODO  method to get task name and due date

        //Flow pane to display Task name and due date
        HBox hb1 = new HBox();
        hb1.prefWidthProperty().bind(root.widthProperty());
        hb1.setStyle("-fx-border-color: #000000;-fx-border-radius: 5");
        Label lTaskName = new Label("This is the name of the task");
        lTaskName.prefWidthProperty().bind(root.widthProperty().multiply(0.6));

        Label lDue = new Label("DUE: ");
        lDue.setAlignment(Pos.CENTER_RIGHT);
        lDue.prefWidthProperty().bind(root.widthProperty().multiply(0.15));

        Label lDueDate = new Label("Due date");
        lDueDate.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
        hb1.getChildren().addAll(lTaskName,lDue, lDueDate);

        //TODO  method to find due time
        //TODO change this to text instead of label
        Label lDueIn = new Label("this task is due in _ days");

        Label lNotes = new Label("Notes");
        lNotes.setStyle("-fx-border-color: black;");
        lNotes.setPadding(new Insets(2,2,2,2));
        lNotes.setStyle("-fx-border-color: #000000;-fx-border-radius: 7");

        //TODO -method to get text
        Label lDisplayNotes = new Label();
        lDisplayNotes.prefWidthProperty().bind(root.widthProperty());
        lDisplayNotes.prefHeightProperty().bind(root.heightProperty().multiply(0.3));
        lDisplayNotes.setText("this is line 1 \n this is line 2 \n this is line 3\n this is line 4\n this is line 5 ");
        lDisplayNotes.setAlignment(Pos.TOP_LEFT);
        lDisplayNotes.setStyle("-fx-border-color: #000000; -fx-background-color: rgb(209,193,245)");




        root.getChildren().addAll(hb1, lDueIn, lNotes, lDisplayNotes);



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
