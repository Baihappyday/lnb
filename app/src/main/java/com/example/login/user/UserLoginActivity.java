package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_username;
    private EditText login_password;
    private Button btn_login_user;
    private Button btn_forget_password;
    private Button btn_enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        //按钮：登录

        login_username=findViewById(R.id.username1);
        login_password=findViewById(R.id.password1);

        btn_login_user = findViewById(R.id.modify);
        btn_login_user.setOnClickListener(this);
//        btn_login_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String username = login_username.getText().toString();
//                final String password = login_password.getText().toString();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        JSONObject obj = new JSONObject();
//                        try {
//                            obj.put("username",username);
//                            obj.put("password",password);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        MediaType type = MediaType.parse("application/json;charset=utf-8");
//                        RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());
//                        try {
//                            OkHttpClient client = new OkHttpClient();
//                            Request request = new Request.Builder()
//                                    // 指定访问的服务器地址
//                                    .url("http://192.168.140.71:9090/login").post(RequestBody2)
//                                    .build();
//                            Response response = client.newCall(request).execute();
//                            String responseData = response.body().string();
//                            parseJSONWithJSONObject(responseData);
//                            if(parseJSONWithJSONObject(responseData).equals("登录成功")){
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(UserLoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
//                                        Intent i= new Intent(UserLoginActivity.this, UserMainInterfaceActivity.class);
//                                        startActivity(i);
//                                    }
//                                });
//                            }
//                            else if(parseJSONWithJSONObject(responseData).equals("用户名或密码错误")){
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(UserLoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(UserLoginActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                }).start();
//            }
//        });


        //按钮：注册
        btn_enroll = findViewById(R.id.enroll);
        btn_enroll.setOnClickListener(this);
//        btn_enroll.setOnClickListener(new View.OnClickListener() {//点击注册账号按钮
//            @Override
//            public void onClick(View view) {
//                //填写页面跳转的逻辑UserLoginActivity
//                Intent intent=new Intent(UserLoginActivity.this,EnrollActicity/*UserMainInterfaceActivity*/.class);
//                startActivity(intent);
//            }
//        });

        //按钮：忘记密码
        btn_forget_password=findViewById(R.id.forget);
        btn_forget_password.setOnClickListener(this);
//        btn_forget_password.setOnClickListener(new View.OnClickListener() {//登录按钮
//            @Override
//            public void onClick(View view) {
//                //填写页面跳转的逻辑
//                Intent i = new Intent(UserLoginActivity.this, ForgetPasswordActivity.class);
//                //启动
//                startActivity(i);
//
//            }
//        });
    }
    private String parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject object=new JSONObject(jsonData);
            String name = object.getString("msg");
            //日志
            Log.d("name", name);
            return name;
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.modify){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("username", login_username.getText().toString());
                    hashMap.put("password", login_password.getText().toString());
                    ArrayList<String> send = new ArrayList<String>();
                    send.add("username");
                    send.add("password");
                    final ArrayList<String> recieve = new ArrayList<String>();
                    recieve.add("msg");
                    OkHttp okHttp = new OkHttp(send,recieve);
                    HashMap<String,String> rhm = okHttp.sendRequestWithOkHttp(hashMap, "http://120.48.5.10:9090/login/users");
                    if (rhm.get("msg").equals("登录成功")){
                        MyApplication application = (MyApplication) UserLoginActivity.this.getApplicationContext();
                        application.setName(hashMap.get("username"));//设置全局变量name
                        application.setLoginState(true);//设置登录状态
                        SharedUtil sp = SharedUtil.getIntance(UserLoginActivity.this,"logininfo");
                        sp.writeShared(send,hashMap);//写入登录信息
                        sp.writeShared("loginstate", true);//登录状态设为真，下次进app直接跳转到对应身份主界面
                        sp.writeShared("identification","0");//设置身份

                        Log.d("tag", String.valueOf(sp.readShared("loginstate", false)));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HashMap<String, String> shm = new HashMap<>();
                                shm.put("uusername", login_username.getText().toString());
                                ArrayList<String> send = new ArrayList<String>();
                                send.add("uusername");
                                ArrayList<String> recieve = new ArrayList<String>();
                                recieve.add("msg");
                                recieve.add("uage");
                                recieve.add("usex");
                                recieve.add("uaddress");
                                recieve.add("uphone");
                                recieve.add("ubloodtype");
                                recieve.add("uhealthcondition");
                                OkHttp okHttp = new OkHttp(send,recieve);
                                HashMap<String,String> rhm = okHttp.sendRequestWithOkHttp(shm, "http://120.48.5.10:9090/display");
                                SharedUtil sp = SharedUtil.getIntance(UserLoginActivity.this,"healthinfo");
                                sp.writeShared(recieve,rhm);

                            }
                        }).start();
                        Intent i= new Intent(UserLoginActivity.this, UserMainInterfaceActivity.class);
                        i.putExtra("frag",0);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                }
            }).start();
        }
        if (view.getId() == R.id.enroll){
            //填写页面跳转的逻辑UserLoginActivity
            Intent intent=new Intent(UserLoginActivity.this, EnrollActicity/*UserMainInterfaceActivity*/.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.forget){
            //填写页面跳转的逻辑
                Intent i = new Intent(UserLoginActivity.this, ForgetPasswordActivity.class);
                //启动
                startActivity(i);
        }
    }

}
