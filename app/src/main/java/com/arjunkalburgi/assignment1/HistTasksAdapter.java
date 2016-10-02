package com.arjunkalburgi.assignment1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Arjun on 2016-09-30.
 */

public class HistTasksAdapter extends ArrayAdapter<Task> {

    final HabitStore habitStore = HabitStore.getInstance();

    public HistTasksAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.item_hist, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hist, parent, false);
        }
        // Lookup view for data population
        TextView habitInfo = (TextView) convertView.findViewById(R.id.habit_info);
        TextView habitDetails = (TextView) convertView.findViewById(R.id.habit_details);
        Button repeatButton = (Button) convertView.findViewById(R.id.repeat_button);
        Button deleteButton = (Button) convertView.findViewById(R.id.delete_button);
        // Populate the data into the template view using the data object
        habitInfo.setText(task.toString());
        Log.d(TAG, "Task dat bin: " + task.getStartDate() + " " + task.getRepeatDays());
        habitDetails.setText(task.getStartDate() + " and " + task.getRepeatDays());
        repeatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d(TAG, "Task dat bin repeated: " + task.getTaskName());
                habitStore.saveHabit(task.getTaskName(), view.getContext());
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d(TAG, "Task dat bin deleted: " + task.getTaskName());
                habitStore.deleteHabit(task, view.getContext());
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

}

