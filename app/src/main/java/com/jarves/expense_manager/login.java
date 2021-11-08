package com.jarves.expense_manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText un;
    EditText pwd;
    String prevStarted = "prevStarted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginuser(View view){
        un = findViewById(R.id.un);
        pwd =findViewById(R.id.pwd);
        int n=0;
        if (un.getText().toString().isEmpty() || pwd.getText().toString().isEmpty() ) {
            Toast.makeText(getApplicationContext(),"Please Fill All the Details", Toast.LENGTH_SHORT).show();
            n++;
        }
        if (n == 0){
            String name = un.getText().toString();
            String pass = pwd.getText().toString();
            DBHandler dbHandler = new DBHandler(login.this);
            boolean val = dbHandler.checkuser(name,pass);
            System.out.println(val);
            if(val){
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", name);
                myEdit.commit();

                SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(prevStarted, Boolean.TRUE);
                editor.apply();


            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
}