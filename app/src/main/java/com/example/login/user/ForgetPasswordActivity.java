package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.login.R;
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private EditText username1;
    private EditText password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btn = findViewById(R.id.button4);
        btn.setOnClickListener(this);
        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button4){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("username", username1.getText().toString());
                    hashMap.put("password", password1.getText().toString());
                    ArrayList<String> send = new ArrayList<String>();
                    send.add("username");
                    send.add("password");
                    final ArrayList<String> recieve = new ArrayList<String>();
                    recieve.add("msg");
                    OkHttp okHttp = new OkHttp(send,recieve);
                    HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://192.168.1.9:9090/login");
                    if (hm.get("msg").equals("登录成功")){
                        Intent i= new Intent(ForgetPasswordActivity.this, UserMainInterfaceActivity.class);
                        startActivity(i);
                    }
                }
            }).start();
        }
    }
}