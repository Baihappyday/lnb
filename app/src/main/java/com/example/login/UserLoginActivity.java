package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login_user;
    private Button btn_forget_password;
    private Button btn_enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        btn_enroll = findViewById(R.id.enroll);
        btn_enroll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.enroll) == v){
            Intent i = new Intent(UserLoginActivity.this, EnrollActicity.class);
            //启动
            startActivity(i);

        }
    }
}