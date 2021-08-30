package com.jarves.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowTaskActivity extends AppCompatActivity {

    String t,amt,date,time,cat;
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        textview=findViewById(R.id.tv);
        Bundle b = getIntent().getExtras();
        t=b.getString("task");
        amt=b.getString("amount");
        date=b.getString("date");
        time=b.getString("time");
        cat=b.getString("category");
        textview.setText(t+"\n"+amt+"\n"+date+"\n"+time+"\n"+cat);
    }
}
