package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//选择用户界面
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.user) == v){
            //我是用户->跳转至user_login用户登录界面
            //保存密码的用户直接跳转至首页？？
            Intent i = new Intent(MainActivity.this, UserLoginActivity.class);
            startActivity(i);
        }
        if (findViewById(R.id.worker) == v){
            //志愿者/家政->

        }
        if (findViewById(R.id.community) == v){
            //社区->

        }
        if (findViewById(R.id.institution) == v){
            //养老机构->

        }
    }
}
