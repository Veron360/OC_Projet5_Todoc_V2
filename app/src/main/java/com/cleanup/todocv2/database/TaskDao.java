package com.cleanup.todocv2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todocv2.model.Task;

import java.util.List;

// regrouper toutes les actions CRUD pour la table project
@Dao
public interface TaskDao {

      /**
       *
       * @return list of task
       */
      @Query("SELECT * FROM Task")
      LiveData<List<Task>> getTasks();


      /**create
       * insert
       * @param task
       * @return
       */
      @Insert
      long insertTask(Task task);

      /**
       * delete task
       * @param taskId
       * @return
       */
      @Query("DELETE FROM Task WHERE id = :taskId")
      int deleteTask(long taskId);

      /**
       * update task
       * @param task
       * @return
       */
      @Update
      int updateTask(Task task);
}
