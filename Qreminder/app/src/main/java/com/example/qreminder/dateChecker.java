package com.example.qreminder;

//import java.sql.Date;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class dateChecker extends Fragment {

    public static Calendar cal = Calendar.getInstance();
    public static int cDay;
    public static int cMonth;
    public static int cYear;


    //Compares the current date to the dates of the tasks
    public static boolean compare(Task task){
        boolean same = false;

        //Collects the current date
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        cMonth = cal.get(Calendar.MONTH);
        cYear = cal.get(Calendar.YEAR);

        //Collects the due date of a given task
        Date dueDate = task.getDateDue();
        int dueYear = task.getYear()+1900;
        int dueMonth = task.getMonth();
        int dueDay = task.getDay();

        //Checks if the current date matches the due date
        if (dueYear == cYear) {
            if (dueMonth == cMonth) {
                if (dueDay == cDay) {
                    same = true;
                }
            }
        }

        return same;
    }

    //Creates a list of tasks that need a notification
    public static List<Task> whatToNotify(List<Task> tasks){

        List<Task> notify = new ArrayList<Task>();
        for (Task task:tasks){
            if( dateChecker.compare(task) /*&& (task.getActive()==1 || task.getActive()==0)*/){
                notify.add(task);
            }
        }

        return notify;
    }

    public static Date currentDate(){
        //Collects the current date
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        cMonth = cal.get(Calendar.MONTH);
        cYear = cal.get(Calendar.YEAR)-1900;
        return new Date(cYear, cMonth, cDay);
    }



}
