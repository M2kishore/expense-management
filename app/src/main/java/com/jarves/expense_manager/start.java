package com.jarves.expense_manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jarves.expense_manager.register;

public class start extends AppCompatActivity {

    String prevStarted = "prevStarted";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (sharedpreferences.getBoolean(prevStarted, false)) {
            finish();
        }
    }
    public void startUsing(View view){
        startActivity(new Intent(this, register.class));
    }

    public void finish(){
        startActivity(new Intent(this, MainActivity.class));
    }
}