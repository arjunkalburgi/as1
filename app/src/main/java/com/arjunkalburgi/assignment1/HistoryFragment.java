package com.arjunkalburgi.assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun on 2016-09-23.
 */
public class HistoryFragment extends Fragment implements iView {
    // Store instance variables
    private static final String TAG = "MainActivity";
    public static ListView oldTasksList;
    public static List<String> histTaskList = new ArrayList<String>();
    private ArrayAdapter<String> histTaskAdapter;
    final HabitStore habitStore = HabitStore.getInstance();

    // newInstance constructor for creating fragment with arguments
    public static HistoryFragment newInstance() {
        HistoryFragment fragmentHist = new HistoryFragment();
        return fragmentHist;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment_layout, container, false);

        ListView list = (ListView) view.findViewById(R.id.list_todo);
        this.histTaskAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_todo);
        list.setAdapter(histTaskAdapter);

        return view;
    }

    public void notifyChange() {
        // refresh history list
        histTaskList = habitStore.getHistoryHabits(getActivity());
        histTaskAdapter.clear();
        histTaskAdapter.addAll(histTaskList);
        histTaskAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        habitStore.addView(this);
        histTaskList = habitStore.getHistoryHabits(getActivity());
        histTaskAdapter.clear();
        histTaskAdapter.addAll(histTaskList);
        histTaskAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        habitStore.removeView(this);
    }
}
