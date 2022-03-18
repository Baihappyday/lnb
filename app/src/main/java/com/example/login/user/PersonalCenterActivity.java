package com.example.login.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.UserLoginActivity;

public class PersonalCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center);

        Button login1 = findViewById(R.id.bt1);//按钮：点击登录
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至登录界面
                Intent intent=new Intent(PersonalCenterActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        Button improve_personal_infor = findViewById(R.id.bt2);//按钮：完善个人信息
        improve_personal_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /******此处完善个人信息界面缺失*****/
                Intent intent=new Intent(PersonalCenterActivity.this, ModifyUserlInfo.class);
                startActivity(intent);
            }
        });

        Button OnGoing = findViewById(R.id.bt3_1);//按钮：进行中
        OnGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        Button done = findViewById(R.id.bt3_2);//按钮：已完成
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        Button toEvaluate = findViewById(R.id.bt3_3);//按钮：待评价
        toEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        Button notPay = findViewById(R.id.bt3_4);//按钮：未付款
        notPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });


        Button wallet = findViewById(R.id.bt4);//按钮：我的钱包
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /****界面缺失***/
            }
        });

        Button toHomePage = findViewById(R.id.bt5_1);//按钮：首页
        toHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至首页界面
                Intent intent=new Intent(PersonalCenterActivity.this, UserMainInterfaceActivity.class);
                startActivity(intent);
            }
        });
        //按钮个人中心，点击无效
    }
}


