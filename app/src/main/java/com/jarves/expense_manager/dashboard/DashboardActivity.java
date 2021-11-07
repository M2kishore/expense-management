package com.jarves.expense_manager.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.jarves.expense_manager.R;
import com.jarves.expense_manager.dashboard.adapters.BudgetAdapter;
import com.jarves.expense_manager.class_components.Date;
import com.jarves.expense_manager.class_components.Task;
import com.jarves.expense_manager.class_components.Time;
import com.jarves.expense_manager.database.Database;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //database
        //test
        Database database = new Database(getApplicationContext());

        Task task1 = new Task("Tution Fee",250,"Knowledge",new Date(12,5,2221), new Time(11,12));
        Task task2 = new Task("Gym Fee",2500,"Health",new Date(11,7,2021),new Time(11,2));
        Task task3 = new Task("Current Bill",500,"Needs",new Date(12,1,2221), new Time(11,12));
        Task task4 = new Task("Mobile Recharge",550,"Needs",new Date(11,2,2021), new Time(11,2));

        database.deleteTask("Current Bill");
        database.deleteTask("Mobile Recharge");
        database.deleteTask("Tution Fee");
        database.deleteTask("Gym Fee");

        task4.setComplete(true);
        task3.setComplete(true);

        database.addNewTask(task1);
        database.addNewTask(task2);
        database.addNewTask(task3);
        database.addNewTask(task4);


        tasks = database.getTasks();
        Task t1 = tasks.get(0);
        Task t2 = tasks.get(1);
        Task t3 = tasks.get(2);
        Task t4 = tasks.get(3);

        Log.d("tmob", t4.toString());
        Log.d("tgym", t2.toString());
        Log.d("ttution", t1.toString());
        Log.d("tcurrent", t3.toString());
        //database






        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final BudgetAdapter adapter = new BudgetAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}