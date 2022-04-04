package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.login.R;
import com.example.login.MyApplication;

public class HealthInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        //民族spinner
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, nationArray);
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);

        Spinner sp = (Spinner) findViewById(R.id.spinner_stuff);
        sp.setPrompt("请选择民族");
        sp.setAdapter(starAdapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new MySelectedListener("nation"));

        //血型spinner
        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, bloodTypeArray);
        bloodTypeAdapter.setDropDownViewResource(R.layout.item_dropdown);

        Spinner sp1 = (Spinner) findViewById(R.id.interval);
        sp1.setPrompt("请选择血型");
        sp1.setAdapter(bloodTypeAdapter);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new MySelectedListener("bloodtype"));
    }

    private String[] nationArray = {"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族",
            "朝鲜族","满族","侗族","瑶族","白族","土家族", "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族",
            "畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族", "土族","达斡尔族","仫佬族",
            "羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",
            "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族",
            "门巴族","珞巴族","基诺族"
            };

    private String[] bloodTypeArray = {"A","B","AB","O","其他"};

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        private String name;
        MySelectedListener(String name){
            this.name = name;
        }

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            if (name.equals("bloodtype")){
                Toast.makeText(HealthInfoActivity.this, "您选择的是"+bloodTypeArray[arg2]+"血型", Toast.LENGTH_SHORT).show();
            }
            else if (name.equals("nation")){
                Toast.makeText(HealthInfoActivity.this, "您选择的是"+nationArray[arg2], Toast.LENGTH_SHORT).show();
            }
            else {

            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}