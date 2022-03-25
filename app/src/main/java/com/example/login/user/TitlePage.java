package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
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
import com.example.login.util.Utils;
import com.example.login.widget.ViewPagerIndicator;
import com.qweather.plugin.view.HeContent;
import com.qweather.plugin.view.HorizonView;
import com.qweather.plugin.view.LeftLargeView;
import com.qweather.plugin.view.QWeatherConfig;
import com.qweather.plugin.view.SuspendView;
import com.qweather.sdk.view.HeConfig;

import java.util.ArrayList;


public class TitlePage extends Fragment implements View.OnClickListener, View.OnTouchListener, ViewPager.OnPageChangeListener {
    protected Context mContext;
    protected View v;
    Button btn1;
    Button btn2;
    Button btn3;
    private ArrayList<GoodsInfo> goodsList;
    ViewPager vp_content;

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


        vp_content = (ViewPager) v.findViewById(R.id.vp_content);
        goodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapater adapter = new ImagePagerAdapater(getActivity(), goodsList);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(0);
        vp_content.addOnPageChangeListener(this);
        //动态设置vp高度
        DisplayMetrics dm =getActivity().getResources().getDisplayMetrics();
        LinearLayout.LayoutParams relativeParams =(LinearLayout.LayoutParams) vp_content.getLayoutParams();
        relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Utils.px2dip(getActivity(),width)*9/16, dm));
        //设置indicator
        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        final ViewPagerIndicator indicator = new ViewPagerIndicator(getActivity());
        vp_content.addView(indicator, layoutParams);


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

        QWeatherConfig.init("8bb6b64ca1f64fa292f4912a2ab9c65c");
        //横向布局
        HorizonView horizonView = v.findViewById(R.id.horizon_view);
//取消默认背景
        horizonView.setDefaultBack(false);
//设置布局的背景圆角角度，颜色，边框宽度，边框颜色
        horizonView.setStroke(0,Color.BLUE,1,Color.BLUE);
//添加地址文字描述，第一个参数为文字大小，单位：sp ，第二个参数为文字颜色，默认白色
        horizonView.addLocation(14, Color.WHITE);
//添加预警图标，参数为图标大小，单位：dp
        horizonView.addAlarmIcon(14);
//添加预警文字
        horizonView.addAlarmTxt(14);
//添加温度描述
        horizonView.addTemp(14, Color.WHITE);
//添加天气图标
        horizonView.addWeatherIcon(14);
//添加天气描述
        horizonView.addCond(14, Color.WHITE);
//添加风向图标
        horizonView.addWindIcon(14);
//添加风力描述
        horizonView.addWind(14, Color.WHITE);
//添加文字：AQI
        horizonView.addAqiText(14, Color.WHITE);
//添加空气质量描述
        horizonView.addAqiQlty(14);
//添加空气质量数值描述
        horizonView.addAqiNum(14);
//添加降雨图标
        horizonView.addRainIcon(14);
//添加降雨描述
        horizonView.addRainDetail(14, Color.WHITE);
//设置控件的对齐方式，默认居中
        horizonView.setViewGravity(HeContent.GRAVITY_CENTER);
//设置控件的内边距，默认为0
        horizonView.setViewPadding(10,10,10,10);
//显示控件
        horizonView.show();

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
                view.setBackground(ContextCompat.getDrawable(this.getActivity(),R.drawable.frame5));
//                ((AnimationDrawable)view.getBackground()).start();
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

    @Override
    public void onStop() {
        super.onStop();
        vp_content.setCurrentItem(0);
    }
}