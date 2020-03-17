package com.cleanup.todocv2.injections;

import android.content.Context;

import com.cleanup.todocv2.database.TodocDatabase;
import com.cleanup.todocv2.repositories.ProjectDataRepository;
import com.cleanup.todocv2.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Centralisee les nouvelles instances de nos repositoris
public class Injection {

        public static TaskDataRepository provideTaskDataSource(Context context) {
            TodocDatabase database = TodocDatabase.getInstance(context);
            return new TaskDataRepository(database.mtaskDao());
        }

        public static ProjectDataRepository provideProjectDataSource(Context context) {
            TodocDatabase database = TodocDatabase.getInstance(context);
            return new ProjectDataRepository(database.mprojectDao());
        }

        public static Executor provideExecutor(){
            return Executors.newSingleThreadExecutor();
        }

        public static ViewModelFactory provideViewModelFactory(Context context) {
            TaskDataRepository dataSourceTask = provideTaskDataSource(context);
            ProjectDataRepository dataSourceProject = provideProjectDataSource(context);
            Executor executor = provideExecutor();
            return new ViewModelFactory(dataSourceTask, dataSourceProject, executor);
        }

}
