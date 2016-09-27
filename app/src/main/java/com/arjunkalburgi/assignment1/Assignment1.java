package com.arjunkalburgi.assignment1;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class Assignment1 extends AppCompatActivity {

    //    FragmentPagerAdapter adapterViewPager;
    private FragmentPagerAdapter adapterViewPager;
    private static final String TAG = "Assignment1";
    private static final String FILENAME = "file.sav";
    public static ListView oldTasksList;
    public static List<String> taskList = new ArrayList<String>();
    private ArrayAdapter<String> taskAdapter;

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return HabitsFragment.newInstance();
                case 1: // Fragment # 1 - This will show SecondFragment
                    return HistoryFragment.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Habits";
                case 1:
                    return "History";
                default:
                    return "null";
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment1);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
//        PagerTabStrip vpPagerTabStrip = (ViewPager) findViewById(R.id.pager_header);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(Assignment1.this,
//                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
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
//                final EditText taskEditText = new EditText(this);
//                AlertDialog dialog = new AlertDialog.Builder(this)
//                        .setTitle("Add a new task")
//                        .setMessage("What do you want to do next?")
//                        .setView(taskEditText)
//                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String task = String.valueOf(taskEditText.getText());
//                                Log.d(TAG, "Task to add: " + task);
//
//                                taskList.add(task);
//                                taskAdapter.notifyDataSetChanged();
//                                saveInFile();
//                            }
//                        })
//                        .setNegativeButton("Cancel", null)
//                        .create();
//                dialog.show();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //	private void saveInFile(String text, Date date) {
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(taskList, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        ArrayList<String> tasks = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt TODAY
            Type lisType = new TypeToken<ArrayList<String>>() {
            }.getType();
            taskList = gson.fromJson(in, lisType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
