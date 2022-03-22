package com.example.login.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

public class CommunityaddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_commmunity_activities);
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(CommunityaddActivity.this, CommunitylistActivity.class);
                startActivity(i);
            }
        });
    }

}
