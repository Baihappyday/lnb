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
    String ostate;


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
                ostate= (String) info.get("oid");
                Log.d("oid", String.valueOf(ostate));


                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        accept.setText("已接受");
                        click=true;
                        Intent intent=new Intent(getActivity(), WorkerAccessOrder.class);
                        intent.putExtra("btstate",click);//boolean
                        intent.putExtra("oid",ostate);//string
                        startActivity(intent);
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