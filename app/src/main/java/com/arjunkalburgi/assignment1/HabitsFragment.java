package com.arjunkalburgi.assignment1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Arjun on 2016-09-23.
 */
public class HabitsFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
//    public static FirstFragment newInstance(int page, String title) {
//        FirstFragment fragmentFirst = new FirstFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
//        fragmentFirst.setArguments(args);
//        return fragmentFirst;
//    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.habit_fragment_layout, container, false);
//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);
        return view;
    }
}