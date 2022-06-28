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

    public Task(@NonNull String taskName, Date taskDateDue, int taskFrequency){
        name = taskName;
        day = taskDateDue.getDate();
        month = taskDateDue.getMonth();
        year = taskDateDue.getYear();

        frequency = taskFrequency;
    }

    public Task() {
        name = "-";
        day = 1;
        month = 1;
        year = 2000;
        frequency = 1;
    }

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

    public String toString() {
        return (getDateDue().toString() + "\n" + getName());
    }

    public Date dateDueFromDateComplete(Date lastCompleted) {
        Calendar c = Calendar.getInstance();
        c.setTime(lastCompleted);
        c.add(Calendar.DATE, frequency);
        return (Date) c.getTime();
    }
}
