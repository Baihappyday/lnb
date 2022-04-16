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

public class InstitutionFragment extends Fragment {
    protected Context mContext;
    protected View v;
    private HashMap<String, Object> info;
    private TextView iname;
    private TextView iaddress;
    private TextView iprice;
    private TextView idescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_institution, container, false);
        mContext = getActivity();
        MyApplication application = (MyApplication) mContext.getApplicationContext();

        iname = v.findViewById(R.id.activitytime);
        iaddress = v.findViewById(R.id.activityaddress);
        iprice = v.findViewById(R.id.activitydescription);
        idescription = v.findViewById(R.id.idescription);

        iname.setText(iname.getText().toString()+info.get("iname"));
        iaddress.setText(iaddress.getText().toString()+info.get("iaddress"));
        iprice.setText(iprice.getText().toString()+info.get("iprice"));
        idescription.setText(idescription.getText().toString()+info.get("idescription"));


        return v;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
    }
}
