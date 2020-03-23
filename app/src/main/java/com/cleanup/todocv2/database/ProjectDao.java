package com.cleanup.todocv2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todocv2.model.Project;

import java.util.List;


// regrouper toutes les actions CRUD pour la table project
@Dao // pour indiquer qu'il s'agit bien d'une interface DAO
public interface ProjectDao {

// Action pour BDD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    // @Query permet de definir la requete comme requetes SQL
    @Query("SELECT * FROM Project WHERE id = :projectId")
    LiveData<Project> getProject(long projectId);

    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAllTheProjects();

}
