package com.cleanup.todocv2.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todocv2.model.Project;
import com.cleanup.todocv2.model.Task;
import com.cleanup.todocv2.repositories.ProjectDataRepository;
import com.cleanup.todocv2.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

//Utile pour fournir a l'activity les donnees utilisees par l'interface graphique.

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    //la classe Executor permet de réaliser de manière asynchrone notamment les requêtes de mise à jour de nos tables SQLite.
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<List<Project>> allProjectsList;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor){

        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }
    void init() {
        if (allProjectsList != null) {
            return;
        }
        allProjectsList = projectDataSource.getAllTheProjects();
    }

    // -------------
    // FOR PROJECTS
    // -------------

    LiveData<List<Project>> getAllTheProjects() { return allProjectsList;  }


    // -------------
    // FOR TASKS
    // -------------

    LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

    void createTask(final Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
    }

    void deleteTask(final Task task) {
        executor.execute(() -> taskDataSource.deleteTask(task));
    }

}
