package com.example.qreminder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;

    private final LiveData<List<Task>> taskList;

    public TaskViewModel (Application application) {
        super(application);
        repository = new TaskRepository(application);
        taskList = repository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() { return taskList; }

    public void insert(Task task) { repository.insert(task); }

    public void update(Task task) {repository.update(task);  }
}
