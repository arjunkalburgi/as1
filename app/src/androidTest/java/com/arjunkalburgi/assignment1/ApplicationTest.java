package com.arjunkalburgi.assignment1;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.UiThread;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.UiThreadTest;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public ApplicationTest() {
        super(com.arjunkalburgi.assignment1.MainActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final HabitStore habitStore = HabitStore.getInstance();
        habitStore.clear(getActivity());
    }

    /*add new habit
    complete a habit for the day
    delete habits
    delete past habit completions
    see per habit, how many times the task has been fulfilled
    The app must be persistent; exiting and stopping the app should not lose data.*/


    /* Test: Add a new Task
    * Does task show up in "habits" list?
    * Does it store all the DaysOfTheWeek information correctly? */
    @UiThreadTest
    public void testAddNewTask() {
        final HabitStore habitStore = HabitStore.getInstance();
        List<Task> habits = habitStore.getHabits(getActivity());
        Task newTask = new Task("Test adding a new task");

        assertFalse(habits.contains(newTask));

        habitStore.saveHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());

        assertTrue(habits.contains(newTask));

        Task newerTask = new Task("Test to see DaysOfTheWeek information is correct");
        newerTask.setTaskToRepeatOn("Monday");
        newerTask.setTaskToRepeatOn("Friday");
        habitStore.saveHabit(newerTask, getActivity());
        habits = habitStore.getHabits(getActivity());
        Task savedNewerTask = habits.get(habits.indexOf(newerTask));

        assertEquals("Repeats on Monday, Friday, ", savedNewerTask.getRepeatDays());
        assertFalse(savedNewerTask.doesTaskRepeatOn("Sunday"));
        assertTrue(savedNewerTask.doesTaskRepeatOn("Monday"));
        assertFalse(savedNewerTask.doesTaskRepeatOn("Tuesday"));
        assertFalse(savedNewerTask.doesTaskRepeatOn("Wednesday"));
        assertFalse(savedNewerTask.doesTaskRepeatOn("Thursday"));
        assertTrue(savedNewerTask.doesTaskRepeatOn("Friday"));
        assertFalse(savedNewerTask.doesTaskRepeatOn("Saturday"));
    }

    /* Test: Complete a Task
    * Does the task show up in "habitsHistory list
    * and get dismissed from "habits" list?
    * Does the task's internal numTimesCompleted count increment? */
    @UiThreadTest
    public void testCompleteTask() {
        final HabitStore habitStore = HabitStore.getInstance();
        List<Task> habits;
        List<Task> histhabits;
        Task newTask = new Task("Test adding a new task");

        habitStore.saveHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());
        histhabits = habitStore.getHistoryHabits(getActivity());

        assertTrue(habits.contains(newTask));
        assertFalse(histhabits.contains(newTask));
        assertEquals(0, newTask.getNumTimesCompleted());

        habitStore.completeHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());
        histhabits = habitStore.getHistoryHabits(getActivity());

        assertFalse(habits.contains(newTask));
        assertTrue(histhabits.contains(newTask));

        Task savedNewTask = histhabits.get(histhabits.indexOf(newTask));

        assertEquals(1, savedNewTask.getNumTimesCompleted());
    }

    /* Test: Delete a Task
    * Does the task get removed from the "habits" list */
    @UiThreadTest
    public void testDeleteTask() {
        final HabitStore habitStore = HabitStore.getInstance();
        List<Task> habits;
        Task newTask = new Task("Test adding a new task");
        habitStore.saveHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());

        assertTrue(habits.contains(newTask));

        habitStore.deleteHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());

        assertFalse(habits.contains(newTask));
    }

    /* Test: Delete a Completed Task
    * Does the task get removed from the "habitsHistory list? */
    @UiThreadTest
    public void testCompleteDeletedTask() {
        final HabitStore habitStore = HabitStore.getInstance();
        List<Task> habits;
        List<Task> histhabits;
        Task newTask = new Task("Test adding a new task");

        habitStore.saveHabit(newTask, getActivity());
        habitStore.completeHabit(newTask, getActivity());
        habitStore.saveHabit(newTask, getActivity()); // repeat Task
        habits = habitStore.getHabits(getActivity());
        histhabits = habitStore.getHistoryHabits(getActivity());

        assertTrue(habits.contains(newTask)); // because repeat
        assertTrue(histhabits.contains(newTask));

        habitStore.deleteHistoryHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());
        histhabits = habitStore.getHistoryHabits(getActivity());

        assertFalse(habits.contains(newTask));
        assertFalse(histhabits.contains(newTask));
    }

    /* Test: Repeat a Task
    * Does the task get placed in the "habits" list? */
    @UiThreadTest
    public void testRepeatTask() {
        final HabitStore habitStore = HabitStore.getInstance();
        List<Task> habits;
        Task newTask = new Task("Test adding a new task");

        habitStore.saveHabit(newTask, getActivity());
        habitStore.completeHabit(newTask, getActivity());
        habitStore.saveHabit(newTask, getActivity()); // repeat Task
        habits = habitStore.getHabits(getActivity());

        assertTrue(habits.contains(newTask)); // because repeat

        habitStore.deleteHistoryHabit(newTask, getActivity());
        habits = habitStore.getHabits(getActivity());

        assertFalse(habits.contains(newTask));
    }
}