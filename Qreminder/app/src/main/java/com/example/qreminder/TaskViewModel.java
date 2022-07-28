package com.example.qreminder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;

    private final LiveData<List<Task>> activeTaskList;
    private final LiveData<List<Task>> inactiveTaskList;

    public TaskViewModel (Application application) {
        super(application);
        repository = new TaskRepository(application);
        activeTaskList = repository.getAllTasks();
        inactiveTaskList = repository.getInactiveTasks();
    }

    LiveData<List<Task>> getInactiveTasks() {
        return inactiveTaskList;
    }

    LiveData<List<Task>> getAllTasks() { return activeTaskList; }

    public void insert(Task task) {
        task.setName(formatName(task.getName()));
        repository.insert(task);
    }

    private static String formatName(String name) {
        String formattedTaskName = "";
        for (int i = 0; i < name.length(); i++) {
            if(i == 0 || name.charAt(i - 1) == ' ') {
                formattedTaskName += name.substring(i, i + 1).toUpperCase();
            } else {
                formattedTaskName += name.substring(i, i + 1).toLowerCase();
            }
        }
        return formattedTaskName;
    }

    public void update(Task task) {
        task.setName(formatName(task.getName()));
        repository.update(task);
    }

    public void delete(String task) {repository.delete(task);  }

    public Task getTask(String name) {
        return repository.getTaskByName(name);
    }

    public static List<Task> searchTasks(List<Task> list, String s) {
        List<Task> result = new ArrayList<Task>();
        s = s.toLowerCase(Locale.ROOT);
        for (Task t:list) {
            if (t.getName().toLowerCase().contains(s)) {
                result.add(t);
            }
        }
        return result;
    }

    public void initializeDatabase() {
        insert(new Task("Water Plants", new Date(), 1, 0));
        insert(new Task("Add Water Softener Salt", new Date(), 60, 0));
        insert(new Task("Change Furnace Filter", new Date(), 90, 0));
        insert(new Task("Check Smoke Detector Batteries", new Date(), 365, 0));
        insert(new Task("Change Fridge Water Filter", new Date(), 180, 0));
        insert(new Task("Change Oil In Car", new Date(), 90, 0));
        insert(new Task("Clean Oven", new Date(), 90, 0));
    }
}
