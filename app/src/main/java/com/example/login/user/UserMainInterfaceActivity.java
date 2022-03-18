package com.example.login.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.login.R;


public class UserMainInterfaceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_interface);

        //按钮：选机构
        Button select_institution = findViewById(R.id.button1);
        select_institution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        //按钮：健康
        Button health = findViewById(R.id.button2);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        //按钮：社区活动
        Button community_act = findViewById(R.id.button3);
        community_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        //按钮：首页，点击无效

        //按钮：个人中心
        Button  mycenter = findViewById(R.id.bt5_2);
        mycenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //填写页面跳转的逻辑
                Intent intent=new Intent(UserMainInterfaceActivity.this, PersonalCenterActivity.class);//跳转到个人中心
                startActivity(intent);
            }
        });


    }
}
