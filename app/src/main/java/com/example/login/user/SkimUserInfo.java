package com.example.login.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.util.SharedUtil;

public class SkimUserInfo extends AppCompatActivity implements View.OnClickListener{
    private Button modify;
    private TextView hi_name;
    private TextView age;
    private TextView gender;
    private TextView address;
    private TextView phonenum;
    private TextView birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        modify = findViewById(R.id.modify);
        modify.setOnClickListener(this);

        SharedUtil sp = SharedUtil.getIntance(this,"healthinfo");
        hi_name = findViewById(R.id.hi_name);
        hi_name.setText(hi_name.getText().toString()+sp.readShared("uusername","无"));
        age = findViewById(R.id.age);
        age.setText(age.getText().toString()+sp.readShared("uage","0"));
        gender = findViewById(R.id.gender);
        gender.setText(gender.getText().toString()+sp.readShared("usex","男"));
        address = findViewById(R.id.address);
        address.setText(address.getText().toString()+sp.readShared("uaddress","无"));
        phonenum = findViewById(R.id.phonenum);
        phonenum.setText(phonenum.getText().toString()+sp.readShared("uphone","无"));
        birthday = findViewById(R.id.birthday);
        birthday.setText(birthday.getText().toString()+sp.readShared("ubirthday","1900年1月1日"));
        birthday.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (modify.getId() == R.id.modify){
            Intent i = new Intent();
            i.setClass(this,ModifyUserlInfo.class);
            startActivity(i);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(this,UserMainInterfaceActivity.class);
        i.putExtra("frag", 2);
        startActivity(i);
        return true;
    }
}
