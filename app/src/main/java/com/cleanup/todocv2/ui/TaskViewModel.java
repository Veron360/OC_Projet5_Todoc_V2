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
    private final TaskDataRepository mTaskDataRepository;
    private final ProjectDataRepository mProjectDataRepository;
    private final Executor mExecutor;


    // constructor
    public TaskViewModel(TaskDataRepository mTaskDataRepository, ProjectDataRepository mProjectDataRepository, Executor mExecutor){
        this.mTaskDataRepository = mTaskDataRepository;
        this.mProjectDataRepository = mProjectDataRepository;
        this.mExecutor = mExecutor;
    }

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<List<Project>>getAllProjects(){
        return this.mProjectDataRepository.getAllTheProjects();
    }
    public void createProject(Project project){
        mExecutor.execute(()->{
            mProjectDataRepository.createProject(project);
        });
    }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks(){
        return mTaskDataRepository.getTasks();
    }
    public void createTask(Task task){
        mExecutor.execute(()->{
            mTaskDataRepository.createTask(task);
        });
    }

    public void deleteTask (long taskId){
        mExecutor.execute(()->{
            mTaskDataRepository.deleteTask(taskId);
        });

    }
    public void updateTask(Task task){
        mExecutor.execute(()->{
            mTaskDataRepository.updateTask(task);
        });
    }
}
