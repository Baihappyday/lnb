package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.login.R;
import com.example.login.MyApplication;
import com.example.login.util.NetWorkUtil;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText healthcondition;
    private Button modify;
    private  Spinner sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

//        //民族spinner
//        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
//                R.layout.item_select, nationArray);
//        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
//
//        Spinner sp = (Spinner) findViewById(R.id.spinner_stuff);
//        sp.setPrompt("请选择民族");
//        sp.setAdapter(starAdapter);
//        sp.setSelection(0);
//        sp.setOnItemSelectedListener(new MySelectedListener("nation"));

        SharedUtil su = SharedUtil.getIntance(this,"healthInfo");
        String bt = su.readShared("ubloodtype", "");

        //血型spinner
        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, bloodTypeArray);
        bloodTypeAdapter.setDropDownViewResource(R.layout.item_dropdown);

        sp1 = (Spinner) findViewById(R.id.interval);
        sp1.setPrompt("请选择血型");
        sp1.setAdapter(bloodTypeAdapter);
        if (bt.equals(bloodTypeArray[0])){
            sp1.setSelection(0);
        }
        if (bt.equals(bloodTypeArray[1])){
            sp1.setSelection(1);
        }
        if (bt.equals(bloodTypeArray[2])){
            sp1.setSelection(2);
        }
        if (bt.equals(bloodTypeArray[3])){
            sp1.setSelection(3);
        }
        if (bt.equals(bloodTypeArray[4])){
            sp1.setSelection(4);
        }
        sp1.setOnItemSelectedListener(new MySelectedListener("bloodtype"));
        sp1.setClickable(false);

        healthcondition = findViewById(R.id.healthcondition);
        healthcondition.setText(su.readShared("uhealthcondition","无"));
        healthcondition.setFocusable(false);

        modify = findViewById(R.id.modify);
        modify.setOnClickListener(this);
    }

//    private String[] nationArray = {"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族",
//            "朝鲜族","满族","侗族","瑶族","白族","土家族", "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族",
//            "畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族", "土族","达斡尔族","仫佬族",
//            "羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",
//            "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族",
//            "门巴族","珞巴族","基诺族"
//            };

    private String[] bloodTypeArray = {"A","B","AB","O","其他"};

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.modify){
            if (NetWorkUtil.isNetWorkOK(this)){
                if (modify.getText().toString().equals("修改信息")){
                    sp1.setClickable(true);
                    healthcondition.setFocusableInTouchMode(true);
                    healthcondition.setFocusable(true);

                    modify.setText("提交修改");
                    Toast.makeText(this,"可以开始修改啦", Toast.LENGTH_SHORT).show();
                }
                else if (modify.getText().toString().equals("提交修改")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SharedUtil sp = SharedUtil.getIntance(HealthInfoActivity.this, "healthinfo");
                            HashMap<String, String> hm = new HashMap<>();
                            MyApplication application = (MyApplication) HealthInfoActivity.this.getApplicationContext();
                            hm.put("uusername", application.getName());
                            hm.put("uhealthcondition", healthcondition.getText().toString());
                            hm.put("ubloodtype", sp1.getSelectedItem().toString());
                            ArrayList<String> send = new ArrayList<>();
                            send.add("uusername");
                            send.add("uhealthcondition");
                            send.add("ubloodtype");
                            ArrayList<String> recieve = new ArrayList<>();
                            recieve.add("msg");
                            recieve.add("uage");
                            recieve.add("usex");
                            recieve.add("uaddress");
                            recieve.add("uphone");
                            recieve.add("ubloodtype");
                            recieve.add("uhealthcondition");
                            OkHttp okHttp = new OkHttp(send, recieve);
                            HashMap<String, String> rhm = okHttp.sendRequestWithOkHttp(hm, "http://120.48.5.10:9090/update-health");
                            Log.d("tag", rhm.get("msg") + rhm.get("ubloodtype"));
                            if (rhm.get("msg").equals("true")){
                                recieve.remove("msg");
                                recieve.remove("uage");
                                recieve.remove("usex");
                                recieve.remove("uaddress");
                                recieve.remove("uphone");
                                sp.writeShared(recieve, rhm);
                                Log.d("tag", sp.readShared("usex", "null") + sp.readShared("uaddress","null"));
                                Looper.prepare();
                                Toast.makeText(HealthInfoActivity.this,"修改成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                            else {
                                Looper.prepare();
                                Toast.makeText(HealthInfoActivity.this,"修改失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    }).start();
                    modify.setText("修改信息");
                    sp1.setClickable(false);
                    healthcondition.setFocusable(false);
                }
                else {

                }
            }
            else {
                if (modify.getText().toString().equals("修改信息")){
                    Toast.makeText(this,"不可修改", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        private String name;
        MySelectedListener(String name){
            this.name = name;
        }

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//            if (name.equals("bloodtype")){
//                Toast.makeText(HealthInfoActivity.this, "您选择的是"+bloodTypeArray[arg2]+"血型", Toast.LENGTH_SHORT).show();
//            }
//            else if (name.equals("nation")){
//                Toast.makeText(HealthInfoActivity.this, "您选择的是"+nationArray[arg2], Toast.LENGTH_SHORT).show();
//            }
//            else {
//
//            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}