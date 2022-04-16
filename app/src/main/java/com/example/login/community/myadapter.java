package com.example.login.community;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.List;

public class myadapter extends BaseAdapter {

    private List<Bean> datas;
    private Context context;


    public myadapter(List<Bean> data,Context context) {
        this.datas = data;
        this.context = context;

    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position)      {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);

        }

        TextView textView = view.findViewById(R.id.text10);
        textView.setText(datas.get(position).getName());

        Log.e("leo", "getView: "+position );

        return view;


    }





}
