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

    private ArrayList<String> send;//发送的每个数据对应的key
    private ArrayList<String> recieve;//接受的每个数据的对应的key

    public OkHttp(ArrayList<String> send, ArrayList<String> recieve){//构造方法
        this.send = send;
        this.recieve = recieve;
    }


    public HashMap<String, String> sendRequestWithOkHttp(HashMap<String, String> hashMap, String url) {//hashMap为发送的数据集合

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

/*
示例用法：
OkHttp okHttp = new OkHttp(send,recieve);
HashMap<String,String> rhm = okHttp.sendRequestWithOkHttp(hashMap, url);
我们发送的数据可以用hashmap存储为一对对数据，如：<username, john> <age, 18> --><key, value>
我们收到的数据也可以用hashmap存储，
则我们需要准备一个hashmap来存储我们需要发送的数据（对应sendRequestWithOkHttp方法中的hashMap参数，
send为发送数据集合中的所有key值，recieve为接收数据集合中的所有key值，（有哪些取决于后端需要）(send与recieve都为ArrayList）
url为端口（如"http://192.168.1.9:9090/login")
sendRequestWithOkHttp的返回值为收到的数据

目前只写了请求后接收的方法，还没有只等待接收的方法
 */
