package com.jarves.expense_manager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.jarves.expense_manager.class_components.Task;
import com.jarves.expense_manager.database.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_one);
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();

        Database database = new Database(this);
        ArrayList<Task> tasks = database.getTasks();
        HashMap<String, Integer> CategoryMap = new HashMap<>();
        for(Integer i=0; i<tasks.size();i++){
            Task task = tasks.get(i);
            if(!CategoryMap.containsKey(task.getCategory())){
                CategoryMap.put(task.getCategory(),1);
            }else{
                CategoryMap.put(task.getCategory(), CategoryMap.get(task.getCategory())+1);
            }
        }
        CategoryMap.forEach((key, value) -> data.add(new ValueDataEntry(key, value)));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
    }
}