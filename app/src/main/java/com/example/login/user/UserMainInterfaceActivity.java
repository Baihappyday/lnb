package com.example.login.user;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.login.R;


public class UserMainInterfaceActivity extends AppCompatActivity implements View.OnClickListener
{
    TitlePage tp = new TitlePage();
    PersonalCenter pc = new PersonalCenter();
    ImageButton button;//首页
    Button homepage;
    ImageButton imageButton2;//个人中心
    Button personalcenter;
    private Drawable ic_user_home;
    private Drawable ic_user_home_selected;
    private Drawable ic_personal_center;
    private Drawable ic_personal_center_selected;
    private int id1 = R.drawable.ic_user_home;//得到本地资源中图片的id
    private int id2 = R.drawable.ic_user_home_selected;
    private int id3 = R.drawable.ic_personal_center;
    private int id4 = R.drawable.ic_personal_center_selected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_interface);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tp).commit();

        button=findViewById(R.id.button);//首页
        homepage = (Button)findViewById(R.id.homepage);
        imageButton2=findViewById(R.id.imageButton2);//个人中心
        personalcenter = findViewById(R.id.personalcenter);
        //获取drawable资源
        ic_user_home= ContextCompat.getDrawable(this,id1);
        ic_user_home_selected= ContextCompat.getDrawable(this,id2);
        ic_personal_center=ContextCompat.getDrawable(this,id3);
        ic_personal_center_selected=ContextCompat.getDrawable(this,id4);
        //初始化底部栏选中状态
        button.setImageDrawable(ic_user_home_selected);
        homepage.setTextColor(getResources().getColor(R.color.choosenGreen));
        //设置监听器（首页与个人中心）
        button.setOnClickListener(this);
        homepage.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        personalcenter.setOnClickListener(this);

        ImageButton button2=findViewById(R.id.taskk);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserMainInterfaceActivity.this,TaskRelease.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View v) {
        if (v.getId() == R.id.homepage){
            button.setImageDrawable(ic_user_home_selected);
            homepage.setTextColor(getResources().getColor(R.color.choosenGreen));
            imageButton2.setImageDrawable(ic_personal_center);
            personalcenter.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tp).commit();

        }
        if (v.getId() == R.id.button){
            button.setImageDrawable(ic_user_home_selected);
            homepage.setTextColor(getResources().getColor(R.color.choosenGreen));
            imageButton2.setImageDrawable(ic_personal_center);
            personalcenter.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, tp).commit();

        }
        if (v.getId() == R.id.imageButton2){
            imageButton2.setImageDrawable(ic_personal_center_selected);
            personalcenter.setTextColor(getResources().getColor(R.color.choosenGreen));
            button.setImageDrawable(ic_user_home);
            homepage.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pc).commit();

        }
        if (v.getId() == R.id.personalcenter){
            imageButton2.setImageDrawable(ic_personal_center_selected);
            personalcenter.setTextColor(getResources().getColor(R.color.choosenGreen));
            button.setImageDrawable(ic_user_home);
            homepage.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pc).commit();

        }

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
