package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserLoginActivity extends AppCompatActivity {

    private Button btn_login_user;
    private Button btn_forget_password;
    private Button btn_enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //按钮：登录
        btn_login_user = findViewById(R.id.login);
        btn_login_user.setOnClickListener(new View.OnClickListener() {//登录按钮
            @Override
            public void onClick(View view) {
                //填写页面跳转的逻辑
                Intent intent=new Intent(UserLoginActivity.this,UserMainInterfaceActivity.class);//跳转到用户首页
                startActivity(intent);
                //btn_login_user.setText("已点击登录");
            }
        });


        //按钮：注册
        btn_enroll = findViewById(R.id.enroll);
        btn_enroll.setOnClickListener(new View.OnClickListener() {//点击注册账号按钮
            @Override
            public void onClick(View view) {
                //填写页面跳转的逻辑UserLoginActivity
                Intent intent=new Intent(UserLoginActivity.this,EnrollActicity/*UserMainInterfaceActivity*/.class);
                startActivity(intent);

            }
        });

        //按钮：忘记密码
        btn_forget_password=findViewById(R.id.forget);
        btn_forget_password.setOnClickListener(new View.OnClickListener() {//登录按钮
            @Override
            public void onClick(View view) {
                //填写页面跳转的逻辑
                //*****此处缺失忘记密码界面****//
                Intent intent=new Intent(UserLoginActivity.this,UserMainInterfaceActivity.class);//跳转到用户首页
                startActivity(intent);

            }
        });



    }


}