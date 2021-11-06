package com.jarves.expense_manager.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jarves.expense_manager.R;
import com.jarves.expense_manager.dashboard.CreateTaskActivity;
import com.jarves.expense_manager.dashboard.DashboardActivity;
import com.jarves.expense_manager.dashboard.adapters.TaskAdapter;
import com.jarves.expense_manager.dashboard.class_components.Date;
import com.jarves.expense_manager.dashboard.class_components.Task;
import com.jarves.expense_manager.dashboard.class_components.Time;
import com.jarves.expense_manager.database.Database;

import java.util.ArrayList;
import java.util.List;


public class CompletedFragment extends Fragment {
    FloatingActionButton createButton;
    ListView listView;
    ArrayList<Task> tasks;
    public  CompletedFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //test
        Database database = new Database(getContext());
        tasks = database.getTasks();

        for(int i=0;i<tasks.size();i++){
            Task task = tasks.get(i);
            if(!task.isComplete()){
                tasks.remove(i);
            }
        }

        //test
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        listView = view.findViewById(R.id.list_view);
        createButton = view.findViewById(R.id.create_button);

        TaskAdapter taskAdapter = new TaskAdapter(getActivity(), tasks);
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //item click to show detailed task
            }
        });

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