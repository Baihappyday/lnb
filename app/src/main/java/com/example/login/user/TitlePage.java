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


public class TitlePage extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_title_page, container, false);
        mContext = getActivity();
        Button btn1 = v.findViewById(R.id.button1);
        btn1.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1){//按钮：选机构
            Intent intent=new Intent(mContext,SkimInstitution.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button2){//按钮：健康

        }
        if (view.getId() == R.id.button3){//按钮：社区活动

        }
    }
}