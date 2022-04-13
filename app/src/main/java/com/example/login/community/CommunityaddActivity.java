package com.example.login.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommunityaddActivity extends AppCompatActivity {

    private EditText caddress;
    private EditText cname;
    private EditText cid;
    private EditText cdescription;
    private Button censure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_add);
        caddress = findViewById(R.id.Caddress);
        cname = findViewById(R.id.Cname);
        cid = findViewById(R.id.Cid);
        cdescription = findViewById(R.id.Cdescription);
        censure = findViewById(R.id.Censure1);
        censure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String adress = caddress.getText().toString();
                final String id = cid.getText().toString();
                final String name = cname.getText().toString();
                final String description = cdescription.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*JSONObject obj = new JSONObject();
                        try {
                            obj.put("ccusername",id);
                            obj.put("caddress", adress);
                            obj.put("cname", name);
                            obj.put("cdescription",description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MediaType type = MediaType.parse("application/json;charset=utf-8");
                        RequestBody RequestBody2 = RequestBody.create(type, "" + obj.toString());*/
                        try {
                            String json = "{\n" +
                                    "  \"ccusername\": \"" + id + "\",\n" +
                                    "  \"caddress\": \"" + adress + "\",\n" +
                                    " \"cname\": \"" + name +"\",\n"+
                                    "\"cdescription\":\"" + description +"\"\n"+
                                    "}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // 指定访问的服务器地址
                                    .url("http://120.48.5.10:9090/community-add")
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
                                        Toast.makeText(CommunityaddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(CommunityaddActivity.this, CommunitylistActivity.class);
                                        startActivity(i);
                                    }
                                });
                            } else if (parseJSONWithJSONObject(responseData).equals("false")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CommunityaddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(CommunityaddActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
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
