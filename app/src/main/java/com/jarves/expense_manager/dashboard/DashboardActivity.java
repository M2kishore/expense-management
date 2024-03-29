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