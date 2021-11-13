package com.jarves.expense_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.jarves.expense_manager.class_components.Date;
import com.jarves.expense_manager.class_components.Task;
import com.jarves.expense_manager.class_components.Time;
import com.jarves.expense_manager.dashboard.DashboardActivity;
import com.jarves.expense_manager.database.Database;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView user;
    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        //createNotificationChannel();
        //notificationManager = NotificationManagerCompat.from(this);
        sendNotification();
        //setUpNotification();

        user = findViewById(R.id.user);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        s1 = "Welcome "+s1;
        user.setText(s1);


        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_home:

                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_drawer_dashboard:
                        Intent dashboard_intent = new Intent(MainActivity.this , DashboardActivity.class);
                        startActivity(dashboard_intent);
                        break;
                    case R.id.nav_drawer_statistics:
                        Intent statistics_intent = new Intent(MainActivity.this , StatisticsActivity.class);

                        startActivity(statistics_intent);
                        break;
                    case R.id.nav_drawer_settings:
                        Intent settings_intent = new Intent(MainActivity.this , SettingsActivity.class);
                        startActivity(settings_intent);
                        break;
                    case R.id.nav_aboutus:
                        Intent intent3 = new Intent(MainActivity.this , aboutus.class);
                        startActivity(intent3);
                        break;


//Policy

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }

    public void darkbutton(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.syncState();

    }
    public void sendNotification(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar cal = Calendar.getInstance();
        Database database = new Database(this);
        ArrayList<Task> tasks = database.getTasksPending();
        for(int i=0;i<tasks.size();i++) {
            Task task = tasks.get(i);
            Date date = task.getDate();
            Time time = task.getTime();
            Calendar thisCal = Calendar.getInstance();
            thisCal.set(Calendar.YEAR, date.getYear());
            thisCal.set(Calendar.MONTH, date.getMonth());
            thisCal.set(Calendar.DAY_OF_MONTH, date.getDay());
            thisCal.set(Calendar.HOUR, time.getHour());
            thisCal.set(Calendar.MINUTE, time.getMinute());
            if (thisCal.getTimeInMillis() < cal.getTimeInMillis()) {
                cal = thisCal;
            }
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }
}