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
               // String[] a ={"","34534535"};
                //String[] ts=tasks.toArray(a);
               // Toast.makeText(getContext(),a[1],Toast.LENGTH_SHORT).show();

                Task task=tasks.get(i);
                Toast.makeText(getContext(),task.toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ShowTaskActivity.class);



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