package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.login.R;

//用户个人中心界面
public class PersonalCenter extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_personal_center, container, false);
        mContext = getActivity();
        Button bt4 = v.findViewById(R.id.bt4);
        bt4.setOnClickListener(this);
        Button bt3_1 = v.findViewById(R.id.bt3_1);
        bt3_1.setOnClickListener(this);
        Button bt3_2 = v.findViewById(R.id.bt3_2);
        bt3_2.setOnClickListener(this);
        Button bt3_3 = v.findViewById(R.id.bt3_3);
        bt3_3.setOnClickListener(this);
        Button bt3_4 = v.findViewById(R.id.bt3_4);
        bt3_4.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt4){//按钮：我的钱包
            Intent intent=new Intent(mContext,UserWallet.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_1) {
            Intent intent=new Intent(mContext, UserOrder.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_2) {
            Intent intent=new Intent(mContext, UserOrder.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_3) {
            Intent intent=new Intent(mContext, UserOrder.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.bt3_4) {
            Intent intent=new Intent(mContext, UserOrder.class);
            startActivity(intent);
        }
        }

}
