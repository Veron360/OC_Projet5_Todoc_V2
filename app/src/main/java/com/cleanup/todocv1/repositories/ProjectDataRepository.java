package com.cleanup.todocv1.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todocv1.database.ProjectDao;
import com.cleanup.todocv1.model.Project;

import java.util.List;

// Utile pour le ViewModel
public class ProjectDataRepository {

    private final ProjectDao projectDao;


    public ProjectDataRepository(ProjectDao dao) {

        projectDao = dao;
    }

    // -- GET ALL PROJECTS --

    public LiveData<List<Project>> getAllTheProjects() { return projectDao.getAllTheProjects(); }

}