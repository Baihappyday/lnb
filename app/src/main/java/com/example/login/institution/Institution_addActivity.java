package com.example.login.institution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.community.CommunityaddActivity;
import com.example.login.community.CommunitylistActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Institution_addActivity extends AppCompatActivity {

    private EditText iuser;
    private EditText iad;
    private EditText ina;
    private EditText ipr;
    private EditText ides;
    private Button btn_isubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institutional_add);
        iuser = findViewById(R.id.institutionuser);
        iad = findViewById(R.id.institutionaddress);
        ina = findViewById(R.id.institutionname);
        ipr = findViewById(R.id.institutionprice);
        ides = findViewById(R.id.institutiondes);
        btn_isubmit = findViewById(R.id.isubmit);
        btn_isubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String iiusername = iuser.getText().toString();
                final String iaddress = iad.getText().toString();
                final String iname = ina.getText().toString();
                final Integer iprice = Integer.parseInt(ipr.getText().toString());
                final String idescription = ides.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       /* JSONObject obj = new JSONObject();
                        try {
                            obj.put("iiusername",iiusername);
                            obj.put("iaddress",iaddress);
                            obj.put("iname", iname);
                            obj.put("iprice", iprice);
                            obj.put("idescription",idescription);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MediaType type = MediaType.parse("application/json;charset=utf-8");
                        RequestBody RequestBody2 = RequestBody.create(type, "" + obj.toString());*/
                        try {
                            String json = "{\n" +
                                    "  \"iiusername\": \"" + iiusername + "\",\n" +
                                    "  \"iaddress\": \"" + iaddress+ "\",\n" +
                                    " \"iname\": \"" + iname +"\",\n" +
                                    "\"iprice\":\"" + iprice +"\",\n"+
                                    "\"idescription\":\"" + idescription +"\"\n"+
                                    "}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // 指定访问的服务器地址
                                    .url("http://192.168.232.1:9090/institution-add")
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
                                        Toast.makeText(Institution_addActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Institution_addActivity.this, Institution_listActivity.class);
                                        startActivity(i);
                                    }
                                });
                            } else if (parseJSONWithJSONObject(responseData).equals("error")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Institution_addActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Institution_addActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
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