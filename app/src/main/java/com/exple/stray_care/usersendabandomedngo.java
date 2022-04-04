package com.exple.stray_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class usersendabandomedngo extends AppCompatActivity {
    TextView tvname,tvemail,tvmobile;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersendabandomedngo);
        tvname=findViewById(R.id.txtname);
        tvemail=findViewById(R.id.txtemail);
        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");
        tvname.setText("NAME: "+userdonate.userdonateNgodata.get(position).getName());
        tvemail.setText("EMAIL: "+userdonate.userdonateNgodata.get(position).getEmail());
    }
}