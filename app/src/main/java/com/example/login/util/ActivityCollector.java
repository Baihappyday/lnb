package com.example.login.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static ActivityCollector instance;//静态对象
    public static List<Activity> activities = new ArrayList<Activity>();//静态List，用来储存Activity
    public ActivityCollector(){

    }//构造方法

    public static ActivityCollector getInstance(){
        if(instance == null) {
            instance = new ActivityCollector();
        }
        return instance;
    }

    public static void addActivity(Activity activity ){
        activities.add(activity);
    }
    public static void exit(){//销毁当前活动
        for (Activity activity : activities){
            if(! activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
