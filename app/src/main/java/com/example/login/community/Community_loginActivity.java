package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.MyApplication;
import com.example.login.user.EnrollActicity;
import com.example.login.user.ForgetPasswordActivity;
import com.example.login.user.UserLoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Community_loginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText login_username;
    private EditText login_password;
    private Button btn_login_user;
    private Button btn_forget_password;
    private Button btn_enroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_login);
        login_username = findViewById(R.id.communityname1);
        login_password = findViewById(R.id.communitypassword1);
        //按钮，登录
        btn_login_user = findViewById(R.id.communitysubmit);

        btn_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cusername = login_username.getText().toString();
                final String cpassword = login_password.getText().toString();
               // Log.d("TAG", cpassword+cusername);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*JSONObject obj = new JSONObject();
                        try {
                            obj.put("cusername", cusername);
                            obj.put("cpassword", cpassword);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                        //MediaType type = MediaType.parse("application/json;charset=utf-8");
                        //RequestBody RequestBody2 = RequestBody.create(type, "application/json" + obj.toString());
                        try {
                           String json = "{\n" +
                                    "  \"cusername\": \"" + cusername + "\",\n" +
                                    "  \"cpassword\": \"" + cpassword + "\"\n" +
                                    "}";
                           //String json2 = "{\n"+"cusername"+"+cpassword""};

                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // 指定访问的服务器地址
                                    .url("http://192.168.232.1:9090/login/communityusers")
                                    //.post(RequestBody2)
                                    .post(RequestBody.create(MediaType.parse("application/json"),json))
                                    .build();
                            Response response = client.newCall(request).execute();

                            final String responseData = response.body().string();
                            //Log.d("TAG", responseData);

                            parseJSONWithJSONObject(responseData);

                            if (parseJSONWithJSONObject(responseData).equals("登录成功")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Community_loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Community_loginActivity.this, CommunitylistActivity.class);
                                        startActivity(i);
                                    }
                                });
                            } else if (parseJSONWithJSONObject(responseData).equals("用户名或密码错误")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Community_loginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Community_loginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }


                }).start();
            }


        });

        //按钮：注册
        btn_enroll = findViewById(R.id.communityeroll);
        btn_enroll.setOnClickListener(this);
        //按钮：忘记密码
        btn_forget_password = findViewById(R.id.commubityforget);
        btn_forget_password.setOnClickListener(this);


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
        if (view.getId() == R.id.communityeroll){
            //填写页面跳转的逻辑UserLoginActivity
            Intent intent=new Intent(Community_loginActivity.this,Community_erollActivity.class );
            startActivity(intent);
        }
        if (view.getId() == R.id.commubityforget){
            //填写页面跳转的逻辑
            Intent i = new Intent(Community_loginActivity.this, Community_forgetActivity.class);
            //启动
            startActivity(i);
        }
        
    }
}