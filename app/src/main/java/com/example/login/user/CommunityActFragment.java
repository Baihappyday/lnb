package com.example.login.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.login.MyApplication;
import com.example.login.R;

import java.util.HashMap;


public class CommunityActFragment extends Fragment {
    protected Context mContext;
    protected View v;
    private HashMap<String, Object> info;
    private TextView activitytime;
    private TextView activityaddress;
    private TextView activitydescription;
    private TextView aid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_community_act, container, false);
        mContext = getActivity();
        MyApplication application = (MyApplication) mContext.getApplicationContext();

        activitytime = v.findViewById(R.id.activitytime);
        activityaddress = v.findViewById(R.id.activityaddress);
        activitydescription = v.findViewById(R.id.activitydescription);
        activitytime.setText(activitytime.getText().toString()+info.get("activitytime"));
        activityaddress.setText(activityaddress.getText().toString()+info.get("activityaddress"));
        activitydescription.setText(activitydescription.getText().toString()+info.get("activitydescription"));


        return v;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
    }
}