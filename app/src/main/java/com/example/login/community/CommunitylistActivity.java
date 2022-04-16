package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

public class CommunitylistActivity extends AppCompatActivity  {
       private ListView listView;
        private myadapter listAdapter;
        private List<Bean> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activities_list);

        listView = findViewById(R.id.lv1);
        listAdapter = new myadapter(datas,this);
        listView.setAdapter(listAdapter);
        /*listView.setOnClickListener({
                @Override
                public void onItemClick
        });*/
        for(int i =0;i<48;i++)
        {
            Bean bean = new Bean();
            bean.setName("活动" + i);
            datas.add(bean);

        }

        Button button1 = findViewById(R.id.add_activity);
        button1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (view.getId() == R.id.add_activity){
                    Intent i = new Intent(CommunitylistActivity.this, Add_activityActivity.class);
                    startActivity(i);
                }

            }
        });
        Button button2 = findViewById(R.id.add_community);
        button2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (view.getId() == R.id.add_community){
                    Intent i = new Intent(CommunitylistActivity.this, CommunityaddActivity.class);
                    startActivity(i);
                }

            }
        });
    }
}
