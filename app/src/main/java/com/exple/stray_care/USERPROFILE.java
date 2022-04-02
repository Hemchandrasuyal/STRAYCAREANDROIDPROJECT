package com.exple.stray_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class USERPROFILE extends AppCompatActivity {
Button v1,v2;
TextView t;
String emaill;
String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Intent i=getIntent();
        emaill=i.getStringExtra("email");
        pass=i.getStringExtra("pass");
        v1=findViewById(R.id.userprofiledonatebutton);
        t=findViewById(R.id.textView3);
        t.setText(emaill);
        t.setText(pass);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), userdonate.class);
                startActivity(i);

            }
        });
        v2=findViewById(R.id.USERSENDABANDOMEDBUTTON);

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), usersendabandomedngo.class);
                startActivity(i);

            }
        });
    }
}