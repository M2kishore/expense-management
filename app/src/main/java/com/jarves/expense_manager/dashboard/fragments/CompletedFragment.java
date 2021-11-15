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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jarves.expense_manager.R;
import com.jarves.expense_manager.dashboard.CreateTaskActivity;
import com.jarves.expense_manager.dashboard.DashboardActivity;
import com.jarves.expense_manager.dashboard.ShowTaskActivity;
import com.jarves.expense_manager.dashboard.adapters.TaskAdapter;
import com.jarves.expense_manager.class_components.Task;
import com.jarves.expense_manager.database.Database;

import java.util.ArrayList;


public class CompletedFragment extends Fragment {
    FloatingActionButton createButton;
    ListView listView;
    ArrayList<Task> tasks;
    public  CompletedFragment(){

    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Database database = new Database(getContext());
        tasks = database.getTasksCompleted();

        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        listView = view.findViewById(R.id.list_view);
        createButton = view.findViewById(R.id.create_button);

        TaskAdapter taskAdapter = new TaskAdapter(getActivity(), tasks);
        listView.setAdapter(taskAdapter);

        //final String sh= String.valueOf(tasks.get(0));






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Task task=tasks.get(i);
                //Toast.makeText(getContext(),task.toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ShowTaskActivity.class);
                intent.putExtra("task",task.toString());
                startActivity(intent);



            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                CompletedFragment fragment = (CompletedFragment) getFragmentManager().findFragmentById(R.id.fragment_completed);
                Intent intent = new Intent(getActivity(), DashboardActivity.class);

                Database database = new Database(getContext());
                Task task = tasks.get(i);
                database.deleteTask(task.getName());
                Toast.makeText(getContext(),
                        "Deteted Task",
                       Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
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