package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.login.R;
import com.example.login.user.ForgetPasswordActivity;
import com.example.login.user.UserMainInterfaceActivity;
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

public class Community_forgetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private EditText username1;
    private EditText password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_forget);

        btn = findViewById(R.id.yanzheng);
        btn.setOnClickListener((View.OnClickListener) this);
        username1 = findViewById(R.id.forgetusername);
        password1 = findViewById(R.id.forgetpassword);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.yanzheng){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("cusername", username1.getText().toString());
                    hashMap.put("cpassword", password1.getText().toString());
                    ArrayList<String> send = new ArrayList<String>();
                    send.add("cusername");
                    send.add("cpassword");
                    final ArrayList<String> recieve = new ArrayList<String>();
                    recieve.add("msg");
                    OkHttp okHttp = new OkHttp(send,recieve);
                    HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "localhost:9090/login/communityusers");
                    if (hm.get("msg").equals("登录成功")){
                        Intent i= new Intent(Community_forgetActivity.this, CommunitylistActivity.class);
                        startActivity(i);
                    }
                }
            }).start();
        }
    }
}