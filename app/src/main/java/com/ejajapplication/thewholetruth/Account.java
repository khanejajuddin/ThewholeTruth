package com.ejajapplication.thewholetruth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Account extends AppCompatActivity {

    TextView tv_id,tv_name,tv_phone,tv_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Account");

        tv_id = findViewById(R.id.tv_Id);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_userName = findViewById(R.id.tv_Username);

//        checkExistenceUser();
        SharedPreferences sp = getSharedPreferences("login_status",MODE_PRIVATE);
        if (sp.contains("Name")){
            tv_name.setText(sp.getString("Name",""));
            tv_phone.setText(sp.getString("Phone",""));
            tv_userName.setText(sp.getString("UserName",""));
            tv_id.setText(sp.getString("ID",""));
        }

    }
}