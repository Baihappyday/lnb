package com.example.login.util;

import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference {
    private SharedPreferences mShared;
    private TextView tv_share;
    private AppCompatActivity a;

    public SharedPreference(AppCompatActivity a){
        this.a = a;
    }



    public void readSharedPreferences(AppCompatActivity a) {
        mShared = a.getSharedPreferences("share", MODE_PRIVATE);
        String desc = "共享参数中保存的信息如下：";
        Map<String, Object> mapParam = (Map<String, Object>) mShared.getAll();
        for (Map.Entry<String, Object> item_map : mapParam.entrySet()) {
            String key = item_map.getKey();
            Object value = item_map.getValue();
            if (value instanceof String) {
                desc = String.format("%s\n　%s的取值为%s", desc, key,
                        mShared.getString(key, ""));
            } else if (value instanceof Integer) {
                desc = String.format("%s\n　%s的取值为%d", desc, key,
                        mShared.getInt(key, 0));
            } else if (value instanceof Float) {
                desc = String.format("%s\n　%s的取值为%f", desc, key,
                        mShared.getFloat(key, 0.0f));
            } else if (value instanceof Boolean) {
                desc = String.format("%s\n　%s的取值为%b", desc, key,
                        mShared.getBoolean(key, false));
            } else if (value instanceof Long) {
                desc = String.format("%s\n　%s的取值为%d", desc, key,
                        mShared.getLong(key, 0l));
            } else {
                desc = String.format("%s\n参数%s的取值为未知类型", desc, key);
            }
        }
        if (mapParam==null || mapParam.size()<=0) {
            desc = "共享参数中保存的信息为空";
        }
        tv_share.setText(desc);
    }

    public void writeSharedPreferences(AppCompatActivity a){

        SharedPreferences.Editor editor = mShared.edit();
//        editor.putString("name", name);
//        editor.putInt("age", Integer.parseInt(age));
//        editor.putLong("height", Long.parseLong(height));
//        editor.putFloat("weight", Float.parseFloat(weight));
//        editor.putBoolean("married", bMarried);
//        editor.putString("update_time", DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
//        editor.commit();
    }
}
