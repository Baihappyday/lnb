package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;

//任务发布界面
public class TaskRelease extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp1;    //服务类型下拉菜单
    private Spinner sp2;    //服务时间下拉菜单
    private EditText input_adress;    //输入的地址
    private Button submit;    //提交按钮
    private EditText editTextTextMultiLine; //备注信息


    public static int ntask=0;   //任务编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_release);

        ArrayAdapter<String> stuffAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, stuffArray);
        ArrayAdapter<String> intervalAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, timeArray);
        sp1 = (Spinner) findViewById(R.id.spinner_stuff);
        sp2 = (Spinner) findViewById(R.id.interval);
        sp1.setPrompt("服务类型");
        sp1.setAdapter(stuffAdapter);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new TaskRelease.MySelectedListener());
        sp2.setPrompt("服务时长");
        sp2.setAdapter(intervalAdapter);
        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new TaskRelease.MySelectedListener());


        submit = (Button)findViewById(R.id.modify);
        submit.setOnClickListener(this);

        input_adress = (EditText) findViewById(R.id.input_adress);
        input_adress.setOnClickListener(this);

        editTextTextMultiLine = (EditText) findViewById(R.id.healthcondition);
        editTextTextMultiLine.setOnClickListener(this);
    }

    private final String[] stuffArray = {"洗衣", "做饭", "打扫卫生", "理发", "其他"};
    private final String[] timeArray = {"0~30分钟", "30~60分钟", "1~2小时", "2小时以上"};

    public void initView(){    //初始化函数

    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }



    @Override
    public void onClick(View v) {
        /*
        发布任务
         */
        if (v.getId() == R.id.modify) {//提交订单
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SharedUtil sp = SharedUtil.getIntance(TaskRelease.this, "taskinfo");
                    HashMap<String, String> hm = new HashMap<>();
                    MyApplication application = (MyApplication) TaskRelease.this.getApplicationContext();
                    hm.put("username", application.getName());

                    String servicetype = sp1.getSelectedItem().toString();
                    if (servicetype.equals("洗衣")) hm.put("otype", String.valueOf(0));
                    else if (servicetype.equals("做饭")) hm.put("otype", String.valueOf(1));
                    else if (servicetype.equals("打扫卫生")) hm.put("otype", String.valueOf(2));
                    else if (servicetype.equals("理发")) hm.put("otype", String.valueOf(3));


                    String duration = sp2.getSelectedItem().toString();
                    Log.d(" ", "run: "+duration);
                    if (duration.equals("0~30分钟")) hm.put("oduration", String.valueOf(0));
                    else if (duration.equals("30~60分钟")) hm.put("oduration", String.valueOf(1));
                    else if (duration.equals("1~2小时")) hm.put("oduration", String.valueOf(2));
                    else if (duration.equals("2小时以上")) hm.put("oduration", String.valueOf(3));


                    hm.put("oprice", String.valueOf((int)(Math.random()*100)));//待修改
                    hm.put("odescription", editTextTextMultiLine.getText().toString());
                    ArrayList<String> send = new ArrayList<>();
                    send.add("username");
                    send.add("otype");
                    send.add("oduration");
                    send.add("oprice");
                    send.add("odescription");
                    ArrayList<String> recieve = new ArrayList<>();
                    recieve.add("msg");
                    recieve.add("username");
                    recieve.add("wusername");
                    recieve.add("otype");
                    recieve.add("oduration");
                    recieve.add("oscore");
                    recieve.add("ostate");
                    recieve.add("oprice");
                    recieve.add("odescription");
                    recieve.add("oid");
                    OkHttp okHttp = new OkHttp(send, recieve);
                    HashMap<String, String> rhm = okHttp.sendRequestWithOkHttp(hm, "http://192.168.56.1:9090/publish-order");
                    Log.d("rhm length", String.valueOf(rhm.size()));
                    Log.d("tag", rhm.get("otype"));
                    if (rhm.get("msg").equals("true")){
                        Looper.prepare();
                        Toast.makeText(TaskRelease.this,"提交成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }).start();
        }
    }
}