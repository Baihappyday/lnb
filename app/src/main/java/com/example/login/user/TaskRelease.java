package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.R;

//任务发布界面
public class TaskRelease extends AppCompatActivity implements View.OnClickListener {

    private Spinner sp;    //服务类型下拉菜单
    private Spinner sp1;    //服务时间下拉菜单
    private EditText input_adress;    //输入的地址
    private Button submit;    //提交按钮
    private EditText editTextTextMultiLine; //备注信息

    private TaskDao taskDao;    //任务数据库操作辅助类
    public static int ntask=0;   //任务编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_release);

        ArrayAdapter<String> stuffAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, stuffArray);
        ArrayAdapter<String> intervalAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, timeArray);
        sp = (Spinner) findViewById(R.id.spinner_stuff);
        sp1 = (Spinner) findViewById(R.id.interval);
        sp.setPrompt("服务类型");
        sp.setAdapter(stuffAdapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new TaskRelease.MySelectedListener());
        sp1.setPrompt("服务时长");
        sp1.setAdapter(intervalAdapter);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new TaskRelease.MySelectedListener());


        submit = (Button)findViewById(R.id.modify);
        submit.setOnClickListener(this);

        input_adress = (EditText) findViewById(R.id.input_adress);
        input_adress.setOnClickListener(this);

        editTextTextMultiLine = (EditText) findViewById(R.id.healthcondition);
        editTextTextMultiLine.setOnClickListener(this);
    }

    private final String[] stuffArray = {"洗衣", "做饭", "打扫卫生", "理发", "其他"};
    private final String[] timeArray = {"0~30分钟", "30~60分钟", "1~2小时", "2小时以上"};

    public void initView(){    //初始化函数

    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(TaskRelease.this, "您选择的是"+stuffArray[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }



    @Override
    public void onClick(View v) {
        taskDao = new TaskDao();
        /*
        发布任务
         */
        if (v.getId() == R.id.modify) {//提交订单
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //                   taskDao.createTask( 2, "test", "test", "111",
                    //                         "111","111");//需要在子线程中处理的逻辑

                    ntask = taskDao.getMax_ntask() + 1;
                    /*
                    需要六个参数：任务编号，用户id，服务人员id，服务类型，服务时间，地址，
                     */
                    taskDao.createTask( ntask, "test", "test", sp.getSelectedItem().toString(),
                            sp1.getSelectedItem().toString(),input_adress.getText().toString(),
                            editTextTextMultiLine.getText().toString());
                }
            }).start();
        }
    }
}