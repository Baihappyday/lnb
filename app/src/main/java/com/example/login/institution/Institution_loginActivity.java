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

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Institution_loginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText login_username;
    private EditText login_password;
    private Button btn_login_user;
    private Button btn_forget_password;
    private Button btn_enroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institution_login);
        login_username = findViewById(R.id.institutionname1);
        login_password = findViewById(R.id.institutionpassword1);
        btn_login_user = findViewById(R.id.institutionsubmit);
        btn_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String iusername = login_username.getText().toString();
                final String ipassword = login_password.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*JSONObject obj = new JSONObject();
                        try {
                            obj.put("iusername", username);
                            obj.put("ipassword", password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                       // MediaType type = MediaType.parse("application/json;charset=utf-8");
                        //RequestBody RequestBody2 = RequestBody.create(type, "" + obj.toString());
                        try {
                            String json = "{\n" +
                                    "  \"iusername\": \"" + iusername + "\",\n" +
                                    "  \"ipassword\": \"" + ipassword + "\"\n" +
                                    "}";
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    // ??????????????????????????????
                                    .url("http://120.48.5.10:9090/login/institutionusers")
                                    //.post(RequestBody2)
                                    .post(RequestBody.create(MediaType.parse("application/json"),json))
                                    .build();
                            Response response = client.newCall(request).execute();

                           final String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                            if (parseJSONWithJSONObject(responseData).equals("????????????")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Institution_loginActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Institution_loginActivity.this, Institution_listActivity.class);
                                        startActivity(i);
                                    }
                                });
                            } else if (parseJSONWithJSONObject(responseData).equals("????????????????????????")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Institution_loginActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Institution_loginActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }


                }).start();
            }


        });
        //???????????????
        btn_enroll = findViewById(R.id.instituioneroll);
        btn_enroll.setOnClickListener(this);
        //?????????????????????
        btn_forget_password = findViewById(R.id.instituionforget);
        btn_forget_password.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.instituioneroll){
            //???????????????????????????UserLoginActivity
            Intent intent=new Intent(Institution_loginActivity.this,Institution_erollActivity.class );
            startActivity(intent);
        }
        if (view.getId() == R.id.instituionforget){
            //???????????????????????????
            Intent i = new Intent(Institution_loginActivity.this, Institution_forgetActivity.class);
            //??????
            startActivity(i);
        }

    }
}
