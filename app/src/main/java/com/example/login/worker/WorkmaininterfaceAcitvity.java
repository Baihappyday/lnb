package com.example.login.worker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.login.R;


public class WorkmaininterfaceAcitvity extends AppCompatActivity
{
    WorkermainActivity mi = new WorkermainActivity();
    WorkpersonalActivity p = new WorkpersonalActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();
        Button button1=findViewById(R.id.button1);//首页
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();
            }
        });
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, p).commit();
            }
        });
    }
}
