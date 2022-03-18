package com.example.login.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.login.CameraActivity;
import com.example.login.R;

public class CommunitymainActivity extends AppCompatActivity {

    private ImageView yingyezhizhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_audits);

        yingyezhizhao = findViewById(R.id.yingyezhizhao);
        CameraActivity.setClipRatio(1,1);
    }
    //从相机获取图片
    public void TestBtn01(View view) {
        startActivity(new Intent(CommunitymainActivity.this, CameraActivity.class).putExtra(CameraActivity.ExtraType, CameraActivity.CAMERA));
    }

    //从相册获取图片
    public void TestBtn02(View view) {
        startActivity(new Intent(CommunitymainActivity.this, CameraActivity.class).putExtra(CameraActivity.ExtraType, CameraActivity.PHOTO));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获得相册、相机返回的结果，并显示
        if (CameraActivity.LISTENING) {
            Log.e("TAG", "返回的Uri结果：" + CameraActivity.IMG_URI);
            Log.e("TAG", "返回的File结果：" + CameraActivity.IMG_File.getPath());
            CameraActivity.LISTENING = false;   //关闭获取结果
            yingyezhizhao.setImageURI(CameraActivity.IMG_URI);  //显示图片到控件
        }
    }
}
