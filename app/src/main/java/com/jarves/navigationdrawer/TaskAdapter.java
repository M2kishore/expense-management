package com.jarves.navigationdrawer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TaskAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    public TaskAdapter(@NonNull DashboardActivity dashboardActivity, @NonNull String[] maintitle, String[] subtitle) {
        super(dashboardActivity, R.layout.task_list, maintitle);
        this.context=dashboardActivity;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.task_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle[position]);
        subtitleText.setText(subtitle[position]);
        return rowView;
    }
}
