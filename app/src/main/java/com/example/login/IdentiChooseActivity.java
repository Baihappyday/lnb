package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.community.Community_loginActivity;
import com.example.login.institution.Institution_loginActivity;
import com.example.login.user.UserMainInterfaceActivity;
import com.example.login.util.Utils;
import com.example.login.widget.CircleImageView;
import com.example.login.worker.WorkmaininterfaceAcitvity;

//import com.example.login.community.Community_loginActivity;

public class IdentiChooseActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button btn_user;
    private Button btn_worker;
    private Button btn_community;
    private Button btn_institution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identi_choice);

        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        DisplayMetrics dm =getResources().getDisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = Utils.px2dip(this,outMetrics.widthPixels);
        int height = outMetrics.heightPixels;

        CircleImageView user = findViewById(R.id.user_identi_ic);
        RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) user.getLayoutParams(); //取控件当前的布局参数
        relativeParams.width=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width/3, dm));// 控件的高强制设置
        CircleImageView worker = findViewById(R.id.worker_identi_ic);
        relativeParams =(RelativeLayout.LayoutParams) worker.getLayoutParams(); //取控件当前的布局参数
        relativeParams.width=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width/3, dm));// 控件的高强制设置
        CircleImageView community = findViewById(R.id.community_identi_ic);
        relativeParams =(RelativeLayout.LayoutParams) community.getLayoutParams(); //取控件当前的布局参数
        relativeParams.width=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width/3, dm));// 控件的高强制设置
        CircleImageView institution = findViewById(R.id.institution_identi_ic);
        relativeParams =(RelativeLayout.LayoutParams) institution.getLayoutParams(); //取控件当前的布局参数
        relativeParams.width=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width/3, dm));// 控件的高强制设置

        btn_user = (Button)findViewById(R.id.user);//我是用户
        btn_worker = (Button) findViewById(R.id.worker);//志愿者/家政
        btn_community = (Button) findViewById(R.id.community);
        btn_institution = (Button) findViewById(R.id.institution);
        btn_user.setOnClickListener(this);
        btn_worker.setOnClickListener(this);
        btn_community.setOnClickListener(this);
        btn_institution.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.user) == v){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//在跳转到目标activity前销毁所以已经存在的activity（实现不能返回的功能）
            intent.setClass(this, UserMainInterfaceActivity.class);
            startActivity(intent);
        }
        if (findViewById(R.id.worker) == v){
            //志愿者/家政->
            Intent i = new Intent(this, WorkmaininterfaceAcitvity.class);
            startActivity(i);
        }
        if (findViewById(R.id.community) == v){
            //社区->
            Intent i = new Intent(this, Community_loginActivity.class);
            startActivity(i);
        }
        if (findViewById(R.id.institution) == v){
            //养老机构->
            Intent i = new Intent(this, Institution_loginActivity.class);
            startActivity(i);
        }
    }
}
