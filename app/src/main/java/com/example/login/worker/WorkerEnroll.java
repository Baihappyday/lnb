package com.example.login.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.user.UserLoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class WorkerEnroll extends AppCompatActivity {
    private EditText enroll_username;
    private EditText enroll_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eoroll);
        enroll_username=findViewById(R.id.username1);
        enroll_password=findViewById(R.id.password1);
        //按钮：确定
        Button confirm = (Button)findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = enroll_username.getText().toString();
                final String password = enroll_password.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("wusername",username);
                            obj.put("wpassword",password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MediaType type = MediaType.parse("application/json;charset=utf-8");
                        RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // 指定访问的服务器地址
                                    .url("http://120.48.5.10:9090/register/workers").post(RequestBody2)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                            if(parseJSONWithJSONObject(responseData).equals("注册成功")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(com.example.login.worker.WorkerEnroll.this,"注册成功！",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else if(parseJSONWithJSONObject(responseData).equals("用户名已存在")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(com.example.login.worker.WorkerEnroll.this,"用户名已存在!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(com.example.login.worker.WorkerEnroll.this,"网络错误",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
                /*Intent i = new Intent(EnrollActicity.this, UserLoginActivity.class);//跳转至登录界面
                //启动
                startActivity(i);*/

            }
        });

        //按钮：获取验证码
        /*Button getcode = (Button)findViewById(R.id.get_code);
        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });*/


        //按钮：已有账号？直接登录
        Button return_login = (Button)findViewById(R.id.return_login);
        return_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(com.example.login.worker.WorkerEnroll.this, UserLoginActivity.class);//跳转至登录界面
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

