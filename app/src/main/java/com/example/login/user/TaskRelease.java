package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.R;

import java.util.Calendar;
//任务发布界面
public class TaskRelease extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_release);

        ArrayAdapter<String> stuffAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, stuffArray);
        ArrayAdapter<String> intervalAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, timeArray);
        Spinner sp = (Spinner) findViewById(R.id.spinner_stuff);
        Spinner sp1 = (Spinner) findViewById(R.id.interval);
        sp.setPrompt("服务类型");
        sp.setAdapter(stuffAdapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new TaskRelease.MySelectedListener());
        sp1.setPrompt("服务时长");
        sp1.setAdapter(intervalAdapter);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new TaskRelease.MySelectedListener());
    }

    private String[] stuffArray = {"洗衣", "做饭", "打扫卫生", "理发", "其他"};
    private String[] timeArray = {"0~30分钟", "30~60分钟", "1~2小时", "2小时以上"};

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(TaskRelease.this, "您选择的是"+stuffArray[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit) {//提交订单

        }
    }
}