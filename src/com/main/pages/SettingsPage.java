package com.main.pages;

import com.main.TaskPoolQuery;
import com.main.pages.HomePage;
import static com.main.TaskPriority.*;
import com.main.filters.PriorityFilter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingsPage {
    
    private GridPane filtersPane;
    private final TaskPoolQuery TPQ;
    private Runnable refresher;
    
    public SettingsPage(TaskPoolQuery TPQ, Runnable refresher){
        filtersPane = new GridPane();
        this.TPQ = TPQ;
        this.refresher = refresher;
    }
    
    public void start(Stage stage){ 
        filtersPane.setPadding(new Insets(10, 10, 10, 10));
        filtersPane.setHgap(10);
        filtersPane.setVgap(10);
        
        Label l = new Label("Filter by:");
        l.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        filtersPane.add(l, 0, 0);

        Button btHighPriority = new Button("High Priority");
        Button btMediumPrioirty = new Button("Medium Priority");
        Button btLowPriority= new Button("Low Priority");
        filtersPane.add(btHighPriority, 0, 1);
        filtersPane.add(btMediumPrioirty, 1, 1);
        filtersPane.add(btLowPriority, 2, 1);
        
        btLowPriority.setOnMousePressed(e ->{
            PriorityFilter pf = new PriorityFilter(LOW);
            TPQ.clearFilters();
            TPQ.addFilter(pf);
            
            refresher.run();
        });
        
         btMediumPrioirty.setOnMousePressed(e ->{
            PriorityFilter pf = new PriorityFilter(MEDIUM);
            TPQ.clearFilters();
            TPQ.addFilter(pf);
            
            refresher.run();
        });
                
        btHighPriority.setOnMousePressed(e ->{
            PriorityFilter pf = new PriorityFilter(HIGH);
            TPQ.clearFilters();
            TPQ.addFilter(pf);
            
            refresher.run();
        });
        
        Scene scene = new Scene(filtersPane, 370, 100);
        stage.setScene(scene);
        stage.show();
    }
}