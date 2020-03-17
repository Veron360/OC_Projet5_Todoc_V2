package com.cleanup.todocv2.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todocv2.database.TaskDao;
import com.cleanup.todocv2.model.Task;

import java.util.List;

// Utile pour le  ViewModel
public class TaskDataRepository {

    private final TaskDao taskDao;


    public TaskDataRepository (TaskDao dao) {

        taskDao = dao;
    }

    // -- GET ALL THE TASKS FROM DB --
    public LiveData<List<Task>> getTasks() { return taskDao.getTasks(); }

    // --- CREATE ---

    public void createTask(Task task){ taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(Task task){ taskDao.deleteTask(task); }

}
