package com.example.login.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.IdentiChooseActivity;
import com.example.login.MainActivity;
import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.util.OkHttp;
import com.example.login.util.SharedUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


//用户修改个人信息界面

public class ModifyUserlInfo extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    //private TextView tv_birthdate;
    //private TextView birthday;
    private Button modify;
    private EditText username1;
    private EditText agee;
    private RadioGroup rg;
    private RadioButton gender;
//通过radioGroup.getCheckedRadioButtonId()来得到选中的RadioButton的ID
    private EditText addresss;
    private EditText input_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_userl_info);

        //tv_birthdate = (TextView)findViewById(R.id.birthdate);
        //tv_birthdate.setOnClickListener(this);
        //tv_birthdate.setVisibility(View.GONE);
        //birthday = findViewById(R.id.birthday);
        //birthday.setVisibility(View.GONE);


        username1 = findViewById(R.id.username1);
        agee = findViewById(R.id.agee);
        rg = findViewById(R.id.rg);
        addresss = findViewById(R.id.addresss);
        input_phone = findViewById(R.id.input_phone);

//        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
//                R.layout.item_select, provinceArray);
//        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,
//                R.layout.item_select, cityArray);
//        ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(this,
//                R.layout.item_select, countyArray);
//        provinceAdapter.setDropDownViewResource(R.layout.item_dropdown);
//        cityAdapter.setDropDownViewResource(R.layout.item_dropdown);
//        countyAdapter.setDropDownViewResource(R.layout.item_dropdown);
//
//        Spinner sp = (Spinner) findViewById(R.id.province);
//        Spinner sp1 = (Spinner) findViewById(R.id.city);
//        Spinner sp2 = (Spinner) findViewById(R.id.county);
//        sp.setPrompt("省");
//        sp.setAdapter(provinceAdapter);
//        sp.setSelection(0);
//        sp.setOnItemSelectedListener(new MySelectedListener());
//
//        sp1.setPrompt("市");
//        sp1.setAdapter(cityAdapter);
//        sp1.setSelection(0);
//        sp1.setOnItemSelectedListener(new MySelectedListener());
//
//        sp2.setPrompt("县");
//        sp2.setAdapter(countyAdapter);
//        sp2.setSelection(0);
//        sp2.setOnItemSelectedListener(new MySelectedListener());

        modify = findViewById(R.id.modify);
        modify.setOnClickListener(this);
    }

//    private String[] provinceArray = {"北京市", "河北省", "安徽省", "河南省", "湖北省", "湖南省"};
//    private String[] cityArray = {"北京市", "郑州市", "合肥市", "武汉市", "长沙市", "芜湖市"};
//    private String[] countyArray = {"北京市", "郑州市", "合肥市", "武汉市", "长沙市", "芜湖市"};

//    class MySelectedListener implements AdapterView.OnItemSelectedListener {
//        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//            Toast.makeText(ModifyUserlInfo.this, "您选择的是"+provinceArray[arg2], Toast.LENGTH_LONG).show();
//        }
//
//        public void onNothingSelected(AdapterView<?> arg0) {
//        }
//    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.birthdate) {
//            Calendar calendar = Calendar.getInstance();
//            DatePickerDialog dialog = new DatePickerDialog(this, this,
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH));
//            dialog.show();
//        }
        if (v.getId() == R.id.modify) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SharedUtil sp = SharedUtil.getIntance(ModifyUserlInfo.this, "healthinfo");
                    gender = findViewById(rg.getCheckedRadioButtonId());
                    HashMap<String, String> hm = new HashMap<>();
                    MyApplication application = (MyApplication) ModifyUserlInfo.this.getApplicationContext();
                    hm.put("uusername", application.getName());
                    hm.put("uage", agee.getText().toString());
                    hm.put("usex", gender.getText().toString());
                    hm.put("uaddress", addresss.getText().toString());
                    hm.put("uphone", input_phone.getText().toString());
                    ArrayList<String> send = new ArrayList<>();
                    send.add("uusername");
                    send.add("uage");
                    send.add("usex");
                    send.add("uaddress");
                    send.add("uphone");
                    ArrayList<String> recieve = new ArrayList<>();
                    recieve.add("msg");
                    recieve.add("uage");
                    recieve.add("usex");
                    recieve.add("uaddress");
                    recieve.add("uphone");
                    recieve.add("ubloodtype");
                    recieve.add("uhealthcondition");
                    OkHttp okHttp = new OkHttp(send, recieve);

                    String msg = "true";

                    HashMap<String, String> rhm = okHttp.sendRequestWithOkHttp(hm, "http://120.48.5.10:9090/update");
                    Log.d("tag", rhm.get("msg") + rhm.get("usex"));
                    if (msg.equals(rhm.get("msg"))){

                        recieve.remove("msg");
                        recieve.remove("ubloodtype");
                        recieve.remove("uhealthcondition");
                        sp.writeShared(recieve, rhm);
                        Log.d("tag", sp.readShared("usex", "null") + sp.readShared("uaddress","null"));
                    }
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//在跳转到目标activity前销毁所以已经存在的activity（实现不能返回的功能）

                    intent.setClass(ModifyUserlInfo.this,SkimUserInfo.class);
                    startActivity(intent);
                }
            }).start();


        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String desc = String.format("%d年%d月%d日",
                year, monthOfYear+1, dayOfMonth);
        //tv_birthdate.setText(desc);
    }
}