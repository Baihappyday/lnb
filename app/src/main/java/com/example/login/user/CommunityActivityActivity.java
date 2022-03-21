package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.login.R;

public class CommunityActivityActivity extends AppCompatActivity {
    CommunityActivityFragment ca = new CommunityActivityFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_activity);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, ca).commit();
    }
}