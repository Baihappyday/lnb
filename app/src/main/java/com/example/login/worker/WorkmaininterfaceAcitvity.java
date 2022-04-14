package com.example.login.worker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.login.R;
import com.example.login.user.UserMainInterfaceActivity;


public class WorkmaininterfaceAcitvity extends AppCompatActivity
{
    WorkermainActivity mi = new WorkermainActivity();
    WorkpersonalActivity p = new WorkpersonalActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_main);
        Toast.makeText(WorkmaininterfaceAcitvity.this,"欢迎使用老年宝，请前往个人中心登录",Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();
        Button button1=findViewById(R.id.button1);//首页
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();
            }
        });
        Button button2=findViewById(R.id.button2);//个人中心
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, p).commit();
            }
        });
    }
}
