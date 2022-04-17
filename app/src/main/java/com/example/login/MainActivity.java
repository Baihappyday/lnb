package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.user.UserMainInterfaceActivity;
import com.example.login.util.SharedUtil;
/*app启动界面*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button skip;
    Handler handler;
    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//app的logo或者推广广告展示界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        application = (MyApplication) this.getApplicationContext();
        skip = (Button)findViewById(R.id.skip);
        skip.setOnClickListener(this);
        handler = new Handler();
        handler.postDelayed(new Runnable() {//5秒后自动跳转
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//在跳转到目标activity前销毁所以已经存在的activity（实现不能返回的功能）
                SharedUtil sp = SharedUtil.getIntance(MainActivity.this,"logininfo");
                boolean loginstate = sp.readShared("loginstate", false);
                String identification = sp.readShared("identification","0");//身份选择页面从上到下依次为0-3
                MyApplication.setName(sp.readShared("username","null"));
                if (loginstate){
                    if (identification.equals("0")){
                        intent.setClass(MainActivity.this, UserMainInterfaceActivity.class);
                        startActivity(intent);
                    }
                    else if (identification.equals("1")){

                       // intent.setClass(MainActivity.this, WorkmaininterfaceAcitvity.class);
                       // startActivity(intent);
                    }
                    else if (identification.equals("2")){

                    }
                    else if (identification.equals("3")){

                    }
                }
                else {
                    intent.setClass(MainActivity.this, IdentiChooseActivity.class);
                    startActivity(intent);//要延时的程序
                }


            }
        },5000);
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.skip) == v){

            Intent intent = new Intent();
            handler.removeCallbacksAndMessages(null);//清除handler中子线程的延时操作
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//在跳转到目标activity前销毁所以已经存在的activity（实现不能返回的功能）
            SharedUtil sp = SharedUtil.getIntance(MainActivity.this,"logininfo");
            Log.d("tag", String.valueOf(sp.readShared("loginstate", false)));
            boolean loginstate = sp.readShared("loginstate", false);
            String identification = sp.readShared("identification","0");//身份选择页面从上到下依次为0-3
            application.setName(sp.readShared("username","null"));
            if (loginstate){
                application.setLoginState(loginstate);
                if (identification.equals("0")){
                    intent.setClass(MainActivity.this, UserMainInterfaceActivity.class);
                    startActivity(intent);
                }
                else if (identification.equals("1")){

                  //  intent.setClass(MainActivity.this, WorkmaininterfaceAcitvity.class);
                  //  startActivity(intent);
                }
                else if (identification.equals("2")){

                }
                else if (identification.equals("3")){

                }

            }
            else {
                intent.setClass(MainActivity.this, IdentiChooseActivity.class);
                startActivity(intent);
            }
        }

    }
}
