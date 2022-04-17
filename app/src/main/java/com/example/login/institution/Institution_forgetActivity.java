package com.example.login.institution;

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
import com.example.login.community.Community_forgetActivity;
import com.example.login.community.CommunitylistActivity;
import com.example.login.user.UserLoginActivity;
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

public class Institution_forgetActivity extends AppCompatActivity implements  View.OnClickListener{


    private Button btn;
    private EditText username1;
    private EditText password1;
    HashMap<String, String> hashMap = new HashMap<>();//存放用户名与新旧密码
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institution_forget);
        textview = findViewById(R.id.itextView);
        btn = findViewById(R.id.yanzheng2);
        btn.setOnClickListener((View.OnClickListener) this);
        username1 = findViewById(R.id.forgetusername2);
        password1 = findViewById(R.id.forgetpassword2);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.yanzheng2){
            String btnText = btn.getText().toString();
            if (btnText.equals("验证")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        hashMap.put("iusername", username1.getText().toString());
                        hashMap.put("ipassword", password1.getText().toString());
                        ArrayList<String> send = new ArrayList<String>();
                        send.add("iusername");
                        send.add("ipassword");
                        final ArrayList<String> recieve = new ArrayList<String>();
                        recieve.add("msg");
                        OkHttp okHttp = new OkHttp(send,recieve);
                        HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/login/institutionusers");
                        Log.d("TAG", hm.get("msg"));
                        if (hm.get("msg").equals("登录成功")){
                            Looper.prepare();
                            Toast.makeText(Institution_forgetActivity.this,"验证通过，请输入新密码", Toast.LENGTH_SHORT).show();
                            textview.setText("请输入新密码");
                            username1.setHint("请输入新密码");
                            username1.setText(null);
                            password1.setHint("请确认新密码");
                            password1.setText(null);
                            btn.setText("确认修改");
                            Looper.loop();

                        } else{
                            Looper.prepare();
                            Toast.makeText(Institution_forgetActivity.this,"身份验证未通过", Toast.LENGTH_SHORT).show();
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
                            String oPswd = hashMap.get("ipassword");
                            hashMap.remove("ipassword");
                            hashMap.put("ioldPassword",oPswd);
                            hashMap.put("inewPassword",s1);
                            ArrayList<String> send = new ArrayList<String>();
                            send.add("iusername");
                            send.add("ioldPassword");
                            send.add("inewPassword");
                            final ArrayList<String> recieve = new ArrayList<String>();
                            recieve.add("msg");
                            recieve.add("judgeinfo");
                            OkHttp okHttp = new OkHttp(send,recieve,2,Institution_forgetActivity.this);
                            HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/chpassword/institutionusers");
                            Log.d("TAG", hm.get("msg"));
                            if (hm.get("judgeinfo").equals("true")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Institution_forgetActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Institution_forgetActivity.this, Institution_loginActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else {
                                Looper.prepare();
                                Toast.makeText(Institution_forgetActivity.this,"修改失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Institution_forgetActivity.this.onBackPressed();
                                    }
                                });


                            }
                        }
                    }).start();
                }
                else if (s1.equals(s2)&&s1.equals("")){
                    Looper.prepare();
                    Toast.makeText(Institution_forgetActivity.this,"密码不能为空", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
                else {
                    Toast.makeText(Institution_forgetActivity.this,"两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
            }
            else if (btnText.equals("返回")){
                super.onBackPressed();
            }
        }
    }
}