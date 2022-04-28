package com.example.login.worker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.MyApplication;
import com.example.login.R;

import com.example.login.user.ForgetPasswordActivity;
import com.example.login.user.OrderFragment0;
import com.example.login.util.OkHttp;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.login.user.UserMainInterfaceActivity;



public class WorkmaininterfaceAcitvity extends AppCompatActivity
{
//    WorkermainActivity mi = new WorkermainActivity();
    WorkpersonalActivity p = new WorkpersonalActivity();


    boolean displayflag=false;
    protected View v;
    private Context mContext;
    
    int ostate = 0;
    ArrayList<OrderFragment1> list0 = new ArrayList<OrderFragment1>();//fragment集合
   // ArrayList<OrderFragment0> list0All = new ArrayList<>();//fragment集合
    ArrayList<HashMap> order0;//订单集合
    boolean threadFlag = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_main);


        display();
        
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();

        //Toast.makeText(WorkmaininterfaceAcitvity.this,"欢迎使用老年宝，请前往个人中心登录",Toast.LENGTH_SHORT).show();
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();

        Button button1=findViewById(R.id.button1);//首页
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mi).commit();
                display();

            }
        });
        Button button2=findViewById(R.id.button2);//个人中心
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment, p).commit();

                    Intent intent=new Intent(WorkmaininterfaceAcitvity.this, WorkpersonalActivity.class);
                    startActivity(intent);

            }
        });
    }

    void display()
    {
        if(displayflag==false)
        new Thread(new Runnable() {
            @Override
            public void run() {
                displayflag=true;
                //SharedUtil sp = SharedUtil.getIntance(mContext, "taskinfo");
                final HashMap<String, String> hm = new HashMap<>();//**********
                MyApplication application = (MyApplication) com.example.login.worker.WorkmaininterfaceAcitvity.this.getApplicationContext();

                //hm.put("wusername", application.getName());

                hm.put("ostate", String.valueOf(ostate));
                ArrayList<String> send = new ArrayList<>();
                //send.add("wusername");
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
                OkHttp okHttp = new OkHttp(send, recieve,1, com.example.login.worker.WorkmaininterfaceAcitvity.this);
                okHttp.sendRequestWithOkHttp(hm, "http://120.48.5.10:9090/GetworkerList");//////
                while (!application.orderSynFlag){

                }
                application.orderSynFlag = false;
                Log.d("TAG", "flag true");
                ArrayList<HashMap> order = okHttp.getOrder();//获取订单
                com.example.login.worker.WorkmaininterfaceAcitvity.this.order0 = order;

                Log.d("order_len", String.valueOf(order0.size()));
                com.example.login.worker.WorkmaininterfaceAcitvity.this.threadFlag = true;

                for (int i = 0; i < order0.size(); i++) {
                    HashMap<String, Object> rhm = order0.get(i);//获取一个订单的信息
                    list0.add(new OrderFragment1());
                    list0.get(i).setInfo(rhm);//传入订单数据给fragment
                    getFragmentManager().beginTransaction().add(R.id.scrll, list0.get(i)).commit();
//                            list0All.add(new OrderFragment0());
//                            list0All.get(i).setInfo(rhm);
//                            getFragmentManager().beginTransaction().add(R.id.scr, list0All.get(i)).commit();



                }
            }
        }).start();

    }

}
