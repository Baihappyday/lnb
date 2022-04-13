package com.example.login.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.TabActivity;
import android.net.Network;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.NetWorkUtil;
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
    ArrayList<OrderFragment0> list0 = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list0All = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list1 = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list1All = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list2 = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list2All = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list3 = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list3All = new ArrayList<>();//fragment集合
    ArrayList<HashMap> order0;//订单集合
    ArrayList<HashMap> order1;//订单集合
    ArrayList<HashMap> order2;//订单集合
    ArrayList<HashMap> order3;//订单集合

    boolean threadFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.user_order);

        // 获取该Activity里面的TabHost组件
        tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.user_order,
                tabHost.getTabContentView(), true);

        boolean netFlag = NetWorkUtil.isNetworkConnected(this);

        if (netFlag){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getDate(0);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("tag", "run:come here ");
                            getDate(1);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("tag", "run:come here ");
                                    getDate(2);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d("tag", "run:come here ");
                                            getDate(3);

                                        }
                                    }).start();
                                }
                            }).start();
                        }
                    }).start();
                }
            }).start();
        }
        else {

        }



        /* 以上创建和添加标签页也可以用如下代码实现 */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("未付款").setContent(R.id.tab011));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("进行中").setContent(R.id.tab022));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("已完成").setContent(R.id.tab033));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("待评价").setContent(R.id.tab044));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator("全部").setContent(R.id.tab055));
//        tabHost.setCurrentTabByTag(R.id.tab01);

        int a = getIntent().getIntExtra("page", 0);
        tabHost.setCurrentTab(a);

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

    private void getDate(final int ostate){//获取服务器端数据并显示

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
                okHttp.sendRequestWithOkHttp(hm, "http://192.168.1.11:9090/userOrderList");
                while (!application.orderSynFlag){

                }
                application.orderSynFlag = false;
                Log.d("TAG", "flag true");
                ArrayList<HashMap> order = okHttp.getOrder();//获取订单
                UserOrder.this.getOrder(order,ostate);

                Log.d("tag", " ");
                UserOrder.this.threadFlag = true;
            }
        }).start();

        while (!threadFlag){
            Log.d("tag", "in threadflag");
        }
        threadFlag = false;
        Log.d("tag", "out threadflag");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (ostate){
                    case 0:

                        for (int i = 0; i < order0.size(); i++) {
                            HashMap<String, Object> rhm = order0.get(i);//获取一个订单的信息
                            list0.add(new OrderFragment0());
                            list0.get(i).setInfo(rhm);//传入订单数据给fragment
                            getFragmentManager().beginTransaction().add(R.id.tab01, list0.get(i)).commit();
                            list0All.add(new OrderFragment0());
                            list0All.get(i).setInfo(rhm);
                            getFragmentManager().beginTransaction().add(R.id.tab05, list0All.get(i)).commit();

                            Log.d("TAG", rhm.get("username") +" "+ rhm.get("oprice"));
                        }

                        break;
                    case 1:
                        for (int i = 0; i < order1.size(); i++) {
                            HashMap<String, Object> rhm = order1.get(i);//获取一个订单的信息
                            list1.add(new OrderFragment0());
                            list1.get(i).setInfo(rhm);//传入订单数据给fragment
                            getFragmentManager().beginTransaction().add(R.id.tab02, list1.get(i)).commit();
                            list1All.add(new OrderFragment0());
                            list1All.get(i).setInfo(rhm);
                            getFragmentManager().beginTransaction().add(R.id.tab05, list1All.get(i)).commit();

                            Log.d("TAG", rhm.get("username") +" "+ rhm.get("oprice"));
                        }
                        break;
                    case 2:
                        for (int i = 0; i < order2.size(); i++) {
                            HashMap<String, Object> rhm = order2.get(i);//获取一个订单的信息
                            list2.add(new OrderFragment0());
                            list2.get(i).setInfo(rhm);//传入订单数据给fragment
                            getFragmentManager().beginTransaction().add(R.id.tab03, list2.get(i)).commit();
                            list2All.add(new OrderFragment0());
                            list2All.get(i).setInfo(rhm);
                            getFragmentManager().beginTransaction().add(R.id.tab05, list2All.get(i)).commit();

                            Log.d("TAG", rhm.get("username") +" "+ rhm.get("oprice"));
                        }
                        break;
                    case 3:
                        for (int i = 0; i < order3.size(); i++) {
                            HashMap<String, Object> rhm = order3.get(i);//获取一个订单的信息
                            list3.add(new OrderFragment0());
                            list3.get(i).setInfo(rhm);//传入订单数据给fragment
                            getFragmentManager().beginTransaction().add(R.id.tab04, list3.get(i)).commit();
                            list3All.add(new OrderFragment0());
                            list3All.get(i).setInfo(rhm);
                            getFragmentManager().beginTransaction().add(R.id.tab05, list3All.get(i)).commit();

                            Log.d("TAG", rhm.get("username") +" "+ rhm.get("oprice"));
                        }
                }
            }
        });
    }

    void getOrder(ArrayList<HashMap> order,int ostate){
        switch (ostate){
            case 0:
                this.order0 = order;
                break;
            case 1:
                this.order1 = order;
                break;
            case 2:
                this.order2 = order;
                break;
            case 3:
                this.order3 = order;
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }
}