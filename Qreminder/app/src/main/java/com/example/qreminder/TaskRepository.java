package com.example.qreminder;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {
    private TaskDAO myTaskDAO;
    private LiveData<List<Task>> activeTaskList;
    private LiveData<List<Task>> inactiveTaskList;


    TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        myTaskDAO = db.taskDAO();
        activeTaskList = myTaskDAO.getTaskListByDate();
        inactiveTaskList = myTaskDAO.getAllTasks();
    }

    LiveData<List<Task>> getInactiveTasks() {
        return inactiveTaskList;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Task>> getAllTasks() {
        return activeTaskList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Task task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            myTaskDAO.insert(task);
        });
    }

    void update(Task task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            myTaskDAO.updateTask(task.getName(), task.getFrequency(), task.getDay(), task.getMonth(), task.getYear(), task.getActive());
        });
    }

    void delete(String task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            myTaskDAO.deleteTaskByName(task);
        });
    }

    Task getTaskByName(String name) {
        for (Task t:activeTaskList.getValue()) {
            if(t.getName() == name) {
                return t;
            }
        }
        return null;
    }

}
