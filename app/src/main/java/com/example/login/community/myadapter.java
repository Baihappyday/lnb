package com.example.login.community;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login.R;

import java.util.List;

public class myadapter extends BaseAdapter {

    private List<Bean> data;
    private Context context;
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }
        TextView textView = viewGroup.findViewById(R.id.text10);
        textView.setText(data.get(position).getName());

        Log.e("leo", "getView: "+position );

        return view;
    }

    public myadapter(List<Bean> data,Context context) {
        this.data = data;
        this.context = context;

    }



}
