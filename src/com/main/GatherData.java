package com.main;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class GatherData {
    public static ObservableList<Task> getAllTasks(ArrayList<Task> tasks) {
        ObservableList<Task> allTasks =  FXCollections.observableArrayList();
        for(Task t : tasks){
            allTasks.add(t);
        }
        return allTasks;
    }
}
