package com.example.login.institution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.R;


public class Institution_chooseActivity extends AppCompatActivity implements  View.OnClickListener{
    Button btn_look;
    Button btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institution_choose);
        btn_look = (Button) findViewById(R.id.button7);
        btn_add = (Button) findViewById(R.id.button10);

        btn_look.setOnClickListener((View.OnClickListener) this);
        btn_add.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public  void onClick(View v){
        //查看机构详情
        if (findViewById(R.id.button7) == v){
            Intent i = new Intent(this, Institution_listActivity.class);
            startActivity(i);
        }
        if (findViewById(R.id.button10) == v){
            //添加机构信息
            Intent i = new Intent(this, Institution_addActivity.class);
            startActivity(i);
        }

    }
}