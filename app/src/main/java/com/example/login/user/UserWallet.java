package com.example.login.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;

//用户钱包界面
public class UserWallet extends AppCompatActivity {
    TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wallet);
        account = findViewById(R.id.account);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> hashMap = new HashMap<>();
                SharedUtil sp = SharedUtil.getIntance(UserWallet.this,"logininfo");
                hashMap.put("username", sp.readShared("username"," "));
                Log.d("username", "run: "+sp.readShared("username"," "));
                Log.d("password", "run: "+sp.readShared("password"," "));
                hashMap.put("password", sp.readShared("password"," "));
                ArrayList<String> send = new ArrayList<String>();
                send.add("username");
                send.add("password");
                final ArrayList<String> recieve = new ArrayList<String>();
                recieve.add("msg");
                recieve.add("account");
                OkHttp okHttp = new OkHttp(send,recieve);
                HashMap<String,String> rhm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/login/users");
                Log.d("msg", "run: "+rhm.get("msg"));
                if (rhm.get("msg").equals("登录成功")){
                    //Toast.makeText(UserLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    sp.writeShared("account",rhm.get("account"));
                    final String account_money = rhm.get("account");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            account.setText(account_money+"元");
                        }
                    });


                }else if (rhm.get("msg").equals("用户名或密码错误")){

                }
            }
        }).start();
        //QRCode = (ImageButton)findViewById(R.id.qr_code);
        //Wallet = (ImageButton)findViewById(R.id.wallet);
    }
}