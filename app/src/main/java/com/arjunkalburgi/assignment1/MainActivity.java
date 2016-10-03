package com.arjunkalburgi.assignment1;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String TAG = "MainActivity";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final HabitStore habitStore = HabitStore.getInstance();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final EditText taskEditText = new EditText(MainActivity.this);
                final View inputView = LayoutInflater.from(MainActivity.this).inflate(R.layout.days_of_the_week, null);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Add a new task")
                        .setView(inputView)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText task = (EditText) inputView.findViewById(R.id.task_name);
                                Task newTask = new Task(task.getText().toString());

                                ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
                                CheckBox sundayBox = (CheckBox) inputView.findViewById(R.id.checkbox_sunday);
                                checkBoxes.add(sundayBox);
                                CheckBox mondayBox = (CheckBox) inputView.findViewById(R.id.checkbox_monday);
                                checkBoxes.add(mondayBox);
                                CheckBox tuesdayBox = (CheckBox) inputView.findViewById(R.id.checkbox_tuesday);
                                checkBoxes.add(tuesdayBox);
                                CheckBox wednesdayBox = (CheckBox) inputView.findViewById(R.id.checkbox_wednesday);
                                checkBoxes.add(wednesdayBox);
                                CheckBox thursdayBox = (CheckBox) inputView.findViewById(R.id.checkbox_thursday);
                                checkBoxes.add(thursdayBox);
                                CheckBox fridayBox = (CheckBox) inputView.findViewById(R.id.checkbox_friday);
                                checkBoxes.add(fridayBox);
                                CheckBox saturdayBox = (CheckBox) inputView.findViewById(R.id.checkbox_saturday);
                                checkBoxes.add(saturdayBox);

                                for (CheckBox checkBox : checkBoxes) {
                                    if (checkBox.isChecked()) {
                                        newTask.setTaskToRepeatOn(checkBox.getText().toString());
                                    }
                                }

                                //Log.d(TAG, "Task to add: " + newTask.getTaskName() + " " + newTask.getRepeatDays());
                                habitStore.saveHabit(newTask, MainActivity.this);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_add_task:

//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return HabitsFragment.newInstance();
                case 1: // Fragment # 1 - This will show SecondFragment
                    return HistoryFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Habits";
                case 1:
                    return "History";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
