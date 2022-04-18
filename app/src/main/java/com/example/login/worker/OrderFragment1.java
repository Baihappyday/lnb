package com.example.login.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderFragment1 extends Fragment {
    protected Context mContext;
    protected View v;
    private HashMap<String, Object> info;
    private TextView type;
    private TextView duration;
    private TextView price;
    private TextView description;
    private Button accept;
    boolean click=false;
    String oid1;
    String wname;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_order1, container, false);
        mContext = getActivity();
        final MyApplication application = (MyApplication) mContext.getApplicationContext();

        type = v.findViewById(R.id.type);
        duration = v.findViewById(R.id.duration);
        price = v.findViewById(R.id.price);
        description = v.findViewById(R.id.description);
        accept = v.findViewById(R.id.bt_ac);

        String toText = null;
        String o = info.get("otype").toString();
        if (o.equals("0")){
            toText = "洗衣";
        }
        else if (o.equals("1")){
            toText = "做饭";
        }
        else if (o.equals("2")){
            toText = "打扫卫生";
        }
        else if (o.equals("3")){
            toText = "理发";
        }
        type.setText(type.getText().toString()+toText);
        //0~30分钟", "30~60分钟", "1~2小时", "2小时以上
        o = info.get("oduration").toString();
        if (o.equals("0")){
            toText = "0~30分钟";
        }
        else if (o.equals("1")){
            toText = "30~60分钟";
        }
        else if (o.equals("2")){
            toText = "1~2小时";
        }
        else if (o.equals("3")){
            toText = "2小时以上";
        }
        duration.setText(duration.getText().toString()+toText);
        price.setText(price.getText().toString()+info.get("oprice"));
        description.setText(description.getText().toString()+info.get("odescription"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                oid1= (String) info.get("oid");//info.get("oid")返回的是string
                Log.d("oid_frag", String.valueOf(oid1));
                //wname=(String) info.get("wusername");
                SharedUtil sp = SharedUtil.getIntance(mContext, "logininfo");
                wname = sp.readShared("wusername", "null");
                Log.d("wusername",wname);

                if (!"0".equals(info.get("ostate"))){
                    accept.setVisibility(View.GONE);
                }

                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        accept.setText("已接受");
                        click=true;
//                        Intent intent=new Intent(getActivity(), WorkerAccessOrder.class);
//                        intent.putExtra("btstate",click);//boolean
//                        intent.putExtra("oid",oid1);//string
//                        startActivity(intent);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if(click==true){
                                    //SharedUtil sp = SharedUtil.getIntance(mContext, "taskinfo");
                                    HashMap<String, String> hm1 = new HashMap<>();
                                    //MyApplication application = (MyApplication) com.example.login.worker.OrderFragment1.this.getApplicationContext();
                                    //MyApplication application = (MyApplication) WorkerAccessOrder.this.getApplicationContext();


                                    //hm.put("wusername", application.getName());

                                    hm1.put("oid", oid1);
                                    hm1.put("wusername",wname);
                                    //Log.d("ostate", String.valueOf(ostate));

                                    ArrayList<String> send = new ArrayList<>();
                                    send.add("oid");
                                    send.add("wusername");
                                    ArrayList<String> recieve = new ArrayList<>();
                                    recieve.add("msg");
//                                    recieve.add("username");
//                                    recieve.add("wusername");
//                                    recieve.add("otype");
//                                    recieve.add("oduration");
//                                    recieve.add("oscore");
//                                    recieve.add("ostate");
//                                    recieve.add("oprice");
//                                    recieve.add("odescription");
//                                    recieve.add("oid");

                                    OkHttp okHttp = new OkHttp(send, recieve/*,2, com.example.login.worker.WorkerAccessOrder.this*/);

                                    HashMap<String,String> hm_return =okHttp.sendRequestWithOkHttp(hm1, "http://192.168.56.1:9090/AccessOrder");//////
                                    Log.d("msg_out", hm_return.get("msg"));

                                    while (!application.orderSynFlag){

                                    }
                                    application.orderSynFlag = false;
                                    //Log.d("TAG", "flag true");
                                    if (hm_return.get("msg").equals("获取任务成功")){
                                        Looper.prepare();
                                        Toast.makeText(application,"接单成功！", Toast.LENGTH_SHORT).show();
                                        Looper.loop();

                                    }
                                }
                            }
                        }).start();
                    }
                });
            }
        }).start();


        return v;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
    }//传入订单数据给fragment
}