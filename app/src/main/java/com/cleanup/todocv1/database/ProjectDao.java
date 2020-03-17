package com.cleanup.todocv1.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todocv1.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

// Action pour BDD
  @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    //Used for tests
    @Query("SELECT * FROM Project WHERE id = :projectId")
    LiveData<Project> getProject(long projectId);

    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAllTheProjects();

}
