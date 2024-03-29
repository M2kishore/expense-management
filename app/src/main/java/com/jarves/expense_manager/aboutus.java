package com.jarves.expense_manager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);



        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Hey there! Struggling to live under a budget , we have got you covered.")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("CONNECT WITH US!")
                .addEmail("cb.en.u4cse19124@cb.students.amrita.edu")
                .addWebsite("https://developer.android.com/studio?gclid=CjwKCAjw4KyJBhAbEiwAaAQbE9kiATxiMEVOiF1tovGoqvtFAGGUrYeGH9xw6lxC6ggOcThTifunaRoCA_gQAvD_BwE&gclsrc=aw.ds")
                .addYoutube("https://youtu.be/0zx_eFyHRU0")   //Enter your youtube link here (replace with my channel link)
                .addPlayStore("com.jarves.navigationdrawer")   //Replace all this with your package name
                .addInstagram("android")    //Your instagram id
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright 19124,27,40,60 AMRITA", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aboutus.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}