package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommunitylistActivity extends AppCompatActivity  {
        private ListView listView;
        private myadapter listAdapter;
        private List<Bean> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activities_list);


        listView = findViewById(R.id.lv1);


        /*for(int i =0;i<48;i++)
        {
            Bean bean = new Bean();
            bean.setDes("活动" + i);
            bean.setAddress("篮球"+i);
            datas.add(bean);

        }*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                OkHttpClient client=new OkHttpClient();

                Request request=new Request.Builder()
                        .get()
                        .url("http://120.48.5.10:9090/activities/all")
                        //.get(RequestBody.create(MediaType.parse("application/json"),)
                        .build();

                Response response = client.newCall(request).execute();

                String responseData = response.body().string();

                //if(response.isSuccessful()){
                    //同步方式下得到返回结果


                    JSONArray jsonArray = new JSONArray(responseData);
                    for(int i = 0;i< jsonArray.length();i++ )
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Bean bean1 = new Bean();
                        //bean1.setID(jsonObject.getInt("AID"));
                        bean1.setTime(jsonObject.getString("activitytime"));
                        bean1.setAddress(jsonObject.getString("activityaddress"));
                        bean1.setDes(jsonObject.getString("activitydescription"));
                        datas.add(bean1);

                    /*  Log.e("活动id", " "+jsonObject.getInt("AID") );
                        Log.e("活动时间", " "+jsonObject.getString("activitytime") );
                        Log.e("活动地点", " "+jsonObject.getString("activityaddress") );
                        Log.e("活动详情", " "+jsonObject.getString("activitydescription") );*/
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listAdapter = new myadapter(datas,CommunitylistActivity.this);
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



        Button button1 = findViewById(R.id.add_activity);
        button1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (view.getId() == R.id.add_activity){
                    Intent i = new Intent(CommunitylistActivity.this, Add_activityActivity.class);
                    startActivity(i);
                }

            }
        });
        Button button2 = findViewById(R.id.add_community);
        button2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (view.getId() == R.id.add_community){
                    Intent i = new Intent(CommunitylistActivity.this, CommunityaddActivity.class);
                    startActivity(i);
                }

            }
        });
    }


}
