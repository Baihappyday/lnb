package com.example.login.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.HashMap;


public class OrderFragment0 extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected View v;
    private HashMap<String, Object> info;
    private TextView type;
    private TextView duration;
    private TextView price;
    private TextView description;
    private Button confirm;
    private int dialogSelection = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_order0, container, false);
        mContext = getActivity();
        MyApplication application = (MyApplication) mContext.getApplicationContext();

        Log.d("info", "onCreateView: "+info.get("oid"));
        type = v.findViewById(R.id.activitytime);
        duration = v.findViewById(R.id.activityaddress);
        price = v.findViewById(R.id.activitydescription);
        description = v.findViewById(R.id.idescription);

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
        confirm = v.findViewById(R.id.confirm);
        if ("0".equals(info.get("ostate"))){
            confirm.setVisibility(View.GONE);
        }
        else if ("1".equals(info.get("ostate"))){

            confirm.setOnClickListener(this);
        }
       else if ("2".equals(info.get("ostate"))){
            confirm.setText("评分");
            confirm.setOnClickListener(this);

        }
        else if ("3".equals(info.get("ostate"))){//待评分
            confirm.setVisibility(View.GONE);
        }

        return v;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
    }

    @Override
    public void onClick(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (view.getId() == R.id.confirm){
                    Button btn = (Button) view;
                    if (btn.getText().toString().equals("确认订单")) {
                        HashMap<String, String> hm = new HashMap<>();
                        MyApplication application = (MyApplication) mContext.getApplicationContext();
                        hm.put("OID", String.valueOf(info.get("oid")));
                        Log.d("infosize", String.valueOf(info.get("oid")));
                        hm.put("username", MyApplication.getName());
                        ArrayList<String> send = new ArrayList<>();
                        send.add("OID");
                        send.add("username");
                        ArrayList<String> recieve = new ArrayList<>();
                        recieve.add("msg");
                        Log.d("gethm", hm.get("oid")+"&username="+hm.get("username"));
                        OkHttp okHttp = new OkHttp(send, recieve, 2, (Activity) mContext);
                        HashMap<String,String> rhm = okHttp.sendRequestWithOkHttp(hm, "http://120.48.5.10:9090/orderFinish");
                        //?OID="+hm.get("uid")+"&username="+hm.get("username")
                        Log.d("hashmap", "onClick: "+rhm);
                        String msg = rhm.get("msg");
                        Log.d("getmsg", "onClick: "+msg);
                        if (msg.equals("余额不足，请充值！")){
                            Looper.prepare();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        else if (msg.equals("支付成功，订单完成！")){
                            Looper.prepare();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//在跳转到目标activity前销毁所以已经存在的activity（实现不能返回的功能）
                            intent.setClass(mContext, UserOrder.class);
                            intent.putExtra("page",((UserOrder)mContext).a);
                            Log.d("page", String.valueOf(((UserOrder)mContext).a));
                            intent.putExtra("frag",2);
                            intent.putExtra("userOrder",true);
                            startActivity(intent);
                            Looper.loop();

                        }
                        else if (msg.equals("订单不存在，请刷新！")){
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(btn.getText().toString().equals("评分")) {
                        createScore();
                    }
                }
            }
        }).start();
    }

    public void createScore(){
        //创建一个警告对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        String[] ss = { "1","2","3","4","5"};

        builder.setTitle("请选择评分");
//        builder.setMessage("对话框的内容在这儿");
        builder.setSingleChoiceItems(ss,0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int score = which +1;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String, String> hm = new HashMap<>();
                        MyApplication application = (MyApplication) mContext.getApplicationContext();
                        hm.put("OID", String.valueOf(info.get("oid")));
                        //Log.d("infosize", String.valueOf(info.get("oid")));
                        hm.put("username", MyApplication.getName());
                        hm.put("oscore", String.valueOf(score));
                        ArrayList<String> send = new ArrayList<>();
                        send.add("OID");
                        send.add("username");
                        send.add("oscore");
                        ArrayList<String> recieve = new ArrayList<>();
                        recieve.add("msg");
                        recieve.add("oscore");
                        recieve.add("oid");
                        //Log.d("gethm", hm.get("oid")+"&username="+hm.get("username"));
                        OkHttp okHttp = new OkHttp(send, recieve, 2, (Activity) mContext);
                        HashMap<String,String> rhm = okHttp.sendRequestWithOkHttp(hm, "http://120.48.5.10:9090/orderScore");
                        if ("true".equals(rhm.get("msg"))&&String.valueOf(score).equals(rhm.get("oscore"))){
                            Looper.prepare();
                            Toast.makeText(mContext,"评分成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//在跳转到目标activity前销毁所以已经存在的activity（实现不能返回的功能）
                            intent.setClass(mContext, UserOrder.class);
                            intent.putExtra("page",((UserOrder)mContext).a);
                            Log.d("page", String.valueOf(((UserOrder)mContext).a));
                            intent.putExtra("frag",2);
                            intent.putExtra("userOrder",true);
                            startActivity(intent);
                            Looper.loop();
                        }
                    }
                }).start();
//                Toast.makeText(mContext,ss[which],Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog.Builder builder1 = builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
//        builder.setNeutralButton("再想想", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this,"选择了再想想",Toast.LENGTH_SHORT).show();
//
//            }
//        });
        Looper.prepare();
        AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
        alertDialog.show();
        Looper.loop();

    }

}