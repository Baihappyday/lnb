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
        Button btn3 = v.findViewById(R.id.button3);
        btn3.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1){
            Intent intent=new Intent(mContext,SkimInstitution.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button2){

        }
        if (view.getId() == R.id.button3){
            Intent intent=new Intent(mContext,CommunityActivityActivity.class);
            startActivity(intent);

        }
    }
}