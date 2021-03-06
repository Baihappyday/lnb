package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        //????????????vp??????
        DisplayMetrics dm =getActivity().getResources().getDisplayMetrics();
        LinearLayout.LayoutParams relativeParams =(LinearLayout.LayoutParams) vp_content.getLayoutParams();
        relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Utils.px2dip(getActivity(),width)*9/16, dm));
        //??????indicator
        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;

        final ViewPagerIndicator indicator = new ViewPagerIndicator(getActivity());
        vp_content.addView(indicator, layoutParams);


        WebView webView = v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://m.baidu.com/sf?county_id=101180101&dspName=iphone&ext={%22bar_sort%22:%22chuanyi,chuyou,xiche,fangshai,huazhuang,ganmao,%22,%22sf_tab_name%22:%22chuanyi%22}&from_sf=1&fromapp=vsgo&openapi=1&pd=life_compare_weather&resource_id=4599&title=%E7%94%9F%E6%B4%BB%E6%B0%94%E8%B1%A1%E6%8C%87%E6%95%B0&word=%E9%83%91%E5%B7%9E&lid=8856305032740678900&referlid=8856305032740678900&ms=1&frsrcid=4982&frorder=1&qq-pf-to=pcqq.c2c");

        WebView webView2 = v.findViewById(R.id.webView2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebViewClient(new WebViewClient() {
            // ??????????????????????????????????????????????????????????????????webview????????????????????????????????????
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView2.loadUrl("http://m.yanglaotoutiao.com/");

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

//        QWeatherConfig.init("8bb6b64ca1f64fa292f4912a2ab9c65c");
//        //????????????
//        HorizonView horizonView = v.findViewById(R.id.horizon_view);
////??????????????????
//        horizonView.setDefaultBack(false);
////????????????????????????????????????????????????????????????????????????
//        horizonView.setStroke(0,Color.BLUE,1,Color.BLUE);
////?????????????????????????????????????????????????????????????????????sp ????????????????????????????????????????????????
//        horizonView.addLocation(14, Color.WHITE);
////??????????????????????????????????????????????????????dp
//        horizonView.addAlarmIcon(14);
////??????????????????
//        horizonView.addAlarmTxt(14);
////??????????????????
//        horizonView.addTemp(14, Color.WHITE);
////??????????????????
//        horizonView.addWeatherIcon(14);
////??????????????????
//        horizonView.addCond(14, Color.WHITE);
////??????????????????
//        horizonView.addWindIcon(14);
////??????????????????
//        horizonView.addWind(14, Color.WHITE);
////???????????????AQI
//        horizonView.addAqiText(14, Color.WHITE);
////????????????????????????
//        horizonView.addAqiQlty(14);
////??????????????????????????????
//        horizonView.addAqiNum(14);
////??????????????????
//        horizonView.addRainIcon(14);
////??????????????????
//        horizonView.addRainDetail(14, Color.WHITE);
////??????????????????????????????????????????
//        horizonView.setViewGravity(HeContent.GRAVITY_CENTER);
////????????????????????????????????????0
//        horizonView.setViewPadding(10,10,10,10);
////????????????
//        horizonView.show();
             return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1){//??????????????????
            Intent intent=new Intent(mContext, SkimInstitution.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button2){//???????????????
            Intent intent=new Intent(mContext, HealthInfoActivity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.button3){
            Intent intent=new Intent(mContext, CommunityActivityActivity.class);
            startActivity(intent);


        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view==btn1||view==btn2||view==btn3)
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //??????
                view.setBackground(ContextCompat.getDrawable(this.getActivity(), R.drawable.frame5));
//                ((AnimationDrawable)view.getBackground()).start();
                break;
            case MotionEvent.ACTION_MOVE:
                //??????

                view.setBackgroundColor(getResources().getColor(R.color.transparent));


                break;
            case MotionEvent.ACTION_UP:
                //??????
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