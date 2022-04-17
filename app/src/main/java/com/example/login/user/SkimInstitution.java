package com.example.login.user;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//用户的机构列表界面
public class SkimInstitution extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    List<InstitutionFragment> list = new ArrayList<>();

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

        getData();
        /*Spinner sp = (Spinner) findViewById(R.id.multi_arrange);
        Spinner sp1 = (Spinner) findViewById(R.id.insti_type);
        Spinner sp2 = (Spinner) findViewById(R.id.region_choose);
        Spinner sp3 = (Spinner) findViewById(R.id.filter);*/
        /*sp.setPrompt("综合排序");
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
        sp3.setOnItemSelectedListener(new SkimInstitution.MySelectedListener());*/
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
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            //Toast.makeText(SkimInstitution.this, "您选择的是"+institypeArray[arg2], Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    void getData(){
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    String json = get("http://120.48.5.10:9090/institutions/all");
                    Log.d("json", json);
                    JSONArray o = new JSONArray(json);

                    for (int i = 0; i < o.length(); i++) {
                        JSONObject jo = o.getJSONObject(i);
                        InstitutionFragment i_f = new InstitutionFragment();
                        list.add(i_f);

                        HashMap<String,Object> hashMap =new HashMap<>();
                        hashMap.put("iname",jo.getString("iname"));
                        hashMap.put("iaddress",jo.getString("iaddress"));
                        hashMap.put("iprice",jo.getInt("iprice"));
                        hashMap.put("idescription",jo.getString("idescription"));
                       // Log.d("json", );
                        i_f.setInfo(hashMap);
                        getSupportFragmentManager().beginTransaction().add(R.id.list_insti, i_f).commit();

                    }
                }catch (Exception e){

                }

            }
        }).start();
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}