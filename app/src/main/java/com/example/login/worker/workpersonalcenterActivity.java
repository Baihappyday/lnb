package com.example.login.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.EnrollActicity;
import com.example.login.R;
import com.example.login.UserLoginActivity;

public class workpersonalcenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_personal_center);

        //关于订单的4个按钮
        Button btn_o1 = findViewById(R.id.workbt1);
        btn_o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {      //to update
                Intent i = new Intent(workpersonalcenterActivity.this, WorkerOrder.class);
                startActivity(i);

            }
        });

        Button btn_o2 = findViewById(R.id.workbt2);
        btn_o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {      //to update
                Intent i = new Intent(workpersonalcenterActivity.this, WorkerOrder.class);
                startActivity(i);

            }
        });

        Button btn_o3 = findViewById(R.id.workbt3);
        btn_o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {      //to update
                Intent i = new Intent(workpersonalcenterActivity.this, WorkerOrder.class);
                startActivity(i);

            }
        });

        Button btn_o4 = findViewById(R.id.workbt4);
        btn_o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {      //to update
                Intent i = new Intent(workpersonalcenterActivity.this, WorkerOrder.class);
                startActivity(i);

            }
        });
        //关于订单的4个按钮结束


        Button btn_shouye2 = findViewById(R.id.shouye2);//按钮：任务列表
        btn_shouye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(workpersonalcenterActivity.this, WorkmaininterfaceAcitvity.class);
                startActivity(intent);
            }
        });
        Button btn_inwallet = findViewById(R.id.inwallet);
        btn_inwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(workpersonalcenterActivity.this, workwalletActivity.class);//此处崩溃
                startActivity(i);

            }
        });


    }
}
