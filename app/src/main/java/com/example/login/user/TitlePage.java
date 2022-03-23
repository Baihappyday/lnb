package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.login.R;
import com.example.login.util.ClickMotion;


public class TitlePage extends Fragment implements View.OnClickListener, View.OnTouchListener {
    protected Context mContext;
    protected View v;
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_title_page, container, false);
        mContext = getActivity();
        btn1 = v.findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        btn1.setOnTouchListener(this);
        btn2 = v.findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        btn2.setOnTouchListener(this);
        btn3 = v.findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        btn3.setOnTouchListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1){//按钮：选机构
            Intent intent=new Intent(mContext,SkimInstitution.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button2){//按钮：健康
            Intent intent=new Intent(mContext,HealthInfoActivity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.button3){
            Intent intent=new Intent(mContext,CommunityActivityActivity.class);
            startActivity(intent);


        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view==btn1||view==btn2||view==btn3)
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //按下
                view.setBackground(ContextCompat.getDrawable(this.getActivity(),R.drawable.frame_animation));
                ((AnimationDrawable)view.getBackground()).start();
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