package com.cleanup.todoc;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private TodocDatabase database;
    private static long PROJECT_ID = 10;
    private static Project PROJECT_TEST = new Project(PROJECT_ID, "Test", 0xFFB4CDBA);
    private static Task TASK_DEMO_1 = new Task(PROJECT_ID, "task 1", 111);
    private static Task TASK_DEMO_2 = new Task(PROJECT_ID, "task 2", 222);


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();

    }


    //Teste l'ajout et la récupération d'un nouveau Projet dans notre base SQLite.
    @Test
    public void insertAndGetProject() throws InterruptedException {
        database.mprojectDao().createProject(PROJECT_TEST);
        Project project = LiveDataTestUtil.getValue(database.mprojectDao().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_TEST.getName()) && project.getId() == PROJECT_ID);
    }


    // Test si table Task est bien vide dans bdd
    @Test
    public void getTasksAndVerifyTaskInsertedIsEmpty() throws InterruptedException {
        List<Task> tasks = LiveDataTestUtil.getValue(database.mtaskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

    //Test l'ajout dans bdd
    @Test
    public void insertAndGetTasks() throws InterruptedException {
        database.mprojectDao().createProject(PROJECT_TEST);
        database.mtaskDao().insertTask(TASK_DEMO_1);
        database.mtaskDao().insertTask(TASK_DEMO_2);

        List<Task> tasks = LiveDataTestUtil.getValue(database.mtaskDao().getTasks());
        assertTrue(tasks.size() == 2);
        assertTrue(tasks.get(0).equals(TASK_DEMO_1) && tasks.get(1).equals(TASK_DEMO_2));

    }

    // Test la suppression task dans bdd
    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        database.mprojectDao().createProject(PROJECT_TEST);
        database.mtaskDao().insertTask(TASK_DEMO_1);
        Task taskAdded = LiveDataTestUtil.getValue(database.mtaskDao().getTasks()).get(0);
        database.mtaskDao().deleteTask(taskAdded);

        List<Task> tasks = LiveDataTestUtil.getValue(database.mtaskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

    // Ferme la db
    @After
    public void closeDb() throws Exception {
        database.close();
    }

}
