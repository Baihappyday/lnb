package com.example.login.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.login.MyApplication;

public class NetWorkUtil {

    public static boolean isNetworkConnected(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {

                //这种方法也可以
                //return mNetworkInfo .getState()== NetworkInfo.State.CONNECTED

                boolean flag = mNetworkInfo.isAvailable();
                application.setNetWorkAvailable(flag);
                return flag;

            }
        }
        application.setNetWorkAvailable(false);
        return false;
    }

    public static boolean isNetworkAvailable(Context context)
    {
//        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        MyApplication application = (MyApplication) context.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            application.setNetWorkAvailable(false);
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
//                    System.out.println(i + "===状态===" + networkInfo[i].getState());
//                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        application.setNetWorkAvailable(true);
                        return true;
                    }
                }
            }
        }
        application.setNetWorkAvailable(false);
        return false;
    }

    public static boolean isNetWorkOK(Context context){
        if (isNetworkConnected(context)&&isNetworkAvailable(context)){
            return true;
        }
        return false;
    }
}
