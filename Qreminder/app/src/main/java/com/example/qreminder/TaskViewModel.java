package com.example.qreminder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

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

    public boolean databaseCreated() { return activeTaskList != null;  }

    LiveData<List<Task>> getInactiveTasks() {
        return inactiveTaskList;
    }

    LiveData<List<Task>> getAllTasks() { return activeTaskList; }

    public void insert(Task task) { repository.insert(task); }

    public void update(Task task) {repository.update(task);  }

    public void delete(String task) {repository.delete(task);  }

    public Task getTask(String name) { return repository.getTaskByName(name);  }

    public void initializeDatabase() {
        repository.insert(new Task("Water Plants", new Date(), 1, 0));
        repository.insert(new Task("Add Water Softener Salt", new Date(), 60, 0));
        repository.insert(new Task("Change Furnace Filter", new Date(), 90, 0));
        repository.insert(new Task("Check Smoke Detector Batteries", new Date(), 365, 0));
        repository.insert(new Task("Change Fridge Water Filter", new Date(), 180, 0));
        repository.insert(new Task("Change Oil in Car", new Date(), 90, 0));
        repository.insert(new Task("Clean Oven", new Date(), 90, 0));
    }
}
