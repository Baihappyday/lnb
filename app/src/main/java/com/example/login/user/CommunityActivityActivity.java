package com.example.login.user;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommunityActivityActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    List<CommunityActFragment> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_activity);

        getData();
    }


    void getData(){
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    String json = get("http://192.168.1.11:9090/activities/all");
                    Log.d("json", json);
                    JSONArray o = new JSONArray(json);

                    for (int i = 0; i < o.length(); i++) {
                        JSONObject jo = o.getJSONObject(i);
                        CommunityActFragment i_f = new CommunityActFragment();
                        list.add(i_f);
                        HashMap<String,Object> hashMap =new HashMap<>();
                        Log.d("json", json);

                        hashMap.put("activitytime",jo.get("activitytime"));
                        hashMap.put("activityaddress",jo.getString("activityaddress"));
                        hashMap.put("activitydescription",jo.getString("activitydescription"));

                        i_f.setInfo(hashMap);
                        Log.d("jsosn", (String) hashMap.get("activityaddress"));
                        getSupportFragmentManager().beginTransaction().add(R.id.list_act, i_f).commit();

                    }
                }catch (Exception e){

                }

            }
        }).start();
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}