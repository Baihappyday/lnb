package com.example.login.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.login.R;


public class UserMainInterfaceActivity extends AppCompatActivity
{
    TitlePage tp = new TitlePage();
    PersonalCenter pc = new PersonalCenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_interface);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tp).commit();
        Button button=findViewById(R.id.button);//首页
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tp).commit();
            }
        });
        Button button1=findViewById(R.id.personalcenter);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pc).commit();
            }
        });
        Button button2=findViewById(R.id.taskk);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserMainInterfaceActivity.this,TaskRelease.class);
                startActivity(intent);
            }
        });
    }


//
//        //按钮：首页，点击无效
//        //按钮：任务发布
//        Button btn = findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /****界面缺失***/
//                Intent intent=new Intent(UserMainInterfaceActivity.this, TaskRelease.class);//跳转到任务发布
//                startActivity(intent);
//            }
//        });
//
//        //按钮：个人中心
//        Button  mycenter = findViewById(R.id.bt5_2);
//        mycenter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //填写页面跳转的逻辑
//                Intent intent=new Intent(UserMainInterfaceActivity.this, PersonalCenterActivity.class);//跳转到个人中心
//                startActivity(intent);
//            }
//        });



}
