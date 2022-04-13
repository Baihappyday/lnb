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

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Add_activityActivity extends AppCompatActivity {
    private EditText add_address;
    private EditText add_time;
    private EditText add_description;
    private Button add_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.add_commmunity_activities);
        super.onCreate(savedInstanceState);

        add_address = findViewById(R.id.address2);
        add_time = findViewById(R.id.time2);
        add_description = findViewById(R.id.description2);
        add_submit = findViewById(R.id.addsubmit);
        add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String address = add_address.getText().toString();
                final String time = add_time.getText().toString();
                final String description = add_description.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*JSONObject obj = new JSONObject();
                        try {
                            obj.put("activityaddress", address);
                            obj.put("activitytime", time);
                            obj.put("activitydescription",description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MediaType type = MediaType.parse("application/json;charset=utf-8");
                        RequestBody RequestBody2 = RequestBody.create(type, "" + obj.toString());*/
                        try {
                            String json = "{\n" +
                                    "  \"activityaddress\": \"" + address + "\",\n" +
                                    "  \"activitytime\": \"" + time + "\",\n" +
                                    " \"activitydescription\": \"" +description +"\"\n"+"}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // 指定访问的服务器地址
                                    .url("http://120.48.5.10:9090/activity-add")
                                    //.post(RequestBody2)
                                    .post(RequestBody.create(MediaType.parse("application/json"),json))
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                            if (parseJSONWithJSONObject(responseData).equals("true")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Add_activityActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Add_activityActivity.this, CommunitylistActivity.class);
                                        startActivity(i);
                                    }
                                });
                            } else if (parseJSONWithJSONObject(responseData).equals("false")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Add_activityActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Add_activityActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }


                }).start();
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