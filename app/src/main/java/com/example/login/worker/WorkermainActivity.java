package com.example.login.worker;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import com.example.login.R;

public class WorkermainActivity extends Fragment {
    protected View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_worker_main, container, false);
        EditText edit=v.findViewById(R.id.text1);
        edit.clearFocus();
        return v;
    }
}
