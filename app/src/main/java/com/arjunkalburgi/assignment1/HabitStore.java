package com.arjunkalburgi.assignment1;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun on 2016-09-26.
 */
public class HabitStore {
    List<iView> views = new ArrayList<iView>();
    List<String> habits = new ArrayList<String>();
    List<String> historyHabits = new ArrayList<String>();
    private static final String FILENAME = "file.sav";
    private static final String HistoryFILENAME = "hist_file.sav";
    private static final String TAG = "MainActivity";

    private static HabitStore instance = null;
    private HabitStore() {}

    public static synchronized HabitStore getInstance() {
        if (instance == null) instance = new HabitStore();
        return instance;
    }

    void addView(iView v) {
        views.add(v);
    }

    void removeView(iView v) {
        views.remove(v);
    }

    void notifyViewsOfChange() {
        Log.d(TAG, "task has been added, there is a new thing!");
        for (iView v : views) {
            v.notifyChange();
        }
    }

    private void addHistoryHabit(String s, Context context) {
        historyHabits.add(s);
        try {
            FileOutputStream fos = context.openFileOutput(HistoryFILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(historyHabits, writer);
            writer.flush();

            notifyViewsOfChange();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<String> getHistoryHabits(Context context) {
        try {
            FileInputStream fis = context.openFileInput(HistoryFILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt TODAY
            Type lisType = new TypeToken<ArrayList<String>>() {}.getType();
            historyHabits = gson.fromJson(in, lisType);

            return historyHabits;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void deleteHabit(String s, Context context) {
        habits.remove(s);
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habits, writer);
            writer.flush();

            addHistoryHabit(s, context);
            notifyViewsOfChange();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void saveHabit(String s, Context context) {
        habits.add(s);
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habits, writer);
            writer.flush();

            notifyViewsOfChange();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<String> getHabits(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt TODAY
            Type lisType = new TypeToken<ArrayList<String>>() {}.getType();
            habits = gson.fromJson(in, lisType);

            return habits;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
