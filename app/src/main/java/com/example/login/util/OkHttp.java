package com.example.login.util;


import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.login.user.UserLoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {


    private static final String TAG = "OkHttp" ;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private ArrayList<String> send;
    private ArrayList<String> recieve;

    public OkHttp(ArrayList<String> send, ArrayList<String> recieve){
        this.send = send;
        this.recieve = recieve;
    }



    OkHttpClient client = new OkHttpClient();


    public HashMap<String, String> sendRequestWithOkHttp(HashMap<String, String> hashMap, String url) {

        JSONObject obj = new JSONObject();
        try {
            Iterator<String> i = send.iterator();
            while (i.hasNext()){
                String s = i.next();
                obj.put(s,hashMap.get(s));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 指定访问的服务器地址
                    .url(url).post(RequestBody2)
                    .build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            return parseJSONWithJSONObject(responseData);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, String>();
        }
    }


    private HashMap<String, String > parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject object=new JSONObject(jsonData);
            Iterator<String> i = recieve.iterator();
            HashMap<String, String> hm = new HashMap<>();
            while (i.hasNext()){
                String s = i.next();
                hm.put(s ,object.getString(s));
            }

            //日志
            Log.d("name", "结果是："+hm.get("msg"));

            return hm;
        } catch (JSONException e) {
            e.printStackTrace();
            return new HashMap<String, String>();
        }
    }
}
