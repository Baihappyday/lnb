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
        Button btn_shouye2 = findViewById(R.id.shouye2);
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
