package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.example.login.adapter.ImagePagerAdapater;
import com.example.login.bean.GoodsInfo;

import java.util.ArrayList;


public class TitlePage extends Fragment implements View.OnClickListener, View.OnTouchListener, ViewPager.OnPageChangeListener {
    protected Context mContext;
    protected View v;
    Button btn1;
    Button btn2;
    Button btn3;
    private ArrayList<GoodsInfo> goodsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_title_page, container, false);

        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;


        ViewPager vp_content = (ViewPager) v.findViewById(R.id.vp_content);
        goodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapater adapter = new ImagePagerAdapater(getActivity(), goodsList);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(0);
        vp_content.addOnPageChangeListener(this);
        //动态设置vp高度
        DisplayMetrics dm =getActivity().getResources().getDisplayMetrics();
        LinearLayout.LayoutParams relativeParams =(LinearLayout.LayoutParams) vp_content.getLayoutParams();
        relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, dm))*3/16;


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

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int arg0) {

    }
}