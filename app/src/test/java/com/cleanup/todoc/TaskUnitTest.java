package com.cleanup.todoc;

import com.cleanup.todoc.model.Task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */


public class TaskUnitTest {

    @Test
    public void test_projects() {
        final Task task1 = new Task(1, "task 1", new Date().getTime());
        final Task task2 = new Task(2, "task 2", new Date().getTime());
        final Task task3 = new Task(3, "task 3", new Date().getTime());
        final Task task4 = new Task(4, "task 4", new Date().getTime());

        assertEquals("Projet Tartampion", task1.getProject().getName());
        assertEquals("Projet Lucidia", task2.getProject().getName());
        assertEquals("Projet Circus", task3.getProject().getName());
        assertNull(task4.getProject());
    }

    @Test
    public void test_az_comparator() {
        final Task task1 = new Task(1, "aaa", 123);
        final Task task2 = new Task(2, "zzz", 124);
        final Task task3 = new Task(3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskAZComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task2);
    }

    @Test
    public void test_za_comparator() {
        final Task task1 = new Task(1, "aaa", 123);
        final Task task2 = new Task(2, "zzz", 124);
        final Task task3 = new Task(3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        final Task task1 = new Task(1, "aaa", 123);
        final Task task2 = new Task(2, "zzz", 124);
        final Task task3 = new Task(3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        final Task task1 = new Task(1, "aaa", 123);
        final Task task2 = new Task(2, "zzz", 124);
        final Task task3 = new Task(3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }

    @Test
    public void test_task_creation(){
        final Task task1 = new Task(1, "abc", 123);
        final Task task2 = new Task(2, "def", 124);
        final Task task3 = new Task(3, "ghi", 125);

        // cree liste et ajouter task
        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        // Ajouter dans tasksFromDB



        //virifie si les tesk sont identique
        assertThat(tasksFromDB.get(0).getId(), equalTo(tasks.get(0).getId()));
        assertThat(tasksFromDB.get(1).getId(), equalTo(tasks.get(1).getId()));
        assertThat(tasksFromDB.get(2).getId(), equalTo(tasks.get(2).getId()));

        //supprime les task
        tasks.remove(task1);
        tasks.remove(task2);
        tasks.remove(task3);


    }


}