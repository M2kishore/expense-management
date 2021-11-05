package com.jarves.expense_manager.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jarves.expense_manager.R;
import com.jarves.expense_manager.dashboard.CreateTaskActivity;
import com.jarves.expense_manager.dashboard.DashboardActivity;
import com.jarves.expense_manager.dashboard.adapters.TaskAdapter;
import com.jarves.expense_manager.dashboard.class_components.Date;
import com.jarves.expense_manager.dashboard.class_components.Task;
import com.jarves.expense_manager.dashboard.class_components.Time;

import java.util.ArrayList;


public class CompletedFragment extends Fragment {
    FloatingActionButton createButton;
    ListView listView;
    String[] maintitle ={
            "Gym Fee","Current Bill"
    };

    String[] subtitle ={
            "200","500"
    };
    public  CompletedFragment(){

    }
    Task task1 = new Task("Current Bill",200,new Date(13,20,2022), new Time(11,12));
    Task task2 = new Task("Mobile Recharge",500,new Date(10,2,2021), new Time(13,2));
    ArrayList<Task> task = new ArrayList<Task>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        task.add(task1);
        task.add(task2);
        listView = view.findViewById(R.id.list_view);
        createButton = view.findViewById(R.id.create_button);

        TaskAdapter taskAdapter = new TaskAdapter((DashboardActivity) getActivity(), task);
        listView.setAdapter(taskAdapter);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create button click event
                Intent intent=new Intent(getContext(), CreateTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}