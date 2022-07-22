package com.example.qreminder;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "taskName")
    private String name;

    @ColumnInfo(name = "day")
    private int day;

    @ColumnInfo(name = "month")
    private int month;

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "frequency")
    private int frequency;

    @ColumnInfo(name = "active")
    private int active; // 0 means not active, 1 means active, but no notifications, 2 means
    // active with notifications.

    public Task(@NonNull String taskName, Date dateLastCompleted, int taskFrequency, int activeLevel){
        name = taskName;
        frequency = taskFrequency;
        Date taskDateDue = dateDueFromDateComplete(dateLastCompleted);
        day = taskDateDue.getDate();
        month = taskDateDue.getMonth();
        year = taskDateDue.getYear();
        active = activeLevel;
    }

    public Task() {
        name = "-";
        day = 1;
        month = 1;
        year = 0;
        frequency = 1;
        active = 0;
    }

    public void setActive(int active){this.active = active;}

    public void setName(@NonNull String newName) {
        name = newName;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDateDue(Date newDateDue) {
        day = newDateDue.getDate();
        month = newDateDue.getMonth();
        year = newDateDue.getYear();
    }

    public void setFrequency(int newFrequency) {
        frequency = newFrequency;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getActive() {
        return active;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date getDateDue() {
        return new Date(year, month, day);
    }

    public int getFrequency() {
        return frequency;
    }

    public String dateString() {
        if (year == Calendar.getInstance().getTime().getYear()){
            return (month + 1) + "/" + day;
        }
        return (month + 1) + "/" + day + "/" + (year + 1900);
    }

    public String lastCompleted() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(year, month, day));
        c.add(Calendar.DAY_OF_MONTH, -frequency);
        return (c.getTime().getMonth() + 1) + "/" + c.getTime().getDate() + "/" + (c.getTime().getYear() + 1900);
    }

    public String toString() {
        return (getDateDue().toString() + "\n" + getName());
    }

    public Date dateDueFromDateComplete(Date lastCompleted) {
        Calendar c = Calendar.getInstance();
        c.setTime(lastCompleted);
        c.add(Calendar.DAY_OF_MONTH, frequency);
        return c.getTime();
    }

    //Sets new date once updated
    public void update(){
        setDateDue(dateDueFromDateComplete(dateChecker.currentDate()));
        //Only temporarily updates the data....



    }
}
