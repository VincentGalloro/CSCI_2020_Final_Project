
package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

//this class was made purely for debug purposes
//this class allowed us to generate random tasks to test UI before
//we could create them in the program. This class is not in use anymore
public class RandomTaskGenerator {
    
    private Random random;
    
    //HOW TO USE THIS CLASS
    //
    //Create the generator object
    //RandomTaskGenerator taskGenerator = new RandomTaskGenerator();
    //
    //2) taskGenerator.generateSingleTask();
    //for creating a single task
    //
    //or
    //
    //3) taskGenerator.generateTaskArray(amount);
    //for creating an arraylist of tasks
    
    public RandomTaskGenerator(){
        random = new Random();
    }
    
    //generate a random letter string (helper function)
    private String genRandomString(int minLen, int maxLen, boolean upperCase){
        String out = "";
        int len = random.nextInt(maxLen-minLen) + minLen;
        for(int i = 0; i < len; i++){
            out += (char)(random.nextInt(26) + (upperCase ? 65 : 96));
        }
        return out; 
    }
    
    //generate a random task object
    public Task generateSingleTask(){
        Task t = new Task(genRandomString(10, 11, true));
        Date dueDate = new Date();
        dueDate.setTime(System.currentTimeMillis() + random.nextInt(864000000)); //up to ten days in future
        t.setDueDate(dueDate);
        if(random.nextBoolean()){
            t.setNotes(genRandomString(10, 400, false));
        }
        t.setTaskPriority(TaskPriority.values()[random.nextInt(TaskPriority.values().length)]);
        return t;
    }
    
    //generate an array of random task objects
    public ArrayList<Task> generateTaskArray(int amount){
        ArrayList<Task> tasks = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            tasks.add(generateSingleTask());
        }
        return tasks;
    }
}
