package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.user.ForgetPasswordActivity;
import com.example.login.user.UserLoginActivity;
import com.example.login.user.UserMainInterfaceActivity;
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

public class Community_forgetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private EditText username1;
    private EditText password1;
    private TextView textview;
    HashMap<String, String> hashMap = new HashMap<>();//存放用户名与新旧密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_forget);
        textview = findViewById(R.id.ctextView);
        btn = findViewById(R.id.yanzheng);
        btn.setOnClickListener((View.OnClickListener) this);
        username1 = findViewById(R.id.forgetusername);
        password1 = findViewById(R.id.forgetpassword);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.yanzheng){
            String btnText = btn.getText().toString();
            if (btnText.equals("验证")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        hashMap.put("cusername", username1.getText().toString());
                        hashMap.put("cpassword", password1.getText().toString());
                        ArrayList<String> send = new ArrayList<String>();
                        send.add("cusername");
                        send.add("cpassword");
                        final ArrayList<String> recieve = new ArrayList<String>();
                        recieve.add("msg");
                        OkHttp okHttp = new OkHttp(send,recieve);
                        HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/login/communityusers");
                        Log.d("TAG", hm.get("msg"));
                        if (hm.get("msg").equals("登录成功")){
                            Looper.prepare();
                            Toast.makeText(Community_forgetActivity.this,"验证通过，请输入新密码", Toast.LENGTH_SHORT).show();
                            textview.setText("请输入新密码");
                            username1.setHint("请输入新密码");
                            username1.setText(null);
                            password1.setHint("请确认新密码");
                            password1.setText(null);
                            btn.setText("确认修改");
                            Looper.loop();

                        } else{
                            Looper.prepare();
                            Toast.makeText(Community_forgetActivity.this,"身份验证未通过", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
            else if (btnText.equals("确认修改")){
                final String s1 = username1.getText().toString();
                String s2 = password1.getText().toString();
                if (s1.equals(s2)&&!s1.equals("")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String oPswd = hashMap.get("cpassword");
                            hashMap.remove("cpassword");
                            hashMap.put("coldPassword",oPswd);
                            hashMap.put("cnewPassword",s1);
                            ArrayList<String> send = new ArrayList<String>();
                            send.add("cusername");
                            send.add("coldPassword");
                            send.add("cnewPassword");
                            final ArrayList<String> recieve = new ArrayList<String>();
                            recieve.add("msg");
                            recieve.add("judgeinfo");
                            OkHttp okHttp = new OkHttp(send,recieve,2,Community_forgetActivity.this);
                            HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/chpassword/communityusers");
                            Log.d("TAG", hm.get("msg"));
                            if (hm.get("judgeinfo").equals("true")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Community_forgetActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Community_forgetActivity.this, Community_loginActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else {
                                Looper.prepare();
                                Toast.makeText(Community_forgetActivity.this,"修改失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Community_forgetActivity.this.onBackPressed();
                                    }
                                });


                            }
                        }
                    }).start();
                }
                else if (s1.equals(s2)&&s1.equals("")){
                    Looper.prepare();
                    Toast.makeText(Community_forgetActivity.this,"密码不能为空", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
                else {
                    Toast.makeText(Community_forgetActivity.this,"两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
            }
            else if (btnText.equals("返回")){
                super.onBackPressed();
            }
        }
    }
}