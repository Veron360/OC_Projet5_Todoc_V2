package com.cleanup.todocv2.injections;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todocv2.repositories.ProjectDataRepository;
import com.cleanup.todocv2.repositories.TaskDataRepository;
import com.cleanup.todocv2.ui.TaskViewModel;

import java.util.concurrent.Executor;

//Permet de deporter la construction de notre ViewModel pour ne pas surcharger le code notre activity (controleur principal)
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    public ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(taskDataSource, projectDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
