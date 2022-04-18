package com.example.login.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.login.R;
import com.example.login.util.SharedUtil;

public class workwalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inwallet);

        String acc_num;

        SharedUtil sp = SharedUtil.getIntance(workwalletActivity.this, "logininfo");
        acc_num = sp.readShared("account", null);

        TextView account=findViewById(R.id.account);
        account.setText(String.valueOf(acc_num));

    }
}
