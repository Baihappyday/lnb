package com.example.login.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.ToastUtils;
import com.example.login.util.ClickMotion;
import com.example.login.util.NetWorkUtil;

public class UserMainInterfaceActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener
{
    boolean ifHadCheckedNet = false;
    TitlePage tpa ;
    PersonalCenter pca;
    BlankFragment bla;
    //发布任务
    ImageButton button2;
    //首页
    ImageButton button;
    Button homepage;
    //个人中心
    ImageButton imageButton2;
    Button personalcenter;
    //用于动态修改页面中图片
    private Drawable ic_user_home;
    private Drawable ic_user_home_selected;
    private Drawable ic_personal_center;
    private Drawable ic_personal_center_selected;
    //得到本地资源中图片的id
    private int id1 = R.drawable.ic_user_home;
    private int id2 = R.drawable.ic_user_home_selected;
    private int id3 = R.drawable.ic_personal_center;
    private int id4 = R.drawable.ic_personal_center_selected;

    FragmentManager fragmentManager  = getSupportFragmentManager();
    FragmentTransaction beginTransaction = fragmentManager.beginTransaction();

    MyApplication application;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_interface1);
        application = (MyApplication) this.getApplicationContext();
        if (!application.getLoginState()){
            ToastUtils.showNOrmalToast(UserMainInterfaceActivity.this,"欢迎使用老年宝，请前往个人中心登录");
        }
        Intent intent = getIntent();


        TitlePage tp = new TitlePage();
        PersonalCenter pc = new PersonalCenter();
        BlankFragment bl = new BlankFragment();
        tpa = tp;
        pca = pc;
        bla = bl;

        application = (MyApplication) this.getApplicationContext();

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
        if (intent.getIntExtra("frag", 1) == 1){
            beginTransaction.add(R.id.right_layout,tp).commit();
            button.setImageDrawable(ic_user_home_selected);
            homepage.setTextColor(getResources().getColor(R.color.choosenGreen));
        }
        else {
            beginTransaction.add(R.id.right_layout,pc).commit();
            imageButton2.setImageDrawable(ic_personal_center_selected);
            personalcenter.setTextColor(getResources().getColor(R.color.choosenGreen));
        }


        //设置监听器（首页与个人中心）
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
        homepage.setOnClickListener(this);
        homepage.setOnTouchListener(this);
        imageButton2.setOnClickListener(this);
        imageButton2.setOnTouchListener(this);
        personalcenter.setOnClickListener(this);
        personalcenter.setOnTouchListener(this);
        //发布任务按钮与其监听器
        button2=findViewById(R.id.taskk);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserMainInterfaceActivity.this, TaskRelease.class);
                startActivity(intent);
            }
        });
        button2.setOnTouchListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean flag = NetWorkUtil.isNetworkConnected(this);
        if (!ifHadCheckedNet){
            if (!flag){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {//5秒后自动跳转
                    @Override
                    public void run() {
                        Toast.makeText(UserMainInterfaceActivity.this,"网络未连接", Toast.LENGTH_SHORT).show();
                    }
                },1500);
            }
            if (!NetWorkUtil.isNetworkAvailable(this)&&flag){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {//5秒后自动跳转
                    @Override
                    public void run() {
                        Toast.makeText(UserMainInterfaceActivity.this,"网络不可用", Toast.LENGTH_SHORT).show();
                    }
                },1500);
            }
            ifHadCheckedNet = true;
        }

    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                //按下
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //移动
//
//                break;
//            case MotionEvent.ACTION_UP:
//                //松开
//
//                break;
//        }
//        return super.onTouchEvent(event);

//    }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //按下
                if (view.getId()== R.id.button||view.getId()== R.id.homepage) {
                    ClickMotion.motion(UserMainInterfaceActivity.this,button,60);
                }
                if (view.getId()== R.id.imageButton2||view.getId()== R.id.personalcenter) {
                    ClickMotion.motion(UserMainInterfaceActivity.this,imageButton2,60);
                }
                if (view.getId()== R.id.taskk) {
                    ClickMotion.motion(UserMainInterfaceActivity.this,button2,85);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //移动

                break;
            case MotionEvent.ACTION_UP:
                //松开

                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.homepage){
            button.setImageDrawable(ic_user_home_selected);
            homepage.setTextColor(getResources().getColor(R.color.choosenGreen));
            imageButton2.setImageDrawable(ic_personal_center);
            personalcenter.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.right_layout, tpa).commit();
        }
        if (v.getId() == R.id.button){
            button.setImageDrawable(ic_user_home_selected);
            homepage.setTextColor(getResources().getColor(R.color.choosenGreen));
            imageButton2.setImageDrawable(ic_personal_center);
            personalcenter.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.right_layout, tpa).commit();
        }
        if (v.getId() == R.id.imageButton2){
            imageButton2.setImageDrawable(ic_personal_center_selected);
            personalcenter.setTextColor(getResources().getColor(R.color.choosenGreen));
            button.setImageDrawable(ic_user_home);
            homepage.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.right_layout, pca).commit();

        }
        if (v.getId() == R.id.personalcenter){
            imageButton2.setImageDrawable(ic_personal_center_selected);
            personalcenter.setTextColor(getResources().getColor(R.color.choosenGreen));
            button.setImageDrawable(ic_user_home);
            homepage.setTextColor(getResources().getColor(R.color.black));
            getSupportFragmentManager().beginTransaction().replace(R.id.right_layout, pca).commit();

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
