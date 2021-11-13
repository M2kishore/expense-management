package com.jarves.expense_manager.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jarves.expense_manager.NotificationReceiver;
import com.jarves.expense_manager.R;
import com.jarves.expense_manager.class_components.Date;
import com.jarves.expense_manager.class_components.Task;
import com.jarves.expense_manager.class_components.Time;
import com.jarves.expense_manager.database.Database;

import java.util.Calendar;

public class CreateTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

   Button b1,b2,b3;
   EditText date,time,ed1,ed2;
   Spinner sp;
   String tname,amt,cat,d,t;
   int dat,y,m,hr,min;
   RelativeLayout rl;
   String[] category={"ELECTRICITY","RECHARGE","GYM"};

   AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        date=findViewById(R.id.et3);
        b1=findViewById(R.id.bt1);
        time=findViewById(R.id.et4);
        b2=findViewById(R.id.bt2);

        ed1=findViewById(R.id.et1);
        ed2=findViewById(R.id.et2);



        sp=findViewById(R.id.sp1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(CreateTaskActivity.this,CreateTaskActivity.this,year,month,day);
                datePickerDialog.show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            Calendar c=Calendar.getInstance();
            int hour=c.get(Calendar.HOUR);
            int min=c.get(Calendar.MINUTE);
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(CreateTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hr=i;
                        min=i1;
                        time.setText(i+":"+i1);
                        t=time.getText().toString();
                    }
                },hour,min,true);
                timePickerDialog.show();
            }
        });

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_checked,category);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView tv=(CheckedTextView) view;
                cat=tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        b3=findViewById(R.id.bt3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tname=ed1.getText().toString();
                amt=ed2.getText().toString();
                Database db = new Database(getApplicationContext()); //obj creation

                if( !(tname.isEmpty() || amt.isEmpty() || d.isEmpty() || t.isEmpty() ) )
                {
                    //pushing to database

                    Task crt=new Task(tname,Integer.parseInt(amt),cat,new Date(dat,m,y),new Time(hr,min));
                    db.addNewTask(crt);

                    Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                    /*intent.putExtra("task",tname);
                    intent.putExtra("amount",amt);
                    intent.putExtra("category",cat);
                    intent.putExtra("date",d);
                    intent.putExtra("time",t);*/
                    startActivity(intent);
              }
                else
                {
                    Toast.makeText(getApplicationContext(),"Fill all the Fields",Toast.LENGTH_SHORT).show();
                }

                tname="";
                amt="";
                d="";
                cat="";
                t="";
            }

        });





    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int da) {
        date.setText(year+"-"+(month-1)+"-"+da);
        dat=da;
        m=month-1;
        y=year;
        d=date.getText().toString();
    }

}
