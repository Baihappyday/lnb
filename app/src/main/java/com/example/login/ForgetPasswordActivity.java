package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ForgetPasswordActivity extends AppCompatActivity {
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        TextView textView = (TextView)findViewById(R.id.textView);
        ll = (LinearLayout)findViewById(R.id.ll) ;
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        layoutParam.setMargins(0, height/4, 0, 0);
        ll.setLayoutParams(layoutParam);
    }
}