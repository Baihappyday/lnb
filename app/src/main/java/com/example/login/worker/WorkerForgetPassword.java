package com.example.login.worker;

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
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkerForgetPassword extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView textview;
    private EditText username1;
    private EditText password1;

    HashMap<String, String> hashMap = new HashMap<>();//存放用户名与新旧密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btn = findViewById(R.id.button4);
        textview = findViewById(R.id.textView);
        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //按钮：验证
        if(view.getId() == R.id.button4){
            String btnText = btn.getText().toString();
            if (btnText.equals("验证")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        hashMap.put("wusername", username1.getText().toString());
                        hashMap.put("wpassword", password1.getText().toString());
                        ArrayList<String> send = new ArrayList<String>();
                        send.add("wusername");
                        send.add("wpassword");
                        final ArrayList<String> recieve = new ArrayList<String>();
                        recieve.add("msg");
                        OkHttp okHttp = new OkHttp(send,recieve);
                        HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/login/workers");
                        Log.d("TAG", hm.get("msg"));
                        if (hm.get("msg").equals("登录成功")){
                            Looper.prepare();
                            Toast.makeText(com.example.login.worker.WorkerForgetPassword.this,"验证通过，请输入新密码", Toast.LENGTH_SHORT).show();
                            textview.setText("请输入新密码");
                            username1.setHint("请输入新密码");
                            username1.setText(null);
                            password1.setHint("请输入新密码");
                            password1.setText(null);
                            btn.setText("确认修改");
                            Looper.loop();

                        }else{
                            Looper.prepare();
                            Toast.makeText(WorkerForgetPassword.this,"身份验证未通过", Toast.LENGTH_SHORT).show();
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
                            String oPswd = hashMap.get("wpassword");
                            hashMap.remove("wpassword");
                            hashMap.put("woldPassword",oPswd);
                            hashMap.put("wnewPassword",s1);
                            ArrayList<String> send = new ArrayList<String>();
                            send.add("wusername");
                            send.add("woldPassword");
                            send.add("wnewPassword");
                            final ArrayList<String> recieve = new ArrayList<String>();
                            recieve.add("msg");
                            recieve.add("judgeinfo");
                            OkHttp okHttp = new OkHttp(send,recieve,2, com.example.login.worker.WorkerForgetPassword.this);
                            HashMap<String,String> hm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/chpassword/workers");
                            Log.d("TAG", hm.get("msg"));
                            if (hm.get("judgeinfo").equals("true")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(WorkerForgetPassword.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                                        Intent ii = new Intent(WorkerForgetPassword.this, WorkerLogin.class);
                                        startActivity(ii);
                                    }
                                });
                            }
                            else {
                                Looper.prepare();
                                Toast.makeText(com.example.login.worker.WorkerForgetPassword.this,"修改失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        com.example.login.worker.WorkerForgetPassword.this.onBackPressed();
                                    }
                                });


                            }
                        }
                    }).start();
                }
                else if (s1.equals(s2)&&s1.equals("")){
                    Looper.prepare();
                    Toast.makeText(com.example.login.worker.WorkerForgetPassword.this,"密码不能为空", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
                else {
                    Toast.makeText(com.example.login.worker.WorkerForgetPassword.this,"两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
            }
            else if (btnText.equals("返回")){
                super.onBackPressed();
            }
        }
    }
}