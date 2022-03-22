package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.community.CommunitymainActivity;
import com.example.login.institution.InstitutionActivity;
import com.example.login.user.UserMainInterfaceActivity;
import com.example.login.worker.WorkmaininterfaceAcitvity;

public class IdentiChooseActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button btn_user;
    private Button btn_worker;
    private Button btn_community;
    private Button btn_institution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identi_choice);
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
            intent.setClass(this,UserMainInterfaceActivity.class);
            startActivity(intent);
        }
        if (findViewById(R.id.worker) == v){
            //志愿者/家政->
            Intent i = new Intent(this, WorkmaininterfaceAcitvity.class);
            startActivity(i);
        }
        if (findViewById(R.id.community) == v){
            //社区->
            Intent i = new Intent(this, CommunitymainActivity.class);
            startActivity(i);
        }
        if (findViewById(R.id.institution) == v){
            //养老机构->
            Intent i = new Intent(this, InstitutionActivity.class);
            startActivity(i);
        }
    }
}
