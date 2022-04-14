package com.example.login.worker;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.user.OrderFragment0;
import com.example.login.util.NetWorkUtil;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;



public class WorkermainActivity extends Fragment {
    protected View v;
    private Context mContext;

    int ostate = 0;
    ArrayList<OrderFragment0> list0 = new ArrayList<>();//fragment集合
    ArrayList<OrderFragment0> list0All = new ArrayList<>();//fragment集合
    ArrayList<HashMap> order0;//订单集合
    boolean threadFlag = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_worker_main, container, false);


        EditText edit=v.findViewById(R.id.text1);
        edit.clearFocus();


        mContext = getActivity();
        final MyApplication application = (MyApplication) mContext.getApplicationContext();

        Spinner spinner1 = (Spinner) v.findViewById(R.id.distance);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] dist = getResources().getStringArray(R.array.address);
                Log.d("address","你点击的是:"+dist[pos]);
                //Toast.makeText(WorkermainActivity.this, "你点击的是:"+dist[pos], Toast.LENGTH_SHORT).show();
                //Toast.makeText(HealthInfoActivity.this, "您选择的是"+nationArray[arg2], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        Spinner spinner2 = (Spinner) v.findViewById(R.id.typeofservice);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] type = getResources().getStringArray(R.array.typeofservice);
                Log.d("typeofservice","你点击的是:"+type[pos]);
                //Toast.makeText(WorkermainActivity.this, "你点击的是:"+dist[pos], Toast.LENGTH_SHORT).show();
                //Toast.makeText(HealthInfoActivity.this, "您选择的是"+nationArray[arg2], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        Spinner spinner3 = (Spinner) v.findViewById(R.id.timeofservice);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] time = getResources().getStringArray(R.array.timeofservice);
                Log.d("typeofservice","你点击的是:"+time[pos]);
                //Toast.makeText(WorkermainActivity.this, "你点击的是:"+dist[pos], Toast.LENGTH_SHORT).show();
                //Toast.makeText(HealthInfoActivity.this, "您选择的是"+nationArray[arg2], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


//        boolean netFlag = NetWorkUtil.isNetworkConnected(this);
//        if (netFlag)
//        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("tag", "run:come here ");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SharedUtil sp = SharedUtil.getIntance(mContext, "taskinfo");
                            HashMap<String, String> hm = new HashMap<>();

                            hm.put("wusername", application.getName());

                            hm.put("ostate", String.valueOf(ostate));
                            ArrayList<String> send = new ArrayList<>();
                            send.add("wusername");
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
                            OkHttp okHttp = new OkHttp(send, recieve/*,1, com.example.login.worker.WorkermainActivity.this*/);
                            okHttp.sendRequestWithOkHttp(hm, "http://120.48.5.10:9090/serOrderList");//////
                            while (!application.orderSynFlag){

                            }
                            application.orderSynFlag = false;
                            Log.d("TAG", "flag true");
                            ArrayList<HashMap> order = okHttp.getOrder();//获取订单
                            com.example.login.worker.WorkermainActivity.this.order0 = order;

                            Log.d("tag", " ");
                            com.example.login.worker.WorkermainActivity.this.threadFlag = true;
                        }
                    }).start();

                }
            }).start();
//        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < order0.size(); i++) {
                    HashMap<String, Object> rhm = order0.get(i);//获取一个订单的信息
                    list0.add(new OrderFragment0());
                    list0.get(i).setInfo(rhm);//传入订单数据给fragment
                    //getFragmentManager().beginTransaction().add(R.id.scr, list0.get(i)).commit();

//            FragmentManager fm;
//            fm = parent.getChildFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            for(OrderFragment0 fragment : list0) {
////                ft.add(R.id.scr,fragment);
////                ft.add(fragment,"aaa");
//            }
//            ft.commit();

//            list0All.add(new OrderFragment0());
//            list0All.get(i).setInfo(rhm);
              //getFragmentManager().beginTransaction().add(R.id.scr, list0All.get(i)).commit();

                    Log.d("TAG", rhm.get("wusername") +" "+ rhm.get("oprice"));
                }
            }
        });

        return v;
    }

}
