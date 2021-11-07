package com.jarves.expense_manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;
    EditText un,em,number,pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b1 = findViewById(R.id.button1);
        b2= findViewById(R.id.button2);
        un = findViewById(R.id.editText1);
        em = findViewById(R.id.editText2);
        pwd= findViewById(R.id.editText4);
        number = findViewById(R.id.editText5);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }
    public void database(){
        DBHandler dbHandler = new DBHandler(register.this);

        String name = un.getText().toString();
        String mobile = number.getText().toString();
        String mail = em.getText().toString();
        String pass = pwd.getText().toString();

        dbHandler.adduser(name,mobile,mail,pass);
    }
    public void onClick(View view) {

        if (view == b1) {//register button
            int n=0;
            if (un.getText().toString().isEmpty() || em.getText().toString().isEmpty() ||
                    pwd.getText().toString().isEmpty() || pwd.getText().toString().isEmpty()
                    || number.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(),"Please Fill All the Details", Toast.LENGTH_SHORT).show();
                n++;
            }
            if(!isValidEmail(em.getText())){
                Toast.makeText(getApplicationContext(),"Please enter a valid email", Toast.LENGTH_SHORT).show();
                n++;
            }
            if(!isValidMobile(String.valueOf(number.getText()))){
                Toast.makeText(getApplicationContext(), "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                n++;
             }
            if(n==0){
                database();
                Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this, login.class);
                startActivity(i);
            }
        }

        if (view == b2){
            Intent i=new Intent(this,login.class);
            startActivity(i);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

}
