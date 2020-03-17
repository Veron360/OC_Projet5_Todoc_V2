package com.cleanup.todocv2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todocv2.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

  // Action pour BDD
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);
}
