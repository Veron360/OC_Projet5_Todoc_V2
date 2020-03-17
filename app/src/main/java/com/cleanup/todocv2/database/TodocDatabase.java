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
    // Creer une unique fois la classe responsable de la BDD et obtenir
    // qu'une seule et unique instance de référence
    private static volatile TodocDatabase INSTANCE;


    // --- DAO ---

    public abstract TaskDao mtaskDao();
    public abstract ProjectDao mprojectDao();

    // --- INSTANCE ---
    // Creer un objet RoomDatabase et un fichier BDD SQLite, a chaque fois qu'elle sera appeler
    // elle renverra la reference de la BDD

    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Permet d'éviter 2 taches simultanés
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyTodocDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    // Remplir la BDD avec projets
    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Project[] projects = Project.getAllProjects();
                for (Project project : projects) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", project.getId());
                    contentValues.put("name", project.getName());
                    contentValues.put("color", project.getColor());
                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }



}