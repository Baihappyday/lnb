package com.example.login.worker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.EnrollActicity;
import com.example.login.R;
import com.example.login.UserLoginActivity;

public class WorkmaininterfaceAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_main_interface);
        Button btn_gerenzhongxin = findViewById(R.id.gerenzhongxin);
        btn_gerenzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(WorkmaininterfaceAcitvity.this, workpersonalcenterActivity.class);
                startActivity(intent);
            }
        });
    }
}
