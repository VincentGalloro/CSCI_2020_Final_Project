package com.main.pages;

import com.main.TaskPoolQuery;
import com.main.TaskPriority;
import com.main.filters.CompletedFilter;
import com.main.filters.DateFilter;
import com.main.filters.Filter;
import com.main.filters.PriorityFilter;
import com.main.sorters.DateSorter;
import com.main.sorters.PrioritySorter;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//This class displayes the settings page that has filters and sorts
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
        filtersPane.setStyle("-fx-background-color: 	CORNFLOWERBLUE");
        filtersPane.setPadding(new Insets(10, 10, 10, 10));
        filtersPane.setHgap(10);
        filtersPane.setVgap(10);
        
        Label filterLabel = new Label("Filter by:");
        filterLabel.setPrefWidth(200);
        filterLabel.setAlignment(Pos.CENTER);
        filterLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25");
        filtersPane.add(filterLabel, 1, 0);
        
        Label sortLabel = new Label("Sort by:");
        sortLabel.setPrefWidth(200);
        sortLabel.setAlignment(Pos.CENTER);
        sortLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25");
        filtersPane.add(sortLabel, 1, 4);

        //These lines define the filter buttons
        Button btHighPriority = new Button("High Priority");
        btHighPriority.setPrefWidth(225);
        Button btMediumPriority = new Button("Medium Priority");
        btMediumPriority.setPrefWidth(225);
        Button btLowPriority= new Button("Low Priority");
        btLowPriority.setPrefWidth(225);
        Button btCompleted = new Button("Completed");
        btCompleted.setPrefWidth(225);
        Button btUncompleted= new Button("Uncompleted");
        btUncompleted.setPrefWidth(225);
        Button btDate= new Button("Date");
        btDate.setPrefWidth(225);
        Button btViewAll = new Button("View All");
        btViewAll.setPrefWidth(225);
        
        //Adds the filter buttons to the pane
        filtersPane.add(btHighPriority, 0, 1);
        filtersPane.add(btMediumPriority, 1, 1);
        filtersPane.add(btLowPriority, 2, 1);
        filtersPane.add(btCompleted, 0, 2);
        filtersPane.add(btUncompleted, 1, 2);
        filtersPane.add(btDate, 2, 2);
        filtersPane.add(btViewAll, 0, 3);
        
        //These lines define the sort buttons
        Button btDateSort = new Button("Due Date");
        btDateSort.setPrefWidth(225);
        Button btPrioritySort = new Button("Priority");
        btPrioritySort.setPrefWidth(225);
        
        //Adds the sort buttons to the pane
        filtersPane.add(btDateSort, 0, 5);
        filtersPane.add(btPrioritySort, 1, 5);
        
        Scene scene = new Scene(filtersPane, 500, 400);
        stage.setScene(scene);
        stage.show();
        
        //Set functionality for each filter and sorter
        btLowPriority.setOnMousePressed(e ->{
            applyFilter(new PriorityFilter(TaskPriority.LOW));
            stage.close();
        });
        btMediumPriority.setOnMousePressed(e ->{
            applyFilter(new PriorityFilter(TaskPriority.MEDIUM));
            stage.close();
        });        
        btHighPriority.setOnMousePressed(e ->{
            applyFilter(new PriorityFilter(TaskPriority.HIGH));
            stage.close();
        });
        btCompleted.setOnMousePressed(e ->{
            applyFilter(new CompletedFilter(true));
            stage.close();
        });
        btUncompleted.setOnMousePressed(e ->{
            applyFilter(new CompletedFilter(false));
            stage.close();
        });
        btDate.setOnMousePressed(e ->{
            applyFilter(new DateFilter(new Date()));
            stage.close();
        });
        btViewAll.setOnMousePressed(e ->{
            TPQ.clearFilters();
            refresher.run();
            stage.close();
        });
        
        btDateSort.setOnMousePressed(e ->{
            TPQ.setSorter(new DateSorter());
            refresher.run();
            stage.close();
        });
        btPrioritySort.setOnMousePressed(e ->{
            TPQ.setSorter(new PrioritySorter());
            refresher.run();
            stage.close();
        });
    }
    
    //This function applies the filter to the tasks once a filter button is pressed
    public void applyFilter(Filter f){        
        TPQ.clearFilters();
        TPQ.addFilter(f);
        refresher.run();
    }
}