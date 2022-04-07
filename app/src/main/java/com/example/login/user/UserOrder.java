package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserOrder /*extends AppCompatActivity*/extends TabActivity {
    TabHost tabHost;
    int ostate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.user_order);

        // 获取该Activity里面的TabHost组件
        tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.user_order,
                tabHost.getTabContentView(), true);

        getDate(0);

        /* 以上创建和添加标签页也可以用如下代码实现 */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("未付款").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("进行中").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("已完成").setContent(R.id.tab03));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("待评价").setContent(R.id.tab04));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator("全部").setContent(R.id.tab05));

        //标签切换事件处理，setOnTabChangedListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            // tabId是newTabSpec参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签

                    //Toast.makeText(WorkerOrder.this, "点击标签页一", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    //Toast.makeText(WorkerOrder.this, "点击标签页二", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab3")) {   //第三个标签
                    //Toast.makeText(WorkerOrder.this, "点击标签页三", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab4")) {   //第四个标签
                    // Toast.makeText(WorkerOrder.this, "点击标签页四", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab5")) {   //第五个标签
                    // Toast.makeText(WorkerOrder.this, "点击标签页五", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Button btn2 = findViewById(R.id.finished);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent intent=new Intent(UserMainInterfaceActivity.this, TaskRelease.class);//跳转到任务发布
                startActivity(intent);*//*
            }
        });*/


    }

    private void getDate(final int ostate){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedUtil sp = SharedUtil.getIntance(UserOrder.this, "taskinfo");
                HashMap<String, String> hm = new HashMap<>();
                MyApplication application = (MyApplication) UserOrder.this.getApplicationContext();
                hm.put("username", application.getName());

                hm.put("ostate", String.valueOf(ostate));
                ArrayList<String> send = new ArrayList<>();
                send.add("username");
                send.add("ostate");
                ArrayList<String> recieve = new ArrayList<>();
                recieve.add("username");
                recieve.add("wusername");
                recieve.add("otype");
                recieve.add("oduration");
                recieve.add("oscore");
                recieve.add("ostate");
                recieve.add("oprice");
                recieve.add("odescription");
                recieve.add("oid");
                OkHttp okHttp = new OkHttp(send, recieve, 1, UserOrder.this);
                okHttp.sendRequestWithOkHttp(hm, "http://192.168.1.9:9090/userOrderList");
                while (!application.orderSynFlag){

                }
                ArrayList<HashMap> order = okHttp.getOrder();//获取订单
                for (int i = 0; i < order.size(); i++) {
                    HashMap<String, Object> rhm = order.get(i);
                    Log.d("TAG", rhm.get("username") +" "+ rhm.get("oprice"));
                }
                Log.d("tag", " ");

            }
        }).start();
    }
}