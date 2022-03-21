package com.example.login.worker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.login.R;
public class WorkerOrder /*extends AppCompatActivity*/ extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.worker_order);
        // 获取该Activity里面的TabHost组件
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.worker_order,
                tabHost.getTabContentView(), true);

        /* 以上创建和添加标签页也可以用如下代码实现 */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("进行中").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("待收款").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("已完成").setContent(R.id.tab03));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("待评价").setContent(R.id.tab04));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator("全部").setContent(R.id.tab05));

        //标签切换事件处理，setOnTabChangedListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            // tabId是newTabSpec参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签
                   //Toast.makeText(WorkerOrder.this, "点击标签页一", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    //Toast.makeText(WorkerOrder.this, "点击标签页二", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab3")) {   //第三个标签
                    //Toast.makeText(WorkerOrder.this, "点击标签页三", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab4")) {   //第四个标签
                   // Toast.makeText(WorkerOrder.this, "点击标签页四", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab5")) {   //第五个标签
                    // Toast.makeText(WorkerOrder.this, "点击标签页五", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}