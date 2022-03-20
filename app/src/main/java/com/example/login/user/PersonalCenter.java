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


public class PersonalCenter extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_personal_center, container, false);
        mContext = getActivity();
        Button bt4 = v.findViewById(R.id.bt4);
        bt4.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt4){
            Intent intent=new Intent(mContext,UserWallet.class);
            startActivity(intent);
        }

    }
}