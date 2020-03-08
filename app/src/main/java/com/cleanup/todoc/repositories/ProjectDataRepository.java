package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

// Utile pour le ViewModel
public class ProjectDataRepository {

    private final ProjectDao projectDao;


    public ProjectDataRepository(ProjectDao dao) {

        projectDao = dao;
    }

    // -- CREATE PROJECT --

    public void createProject(Project project) { projectDao.createProject(project); }

    // -- GET ALL PROJECTS --

    public LiveData<List<Project>> getAllTheProjects() { return projectDao.getAllTheProjects(); }

}