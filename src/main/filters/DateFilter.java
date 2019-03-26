package main.filters;

import main.Task;
import java.util.Calendar;
import java.util.Date;

//this filter checks a tasks date
public class DateFilter implements Filter{

    public final Date date;

    public DateFilter(Date date){
        this.date = date;
    }
    
    //allow the task if it is due today
    public boolean shouldAllow(Task t) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        cal2.setTime(t.getDueDate());
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }
}