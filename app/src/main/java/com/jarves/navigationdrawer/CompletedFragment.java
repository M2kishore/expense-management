package com.jarves.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        listView = view.findViewById(R.id.list_view);
        createButton = view.findViewById(R.id.create_button);

        TaskAdapter taskAdapter = new TaskAdapter((DashboardActivity) getActivity(), maintitle, subtitle);
        listView.setAdapter(taskAdapter);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create button click event
                Intent intent=new Intent(getContext(),CreateTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}