package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.login.R;

public class CommunitylistActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activities_list);
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
