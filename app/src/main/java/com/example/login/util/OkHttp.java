package com.example.login.util;


import android.app.Activity;
import android.app.Fragment;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.login.MyApplication;
import com.example.login.user.UserLoginActivity;
import com.example.login.user.UserOrder;
import com.example.login.worker.WorkermainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {


    private static final String TAG = "OkHttp" ;

    private static int state_JSON = 0;//判断返回Json数据格式
    private static Activity activity;


    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private ArrayList<String> send;//发送的每个数据对应的key
    private ArrayList<String> recieve;//接受的每个数据的对应的key

    private ArrayList<HashMap> order = new ArrayList<>();//state_JSON为1时使用

    public OkHttp(ArrayList<String> send, ArrayList<String> recieve){//构造方法
        this.send = send;
        this.recieve = recieve;
    }

    public OkHttp(ArrayList<String> send, ArrayList<String> recieve, int state_JSON, Activity activity){//构造方法
        this.send = send;
        this.recieve = recieve;
        this.state_JSON = state_JSON;
        this.activity = activity;
    }


    public HashMap<String, String> sendRequestWithOkHttp(HashMap<String, String> hashMap, String url) {//hashMap为发送的数据集合

        JSONObject obj = new JSONObject();
        FormBody.Builder params = new FormBody.Builder();
        try {
            Iterator<String> i = send.iterator();
            if (state_JSON == 0){
                while (i.hasNext()){
                    String s = i.next();
                    obj.put(s,hashMap.get(s));
                }
            }
            else if (state_JSON == 1||state_JSON == 2){
                while (i.hasNext()){
                    String s = i.next();
                    params.add(s,hashMap.get(s));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type,""+obj.toString());
        try {
            OkHttpClient client = new OkHttpClient();
            if (state_JSON == 0){
                Request request = new Request.Builder()
                        // 指定访问的服务器地址
                        .url(url).post(RequestBody2)
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Log.d("TAG", "sendRequestWithOkHttp: tohere");
                return parseJSONWithJSONObject(responseData);
            }
            else if (state_JSON == 1||state_JSON == 2){
                Request request = new Request.Builder()
                        // 指定访问的服务器地址
                        .url(url).post(params.build())
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                return parseJSONWithJSONObject(responseData);
            }
            else {
                Request request = new Request.Builder()
                        // 指定访问的服务器地址
                        .url(url).post(RequestBody2)
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                return parseJSONWithJSONObject(responseData);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, String>();
        }
    }


    private HashMap<String, String > parseJSONWithJSONObject(String jsonData) {
        Log.d("jsondata", jsonData);
        if (state_JSON == 0||state_JSON == 2){
            state_JSON = 0;
            try {
                JSONObject object=new JSONObject(jsonData);
              //  Log.d("name", "JSON length: "+object.length());
               // Log.d("msg", object.get("msg").toString());
                Iterator<String> i = recieve.iterator();
                HashMap<String, String> hm = new HashMap<>();
                while (i.hasNext()){
                    String s = i.next();
                    if (s.equals("oid")){
                        //hm.put(s ,String.valueOf(object.getInt(s)));
                    }
                    else if (s.equals("oprice")){
                        hm.put(s ,String.valueOf(object.getInt(s)));
                    }
                    else if (s.equals("judgeinfo")){
                        hm.put(s , String.valueOf(object.getBoolean(s)));
                    }
                    else if (s.equals("account")){
                        hm.put(s , String.valueOf(object.getInt(s)));
                    }
                    else {
                        hm.put(s ,object.getString(s));
                    }

                }

                //日志
                Log.d("name", "结果是："+hm.get("msg"));
                return hm;
            } catch (JSONException e) {
                e.printStackTrace();
                return new HashMap<String, String>();
            }
        }
        else if (state_JSON == 1){
            state_JSON = 0;
            try {
                JSONArray array = new JSONArray(jsonData);
                int len = array.length();
                Log.d("name", "JSON length: "+len);
                for (int j = 0; j < len; j++) {
                    JSONObject object = array.getJSONObject(j);
                    Log.d("name", "JSON length: "+object.length());
                    Iterator<String> i = recieve.iterator();
                    HashMap<String, String> hm = new HashMap<>();
                    while (i.hasNext()){
                        String s = i.next();
                        if (s.equals("oid")){
                            hm.put(s ,String.valueOf(object.getInt(s)));
                        }
                        else if (s.equals("oprice")){
                            hm.put(s ,String.valueOf(object.getInt(s)));
                        }
                        else if (s.equals("judgeinfo")){
                            hm.put(s , String.valueOf(object.getBoolean(s)));
                        }
                        else {
                            hm.put(s ,object.getString(s));
                        }

                    }

                    //日志
                    Log.d("name", "结果是："+hm.get("oprice"));
                    order.add(hm);
                }
                MyApplication application = (MyApplication) activity.getApplicationContext();
                application.orderSynFlag = true;
                return new HashMap<String, String>();
            } catch (JSONException e) {
                e.printStackTrace();
                return new HashMap<String, String>();
            }
        }
        return new HashMap<String, String>();
    }

    public ArrayList<HashMap> getOrder() {
        return order;
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
