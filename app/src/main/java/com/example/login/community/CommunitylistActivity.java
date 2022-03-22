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
        Button button1 = findViewById(R.id.button8);
        button1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(CommunitylistActivity.this, CommunityaddActivity.class);
                startActivity(i);
            }
        });
    }
}
