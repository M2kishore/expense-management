package com.jarves.expense_manager.dashboard.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jarves.expense_manager.R;
import com.jarves.expense_manager.dashboard.DashboardActivity;
import com.jarves.expense_manager.dashboard.class_components.Task;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    Activity context;
    public TaskAdapter(@NonNull Activity dashboardActivity, ArrayList<Task> tasks) {
        super(dashboardActivity, 0,tasks);
        this.context = dashboardActivity;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.task_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        Task task = getItem(position);
        String name = task.getName();
        String amount = task.getAmount();
        titleText.setText(name);
        subtitleText.setText(amount);
        return rowView;
    }
}
