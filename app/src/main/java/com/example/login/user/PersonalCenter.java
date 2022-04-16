package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.login.IdentiChooseActivity;
import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.SharedUtil;

//用户个人中心界面
public class PersonalCenter extends Fragment implements View.OnClickListener, View.OnTouchListener {
    protected Context mContext;
    protected View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_personal_center, container, false);
        mContext = getActivity();
        MyApplication application = (MyApplication) mContext.getApplicationContext();
        Button bt2 = v.findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        Button bt101 = v.findViewById(R.id.bt101);
        bt101.setOnClickListener(this);
        //Button bt4 = v.findViewById(R.id.bt4);
        //bt4.setOnClickListener(this);
        Button bt3_1 = v.findViewById(R.id.bt3_1);
        bt3_1.setOnClickListener(this);
        Button bt3_2 = v.findViewById(R.id.bt3_2);
        bt3_2.setOnClickListener(this);
        Button bt3_3 = v.findViewById(R.id.bt3_3);
        bt3_3.setOnClickListener(this);
        Button bt3_4 = v.findViewById(R.id.bt3_4);
        bt3_4.setOnClickListener(this);
        Button bt1 = v.findViewById(R.id.bt1);
        Button button6 = v.findViewById(R.id.button6);
        Button button13 = v.findViewById(R.id.button13);
        button13.setOnClickListener(this);
        if (!application.getLoginState()){
            button13.setVisibility(View.GONE);
        }
        if (application.getLoginState()){//若之前已登录
            SharedUtil sp = SharedUtil.getIntance(mContext,"logininfo");
            String s = sp.readShared("username", "null");
            Log.d("tag", s);
            if (s.equals("null")){
                bt1.setText("点击登录");
            }
            else {
                bt1.setText("用户"+s);
            }
        }
        else {
            bt1.setOnClickListener(this);
            button6.setOnClickListener(this);
        }
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt1){//按钮：登录
            Intent intent=new Intent(mContext,UserLoginActivity.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt101){//按钮：我的钱包
            Intent intent=new Intent(mContext,UserWallet.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt2){//按钮：完善个人信息
            Intent intent=new Intent(mContext,SkimUserInfo.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_1) {
            Intent intent=new Intent(mContext, UserOrder.class);
            intent.putExtra("page", 0);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_2) {
            Intent intent=new Intent(mContext, UserOrder.class);
            intent.putExtra("page", 1);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_3) {
            Intent intent=new Intent(mContext, UserOrder.class);
            intent.putExtra("page", 2);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_4) {
            Intent intent=new Intent(mContext, UserOrder.class);
            intent.putExtra("page", 3);
            startActivity(intent);
        }
        if (view.getId() == R.id.button6){//按钮：完善个人信息
            Intent intent=new Intent(mContext, UserLoginActivity.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button13){//按钮：退出登录
            Intent intent=new Intent(mContext, IdentiChooseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            SharedUtil.clearShared(mContext);
            MyApplication application = (MyApplication) mContext.getApplicationContext();
            application.setLoginState(false);
            startActivity(intent);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    //按下
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){}
                    break;
                case MotionEvent.ACTION_MOVE:
                    //移动

                    view.setBackgroundColor(getResources().getColor(R.color.transparent));


                    break;
                case MotionEvent.ACTION_UP:
                    //松开
                    view.setBackgroundColor(getResources().getColor(R.color.transparent));

                    break;
            }
        return false;
    }

}
