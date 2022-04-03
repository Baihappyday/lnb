package com.example.login.worker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.login.R;
import com.example.login.user.UserLoginActivity;

public class WorkpersonalActivity extends Fragment  implements View.OnClickListener{
    private View v;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_worker_personal, container, false);
        mContext = getActivity();
        Button btn1 = v.findViewById(R.id.bt1);
        btn1.setOnClickListener(this);
        Button btn2 = v.findViewById(R.id.bt2);
        btn2.setOnClickListener(this);
        Button btn3 = v.findViewById(R.id.bt3);
        btn3.setOnClickListener(this);
        Button btn4 = v.findViewById(R.id.bt4);
        btn4.setOnClickListener(this);
        Button btn5 = v.findViewById(R.id.bt5);
        btn5.setOnClickListener(this);
        Button btn6 = v.findViewById(R.id.bt6);
        btn6.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt1){//登录
            Intent intent=new Intent(mContext, UserLoginActivity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt2){//全部
            Intent intent=new Intent(mContext, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt3){//全部
            Intent intent=new Intent(mContext, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt4){//全部
            Intent intent=new Intent(mContext, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt5){//全部
            Intent intent=new Intent(mContext, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt6){
            Intent intent=new Intent(mContext, workwalletActivity.class);
            startActivity(intent);
        }
    }
}
