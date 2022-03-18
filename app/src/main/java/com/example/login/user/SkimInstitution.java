package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.R;

public class SkimInstitution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skim_institution);

        ArrayAdapter<String> multiarrangeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select_insti, multiarrangeArray);
        ArrayAdapter<String> institypeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select_insti, institypeArray);
        ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select_insti, regionArray);
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select_insti, filterArray);

        Spinner sp = (Spinner) findViewById(R.id.multi_arrange);
        Spinner sp1 = (Spinner) findViewById(R.id.insti_type);
        Spinner sp2 = (Spinner) findViewById(R.id.region_choose);
        Spinner sp3 = (Spinner) findViewById(R.id.filter);
        sp.setPrompt("综合排序");
        sp.setAdapter(multiarrangeAdapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new SkimInstitution.MySelectedListener());
        sp1.setPrompt("机构类型");
        sp1.setAdapter(institypeAdapter);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new SkimInstitution.MySelectedListener());
        sp2.setPrompt("区县选择");
        sp2.setAdapter(regionAdapter);
        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new SkimInstitution.MySelectedListener());
        sp3.setPrompt("筛选");
        sp3.setAdapter(filterAdapter);
        sp3.setSelection(0);
        sp3.setOnItemSelectedListener(new SkimInstitution.MySelectedListener());
        //养老院信息等待连接数据库后加载

//        LinearLayout ll = (LinearLayout)findViewById(R.id.institution_list);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        RelativeLayout r = (RelativeLayout)inflater.inflate(R.layout.institution, (ViewGroup) findViewById(R.id.institution), true);
//        ll.addView(r);

    }

    private String[] multiarrangeArray = {"综合排序", "1", "2", "3", "4"};
    private String[] institypeArray = {"机构类型", "1", "2", "3"};
    private String[] regionArray = {"区县选择", "1", "2", "3"};
    private String[] filterArray = {"筛选", "1", "2", "3"};


    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            //Toast.makeText(SkimInstitution.this, "您选择的是"+institypeArray[arg2], Toast.LENGTH_SHORT).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}