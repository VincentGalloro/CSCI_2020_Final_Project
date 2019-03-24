package com.main.pages;

import com.main.TaskPoolQuery;
import com.main.TaskPriority;
import com.main.filters.Filter;
import com.main.filters.PriorityFilter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
            applyFilter(new PriorityFilter(TaskPriority.LOW));
        });
        btMediumPrioirty.setOnMousePressed(e ->{
            applyFilter(new PriorityFilter(TaskPriority.MEDIUM));
        });        
        btHighPriority.setOnMousePressed(e ->{
            applyFilter(new PriorityFilter(TaskPriority.HIGH));
        });
        
        Scene scene = new Scene(filtersPane, 370, 100);
        stage.setScene(scene);
        stage.show();
    }
    
    public void applyFilter(Filter f){        
        TPQ.clearFilters();
        TPQ.addFilter(f);
        refresher.run();
    }
}