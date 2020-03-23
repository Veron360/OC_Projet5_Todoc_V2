package com.cleanup.todocv2.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todocv2.model.Project;
import com.cleanup.todocv2.model.Task;

// Lier classes de model aux interfaces DAO, et de configurer notre base de donnees.
@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile TodocDatabase INSTANCE;

    // --- DAO ---
    public abstract TaskDao mTaskDao();

    public abstract ProjectDao mProjectDao();

    private static Project[] projects = Project.getAllProjects();

    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                for (Project pr : projects) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id",pr.getId());
                    contentValues.put("name", pr.getName());
                    contentValues.put("color", pr.getColor());

                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
                }
            }

        };

    }
}