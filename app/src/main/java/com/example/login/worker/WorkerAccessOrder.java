package com.example.login.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.login.MyApplication;
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkerAccessOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.worker_access_order);

        final int ostate=0;
        boolean btstate=getIntent().getBooleanExtra("btstate",false);
        Log.d("btstate", String.valueOf(btstate));
        final String oid=getIntent().getStringExtra("oid");
        //final String oid="23";
        Log.d("oid11", oid);

        if(btstate==true)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //SharedUtil sp = SharedUtil.getIntance(mContext, "taskinfo");
                    HashMap<String, String> hm1 = new HashMap<>();
                    //MyApplication application = (MyApplication) com.example.login.worker.OrderFragment1.this.getApplicationContext();
                    MyApplication application = (MyApplication) WorkerAccessOrder.this.getApplicationContext();


                    //hm.put("wusername", application.getName());

                    hm1.put("oid", oid);
                    hm1.put("ostate",String.valueOf(ostate));
                    Log.d("ostate", String.valueOf(ostate));

                    ArrayList<String> send = new ArrayList<>();
                    //send.add("wusername");
                    send.add("oid");
                    send.add("ostate");
                    ArrayList<String> recieve = new ArrayList<>();
                    recieve.add("msg");
//                    recieve.add("username");
//                    recieve.add("wusername");
//                    recieve.add("otype");
//                    recieve.add("oduration");
//                    recieve.add("oscore");
//                    recieve.add("ostate");
//                    recieve.add("oprice");
//                    recieve.add("odescription");
//                    recieve.add("oid");

                    OkHttp okHttp = new OkHttp(send, recieve/*,2, com.example.login.worker.WorkerAccessOrder.this*/);

                    HashMap<String,String> hm_return =okHttp.sendRequestWithOkHttp(hm1, "http://120.48.5.10:9090/AccessOrder");//////
                    Log.d("msg_out", hm_return.get("msg"));

                    while (!application.orderSynFlag){

                    }
                    application.orderSynFlag = false;
                    //Log.d("TAG", "flag true");
                    if (hm_return.get("msg").equals("获取任务成功")){
                        Looper.prepare();
                        Toast.makeText(WorkerAccessOrder.this,"接单成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();

                    }


                }
            }).start();
        }

    }
}