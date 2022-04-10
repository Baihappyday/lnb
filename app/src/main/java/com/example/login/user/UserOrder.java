package com.example.login.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.TabActivity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;
import com.google.android.material.tabs.TabLayout;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserOrder /*extends AppCompatActivity*/extends TabActivity {
    TabHost tabHost;
    int ostate = 0;
    ArrayList<OrderFragment0> list = new ArrayList<>();
    ArrayList<HashMap> order;//订单集合


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
//        getFragmentManager().beginTransaction().add(R.id.tab01, of0).commit();
//        getFragmentManager().beginTransaction().add(R.id.tab01, new OrderFragment0()).commit();
//        getFragmentManager().beginTransaction().add(R.id.tab01, new OrderFragment0()).commit();
//        getFragmentManager().beginTransaction().add(R.id.tab01, new OrderFragment0()).commit();
    }

    private void getDate(final int ostate){
        boolean threadFlag = false;
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
                okHttp.sendRequestWithOkHttp(hm, "http://192.168.56.1:9090/userOrderList");
                while (!application.orderSynFlag){

                }
                ArrayList<HashMap> order = okHttp.getOrder();//获取订单
                UserOrder.this.getOrder(order);

                Log.d("tag", " ");

            }
        }).start();
        while (!threadFlag){

        }
        switch (ostate){
            case 0:
                for (int i = 0; i < order.size(); i++) {
                    HashMap<String, Object> rhm = order.get(i);//获取一个订单的信息
                    list.add(new OrderFragment0());

                    getFragmentManager().beginTransaction().add(R.id.tab01, list.get(i)).commit();

                    Log.d("TAG", rhm.get("username") +" "+ rhm.get("oprice"));
                }
                break;
//            case 1
//            case 2
//            case 3
        }

    }

    void getOrder(ArrayList<HashMap> order){
        this.order = order;
    }

    void getDate(){

    }


}