
package com.main;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class GatherData {
        public static ObservableList<Task> getAllMarks(ArrayList<Task> tasks) {
        ObservableList<Task> marks =  FXCollections.observableArrayList();
        // Student ID, Assignments, Midterm, Final exam
        for(Task t : tasks){
            marks.add(t);
        }
        return marks;
    }
}
