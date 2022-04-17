package com.example.login.institution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.login.R;
import com.example.login.community.Bean;
import com.example.login.community.CommunityaddActivity;
import com.example.login.community.CommunitylistActivity;
import com.example.login.community.myadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Institution_listActivity extends AppCompatActivity {


    private ListView listView;
    private Myadapter listAdapter;
    private List<bean> datas = new ArrayList<>();

    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institution);
        listView = findViewById(R.id.lv2);
        bt = findViewById(R.id.addis);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    OkHttpClient client=new OkHttpClient();

                    Request request=new Request.Builder()
                            .get()
                            .url("http://120.48.5.10:9090/institutions/all")

                            .build();

                    Response response = client.newCall(request).execute();

                    String responseData = response.body().string();

                    JSONArray jsonArray = new JSONArray(responseData);
                    for(int i = 0;i< jsonArray.length();i++ )
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        bean bean1 = new bean();
                        //bean1.setID(jsonObject.getInt("AID"));

                        bean1.setIiusername(jsonObject.getString("iiusername"));
                        bean1.setIname(jsonObject.getString("iaddress"));
                        bean1.setIaddress(jsonObject.getString("iname"));
                        bean1.setIprice(jsonObject.getInt("iprice"));
                        bean1.setIdescription(jsonObject.getString("idescription"));
                        datas.add(bean1);

                    /*  Log.e("活动id", " "+jsonObject.getInt("AID") );
                        Log.e("活动时间", " "+jsonObject.getString("activitytime") );
                        Log.e("活动地点", " "+jsonObject.getString("activityaddress") );
                        Log.e("活动详情", " "+jsonObject.getString("activitydescription") );*/
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listAdapter = new Myadapter(datas, Institution_listActivity.this);
                            listView.setAdapter(listAdapter);
                        }
                    });

                    // }
                } catch (
                        IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }}).start();

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (view.getId() == R.id.addis){
                    Intent i = new Intent(Institution_listActivity.this, Institution_addActivity.class);
                    startActivity(i);
                }

            }
        });




    }
}