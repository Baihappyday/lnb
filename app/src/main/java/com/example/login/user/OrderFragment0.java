package com.example.login.user;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.login.MyApplication;
import com.example.login.R;


public class OrderFragment0 extends Fragment {
    protected Context mContext;
    protected View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_order0, container, false);
        mContext = getActivity();
        MyApplication application = (MyApplication) mContext.getApplicationContext();
        return v;
    }
}