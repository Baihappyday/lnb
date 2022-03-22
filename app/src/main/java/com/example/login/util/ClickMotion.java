package com.example.login.util;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ClickMotion {
    //参数一为活动，参数二为控件名
    public static void motion(AppCompatActivity a, final View v){
        final DisplayMetrics dm =a.getResources().getDisplayMetrics();
        Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, dm));// 控件的高强制设置
                Handler h2 = new Handler();
                h2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                        relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52, dm));// 控件的高强制设置
                        Handler h3 = new Handler();
                        h3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54, dm));// 控件的高强制设置
                                Handler h4 = new Handler();
                                h4.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                        relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, dm));// 控件的高强制设置
                                        Handler h5 = new Handler();
                                        h5.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                                relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 58, dm));// 控件的高强制设置

                                                v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                            }
                                        },40);
                                        v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                    }
                                },40);
                                v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                            }
                        },40);
                        v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                    }
                },40);
                v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
            }
        },40);
    }

}
