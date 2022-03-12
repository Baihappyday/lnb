package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_user;
    private Button btn_worker;
    private Button btn_community;
    private Button btn_institution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identi_choice);
        btn_user = (Button)findViewById(R.id.user);
        btn_worker = (Button) findViewById(R.id.worker);
        btn_community = (Button) findViewById(R.id.community);
        btn_institution = (Button) findViewById(R.id.institution);
        btn_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.user) == v){
            Intent i = new Intent(MainActivity.this, UserLoginActivity.class);
            //启动
            startActivity(i);
        }
        if (findViewById(R.id.worker) == v){

        }
        if (findViewById(R.id.community) == v){

        }
        if (findViewById(R.id.institution) == v){

        }
    }
}
