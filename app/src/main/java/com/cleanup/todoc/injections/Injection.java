package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.TaskDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Centralisee les nouvelles instances de nos repositoris
public class Injection {

        public static TaskDataRepository provideTaskDataSource(Context context) {
            TaskDatabase database = TaskDatabase.getInstance(context);
            return new TaskDataRepository(database.mtaskDao());
        }

        public static ProjectDataRepository provideProjectDataSource(Context context) {
            TaskDatabase database = TaskDatabase.getInstance(context);
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
