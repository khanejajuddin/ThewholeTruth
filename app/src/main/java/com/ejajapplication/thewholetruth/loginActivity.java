package com.ejajapplication.thewholetruth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    EditText userNameLog,passwordLog;
    TextView registerPage;
    Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        checkExistenceUser();

        userNameLog = findViewById(R.id.UserName_log);
        passwordLog = findViewById(R.id.Password_log);
        registerPage = findViewById(R.id.Register_page);
        LoginBtn = findViewById(R.id.LoginBtn);


        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });
    }
    void processLogin(){
        String Username = userNameLog.getText().toString();
        String Password = passwordLog.getText().toString();

        Call<responseModel> call = controller.getInstance().getApi().verifyuser(Username,Password);
        call.enqueue(new Callback<responseModel>() {
            @Override
            public void onResponse(Call<responseModel> call, Response<responseModel> response) {
                responseModel obj = response.body();
                String outPut = obj.getMessage();

                if (outPut.equals("user not exist")){
                    userNameLog.setText("");
                    passwordLog.setText("");
                    Toast.makeText(getApplicationContext(),"Invalid details",Toast.LENGTH_SHORT).show();
                }
                if (outPut.equals("exist")){
                    String Name = obj.getName();
                    String Phone = obj.getPhone();
                    String userName  = obj.getUsername();
                    String id = obj.getId();

                    SharedPreferences sp = getSharedPreferences("login_status",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Name",Name);
                    editor.putString("Phone",Phone);
                    editor.putString("UserName",userName);
                    editor.putString("ID",id);
                    editor.commit();
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(),Home.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<responseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void checkExistenceUser(){
        SharedPreferences sp = getSharedPreferences("login_status",MODE_PRIVATE);
        if (sp.contains("Name")){
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"please login",Toast.LENGTH_SHORT).show();

        }
    }
}