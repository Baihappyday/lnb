package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.user) == v){
            Intent i = new Intent(this, UserMainInterfaceActivity.class);
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