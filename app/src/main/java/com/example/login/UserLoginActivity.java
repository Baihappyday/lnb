package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.user.UserMainInterfaceActivity;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserLoginActivity extends AppCompatActivity {
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

        btn_login_user = findViewById(R.id.submit);
        btn_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = login_username.getText().toString();
                final String password = login_password.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("username",username);
                            obj.put("password",password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MediaType type = MediaType.parse("application/json;charset=utf-8");
                        RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // 指定访问的服务器地址
                                    .url("http://192.168.140.71:9090/login").post(RequestBody2)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                            if(parseJSONWithJSONObject(responseData).equals("登录成功")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UserLoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                        Intent i= new Intent(UserLoginActivity.this, UserMainInterfaceActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(parseJSONWithJSONObject(responseData).equals("用户名或密码错误")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UserLoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UserLoginActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });


        //按钮：注册
        btn_enroll = findViewById(R.id.enroll);
        btn_enroll.setOnClickListener(new View.OnClickListener() {//点击注册账号按钮
            @Override
            public void onClick(View view) {
                //填写页面跳转的逻辑UserLoginActivity
                Intent intent=new Intent(UserLoginActivity.this,EnrollActicity/*UserMainInterfaceActivity*/.class);
                startActivity(intent);

            }
        });

        //按钮：忘记密码
        btn_forget_password=findViewById(R.id.forget);
        btn_forget_password.setOnClickListener(new View.OnClickListener() {//登录按钮
            @Override
            public void onClick(View view) {
                //填写页面跳转的逻辑
                Intent i = new Intent(UserLoginActivity.this, ForgetPasswordActivity.class);
                //启动
                startActivity(i);

            }
        });
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
}
