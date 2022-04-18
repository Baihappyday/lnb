package com.example.login.worker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.login.IdentiChooseActivity;
import com.example.login.MyApplication;
import com.example.login.R;
import com.example.login.user.HealthInfoActivity;
import com.example.login.user.UserLoginActivity;
import com.example.login.util.SharedUtil;

public class WorkpersonalActivity /*extends Fragment  implements View.OnClickListener*/extends AppCompatActivity implements View.OnClickListener  {
//    private View v;
//    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        v = inflater.inflate(R.layout.fragment_worker_personal, container, false);
//        mContext = getActivity();
//        MyApplication application = (MyApplication) mContext.getApplicationContext();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_worker_personal);

        MyApplication application = (MyApplication) WorkpersonalActivity.this.getApplicationContext();

        TextView score=findViewById(R.id.pingfen);
        SharedUtil sp = SharedUtil.getIntance(WorkpersonalActivity.this, "logininfo");
        String score_num = sp.readShared("score", null);
        if(score_num==null)
            score.setText("综合评分");
        else
            score.setText("综合评分"+String.valueOf(score_num));

        Button btn1 = findViewById(R.id.bt1);
        btn1.setOnClickListener(this);
//        Button btn2 = findViewById(R.id.bt2);
//        btn2.setOnClickListener(this);
        Button btn3 = findViewById(R.id.bt3);
        btn3.setOnClickListener(this);
        Button btn4 = findViewById(R.id.bt4);
        btn4.setOnClickListener(this);
        Button btn5 = findViewById(R.id.bt5);
        btn5.setOnClickListener(this);
        Button btn6 = findViewById(R.id.bt6);
        btn6.setOnClickListener(this);
        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(this);
        Button home = findViewById(R.id.button1);//首页
        home.setOnClickListener(this);
        Button person = findViewById(R.id.button2);//个人中心
        person.setOnClickListener(this);

        if (!application.getLoginState()){
            button13.setVisibility(View.GONE);
        }
        if (application.getLoginState()) {//若之前已登录
            //SharedUtil sp = SharedUtil.getIntance(this, "logininfo");
            String s = sp.readShared("wusername", "null");
            Log.d("tag", s);
            if (s.equals("null")) {
                btn1.setText("点击登录");
            } else {
                btn1.setText("用户" + s);
            }
        } else {
            btn1.setOnClickListener(this);
            //button6.setOnClickListener(this);
        }
       // return v;
    }

    //@Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt1){//登录
            Intent intent=new Intent(WorkpersonalActivity.this, WorkerLogin.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt2){//全部
            Intent intent=new Intent(WorkpersonalActivity.this, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt3){//全部
            Intent intent=new Intent(WorkpersonalActivity.this, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt4){//全部
            Intent intent=new Intent(WorkpersonalActivity.this, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt5){//全部
            Intent intent=new Intent(WorkpersonalActivity.this, WorkerOrder.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.bt6){//收入钱包
            Intent intent=new Intent(WorkpersonalActivity.this, workwalletActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button13){//退出登录
            Intent intent=new Intent(WorkpersonalActivity.this, IdentiChooseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            SharedUtil.clearShared(WorkpersonalActivity.this);
            MyApplication application = (MyApplication) WorkpersonalActivity.this.getApplicationContext();
            application.setLoginState(false);
            startActivity(intent);
        }
        if(view.getId() == R.id.button1){//首页
            Intent intent=new Intent(WorkpersonalActivity.this, WorkmaininterfaceAcitvity.class);
            startActivity(intent);

        }
        if(view.getId() == R.id.button2){//个人中心

        }
    }
}
