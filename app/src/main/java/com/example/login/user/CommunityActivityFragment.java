package com.example.login.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.login.R;


public class CommunityActivityFragment extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_community_activity, container, false);
        mContext = getActivity();
        Button btn5 = (Button)v.findViewById(R.id.button5);
        btn5.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button5){
            Intent intent=new Intent(mContext,InfoResourceActivity.class);
            startActivity(intent);

        }
    }
}