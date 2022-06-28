package com.example.qreminder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface TaskDAO {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task word);

    @Query("UPDATE task_table SET frequency = :frequency, day = :day, month = :month, year = :year WHERE taskName = :name")
    void updateTask(String name, int frequency, int day, int month, int year);

    @Query("DELETE FROM task_table WHERE taskName =  :name")
    void deleteTaskByName(String name);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * FROM task_table WHERE taskName = :name")
    Task getTaskByName(String name);

    @Query("SELECT * FROM task_table ORDER BY year, month, day")
    LiveData<List<Task>> getTaskListByDate();

}