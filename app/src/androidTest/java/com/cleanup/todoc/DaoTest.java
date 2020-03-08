package com.cleanup.todoc;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TaskDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private TaskDatabase database;
    private static long PROJECT_ID = 10;
    private static Project PROJECT_Test = new Project(PROJECT_ID, "Projet test", 0xFFB4CDBA);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    //Teste l'ajout et la récupération d'un nouveau Projet dans notre base SQLite.
    @Test
    public void insertAndGetProject() throws InterruptedException {
        database.mprojectDao().createProject(PROJECT_Test);
        Project project = LiveDataTestUtil.getValue(database.mprojectDao().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_Test.getName()) && project.getId() == PROJECT_ID);
    }


}
