package com.example.login;

import android.app.Application;

/**
 * 自定义的MyApplication继承Application
 *
 * @author way
 *
 */
public class MyApplication extends Application {
    /**
     * 引发异常：在一些不规范的代码中经常看到Activity或者是Service当中定义许多静态成员属性。这样做可能会造成许多莫名其妙的 null
     * pointer异常。
     */

    /**
     * 异常分析：Java虚拟机的垃圾回收机制会主动回收没有被引用的对象或属性。在内存不足时，虚拟机会主动回收处于后台的Activity或
     * Service所占用的内存。当应用再次去调用静态属性或对象的时候，就会造成null pointer异常
     */

    /**
     * 解决异常：Application在整个应用中，只要进程存在，Application的静态成员变量就不会被回收，不会造成null pointer异常
     */
    static private boolean loginState = false;//登录状态
    static private int identification = 0; //0未选择，1用户，2工作人员，3社区，4机构
    static private String name;

    public boolean orderSynFlag = false;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public boolean getLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        MyApplication.name = name;
    }
}

/*
* 使用例子：
MyApplication application = (MyApplication) this.getApplicationContext();
//保存变量
application.setNumber(5);
//取出变量
application.getNumber();
* */