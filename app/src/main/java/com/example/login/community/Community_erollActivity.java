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
import com.example.login.institution.Institution_erollActivity;
import com.example.login.institution.Institution_loginActivity;
import com.example.login.user.EnrollActicity;
import com.example.login.user.UserLoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Community_erollActivity extends AppCompatActivity {

    private EditText enroll_username;
    private EditText enroll_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_eroll);
        enroll_username=findViewById(R.id.username3);
        enroll_password=findViewById(R.id.password3);
        Button confirm = (Button)findViewById(R.id.confirm_button3);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cusername = enroll_username.getText().toString();
                final String cpassword = enroll_password.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*JSONObject obj = new JSONObject();
                        try {
                            obj.put("cusername",username);
                            obj.put("cpassword",password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MediaType type = MediaType.parse("application/json;charset=utf-8");
                        RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());*/
                        try {

                                String json = "{\n" +
                                        "  \"cusername\": \"" + cusername + "\",\n" +
                                        "  \"cpassword\": \"" + cpassword + "\"\n" +
                                        "}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // ??????????????????????????????
                                    .url("http://120.48.5.10:9090/register/communityusers")
                                    //.post(RequestBody2)
                                    .post(RequestBody.create(MediaType.parse("application/json"),json))
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                            if(parseJSONWithJSONObject(responseData).equals("????????????")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Community_erollActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Community_erollActivity.this, Community_loginActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else if(parseJSONWithJSONObject(responseData).equals("??????????????????")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Community_erollActivity.this,"??????????????????!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Community_erollActivity.this,"????????????",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
                /*Intent i = new Intent(EnrollActicity.this, UserLoginActivity.class);//?????????????????????
                //??????
                startActivity(i);*/

            }
        });

        //????????????????????????
        /*Button getcode = (Button)findViewById(R.id.get_code3);
        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        //????????????????????????????????????
        Button return_login = (Button)findViewById(R.id.return_login3);
        return_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent???????????????????????????run-time binding????????????????????????????????????????????????????????????????????????
                //??????????????????????????????????????????
                Intent i = new Intent(Community_erollActivity.this, Community_loginActivity.class);//?????????????????????
                //??????
                startActivity(i);
            }
        });
    }
    private String parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject object=new JSONObject(jsonData);
            String name = object.getString("msg");
            //??????
            Log.d("name", name);
            return name;
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }

}