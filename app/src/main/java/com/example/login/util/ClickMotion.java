package com.example.login.util;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

public class ClickMotion {
    public static void motion(AppCompatActivity a, final View v, final int height){//活动，控件，控件原始高度（单位dp）
        final DisplayMetrics dm =a.getResources().getDisplayMetrics();
        final int decline = height/20;
        if (v.getId()== R.id.taskk){
            Handler h1 = new Handler();
            h1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                    relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, dm));// 控件的高强制设置
                    Handler h2 = new Handler();
                    h2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                            relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height-decline, dm));// 控件的高强制设置
                            Handler h3 = new Handler();
                            h3.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                    relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height-2*decline, dm));// 控件的高强制设置
                                    Handler h4 = new Handler();
                                    h4.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                            relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height-decline, dm));// 控件的高强制设置
                                            Handler h5 = new Handler();
                                            h5.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                                    relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, dm));// 控件的高强制设置

                                                    v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                                }
                                            },20);
                                            v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                        }
                                    },20);
                                    v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                }
                            },20);
                            v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                        }
                    },20);
                    v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                }
            },20);
        }
        else {
            Handler h1 = new Handler();
            h1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                    relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, dm));// 控件的高强制设置
                    Handler h2 = new Handler();
                    h2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                            relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 57, dm));// 控件的高强制设置
                            Handler h3 = new Handler();
                            h3.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                    relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55, dm));// 控件的高强制设置
                                    Handler h4 = new Handler();
                                    h4.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                            relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 57, dm));// 控件的高强制设置
                                            Handler h5 = new Handler();
                                            h5.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    RelativeLayout.LayoutParams relativeParams =(RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件当前的布局参数

                                                    relativeParams.height=((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, dm));// 控件的高强制设置

                                                    v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                                }
                                            },20);
                                            v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                        }
                                    },20);
                                    v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                                }
                            },20);
                            v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                        }
                    },20);
                    v.setLayoutParams(relativeParams); //使设置好的布局参数应用到控件
                }
            },20);
        }
        }


}
