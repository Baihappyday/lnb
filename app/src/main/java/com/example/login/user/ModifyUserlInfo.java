package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;

import java.util.Calendar;


//用户修改个人信息界面

public class ModifyUserlInfo extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private TextView tv_birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_userl_info);

        tv_birthdate = (TextView)findViewById(R.id.birthdate);
        tv_birthdate.setOnClickListener(this);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, provinceArray);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, cityArray);
        ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, countyArray);
        provinceAdapter.setDropDownViewResource(R.layout.item_dropdown);
        cityAdapter.setDropDownViewResource(R.layout.item_dropdown);
        countyAdapter.setDropDownViewResource(R.layout.item_dropdown);

        Spinner sp = (Spinner) findViewById(R.id.province);
        Spinner sp1 = (Spinner) findViewById(R.id.city);
        Spinner sp2 = (Spinner) findViewById(R.id.county);
        sp.setPrompt("省");
        sp.setAdapter(provinceAdapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new MySelectedListener());

        sp1.setPrompt("市");
        sp1.setAdapter(cityAdapter);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new MySelectedListener());

        sp2.setPrompt("县");
        sp2.setAdapter(countyAdapter);
        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new MySelectedListener());
    }

    private String[] provinceArray = {"北京市", "河北省", "安徽省", "河南省", "湖北省", "湖南省"};
    private String[] cityArray = {"北京市", "郑州市", "合肥市", "武汉市", "长沙市", "芜湖市"};
    private String[] countyArray = {"北京市", "郑州市", "合肥市", "武汉市", "长沙市", "芜湖市"};

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(ModifyUserlInfo.this, "您选择的是"+provinceArray[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.birthdate) {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String desc = String.format("%d年%d月%d日",
                year, monthOfYear+1, dayOfMonth);
        tv_birthdate.setText(desc);
    }
}