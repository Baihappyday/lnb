package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.R;

import java.util.List;


public class ModifyUserlInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_userl_info);

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
    private List<Integer> yearArray;
    private List<Integer> monthArray;
    private List<Integer> dayArray;

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(ModifyUserlInfo.this, "您选择的是"+provinceArray[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}