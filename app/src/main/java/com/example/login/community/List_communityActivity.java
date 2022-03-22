package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.login.R;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class List_communityActivity extends AppCompatActivity {

    private List<Bean> data = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activities_list);

        for(int i = 1; i <= 50; i++){
            Bean bean = new Bean();
            bean.setName("活动" + i);
            data.add(bean);

        }
        ListView listview = findViewById(R.id.lv1);
        listview.setAdapter(new myadapter(data,this));

    }
}