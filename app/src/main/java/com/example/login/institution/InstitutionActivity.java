package com.example.login.institution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.R;


public class InstitutionActivity extends AppCompatActivity implements  View.OnClickListener{
    Button btn_xiangqing;
    Button btn_shenhe;
    Button btn_zhuangtai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institution_choose);
        btn_xiangqing = (Button) findViewById(R.id.button7);
        btn_shenhe = (Button) findViewById(R.id.button10);
        btn_zhuangtai = (Button) findViewById(R.id.button11);
        btn_xiangqing.setOnClickListener((View.OnClickListener) this);
        btn_shenhe.setOnClickListener((View.OnClickListener) this);
        btn_zhuangtai.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public  void onClick(View v){
        //机构详情
        if (findViewById(R.id.button7) == v){
            Intent i = new Intent(this, XiangqingActivity.class);
            startActivity(i);
        }
        if (findViewById(R.id.button10) == v){
            //我要审核
            Intent i = new Intent(this,ShenheActivity.class);
            startActivity(i);
        }
        if (findViewById(R.id.button11) == v){
            //审核状态
            Intent i = new Intent(this, ZhuangtaiActivity.class);
            startActivity(i);
        }
    }
}