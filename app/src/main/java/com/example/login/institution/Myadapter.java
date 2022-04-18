package com.example.login.institution;

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

public class Myadapter extends BaseAdapter {

    private List<bean> datas;
    private Context context;


    public Myadapter(List<bean> data,Context context) {
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

            view = LayoutInflater.from(context).inflate(R.layout.list_item2, viewGroup, false);

        }

        /*TextView textView1 = view.findViewById(R.id.text10);
        textView1.setText(datas.get(position).getId());*/

        TextView textView2 = view.findViewById(R.id.text14);
        textView2.setText(datas.get(position).getIiusername());

        TextView textView3 = view.findViewById(R.id.text15);
        textView3.setText(datas.get(position).getIname());

        TextView textView4 = view.findViewById(R.id.text16);
        textView4.setText(datas.get(position).getIaddress());

        TextView textView5 = view.findViewById(R.id.text17);
        textView5.setText(String.valueOf(datas.get(position).getIprice()));

        TextView textView6 = view.findViewById(R.id.text18);
        textView6.setText(datas.get(position).getIdescription());

        Log.e("leo", "getView: "+position );

        return view;


    }

    @Override
    public  int getViewTypeCount(){
        return 1;
    }





}
